<#if packageStr ?? && packageStr!=''>
package ${packageStr};
</#if>

<#if classInfo.importedJavaTypes?? && (classInfo.importedJavaTypes?size != 0)><#list classInfo.importedJavaTypes as item>
import ${item};
</#list>

</#if>

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

<#macro MPKTYPE><#list classInfo.primaryKey as pk><#if pk_index == 0>${pk.javaType}</#if></#list></#macro>
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
<#macro MPK>
<#list classInfo.primaryKey as pk>
<#if pk_index = 0>
	public ${pk.javaType} getPK(){
		return ${pk.javaName};
	}
</#if>
</#list>
</#macro>
/**<p>Title:<#if classInfo.remarks?? && classInfo.remarks!=''>${classInfo.remarks}<#else>${classInfo.firstUpperName?default(classInfo.firstUpperName)}</#if> </p>
 * <p>Description: </p>
 * @author ${author}
 * @date ${datetime}
 */
public class ${classInfo.firstUpperName} implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	<#list classInfo.elements as el>
	<#if el.remark?? && el.remark!=''>
	/**
	 * ${el.remark}
	 */
	</#if>
	private ${el.javaType} ${el.javaName};
	</#list>
	
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE );
    }
	
	<#list classInfo.elements as el>
	public ${el.javaType} get${el.firstUpperName}(){
		return ${el.javaName};
	}
	
	public void set${el.firstUpperName}(${el.javaType} ${el.javaName}){
		this.${el.javaName} = ${el.javaName};
	}
	</#list>
    
}
