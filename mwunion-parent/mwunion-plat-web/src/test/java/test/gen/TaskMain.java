package test.gen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.junit.Test;

import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.commons.paclscan.ClasspathPackageScanner;
import com.sungan.ad.commons.paclscan.PackageScanner;
import com.sungan.ad.dao.model.AdHostAccountAdOrder;

import net.sf.json.JSONObject;

/**
 * 说明:
 * 
 * @version V1.1
 */
public class TaskMain {

	@Test
	public void test2() throws IOException {
		PackageScanner scan = new ClasspathPackageScanner("com.sungan.ad.dao.model");
		List<String> fullyQualifiedClassNameList = scan.getFullyQualifiedClassNameList();
		System.out.println(fullyQualifiedClassNameList);
	}

	public static void main(String[] args) {
	}

	@Test
	public void test1() throws URISyntaxException, UnsupportedEncodingException {
		AdHostAccountAdOrder order = new AdHostAccountAdOrder();
		String nextId = IdGeneratorFactory.nextId();
		//order.setId(nextId);
		order.setCreateTime(new Date());
		order.setUpdateTime(new Date());
		try {
			URL resource = TaskMain.class.getClassLoader().getResource("template");
			File f = new File(resource.toURI());
			String[] list = f.list();

			VelocityEngine ve = new VelocityEngine();
			ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
			ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
			ve.init();
			PackageScanner scan = new ClasspathPackageScanner("com.sungan.ad.dao.model");
			List<String> fullyQualifiedClassNameList = scan.getFullyQualifiedClassNameList();
			for (String str : fullyQualifiedClassNameList) {
				if(str.contains("adenum")){
					continue;
				}
				String pk = "com.sungan.ad";
				String daminPk = "com.sungan.ad";
				Class clazz = Class.forName(str); // AppTask.class;
				String[] split = clazz.getName().split("\\.");
				String domainName = split[split.length - 1];
				String domainLow = domainName.substring(0, 1).toLowerCase() + domainName.substring(1);
				;// "adWeightGroup" ;
				String varname = domainLow + "DAO";

				Field[] declaredFields = clazz.getDeclaredFields();
				List<String> keySet = new ArrayList<String>();
				for (Field fname : declaredFields) {
					if (fname.getName().equals("class")) {
						continue;
					}
					keySet.add(fname.getName());
				}

				// conroller
				String contollerMapping = domainName.toLowerCase();
				String suffix = domainName.toLowerCase();

				// VM
				String vcKey = "#";

				for (String tname : list) {
					Template t = ve.getTemplate("/template/" + tname);
					VelocityContext ctx = new VelocityContext();
					String javaName = domainName + tname.replace(".vm", ".java");
					if (tname.equals("list.vm")) {
						javaName = domainName.toLowerCase() + ".vm";
					}
					String storePath = "D:/mwunion/";
					if(javaName.contains("DAO")){
						storePath = storePath.concat("dao/");
						if(javaName.contains("DAOImpl")){
							storePath = storePath.concat("dao/impl/");
						}
					}else if(javaName.contains("Service")){
						storePath = storePath.concat("service/");
						if(javaName.contains("ServiceImpl")){
							storePath = storePath.concat("service/impl/");
						}
					}else if(javaName.contains("Controller")){
						storePath = storePath.concat("controller/");
					}else if(javaName.contains("Vo")){
						storePath = storePath.concat("vo/");
					}else if(javaName.contains("Valid")){
						storePath = storePath.concat("valid/");
					}
					File newF = new File(storePath + javaName);
					if (!newF.exists()) {
						new File(storePath).mkdirs();
						newF.createNewFile();
					}
					FileOutputStream out = new FileOutputStream(newF);
					BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
					ctx.put("package", pk);
					ctx.put("domainPackage", daminPk);
					ctx.put("domain", domainName);
					ctx.put("domainLow", domainLow);
					ctx.put("varname", varname);
					ctx.put("contollerMapping", contollerMapping);
					ctx.put("suffix", suffix);
					ctx.put("vcKey", vcKey);
					ctx.put("beanPro", keySet);
					// List temp = new ArrayList();
					// temp.add("1");
					// temp.add("2");
					// ctx.put("list", temp);

					StringWriter sw = new StringWriter();
					// t.setEncoding("UTF-8");
					t.merge(ctx, sw);
					System.out.println(new String(sw.toString().getBytes("ISO-8859-1"), "UTF-8"));
					writer.write(new String(sw.toString().getBytes("ISO-8859-1"), "UTF-8"));
					writer.close();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
