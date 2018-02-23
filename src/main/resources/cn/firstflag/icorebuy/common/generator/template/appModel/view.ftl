<#if packageStr ?? && packageStr!=''>
package ${packageStr};
</#if>

import java.util.List;
import com.xiaoerzuche.common.page.PageFinder;

<#list classInfo.primaryKey as el>
<#if !el.javaTypeDetail?contains('java.lang.'+el.javaType)>
import ${el.javaTypeDetail};
</#if>
</#list>
import ${generatedMap[classInfo.firstUpperName + "Vo"]};

/**
 *  ${classInfo.firstUpperName?default(classInfo.firstUpperName)} View接口类
 * @author yong
 *
 */
public interface I${classInfo.firstUpperName}View {
	
	/**
	 * 增加对象
	 * @param obj
	 */
	public ${classInfo.firstUpperName}Vo add(${classInfo.firstUpperName}Vo ${classInfo.className}Vo);

	/**
	 * 修改对象
	 * @param obj
	 */
	public int update(${classInfo.firstUpperName}Vo ${classInfo.className}Vo);

	/**
	 * 删除对象
	 * @param pk
	 */
	public int delete(String pk);

	/**
	 * 得到某个对象
	 * @param pk
	 */
	public ${classInfo.firstUpperName}Vo get(String pk);
	
		/**
	 * 查询所有数据
	 * @return
	 */
	public List<${classInfo.firstUpperName}Vo> queryAll(${classInfo.firstUpperName}Vo ${classInfo.className}Vo);
	
	/**
	 * 分页查询
	 * @param object
	 * @return
	 */
	public PageFinder<${classInfo.firstUpperName}Vo> pageList(${classInfo.firstUpperName}Vo ${classInfo.className}Vo,int pageNo,int pageSize);
	
}
