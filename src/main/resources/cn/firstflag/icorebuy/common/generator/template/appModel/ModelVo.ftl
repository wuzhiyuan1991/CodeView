<#if packageStr ?? && packageStr!=''>
package ${packageStr};
</#if>

import java.io.Serializable;
<#list classInfo.elements as el>
<#if !el.javaTypeDetail?contains('java.lang.'+el.javaType)>
import ${el.javaTypeDetail};
</#if>
</#list>


/**
 * ${classInfo.firstUpperName?default(classInfo.firstUpperName)} 数据库实体类
 */
public class ${classInfo.firstUpperName}Vo implements Serializable {
	
	private static final long serialVersionUID = 1l;
	
	<#list classInfo.elements as el>
	<#if el.remark?? && el.remark!=''>
	//${el.remark}
	</#if>
	private ${el.javaType} ${el.javaName};
	</#list>
	
	
	<#list classInfo.elements as el>
	public ${el.javaType} get${el.firstUpperName}(){
		return ${el.javaName};
	}
	
	public void set${el.firstUpperName}(${el.javaType} ${el.javaName}){
		this.${el.javaName} = ${el.javaName};
	}
	</#list>
}
