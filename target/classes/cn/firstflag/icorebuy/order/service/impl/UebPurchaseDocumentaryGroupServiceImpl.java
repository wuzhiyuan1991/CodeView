package cn.firstflag.icorebuy.order.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;

import cn.firstflag.icorebuy.common.BaseService;
import cn.firstflag.icorebuy.common.constant.ResultCodeConstants;
import cn.firstflag.icorebuy.common.constant.ResultMessageConstants;
import cn.firstflag.icorebuy.common.redis.util.PrimarykeyUtil;

import cn.firstflag.icorebuy.common.dto.DubboResult;
import cn.firstflag.icorebuy.common.dto.Query;
import cn.firstflag.icorebuy.common.dto.FlipResult;
import cn.firstflag.icorebuy.common.model.UebPurchaseDocumentaryGroup;
import cn.firstflag.icorebuy.order.dao.UebPurchaseDocumentaryGroupDao;
import cn.firstflag.icorebuy.order.service.UebPurchaseDocumentaryGroupService;

/**<p>Title: </p>
 * <p>Description: </p>
 * @author zhiyuan.wu
 * @date 2018-02-06 10:27:53
 */
@Service
public class UebPurchaseDocumentaryGroupServiceImpl extends BaseService implements UebPurchaseDocumentaryGroupService {

	@Resource
	private UebPurchaseDocumentaryGroupDao uebPurchaseDocumentaryGroupDao;
	

	/**
	 * 根据查询条件，查询并返回UebPurchaseDocumentaryGroup的列表
	 * @param query 查询条件
	 * @return
	 */
	public DubboResult getUebPurchaseDocumentaryGroupList(Query query) {
		List<UebPurchaseDocumentaryGroup> list = null;
        DubboResult dubboResult = new DubboResult();
		try {
			//直接调用Dao方法进行查询
			list = uebPurchaseDocumentaryGroupDao.queryAll(query);
			dubboResult.setCode(ResultCodeConstants.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage()+", query = " + query, e);
			dubboResult.setCode(ResultCodeConstants.FAIL);
			dubboResult.setMessage(ResultMessageConstants.B00001.message());
			
		}
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<UebPurchaseDocumentaryGroup>(0) : list;
        dubboResult.setValue(list);
		return dubboResult;
	}
	
	/**
	 * 根据查询条件，分页查询并返回UebPurchaseDocumentaryGroup的分页结果
	 * @param query 查询条件
	 * @return
	 */
	public DubboResult getUebPurchaseDocumentaryGroupPagedList(Query query) {
		DubboResult dubboResult = new DubboResult();
		
		List<UebPurchaseDocumentaryGroup> list = null;
		long rowCount = 0L;
		
		try {
			//调用dao查询满足条件的分页数据
			list = uebPurchaseDocumentaryGroupDao.pageList(query);
			//调用dao统计满足条件的记录总数
			rowCount = uebPurchaseDocumentaryGroupDao.count(query);
			dubboResult.setCode(ResultCodeConstants.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage()+", query = " + query, e);
            dubboResult.setCode(ResultCodeConstants.FAIL);
            dubboResult.setMessage(ResultMessageConstants.B00001.message());
            
		}
		
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<UebPurchaseDocumentaryGroup>(0) : list;
	
		//将分页数据和记录总数设置到分页结果对象中
		FlipResult page = new FlipResult(query, list, rowCount);
        dubboResult.setValue(page);
		
		return dubboResult;
	}	
	
	/**
	 * 根据主键，查询并返回一个UebPurchaseDocumentaryGroup对象
	 * @param id 主键id
	 * @return
	 */
	public DubboResult getUebPurchaseDocumentaryGroup(Long id) {
		DubboResult dubboResult = new DubboResult();
        UebPurchaseDocumentaryGroup obj = null;
        try {
			if (id == null || id <= 0) { //传入的主键无效时直接返回null
		            logger.info("id is null. id = " + id);
					dubboResult.setCode(ResultCodeConstants.WARN);
					dubboResult.setMessage(ResultMessageConstants.T00001.message());
					return dubboResult;
			}
			//调用dao，根据主键查询
			obj = uebPurchaseDocumentaryGroupDao.get(id);
			dubboResult.setCode(ResultCodeConstants.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage() + ", id = " + id, e);
			dubboResult.setCode(ResultCodeConstants.FAIL);
			dubboResult.setMessage(ResultMessageConstants.B00001.message());
			
		}
		dubboResult.setValue(obj);
		return dubboResult;
	}

