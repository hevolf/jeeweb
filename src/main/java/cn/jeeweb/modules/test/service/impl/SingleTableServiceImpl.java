package cn.jeeweb.modules.test.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.test.mapper.SingleTableMapper;
import cn.jeeweb.modules.test.entity.SingleTable;
import cn.jeeweb.modules.test.service.ISingleTableService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 测试单表
 * @Description: 测试单表
 * @author jeeweb
 * @date 2017-09-10 15:02:18
 * @version V1.0   
 *
 */
@Transactional
@Service("singleTableService")
public class SingleTableServiceImpl  extends CommonServiceImpl<SingleTableMapper,SingleTable> implements  ISingleTableService {

}
