<#if packageStr ?? && packageStr!=''>
package ${packageStr};
</#if>

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.xiaoerzuche.common.utils.BeanUtils;
import ${generatedMap[classInfo.firstUpperName]};
import ${generatedMap[classInfo.firstUpperName+"Vo"]};

<#macro MPKTYPE><#list classInfo.primaryKey as pk><#if pk_index = 0>${pk.javaType}</#if></#list></#macro>

/**
 * LeaseInfoVo转换器
 * @author yong
 *
 */
 /**
 * ${classInfo.firstUpperName?default(classInfo.firstUpperName+"Vo")} 转换器
 * @author yong
 */
@Component
public class ${classInfo.firstUpperName}VoConvetor {
	
	/**
	 * ${classInfo.firstUpperName}Vo 转换成 ${classInfo.firstUpperName}
	 * @param ${classInfo.firstUpperName}Vo
	 * @return
	 */
	public ${classInfo.firstUpperName} convert${classInfo.firstUpperName}Vo2Bean(${classInfo.firstUpperName}Vo ${classInfo.className}Vo){
		
		if(null == ${classInfo.className}Vo){
			return null;
		}
		${classInfo.firstUpperName} ${classInfo.className}= new ${classInfo.firstUpperName}();
		BeanUtils.deepCopy(${classInfo.className}Vo, ${classInfo.className});
		
		return ${classInfo.className};
	}
	
	/**
	 * ${classInfo.firstUpperName} 转换成${classInfo.firstUpperName}Vo
	 * @param ${classInfo.firstUpperName}
	 * @return
	 */
	public ${classInfo.firstUpperName}Vo convert${classInfo.firstUpperName}Bean2Vo(${classInfo.firstUpperName} ${classInfo.className}){
		
		if(null == ${classInfo.className}){
			return null;
		}
		${classInfo.firstUpperName}Vo ${classInfo.className}Vo= new ${classInfo.firstUpperName}Vo();
		BeanUtils.deepCopy(${classInfo.className}, ${classInfo.className}Vo);
		
		return ${classInfo.className}Vo;
	}
	
	/**
	 * List ${classInfo.firstUpperName}Vo 转换成 List ${classInfo.firstUpperName}
	 * @param {classInfo.className}s
	 * @return
	 */
	public List<${classInfo.firstUpperName}> convert${classInfo.firstUpperName}Vos2Beans(List<${classInfo.firstUpperName}Vo> ${classInfo.className}Vos) {
		
		if(null == ${classInfo.className}Vos || ${classInfo.className}Vos.isEmpty()){
			return null;
		}
		
		List<${classInfo.firstUpperName}> ${classInfo.className}s =  new ArrayList<${classInfo.firstUpperName}>(${classInfo.className}Vos.size());
		for(${classInfo.firstUpperName}Vo vo : ${classInfo.className}Vos) {
			${classInfo.firstUpperName} bean = convert${classInfo.firstUpperName}Vo2Bean(vo);
			if(bean != null) {
				${classInfo.className}s.add(bean);
			}
		}
		return ${classInfo.className}s;
	}
	
	/**
	 * List ${classInfo.firstUpperName} 转换成 List ${classInfo.firstUpperName}Vo
	 * @param {classInfo.className}Vos
	 * @return
	 */
	public List<${classInfo.firstUpperName}Vo> convert${classInfo.firstUpperName}Beans2Vos(List<${classInfo.firstUpperName}> ${classInfo.className}s) {
		
		if(null == ${classInfo.className}s || ${classInfo.className}s.isEmpty()){
			return null;
		}
		
		List<${classInfo.firstUpperName}Vo> ${classInfo.className}Vos =  new ArrayList<${classInfo.firstUpperName}Vo>(${classInfo.className}s.size());
		for(${classInfo.firstUpperName} bean : ${classInfo.className}s) {
			${classInfo.firstUpperName}Vo vo = convert${classInfo.firstUpperName}Bean2Vo(bean);
			if(vo != null) {
				${classInfo.className}Vos.add(vo);
			}
		}
		return ${classInfo.className}Vos;
	}
	
}
