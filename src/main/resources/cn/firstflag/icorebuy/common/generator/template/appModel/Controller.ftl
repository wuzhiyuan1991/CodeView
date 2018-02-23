<#if packageStr ?? && packageStr!=''>
package ${packageStr};
</#if>

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ${generatedMap[classInfo.firstUpperName+"Vo"]};
import ${generatedMap["I"+classInfo.firstUpperName+"View"]};
import ${generatedMap[classInfo.firstUpperName+"VoConvetor"]};
import ${generatedMap["BaseController"]};



<#list classInfo.primaryKey as el>
<#if !el.javaTypeDetail?contains('java.lang.'+el.javaType)>
import ${el.javaTypeDetail};
</#if>
</#list>

<#macro MPKTYPE><#list classInfo.primaryKey as pk><#if pk_index = 0>${pk.javaType}</#if></#list></#macro>

/**
 * ${classInfo.firstUpperName?default(classInfo.firstUpperName)} Action处理类
 */
@Controller
@RequestMapping("mgt/${classInfo.className}")
public class ${classInfo.firstUpperName}Controller extends BaseController{
	
	@Resource
	private I${classInfo.firstUpperName}View ${classInfo.className}View;
	
	@Resource
	private ${classInfo.firstUpperName}VoConvetor ${classInfo.className}VoConvetor;
	
	
	@RequestMapping(value = "/to${classInfo.firstUpperName}", produces = "text/html;charset=UTF-8")
	public String to${classInfo.firstUpperName}(${classInfo.firstUpperName}Vo ${classInfo.className},HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
		
		boolean flag = this.isLogin(request);
		if(flag){
			return "/mgt/${classInfo.className}";
		}
		
		return "/main/login";
	}
	
	@ResponseBody
	@RequestMapping(value = "/add${classInfo.firstUpperName}", produces = "text/html;charset=UTF-8")
	public String add${classInfo.firstUpperName}(${classInfo.firstUpperName}Vo ${classInfo.className},HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
		
		
		return "{success:true}";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/update${classInfo.firstUpperName}", produces = "text/html;charset=UTF-8")
	public String udpate${classInfo.firstUpperName}(${classInfo.firstUpperName}Vo ${classInfo.className},HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
		
		return "{success:true}";
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/get${classInfo.firstUpperName}", produces = "text/html;charset=UTF-8")
	public String get${classInfo.firstUpperName}(${classInfo.firstUpperName}Vo ${classInfo.className},HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
		
		return"{success:true}";
	}
	
	@ResponseBody
	@RequestMapping(value = "/del${classInfo.firstUpperName}", produces = "text/html;charset=UTF-8")
	public String del${classInfo.firstUpperName}(${classInfo.firstUpperName}Vo ${classInfo.className},HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
		
		return "{success:true}";
	}
	
	
}
