<#macro DEFVAL javaType,defaultValue>
<#if javaType=="Long" || javaType=="long">
${defaultValue}L<#return>
</#if>
<#if javaType=="Integer" || javaType=="int">
${defaultValue}<#return>
</#if>
<#if javaType=="String">
"${defaultValue}"<#return>
</#if>
<#if javaType=="Double" || javaType=="double">
${defaultValue}d<#return>
</#if>
<#if javaType=="Float" || javaType=="float">
${defaultValue}f<#return>
</#if>
</#macro>
<#if packageStr ?? && packageStr!=''>
package ${packageStr};
</#if>

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
import ${generatedMap[classInfo.firstUpperName]};
import ${generatedMap[classInfo.firstUpperName+"Dao"]};
import ${generatedMap[classInfo.firstUpperName+"Service"]};

<#macro MPKTYPE><#list classInfo.primaryKey as pk><#if pk_index = 0>${pk.javaType}</#if></#list></#macro>
/**<p>Title: </p>
 * <p>Description: </p>
 * @author ${author}
 * @date ${datetime}
 */
@Service
public class ${classInfo.firstUpperName}ServiceImpl extends BaseService implements ${classInfo.firstUpperName}Service {

	@Resource
	private ${classInfo.firstUpperName}Dao ${classInfo.className}Dao;
	

	/**
	 * 根据查询条件，查询并返回${classInfo.firstUpperName}的列表
	 * @param query 查询条件
	 * @return
	 */
	public DubboResult get${classInfo.firstUpperName}List(Query query) {
		List<${classInfo.firstUpperName}> list = null;
        DubboResult dubboResult = new DubboResult();
		try {
            <#if classInfo.hasDeletedFlag==true>
			//已删除的不查出
			${classInfo.firstUpperName} ${classInfo.className} = (${classInfo.firstUpperName})query.getQuery();
			if (${classInfo.className} != null) {
				${classInfo.className}.set${classInfo.deletedFlagElement.firstUpperName!'IsDeleted'}(0);
			}

			</#if>
			//直接调用Dao方法进行查询
			list = ${classInfo.className}Dao.queryAll(query);
			dubboResult.setCode(ResultCodeConstants.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage()+", query = " + query, e);
			dubboResult.setCode(ResultCodeConstants.FAIL);
			dubboResult.setMessage(ResultMessageConstants.B00001.message());
			
		}
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<${classInfo.firstUpperName}>(0) : list;
        dubboResult.setValue(list);
		return dubboResult;
	}
	
	/**
	 * 根据查询条件，分页查询并返回${classInfo.firstUpperName}的分页结果
	 * @param query 查询条件
	 * @return
	 */
	public DubboResult get${classInfo.firstUpperName}PagedList(Query query) {
		DubboResult dubboResult = new DubboResult();
		
		List<${classInfo.firstUpperName}> list = null;
		long rowCount = 0L;
		
		try {
		  <#if classInfo.hasDeletedFlag==true>
			//已删除的不查出
			${classInfo.firstUpperName} ${classInfo.className} = (${classInfo.firstUpperName})query.getQuery();
			if (${classInfo.className} != null) {
				${classInfo.className}.set${classInfo.deletedFlagElement.firstUpperName!'IsDeleted'}(0);
			}
					
		  </#if>
			//调用dao查询满足条件的分页数据
			list = ${classInfo.className}Dao.pageList(query);
			//调用dao统计满足条件的记录总数
			rowCount = ${classInfo.className}Dao.count(query);
			dubboResult.setCode(ResultCodeConstants.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage()+", query = " + query, e);
            dubboResult.setCode(ResultCodeConstants.FAIL);
            dubboResult.setMessage(ResultMessageConstants.B00001.message());
            
		}
		
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<${classInfo.firstUpperName}>(0) : list;
	
		//将分页数据和记录总数设置到分页结果对象中
		FlipResult page = new FlipResult(query, list, rowCount);
        dubboResult.setValue(page);
		
		return dubboResult;
	}	
	
