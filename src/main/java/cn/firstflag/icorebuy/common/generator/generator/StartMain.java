package cn.firstflag.icorebuy.common.generator.generator;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

import cn.firstflag.icorebuy.common.generator.classdata.ClassElement;
import cn.firstflag.icorebuy.common.generator.commons.FileUtils;
import cn.firstflag.icorebuy.common.generator.commons.MetaDataUtils;
import cn.firstflag.icorebuy.common.generator.commons.StringUtils;
import cn.firstflag.icorebuy.common.generator.configure.PropertiesHandle;

/**
 * 启动类
 * @author yong
 *
 */
public class StartMain {
	
	private Map<String, String> dataTypeCfg;
	private GeneratorParam generatorParam;
	private GenerateFilesCfg generateFiles;
	
	/**
	 * 初始化数据
	 */
	private void initData(){
		
		generatorParam = PropertiesHandle.getGeneratorParam();
		
		dataTypeCfg = PropertiesHandle.getDataTypeCfg();
		
		generateFiles = PropertiesHandle.getGenerateFiles();
		
	}
	
	/**
	 * 生成代码文件
	 */
	private void generate(){
		TemplateGenerator tempGene = new TemplateGenerator(generatorParam);
		String generatorDirect = FileUtils.getCfgDirectory(generatorParam.getGeneratorPath());//代码生成目录
		Map<String,String > generatedFile = new HashMap<String,String>(); //生成的文件
		//生成基础代码
		Map<String, String> baseFiles = generateFiles.getBaseFiles();
		String baseTemplateDire = FileUtils.getCfgDirectory(generatorParam.getBaseTempDirect());
		Map<String,Object> baseParamData = new HashMap<String,Object>();
		baseParamData.put("imports", generateFiles.getImports());
		Iterator<Entry<String, String>> baseIterator = baseFiles.entrySet().iterator();
		while (baseIterator.hasNext()) {
			Entry<String, String> next = baseIterator.next();
			String templateFile = next.getKey();
			String targetValue = next.getValue();
			
			//变量替换
			targetValue = MetaDataUtils.replaceVariable(targetValue,generateFiles.getVariables());
			//生成文件目录
			String packageDirect = FileUtils.getDirectByPackage(targetValue);
			String targetDirect = FileUtils.concatPath(generatorDirect,packageDirect,null);
			String targetName = FileUtils.getFileNameByPackage(targetValue);
			//文件包结构
			String packageStr = FileUtils.getPackage(targetValue);
			baseParamData.put("packageStr", packageStr);
			generatedFile.put(FileUtils.removeJavaPostFix(targetName), FileUtils.getFullPackage(targetValue));
			//生成文件的包路径
			baseParamData.put("generatedMap", generatedFile);
			
			tempGene.generatorFile(baseTemplateDire, templateFile, targetDirect, targetName, baseParamData);
		}
		
		//生成应用代码
		DBInfoToClassInfo dbToClass = new DBInfoToClassInfo();
		List<ClassElement> allClassInfo = dbToClass.getAllClassInfo(generatorParam, dataTypeCfg);
		if(null == allClassInfo || allClassInfo.isEmpty()){
			return ;
		}
		
		Map<String, String> appFiles = generateFiles.getAppFiles();
		String templateDire = FileUtils.getCfgDirectory(generatorParam.getApplTempDirect());
		
		//得到所有要生成java代码的包路径,用作import使用.
		Map<String, String> generateFilePackage = getGenerateFilePackage(allClassInfo,appFiles);
		generatedFile.putAll(generateFilePackage);

		Random random = new Random();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(int i=0;i<allClassInfo.size();i++){
			ClassElement classElement = allClassInfo.get(i);
			Iterator<Entry<String, String>> iterator = appFiles.entrySet().iterator();
			Map<String,Object> paramData = new HashMap<String,Object>();
			paramData.put("classInfo", classElement);
			paramData.put("imports", generateFiles.getImports());
			paramData.put("generatedMap", generatedFile);
			paramData.put("author", generatorParam.getAuthor());
			while (iterator.hasNext()) {
				Entry<String, String> next = iterator.next();
				String templateFile = next.getKey();
				String targetValue = next.getValue();
				
				//变量替换
				targetValue = MetaDataUtils.replaceVariable(targetValue,generateFiles.getVariables());
				//处理模块
				targetValue = MetaDataUtils.replaceModule(targetValue, classElement.getModuleGroup());
				//星号替换
				targetValue = MetaDataUtils.replaceAsterisk(targetValue, classElement.getClassName());
				//字符串内置函数处理
				targetValue = MetaDataUtils.StringFuncHandle(targetValue);
				
				//生成文件目录
				String packageDirect = FileUtils.getDirectByPackage(targetValue);
				String targetDirect = FileUtils.concatPath(generatorDirect,packageDirect,null);
				String targetName = FileUtils.getFileNameByPackage(targetValue);
				//文件包结构
				String packageStr = FileUtils.getPackage(targetValue);
				paramData.put("packageStr", packageStr);
				String randomLong = random.nextLong() + "L";
				paramData.put("datetime", simpleDateFormat.format(new Date()));
				paramData.put("randomLong", randomLong);
				
				tempGene.generatorFile(templateDire, templateFile, targetDirect, targetName, paramData);
			}
		}
	}
	
