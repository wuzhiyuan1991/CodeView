package cn.firstflag.icorebuy.order.service;

import java.util.List;

import cn.firstflag.icorebuy.common.dto.DubboResult;
import cn.firstflag.icorebuy.common.dto.Query;
import cn.firstflag.icorebuy.common.BaseService;
import cn.firstflag.icorebuy.common.model.UebPurchaseDocumentaryGroup;

/**<p>Title:采购跟单组别管理 </p>
 * <p>Description: </p>
 * @author zhiyuan.wu
 * @date 2018-02-06 10:27:53
 */
public interface UebPurchaseDocumentaryGroupService {

	/**
	 * 根据查询条件，查询并返回UebPurchaseDocumentaryGroup的列表
	 * @param q 查询条件
	 * @return
	 */
	public DubboResult getUebPurchaseDocumentaryGroupList(Query q);
	
	/**
	 * 根据查询条件，分页查询并返回UebPurchaseDocumentaryGroup的分页结果
	 * @param q 查询条件
	 * @return
	 */
	public DubboResult getUebPurchaseDocumentaryGroupPagedList(Query q);
	
	/**
	 * 根据主键，查询并返回一个UebPurchaseDocumentaryGroup对象
	 * @param id 主键id
	 * @return
	 */
	public DubboResult getUebPurchaseDocumentaryGroup(Long id);

	/**
	 * 根据主键数组，查询并返回一组UebPurchaseDocumentaryGroup对象
	 * @param ids 主键数组，数组中的主键值应当无重复值，如有重复值时，则返回的列表长度可能小于传入的数组长度
	 * @return
	 */
	public DubboResult getUebPurchaseDocumentaryGroupByIds(Long[] ids);
	
	/**
	 * 根据主键数组，删除数组特定的{classInfo.firstUpperName}记录
	 * @param ids 主键数组，数组中的主键值应当无重复值，如有重复值时，则返回的列表长度可能小于传入的数组长度
	 * @return
	 */
	public DubboResult delUebPurchaseDocumentaryGroupByIds(Long[] ids);

	/**
	 * 根据主键，删除特定的UebPurchaseDocumentaryGroup记录
	 * @param id 主键id
	 * @param operator 操作人对象
	 * @return
	 */
	public DubboResult delUebPurchaseDocumentaryGroup(Long id);
	
	/**
	 * 新增一条UebPurchaseDocumentaryGroup记录，执行成功后传入对象及返回对象的主键属性值不为null
	 * @param uebPurchaseDocumentaryGroup 新增的UebPurchaseDocumentaryGroup数据（如果无特殊需求，新增对象的主键值请保留为null）
	 * @param operator 操作人对象
	 * @return
	 */
	public DubboResult addUebPurchaseDocumentaryGroup(UebPurchaseDocumentaryGroup uebPurchaseDocumentaryGroup);
	
	/**
	 * 根据主键，更新一条UebPurchaseDocumentaryGroup记录
	 * @param uebPurchaseDocumentaryGroup 更新的UebPurchaseDocumentaryGroup数据，且其主键不能为空
	 * @param operator 操作人对象
	 * @return
	 */
	public DubboResult updateUebPurchaseDocumentaryGroup(UebPurchaseDocumentaryGroup uebPurchaseDocumentaryGroup);
	
    /**
	 * 为UebPurchaseDocumentaryGroup对象设置默认值
	 * @param obj
	 */
	public void fillDefaultValues(UebPurchaseDocumentaryGroup obj);
		
}
