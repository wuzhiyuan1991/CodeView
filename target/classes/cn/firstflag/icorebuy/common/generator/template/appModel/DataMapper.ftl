<#if packageStr ?? && packageStr!=''>
package ${packageStr};
</#if>

import java.util.List;

import ${generatedMap[classInfo.firstUpperName]};
import ${generatedMap["BaseDao"]};
import cn.firstflag.icorebuy.common.dto.Query;

<#macro MPKTYPE><#list classInfo.primaryKey as pk><#if pk_index = 0>${pk.javaType}</#if></#list></#macro>
/**<p>Title:<#if classInfo.remarks?? && classInfo.remarks!=''>${classInfo.remarks}<#else>${classInfo.firstUpperName?default(classInfo.firstUpperName)}</#if> </p>
 * <p>Description: </p>
 * @author ${author}
 * @date ${datetime}
 */
public interface ${classInfo.firstUpperName}Dao extends BaseDao<${classInfo.firstUpperName},<@MPKTYPE/>> {

    List<${classInfo.firstUpperName}> get${classInfo.firstUpperName} (Query query);
}