	/**
	 * 得到所有要生成java代码的包路径
	 * @param allClassInfo
	 * @param appFiles
	 * @return
	 */
	private Map<String,String> getGenerateFilePackage(List<ClassElement> allClassInfo,Map<String,String> appFiles){
		Map<String,String > generatedFile = new HashMap<String,String>(); //生成的文件
		Map<String, String> printService = new HashMap<String, String>(); //打印dubbo配置
		for(int i=0;i<allClassInfo.size();i++){
			ClassElement classElement = allClassInfo.get(i);
			Iterator<Entry<String, String>> iterator = appFiles.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, String> next = iterator.next();

				String targetValue = next.getValue();
				
				//变量替换
				targetValue = MetaDataUtils.replaceVariable(targetValue,generateFiles.getVariables());
				//处理模块
				targetValue = MetaDataUtils.replaceModule(targetValue, classElement.getModuleGroup());
				//星号替换
				targetValue = MetaDataUtils.replaceAsterisk(targetValue, classElement.getClassName());
				//字符串内置函数处理
				targetValue = MetaDataUtils.StringFuncHandle(targetValue);
				//得到java名
				String targetName = FileUtils.getFileNameByPackage(targetValue);
				// 全名
				String fullName = FileUtils.getFullPackage(targetValue);
				// 打印 dubbo-provider  dubbo-consumer 配置
				if(next.getKey().equals("Services")) {
					printService.put("Services", fullName);
				}
				if(next.getKey().equals("IService")) {
					printService.put("IService", fullName);
					printDubbo(printService);
				}

				generatedFile.put(FileUtils.removeJavaPostFix(targetName), fullName);

			}
		}
		return generatedFile;
	}

	/**
	 * 打印 Dubbo 配置
	 * @param printService
     */
	private void printDubbo(Map<String, String> printService) {
		// 打印 dubbo-provider
		String service = printService.get("IService");
		String serviceImpl = printService.get("Services");
		String serviceName = service.substring(service.lastIndexOf(".") + 1);
		serviceName = StringUtils.firstLowerStr(serviceName);
		System.out.println("dubbo-privider-api");
		System.out.printf("    <dubbo:service interface=\"%s\" ref=\"%s\" />\n" +
				"    <bean id=\"%s\" class=\"%s\" />\n\n", service, serviceName, serviceName, serviceImpl);

		System.out.println("dubbo-consumer-api");
		System.out.printf("    <dubbo:reference id=\"%s\" interface=\"%s\" />\n\n\n\n", serviceName, service);
	}

	public void startGenerate(){
		//初始化配置文件
		initData();
		//生成文件
		generate();
	}
	
	
	
	public static void main(String[] args){
		
		StartMain startMain = new StartMain();

		startMain.startGenerate();
		
		System.out.println("--------已执行完成...");
	}
	
}
