package cn.firstflag.icorebuy.common.generator.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import cn.firstflag.icorebuy.common.generator.commons.FileUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 生成器
 * @author yong
 *
 */
public class TemplateGenerator {
	
	GeneratorParam param = null;
	
	public TemplateGenerator(GeneratorParam param){
		this.param = param;
	}
	
	/**
	 * 根据模板文件生成目标文件
	 * @param templateDire
	 * @param templateFile
	 * @param targetDire
	 * @param targetName
	 * @param paramData
	 */
	public void generatorFile(String templateDire,String templateFile,String targetDire,
			String targetName, Map<String, Object> paramData) {
		
		Writer out = null;
		OutputStreamWriter streamWriter = null;
		FileOutputStream outputStream = null;
		
		try {
			//生成的文件,判断是否覆盖原存在文件
			File targetFile = new File(FileUtils.addPathSeparator(targetDire, null)+targetName);
			if(!param.isOverExistFile()){
				if(targetFile.exists()){
					return;
				}
			}
			
			Configuration freemarkCfg = new Configuration();
			freemarkCfg.setDirectoryForTemplateLoading(new File(templateDire));
			
			if(null != param.getDefaultEncoding() 
					&& param.getDefaultEncoding().trim().length()>0){
				freemarkCfg.setEncoding(Locale.getDefault(), param.getDefaultEncoding().trim());
			}
			//加文件后缀
			templateFile = setFilePostFix(templateFile,param.getTemplatepostfix());
			Template template = freemarkCfg.getTemplate(templateFile,param.getDefaultEncoding().trim());
			template.setEncoding(param.getDefaultEncoding().trim());
			//生成文件目录
			if(!FileUtils.checkDirectory(targetDire)){
				throw new Exception(targetDire+":The target directory to create a failure");
			}
				
			outputStream = new FileOutputStream(targetFile);
			streamWriter = new OutputStreamWriter(outputStream);
			out = new BufferedWriter(streamWriter);
				
			template.process(null == paramData?new HashMap<String,Object>():paramData, out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(streamWriter != null){
				try {
					streamWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(outputStream != null){
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 连接文件后缀
	 * @param fileName
	 * @param postFix
	 * @return
	 */
	public String setFilePostFix(String fileName, String postFix){
		if(null != fileName){
			if(fileName.contains("."+postFix)){
				return fileName;
			}else{
				return fileName.trim()+"."+postFix;
			}
		}
		return fileName;
	}
}
