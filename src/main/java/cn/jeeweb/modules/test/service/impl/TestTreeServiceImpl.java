package cn.jeeweb.modules.test.service.impl;

import cn.jeeweb.core.common.service.impl.TreeCommonServiceImpl;
import cn.jeeweb.modules.test.mapper.TestTreeMapper;
import cn.jeeweb.modules.test.entity.TestTree;
import cn.jeeweb.modules.test.service.ITestTreeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 测试树
 * @Description: 测试树
 * @author jeeweb
 * @date 2017-09-10 14:48:58
 * @version V1.0   
 *
 */
@Transactional
@Service("testTreeService")
public class TestTreeServiceImpl  extends TreeCommonServiceImpl<TestTreeMapper,TestTree,String> implements  ITestTreeService {

}