	/**
	 * 根据主键数组，查询并返回一组UebPurchaseDocumentaryGroup对象
	 * @param ids 主键数组，数组中的主键值应当无重复值，如有重复值时，则返回的列表长度可能小于传入的数组长度
	 * @return
	 */
	public DubboResult getUebPurchaseDocumentaryGroupByIds(Long[] ids) {
        DubboResult dubboResult = new DubboResult();
		List<UebPurchaseDocumentaryGroup> list = null;
		try {
			if (ids == null || ids.length == 0) {
				logger.info("ids is null. ids = " + ids);
				dubboResult.setCode(ResultCodeConstants.WARN);
				dubboResult.setMessage(ResultMessageConstants.T00001.message());
				return dubboResult;
			} 
			
			//调用dao，根据主键数组查询
			list = uebPurchaseDocumentaryGroupDao.getByIds(ids);
			dubboResult.setCode(ResultCodeConstants.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage() + ", ids = " + ids, e);
			dubboResult.setCode(ResultCodeConstants.FAIL);
			dubboResult.setMessage(ResultMessageConstants.B00001.message());
			
		}
		
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<UebPurchaseDocumentaryGroup>(0) : list;
        dubboResult.setValue(list);
		return dubboResult;
	}	
	
	/**
	 * 根据主键数组，删除数组特定的UebPurchaseDocumentaryGroup记录
	 * @param ids 主键数组，数组中的主键值应当无重复值，如有重复值时，则返回的列表长度可能小于传入的数组长度
	 * @return
	 */
	public DubboResult delUebPurchaseDocumentaryGroupByIds(Long[] ids) {
        DubboResult dubboResult = new DubboResult();
		List<UebPurchaseDocumentaryGroup> list = null;
		try {
			if (ids == null || ids.length == 0) {
				logger.info("ids is null. ids = " + ids);
				dubboResult.setCode(ResultCodeConstants.WARN);
				dubboResult.setMessage(ResultMessageConstants.T00001.message());
				return dubboResult;
			} 
			
			//调用Dao执行删除，并判断删除语句执行结果
			int count = uebPurchaseDocumentaryGroupDao.deleteByIds(ids);
            if (count <= 0) {
                dubboResult.setCode(ResultCodeConstants.FAIL);
            } else {
            	dubboResult.setCode(ResultCodeConstants.SUCCESS);
            }
		} catch (Exception e) {
			logger.error(e.getMessage() + ", ids = " + ids, e);
			dubboResult.setCode(ResultCodeConstants.FAIL);
			dubboResult.setMessage(ResultMessageConstants.B00001.message());
		}
		
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<UebPurchaseDocumentaryGroup>(0) : list;
        dubboResult.setValue(list);
		return dubboResult;
	}	
	
	/**
	 * 根据主键，删除特定的UebPurchaseDocumentaryGroup记录
	 * @param id 主键id
	 * @param operator 操作人对象
	 * @return
	 */
	public DubboResult delUebPurchaseDocumentaryGroup(Long id) {
		DubboResult dubboResult = new DubboResult();
		try {
			if (id == null || id <= 0) { //传入的主键无效时直接返回失败结果
					logger.info("id is null. id = " + id);
					dubboResult.setCode(ResultCodeConstants.WARN);
					dubboResult.setMessage(ResultMessageConstants.T00001.message());
					return dubboResult;
			}
		
		    //调用Dao执行删除，并判断删除语句执行结果
			int count = uebPurchaseDocumentaryGroupDao.delete(id);
            if (count <= 0) {
                dubboResult.setCode(ResultCodeConstants.FAIL);
            } else {
            	dubboResult.setCode(ResultCodeConstants.SUCCESS);
            }
		} catch (Exception e) {
			logger.error(e.getMessage()+", id = " + id, e);
			dubboResult.setCode(ResultCodeConstants.FAIL);
			dubboResult.setMessage(ResultMessageConstants.B00001.message());
			
		}
		return dubboResult;
	}
		
