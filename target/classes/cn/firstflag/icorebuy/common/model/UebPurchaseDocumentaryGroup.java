package cn.firstflag.icorebuy.common.model;

import java.util.Date;


import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**<p>Title:采购跟单组别管理 </p>
 * <p>Description: </p>
 * @author zhiyuan.wu
 * @date 2018-02-06 10:27:53
 */
public class UebPurchaseDocumentaryGroup implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	/**
	 * 组名称
	 */
	private String groupName;
	/**
	 * 部门id
	 */
	private Integer deptId;
	/**
	 * 组长
	 */
	private Integer groupLeader;
	/**
	 * 组员
	 */
	private String groupMembers;
	/**
	 * 创建用户id
	 */
	private Integer createUserId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改用户id
	 */
	private Integer modifyUserId;
	/**
	 * 修改时间
	 */
	private Date modifyTime;
	/**
	 * 是否删除
	 */
	private Boolean isDel;
	
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE );
    }
	
	public Integer getId(){
		return id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	public String getGroupName(){
		return groupName;
	}
	
	public void setGroupName(String groupName){
		this.groupName = groupName;
	}
	public Integer getDeptId(){
		return deptId;
	}
	
	public void setDeptId(Integer deptId){
		this.deptId = deptId;
	}
	public Integer getGroupLeader(){
		return groupLeader;
	}
	
	public void setGroupLeader(Integer groupLeader){
		this.groupLeader = groupLeader;
	}
	public String getGroupMembers(){
		return groupMembers;
	}
	
	public void setGroupMembers(String groupMembers){
		this.groupMembers = groupMembers;
	}
	public Integer getCreateUserId(){
		return createUserId;
	}
	
	public void setCreateUserId(Integer createUserId){
		this.createUserId = createUserId;
	}
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Integer getModifyUserId(){
		return modifyUserId;
	}
	
	public void setModifyUserId(Integer modifyUserId){
		this.modifyUserId = modifyUserId;
	}
	public Date getModifyTime(){
		return modifyTime;
	}
	
	public void setModifyTime(Date modifyTime){
		this.modifyTime = modifyTime;
	}
	public Boolean getIsDel(){
		return isDel;
	}
	
	public void setIsDel(Boolean isDel){
		this.isDel = isDel;
	}
    
}
