package cn.jeeweb.modules.test.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import cn.jeeweb.modules.sys.entity.User;
import java.util.Date;

/**   
 * @Title: 测试主表
 * @Description: 测试主表
 * @author jeeweb
 * @date 2017-09-10 14:48:06
 * @version V1.0   
 *
 */
@TableName("TEST_ORDER_MAIN")
@SuppressWarnings("serial")
public class TestOrderMain extends AbstractEntity<String> {

    /**主键*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**订单号*/
    @TableField(value = "ORDERNO")
	private String orderno;
    /**订单金额*/
    @TableField(value = "MONEY")
	private String money;
    /**订单日期*/
    @TableField(value = "ORDERDATE")
	private String orderdate;
    /**创建者*/
    @TableField(value = "CREATE_BY",el="createBy.id",fill = FieldFill.INSERT)
	private User createBy;
    /**创建时间*/
    @TableField(value = "CREATE_DATE",fill = FieldFill.INSERT)
	private Date createDate;
    /**更新者*/
    @TableField(value = "UPDATE_BY",el="updateBy.id",fill = FieldFill.UPDATE)
	private User updateBy;
    /**更新时间*/
    @TableField(value = "UPDATE_DATE",fill = FieldFill.UPDATE)
	private Date updateDate;
    /**删除标记（0：正常；1：删除）*/
    @TableField(value = "DEL_FLAG")
	private String delFlag;
    /**备注信息*/
    @TableField(value = "REMARKS")
	private String remarks;
	
	/**
	 * 获取  id
	 *@return: String  主键
	 */
	public String getId(){
		return this.id;
	}

	/**
	 * 设置  id
	 *@param: id  主键
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 * 获取  orderno
	 *@return: String  订单号
	 */
	public String getOrderno(){
		return this.orderno;
	}

	/**
	 * 设置  orderno
	 *@param: orderno  订单号
	 */
	public void setOrderno(String orderno){
		this.orderno = orderno;
	}
	/**
	 * 获取  money
	 *@return: String  订单金额
	 */
	public String getMoney(){
		return this.money;
	}

	/**
	 * 设置  money
	 *@param: money  订单金额
	 */
	public void setMoney(String money){
		this.money = money;
	}
	/**
	 * 获取  orderdate
	 *@return: String  订单日期
	 */
	public String getOrderdate(){
		return this.orderdate;
	}

	/**
	 * 设置  orderdate
	 *@param: orderdate  订单日期
	 */
	public void setOrderdate(String orderdate){
		this.orderdate = orderdate;
	}
	/**
	 * 获取  createBy
	 *@return: User  创建者
	 */
	public User getCreateBy(){
		return this.createBy;
	}

	/**
	 * 设置  createBy
	 *@param: createBy  创建者
	 */
	public void setCreateBy(User createBy){
		this.createBy = createBy;
	}
	/**
	 * 获取  createDate
	 *@return: Date  创建时间
	 */
	public Date getCreateDate(){
		return this.createDate;
	}

	/**
	 * 设置  createDate
	 *@param: createDate  创建时间
	 */
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	/**
	 * 获取  updateBy
	 *@return: User  更新者
	 */
	public User getUpdateBy(){
		return this.updateBy;
	}

	/**
	 * 设置  updateBy
	 *@param: updateBy  更新者
	 */
	public void setUpdateBy(User updateBy){
		this.updateBy = updateBy;
	}
	/**
	 * 获取  updateDate
	 *@return: Date  更新时间
	 */
	public Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 * 设置  updateDate
	 *@param: updateDate  更新时间
	 */
	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 * 获取  delFlag
	 *@return: String  删除标记（0：正常；1：删除）
	 */
	public String getDelFlag(){
		return this.delFlag;
	}

	/**
	 * 设置  delFlag
	 *@param: delFlag  删除标记（0：正常；1：删除）
	 */
	public void setDelFlag(String delFlag){
		this.delFlag = delFlag;
	}
	/**
	 * 获取  remarks
	 *@return: String  备注信息
	 */
	public String getRemarks(){
		return this.remarks;
	}

	/**
	 * 设置  remarks
	 *@param: remarks  备注信息
	 */
	public void setRemarks(String remarks){
		this.remarks = remarks;
	}
	
}