	/**
	 * 新增一条UebPurchaseDocumentaryGroup记录，执行成功后传入对象及返回对象的主键属性值不为null
	 * @param uebPurchaseDocumentaryGroup 新增的UebPurchaseDocumentaryGroup数据（如果无特殊需求，新增对象的主键值请保留为null）
	 * @param operator 操作人对象
	 * @return
	 */
	public DubboResult addUebPurchaseDocumentaryGroup(UebPurchaseDocumentaryGroup uebPurchaseDocumentaryGroup) {
		DubboResult dubboResult = new DubboResult();
		try {
			if (uebPurchaseDocumentaryGroup == null) { //传入参数无效时直接返回失败结果
					logger.info("uebPurchaseDocumentaryGroup is null, uebPurchaseDocumentaryGroup = " + uebPurchaseDocumentaryGroup);
					dubboResult.setCode(ResultCodeConstants.WARN);
					dubboResult.setMessage(ResultMessageConstants.T00001.message());
					return dubboResult;
			} 
			//如果传入参数的主键为null，则调用生成主键的方法获取新的主键
            //TODO: 设置主键
			if (uebPurchaseDocumentaryGroup.getId() == null) {
				uebPurchaseDocumentaryGroup.setId(PrimarykeyUtil.next(UebPurchaseDocumentaryGroup.class));
			}
			//如果传入的操作人不为null，则设置新增记录的操作人类型和操作人id
			/*if (operator != null) {
				uebPurchaseDocumentaryGroup.setOperatorType(operator.getOperatorType());
				uebPurchaseDocumentaryGroup.setOperatorId(operator.getOperatorId());
			}*/
			
			//设置创建时间和更新时间为当前时间
			Date now = new Date();
			uebPurchaseDocumentaryGroup.setCreateTime(now);
			uebPurchaseDocumentaryGroup.setUpdateTime(now);
			
			//填充默认值
			this.fillDefaultValues(uebPurchaseDocumentaryGroup);
			
			//调用Dao执行插入操作
			uebPurchaseDocumentaryGroupDao.add(uebPurchaseDocumentaryGroup);
			dubboResult.setCode(ResultCodeConstants.SUCCESS);
			dubboResult.setValue(uebPurchaseDocumentaryGroup);
		} catch (Exception e) {
			logger.error(e.getMessage() + ", uebPurchaseDocumentaryGroup = " + uebPurchaseDocumentaryGroup, e);
			dubboResult.setCode(ResultCodeConstants.FAIL);
			dubboResult.setMessage(ResultMessageConstants.B00001.message());
			
		}	
		
		return dubboResult;
	}			
	
	/**
	 * 根据主键，更新一条UebPurchaseDocumentaryGroup记录
	 * @param uebPurchaseDocumentaryGroup 更新的UebPurchaseDocumentaryGroup数据，且其主键不能为空
	 * @param operator 操作人对象
	 * @return
	 */
	public DubboResult updateUebPurchaseDocumentaryGroup(UebPurchaseDocumentaryGroup uebPurchaseDocumentaryGroup) {
        DubboResult dubboResult = new DubboResult();
		try {
		
		if (uebPurchaseDocumentaryGroup == null || uebPurchaseDocumentaryGroup.getId() == null) { //传入参数无效时直接返回失败结果
				logger.info("uebPurchaseDocumentaryGroup is null, uebPurchaseDocumentaryGroup = " + uebPurchaseDocumentaryGroup);
				dubboResult.setCode(ResultCodeConstants.WARN);
				dubboResult.setMessage(ResultMessageConstants.T00001.message());
				return dubboResult;
			} 
			
			//如果传入的操作人不为null，则设置更新记录的操作人类型和操作人id
			/*if (operator != null) {
				uebPurchaseDocumentaryGroup.setOperatorType(operator.getOperatorType());
				uebPurchaseDocumentaryGroup.setOperatorId(operator.getOperatorId());
			}*/
			
			//设置更新时间为当前时间
			uebPurchaseDocumentaryGroup.setUpdateTime(new Date());
			
			//调用Dao执行更新操作，并判断更新语句执行结果
			int count = uebPurchaseDocumentaryGroupDao.update(uebPurchaseDocumentaryGroup);			
			if (count <= 0) {
                dubboResult.setCode(ResultCodeConstants.FAIL);
			} else {
			    dubboResult.setCode(ResultCodeConstants.SUCCESS);
			}
            dubboResult.setValue(uebPurchaseDocumentaryGroup);
			} catch (Exception e) {
				logger.error(e.getMessage() + ", uebPurchaseDocumentaryGroup = " + uebPurchaseDocumentaryGroup, e);
				dubboResult.setCode(ResultCodeConstants.FAIL);
				dubboResult.setMessage(ResultMessageConstants.B00001.message());
				
			}
		
		return dubboResult;
	}
	
	/**
	 * 为UebPurchaseDocumentaryGroup对象设置默认值
	 * @param obj
	 */
	public void fillDefaultValues(UebPurchaseDocumentaryGroup obj) {
		if (obj != null) {
		    if (obj.getDeptId() == null) {
		    	obj.setDeptId(0);
		    }
		    if (obj.getGroupLeader() == null) {
		    	obj.setGroupLeader(0);
		    }
		    if (obj.getCreateUserId() == null) {
		    	obj.setCreateUserId(0);
		    }
		    if (obj.getCreateTime() == null) {
		    	obj.setCreateTime();
		    }
		    if (obj.getModifyUserId() == null) {
		    	obj.setModifyUserId(0);
		    }
		    if (obj.getModifyTime() == null) {
		    	obj.setModifyTime();
		    }
		    if (obj.getIsDel() == null) {
		    	obj.setIsDel();
		    }
		}
	}

}
