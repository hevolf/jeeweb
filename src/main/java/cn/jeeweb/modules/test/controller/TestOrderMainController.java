package cn.jeeweb.modules.test.controller;

import cn.jeeweb.core.common.data.DuplicateValid;
import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.model.ValidJson;
import cn.jeeweb.core.query.annotation.PageableDefaults;
import cn.jeeweb.core.query.data.PropertyPreFilterable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.utils.QueryableConvertUtils;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresMethodPermissions;
import cn.jeeweb.core.utils.JeewebPropertiesUtil;
import cn.jeeweb.core.utils.ObjectUtils;
import cn.jeeweb.core.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import cn.jeeweb.core.common.controller.BaseBeanController;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import cn.jeeweb.modules.test.entity.TestOrderMain;
import cn.jeeweb.modules.test.service.ITestOrderMainService;
import cn.jeeweb.modules.test.entity.TestOrderTicket;
import cn.jeeweb.modules.test.service.ITestOrderTicketService;
import cn.jeeweb.modules.test.entity.TestOrderCustomer;
import cn.jeeweb.modules.test.service.ITestOrderCustomerService;
/**   
 * @Title: 测试主表
 * @Description: 测试主表
 * @author jeeweb
 * @date 2017-09-10 14:48:06
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/test/testordermain")
@RequiresPathPermission("test:testordermain")
public class TestOrderMainController extends BaseBeanController<TestOrderMain> {

    @Autowired
    protected ITestOrderMainService testOrderMainService;
	@Autowired
	private ITestOrderTicketService testOrderTicketService;
	@Autowired
	private ITestOrderCustomerService testOrderCustomerService;
    public TestOrderMain get(String id) {
        if (!ObjectUtils.isNullOrEmpty(id)) {
            return testOrderMainService.selectById(id);
        } else {
            return newModel();
        }
    }

    @RequiresMethodPermissions("list")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request, HttpServletResponse response) {
        return display("list");
    }

    @RequestMapping(value = "ajaxList", method = { RequestMethod.GET, RequestMethod.POST })
    @PageableDefaults(sort = "id=desc")
    private void ajaxList(Queryable queryable, PropertyPreFilterable propertyPreFilterable, HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        EntityWrapper<TestOrderMain> entityWrapper = new EntityWrapper<TestOrderMain>(entityClass);
        propertyPreFilterable.addQueryProperty("id");
        // 预处理
        QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
        SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
        PageJson<TestOrderMain> pagejson = new PageJson<TestOrderMain>(testOrderMainService.list(queryable,entityWrapper));
        String content = JSON.toJSONString(pagejson, filter);
        StringUtils.printJson(response, content);
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request, HttpServletResponse response) {
        if (!model.containsAttribute("data")) {
            model.addAttribute("data", newModel());
        }
        return display("edit");
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson create(Model model, @Valid @ModelAttribute("data") TestOrderMain testOrderMain, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(testOrderMain, request, response, result);
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") String id, Model model, HttpServletRequest request,
                              HttpServletResponse response) {
        TestOrderMain testOrderMain = get(id);
        model.addAttribute("data", testOrderMain);
		// 获得订单票据数据
		List<TestOrderTicket> testOrderTicketList = testOrderTicketService.selectList(new EntityWrapper<TestOrderTicket>(TestOrderTicket.class).eq("order.id",testOrderMain.getId()));
		model.addAttribute("testOrderTicketList", testOrderTicketList);
		// 获得订单客户信息数据
		List<TestOrderCustomer> testOrderCustomerList = testOrderCustomerService.selectList(new EntityWrapper<TestOrderCustomer>(TestOrderCustomer.class).eq("order.id",testOrderMain.getId()));
		model.addAttribute("testOrderCustomerList", testOrderCustomerList);
        return display("edit");
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson update(Model model, @Valid @ModelAttribute("data") TestOrderMain testOrderMain, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response) {
        return doSave(testOrderMain, request, response, result);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson doSave(TestOrderMain testOrderMain, HttpServletRequest request, HttpServletResponse response,
                           BindingResult result) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("保存成功");
        if (hasError(testOrderMain, result)) {
            // 错误提示
            String errorMsg = errorMsg(result);
            if (!StringUtils.isEmpty(errorMsg)) {
                ajaxJson.fail(errorMsg);
            } else {
                ajaxJson.fail("保存失败");
            }
            return ajaxJson;
        }
        try {
            if (StringUtils.isEmpty(testOrderMain.getId())) {
                testOrderMainService.insert(testOrderMain);
            } else {
                testOrderMainService.insertOrUpdate(testOrderMain);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("保存失败!<br />原因:" + e.getMessage());
        }
        return ajaxJson;
    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson delete(@PathVariable("id") String id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("删除成功");
        try {
            testOrderMainService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("删除失败");
        }
        return ajaxJson;
    }

    @RequestMapping(value = "batch/delete", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public AjaxJson batchDelete(@RequestParam(value = "ids", required = false) String[] ids) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("删除成功");
        try {
            List<String> idList = java.util.Arrays.asList(ids);
            testOrderMainService.deleteBatchIds(idList);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("删除失败");
        }
        return ajaxJson;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable("id") String id, HttpServletRequest request,
                       HttpServletResponse response) {
        TestOrderMain testOrderMain = get(id);
        model.addAttribute("data", testOrderMain);
        return display("edit");
    }

    @RequestMapping(value = "validate", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ValidJson validate(DuplicateValid duplicateValid, HttpServletRequest request) {
        ValidJson validJson = new ValidJson();
        Boolean valid = Boolean.FALSE;
        try {
            EntityWrapper<TestOrderMain> entityWrapper = new EntityWrapper<TestOrderMain>(entityClass);
            valid = testOrderMainService.doValid(duplicateValid,entityWrapper);
            if (valid) {
                validJson.setStatus("y");
                validJson.setInfo("验证通过!");
            } else {
                validJson.setStatus("n");
                if (!StringUtils.isEmpty(duplicateValid.getErrorMsg())) {
                    validJson.setInfo(duplicateValid.getErrorMsg());
                } else {
                    validJson.setInfo("当前信息重复!");
                }
            }
        } catch (Exception e) {
            validJson.setStatus("n");
            validJson.setInfo("验证异常，请检查字段是否正确!");
        }
        return validJson;
    }
}