	/**
	 * 根据主键，查询并返回一个${classInfo.firstUpperName}对象
	 * @param id 主键id
	 * @return
	 */
	public DubboResult get${classInfo.firstUpperName}(Long id) {
		DubboResult dubboResult = new DubboResult();
        ${classInfo.firstUpperName} obj = null;
        try {
			if (id == null || id <= 0) { //传入的主键无效时直接返回null
		            logger.info("id is null. id = " + id);
					dubboResult.setCode(ResultCodeConstants.WARN);
					dubboResult.setMessage(ResultMessageConstants.T00001.message());
					return dubboResult;
			}
			//调用dao，根据主键查询
			obj = ${classInfo.className}Dao.get(id);
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
	 * 根据主键数组，查询并返回一组${classInfo.firstUpperName}对象
	 * @param ids 主键数组，数组中的主键值应当无重复值，如有重复值时，则返回的列表长度可能小于传入的数组长度
	 * @return
	 */
	public DubboResult get${classInfo.firstUpperName}ByIds(Long[] ids) {
        DubboResult dubboResult = new DubboResult();
		List<${classInfo.firstUpperName}> list = null;
		try {
			if (ids == null || ids.length == 0) {
				logger.info("ids is null. ids = " + ids);
				dubboResult.setCode(ResultCodeConstants.WARN);
				dubboResult.setMessage(ResultMessageConstants.T00001.message());
				return dubboResult;
			} 
			
			//调用dao，根据主键数组查询
			list = ${classInfo.className}Dao.getByIds(ids);
			dubboResult.setCode(ResultCodeConstants.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage() + ", ids = " + ids, e);
			dubboResult.setCode(ResultCodeConstants.FAIL);
			dubboResult.setMessage(ResultMessageConstants.B00001.message());
			
		}
		
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<${classInfo.firstUpperName}>(0) : list;
        dubboResult.setValue(list);
		return dubboResult;
	}	
	
	/**
	 * 根据主键数组，删除数组特定的${classInfo.firstUpperName}记录
	 * @param ids 主键数组，数组中的主键值应当无重复值，如有重复值时，则返回的列表长度可能小于传入的数组长度
	 * @return
	 */
	public DubboResult del${classInfo.firstUpperName}ByIds(Long[] ids) {
        DubboResult dubboResult = new DubboResult();
		List<${classInfo.firstUpperName}> list = null;
		try {
			if (ids == null || ids.length == 0) {
				logger.info("ids is null. ids = " + ids);
				dubboResult.setCode(ResultCodeConstants.WARN);
				dubboResult.setMessage(ResultMessageConstants.T00001.message());
				return dubboResult;
			} 
			
			//调用Dao执行删除，并判断删除语句执行结果
			int count = ${classInfo.className}Dao.deleteByIds(ids);
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
		list = list == null ? new ArrayList<${classInfo.firstUpperName}>(0) : list;
        dubboResult.setValue(list);
		return dubboResult;
	}	
	
	/**
	 * 根据主键，删除特定的${classInfo.firstUpperName}记录
	 * @param id 主键id
	 * @param operator 操作人对象
	 * @return
	 */
	public DubboResult del${classInfo.firstUpperName}(Long id) {
		DubboResult dubboResult = new DubboResult();
		try {
			if (id == null || id <= 0) { //传入的主键无效时直接返回失败结果
					logger.info("id is null. id = " + id);
					dubboResult.setCode(ResultCodeConstants.WARN);
					dubboResult.setMessage(ResultMessageConstants.T00001.message());
					return dubboResult;
			}
		
            <#if classInfo.hasDeletedFlag==true>
			//逻辑删除，实际上执行update语句，以下设置待更新记录的主键、删除标识、更新时间、操作人信息等
			${classInfo.firstUpperName} ${classInfo.className} = new ${classInfo.firstUpperName}();
			${classInfo.className}.set${classInfo.primaryKeyElement.firstUpperName}(id);
			${classInfo.className}.set${classInfo.deletedFlagElement.firstUpperName!'IsDeleted'}(1);
			${classInfo.className}.setUpdateTime(new Date());
			/*if (operator != null) { //最近操作人
				${classInfo.className}.setOperatorType(operator.getOperatorType());
				${classInfo.className}.setOperatorId(operator.getOperatorId());
			}*/
			
			//调用Dao执行更新操作，并判断更新语句执行结果
			int count = ${classInfo.className}Dao.update(${classInfo.className});			
			if (count <= 0) {
				dubboResult.setCode(ResultCodeConstants.FAIL);
			} else {
            	dubboResult.setCode(ResultCodeConstants.SUCCESS);
            }
			dubboResult.setValue(${classInfo.className});
		  <#else>
		    //调用Dao执行删除，并判断删除语句执行结果
			int count = ${classInfo.className}Dao.delete(id);
            if (count <= 0) {
                dubboResult.setCode(ResultCodeConstants.FAIL);
            } else {
            	dubboResult.setCode(ResultCodeConstants.SUCCESS);
            }
		  </#if>	
		} catch (Exception e) {
			logger.error(e.getMessage()+", id = " + id, e);
			dubboResult.setCode(ResultCodeConstants.FAIL);
			dubboResult.setMessage(ResultMessageConstants.B00001.message());
			
		}
		return dubboResult;
	}
		
	/**
	 * 新增一条${classInfo.firstUpperName}记录，执行成功后传入对象及返回对象的主键属性值不为null
	 * @param ${classInfo.className} 新增的${classInfo.firstUpperName}数据（如果无特殊需求，新增对象的主键值请保留为null）
	 * @param operator 操作人对象
	 * @return
	 */
	public DubboResult add${classInfo.firstUpperName}(${classInfo.firstUpperName} ${classInfo.className}) {
		DubboResult dubboResult = new DubboResult();
		try {
			if (${classInfo.className} == null) { //传入参数无效时直接返回失败结果
					logger.info("${classInfo.className} is null, ${classInfo.className} = " + ${classInfo.className});
					dubboResult.setCode(ResultCodeConstants.WARN);
					dubboResult.setMessage(ResultMessageConstants.T00001.message());
					return dubboResult;
			} 
			//如果传入参数的主键为null，则调用生成主键的方法获取新的主键
            //TODO: 设置主键
			if (${classInfo.className}.get${classInfo.primaryKeyElement.firstUpperName}() == null) {
				${classInfo.className}.set${classInfo.primaryKeyElement.firstUpperName}(PrimarykeyUtil.next(${classInfo.firstUpperName}.class));
			}
			//如果传入的操作人不为null，则设置新增记录的操作人类型和操作人id
			/*if (operator != null) {
				${classInfo.className}.setOperatorType(operator.getOperatorType());
				${classInfo.className}.setOperatorId(operator.getOperatorId());
			}*/
			
			//设置创建时间和更新时间为当前时间
			Date now = new Date();
			${classInfo.className}.setCreateTime(now);
			${classInfo.className}.setUpdateTime(now);
			
			//填充默认值
			this.fillDefaultValues(${classInfo.className});
			
			//调用Dao执行插入操作
			${classInfo.className}Dao.add(${classInfo.className});
			dubboResult.setCode(ResultCodeConstants.SUCCESS);
			dubboResult.setValue(${classInfo.className});
		} catch (Exception e) {
			logger.error(e.getMessage() + ", ${classInfo.className} = " + ${classInfo.className}, e);
			dubboResult.setCode(ResultCodeConstants.FAIL);
			dubboResult.setMessage(ResultMessageConstants.B00001.message());
			
		}	
		
		return dubboResult;
	}			
	
	/**
	 * 根据主键，更新一条${classInfo.firstUpperName}记录
	 * @param ${classInfo.className} 更新的${classInfo.firstUpperName}数据，且其主键不能为空
	 * @param operator 操作人对象
	 * @return
	 */
	public DubboResult update${classInfo.firstUpperName}(${classInfo.firstUpperName} ${classInfo.className}) {
        DubboResult dubboResult = new DubboResult();
		try {
		
		if (${classInfo.className} == null || ${classInfo.className}.get${classInfo.primaryKeyElement.firstUpperName}() == null) { //传入参数无效时直接返回失败结果
				logger.info("${classInfo.className} is null, ${classInfo.className} = " + ${classInfo.className});
				dubboResult.setCode(ResultCodeConstants.WARN);
				dubboResult.setMessage(ResultMessageConstants.T00001.message());
				return dubboResult;
			} 
			
			//如果传入的操作人不为null，则设置更新记录的操作人类型和操作人id
			/*if (operator != null) {
				${classInfo.className}.setOperatorType(operator.getOperatorType());
				${classInfo.className}.setOperatorId(operator.getOperatorId());
			}*/
			
			//设置更新时间为当前时间
			${classInfo.className}.setUpdateTime(new Date());
			
			//调用Dao执行更新操作，并判断更新语句执行结果
			int count = ${classInfo.className}Dao.update(${classInfo.className});			
			if (count <= 0) {
                dubboResult.setCode(ResultCodeConstants.FAIL);
			} else {
			    dubboResult.setCode(ResultCodeConstants.SUCCESS);
			}
            dubboResult.setValue(${classInfo.className});
			} catch (Exception e) {
				logger.error(e.getMessage() + ", ${classInfo.className} = " + ${classInfo.className}, e);
				dubboResult.setCode(ResultCodeConstants.FAIL);
				dubboResult.setMessage(ResultMessageConstants.B00001.message());
				
			}
		
		return dubboResult;
	}
	
	/**
	 * 为${classInfo.firstUpperName}对象设置默认值
	 * @param obj
	 */
	public void fillDefaultValues(${classInfo.firstUpperName} obj) {
		if (obj != null) {
		<#list classInfo.elements as el>
		  <#if el.defaultValue ?? && el.defaultValue!=''>
		    if (obj.get${el.firstUpperName}() == null) {
		    	obj.set${el.firstUpperName}(<@DEFVAL javaType=el.javaType defaultValue=el.defaultValue />);
		    }
		  </#if>
		</#list>
		}
	}

}
