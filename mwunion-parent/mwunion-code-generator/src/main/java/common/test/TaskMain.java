package common.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.junit.Test;


/**
 * 说明:
 * @version V1.1
 */
public class TaskMain {
	
	@Test
	public void test1() throws URISyntaxException, UnsupportedEncodingException{
		try {
			URL resource = TaskMain.class.getClassLoader().getResource("template");
			File f = new  File(resource.toURI());
			 String[] list = f.list();
				
			VelocityEngine ve = new VelocityEngine();
			 ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
			 ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
			 ve.init();
			 
			 String pk = "com.sungan.ad";
			 String daminPk = "com.sungan.ad";
			 Class clazz = null;
			 String[] split = clazz.getName().split("\\.");
			 String domainName = split[split.length-1];
			 String domainLow = domainName.substring(0,1).toLowerCase() + domainName.substring(1); ;//"adWeightGroup" ;
			 String varname = domainLow+"DAO";
			 
//			 Map<String, Object> beanFile = AdCommonsUtil.getBeanFile(clazz);
//			 Set<String> keySet = beanFile.keySet();
			 Field[] declaredFields = clazz.getDeclaredFields();
			 List<String> keySet = new ArrayList<String>();
			 for(Field fname :declaredFields){
				 if(fname.getName().equals("class")){
					 continue;
				 }
				 keySet.add(fname.getName());
			 }
			 
			 //conroller
			 String contollerMapping = domainName.toLowerCase();
			 String suffix = domainName.toLowerCase();
			 
			 //VM
			 String vcKey = "#";
			 
			 
			 for(String tname:list){
				 Template t = ve.getTemplate("/template/"+tname);
				 VelocityContext ctx = new VelocityContext();
				 String javaName = domainName+tname.replace(".vm", ".java");
				 if(tname.equals("list.vm")){
					 javaName = domainName.toLowerCase()+".vm";
				 }
				 File newF= new File("D:/log/"+javaName);
				 if(!newF.exists()){
					 new File("D:/log/").mkdirs();
					 newF.createNewFile();
				 }
				 FileOutputStream out = new FileOutputStream(newF);
				 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
				 ctx.put("package", pk);
				 ctx.put("domainPackage",daminPk);
				 ctx.put("domain", domainName);
				 ctx.put("domainLow", domainLow);
				 ctx.put("varname", varname);
				 ctx.put("contollerMapping", contollerMapping);
				 ctx.put("suffix", suffix);
				 ctx.put("vcKey", vcKey);
				 ctx.put("beanPro", keySet);
				 
				 StringWriter sw = new StringWriter();
//				 t.setEncoding("UTF-8");
				 t.merge(ctx, sw);
				 System.out.println(new String(sw.toString().getBytes("ISO-8859-1"),"UTF-8"));
				 writer.write(new String(sw.toString().getBytes("ISO-8859-1"),"UTF-8"));
				 writer.close();
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
				
	}
}
