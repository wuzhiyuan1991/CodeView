<#if packageStr ?? && packageStr!=''>
package ${packageStr};
</#if>

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

<#list classInfo.primaryKey as el>
<#if !el.javaTypeDetail?contains('java.lang.'+el.javaType)>
import ${el.javaTypeDetail};
</#if>
</#list>
import ${generatedMap[classInfo.firstUpperName+"VoConvetor"]};
import ${generatedMap[classInfo.firstUpperName]};
import ${generatedMap["I"+classInfo.firstUpperName+"Service"]};
import ${generatedMap["I"+classInfo.firstUpperName+"View"]};
import ${generatedMap[classInfo.firstUpperName+"Vo"]};
import com.xiaoerzuche.common.page.PageFinder;

/**
 * ${classInfo.firstUpperName?default(classInfo.firstUpperName)} View服务类
 * @author yong
 *
 */
@Service
public class ${classInfo.firstUpperName}ViewImpl implements I${classInfo.firstUpperName}View {
	
	@Resource
	private I${classInfo.firstUpperName}Service ${classInfo.className}Service;
	
	@Resource
	private ${classInfo.firstUpperName}VoConvetor ${classInfo.className}VoConvetor;
	
	@Override
	public ${classInfo.firstUpperName}Vo add(${classInfo.firstUpperName}Vo ${classInfo.className}Vo) {
	
		${classInfo.firstUpperName} ${classInfo.className} = ${classInfo.className}VoConvetor.convert${classInfo.firstUpperName}Vo2Bean(${classInfo.className}Vo);
		${classInfo.className} = ${classInfo.className}Service.add(${classInfo.className});
		${classInfo.className}Vo = ${classInfo.className}VoConvetor.convert${classInfo.firstUpperName}Bean2Vo(${classInfo.className});
		return ${classInfo.className}Vo;
	}

	@Override
	public int update(${classInfo.firstUpperName}Vo ${classInfo.className}Vo) {
		
		${classInfo.firstUpperName} ${classInfo.className} = ${classInfo.className}VoConvetor.convert${classInfo.firstUpperName}Vo2Bean(${classInfo.className}Vo);
		return ${classInfo.className}Service.update(${classInfo.className});
	}

	@Override
	public int delete(String pk) {
		
		return ${classInfo.className}Service.delete(pk);
	}

	@Override
	public ${classInfo.firstUpperName}Vo get(String pk) {
		
		${classInfo.firstUpperName} ${classInfo.className} =${classInfo.className}Service.get(pk);
		${classInfo.firstUpperName}Vo ${classInfo.className}Vo = ${classInfo.className}VoConvetor.convert${classInfo.firstUpperName}Bean2Vo(${classInfo.className});
		return ${classInfo.className}Vo;
	}
	
	@Override
	public List<${classInfo.firstUpperName}Vo> queryAll(${classInfo.firstUpperName}Vo ${classInfo.className}Vo) {
		
		${classInfo.firstUpperName} ${classInfo.className} = ${classInfo.className}VoConvetor.convert${classInfo.firstUpperName}Vo2Bean(${classInfo.className}Vo);
		List<${classInfo.firstUpperName}> data= ${classInfo.className}Service.queryAll(${classInfo.className});
		List<${classInfo.firstUpperName}Vo> dataVo = ${classInfo.className}VoConvetor.convert${classInfo.firstUpperName}Beans2Vos(data);
		return dataVo;
	}

	@Override
	public PageFinder<${classInfo.firstUpperName}Vo> pageList(${classInfo.firstUpperName}Vo ${classInfo.className}Vo, int pageNo, int pageSize) {
		
		${classInfo.firstUpperName} ${classInfo.className} = ${classInfo.className}VoConvetor.convert${classInfo.firstUpperName}Vo2Bean(${classInfo.className}Vo);
		PageFinder<${classInfo.firstUpperName}> data = ${classInfo.className}Service.pageList(${classInfo.className},pageNo,pageSize);
		
		List<${classInfo.firstUpperName}Vo> dataVo = ${classInfo.className}VoConvetor.convert${classInfo.firstUpperName}Beans2Vos(data.getData());
		PageFinder<${classInfo.firstUpperName}Vo> p = new PageFinder<${classInfo.firstUpperName}Vo>(pageNo, pageSize, data.getRowCount(), dataVo);
		return p;
		
	}
	
}
