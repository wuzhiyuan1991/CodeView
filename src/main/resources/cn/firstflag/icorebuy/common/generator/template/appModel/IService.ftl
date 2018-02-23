<#if packageStr ?? && packageStr!=''>
package ${packageStr};
</#if>

import java.util.List;

import cn.firstflag.icorebuy.common.dto.DubboResult;
import cn.firstflag.icorebuy.common.dto.Query;
import ${generatedMap["BaseService"]};
import ${generatedMap[classInfo.firstUpperName]};

<#macro MPKTYPE><#list classInfo.primaryKey as pk><#if pk_index = 0>${pk.javaType}</#if></#list></#macro>
/**<p>Title:<#if classInfo.remarks?? && classInfo.remarks!=''>${classInfo.remarks}<#else>${classInfo.firstUpperName?default(classInfo.firstUpperName)}</#if> </p>
 * <p>Description: </p>
 * @author ${author}
 * @date ${datetime}
 */
public interface ${classInfo.firstUpperName}Service {

	/**
	 * 根据查询条件，查询并返回${classInfo.firstUpperName}的列表
	 * @param q 查询条件
	 * @return
	 */
	public DubboResult get${classInfo.firstUpperName}List(Query q);
	
	/**
	 * 根据查询条件，分页查询并返回${classInfo.firstUpperName}的分页结果
	 * @param q 查询条件
	 * @return
	 */
	public DubboResult get${classInfo.firstUpperName}PagedList(Query q);
	
	/**
	 * 根据主键，查询并返回一个${classInfo.firstUpperName}对象
	 * @param id 主键id
	 * @return
	 */
	public DubboResult get${classInfo.firstUpperName}(Long id);

	/**
	 * 根据主键数组，查询并返回一组${classInfo.firstUpperName}对象
	 * @param ids 主键数组，数组中的主键值应当无重复值，如有重复值时，则返回的列表长度可能小于传入的数组长度
	 * @return
	 */
	public DubboResult get${classInfo.firstUpperName}ByIds(Long[] ids);
	
	/**
	 * 根据主键数组，删除数组特定的{classInfo.firstUpperName}记录
	 * @param ids 主键数组，数组中的主键值应当无重复值，如有重复值时，则返回的列表长度可能小于传入的数组长度
	 * @return
	 */
	public DubboResult del${classInfo.firstUpperName}ByIds(Long[] ids);

	/**
	 * 根据主键，删除特定的${classInfo.firstUpperName}记录
	 * @param id 主键id
	 * @param operator 操作人对象
	 * @return
	 */
	public DubboResult del${classInfo.firstUpperName}(Long id);
	
	/**
	 * 新增一条${classInfo.firstUpperName}记录，执行成功后传入对象及返回对象的主键属性值不为null
	 * @param ${classInfo.className} 新增的${classInfo.firstUpperName}数据（如果无特殊需求，新增对象的主键值请保留为null）
	 * @param operator 操作人对象
	 * @return
	 */
	public DubboResult add${classInfo.firstUpperName}(${classInfo.firstUpperName} ${classInfo.className});
	
	/**
	 * 根据主键，更新一条${classInfo.firstUpperName}记录
	 * @param ${classInfo.className} 更新的${classInfo.firstUpperName}数据，且其主键不能为空
	 * @param operator 操作人对象
	 * @return
	 */
	public DubboResult update${classInfo.firstUpperName}(${classInfo.firstUpperName} ${classInfo.className});
	
    /**
	 * 为${classInfo.firstUpperName}对象设置默认值
	 * @param obj
	 */
	public void fillDefaultValues(${classInfo.firstUpperName} obj);
		
}
