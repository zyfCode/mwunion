package com.sungan.ad.commons;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;


import com.sungan.ad.commons.jaxb.CDataXMLStreamWriter;

public class JAXBUtil {
	// 输出 的编码方�?
	private static final String ECODING = "UTF-8";

	private JAXBUtil() {
	}

	/**
	 * 将对象序列化到本�?
	 * 
	 * @param jaxbEntity
	 *            可�?过JAXB序列化的对象
	 * @param file
	 *            必须的形�?./xx.xml，并且要保证xx.xml文件的父路径存在
	 * @throws JAXBException
	 * @throws IOException
	 */
	public static void marshallerTolocal(Object jaxbEntity, File file) throws JAXBException, IOException {
		JAXBContext context = JAXBContext.newInstance(jaxbEntity.getClass());
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, JAXBUtil.ECODING);
		marshaller.marshal(jaxbEntity, file);
	}
	
	   /**使用JAXB方式解决CDATA问题 
     * 
     * @throws Exception 
     */  
    public static<T> String ojbectToXmlWithCDATA(T t) throws Exception {  
  
        JAXBContext context = JAXBContext.newInstance(t.getClass());  
        ByteArrayOutputStream op = null;
		CDataXMLStreamWriter cdataStreamWriter;
		try {
			op = new ByteArrayOutputStream();  
  
			XMLOutputFactory xof = XMLOutputFactory.newInstance();  
			XMLStreamWriter streamWriter = xof.createXMLStreamWriter(op);  
			cdataStreamWriter = new CDataXMLStreamWriter(streamWriter);  
  
			Marshaller mar = context.createMarshaller();  
			mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);  
			mar.marshal(t, cdataStreamWriter);  
			  cdataStreamWriter.flush();
		} finally {
			if(op!=null){
				op.close();
			}
		}  
	      cdataStreamWriter.close();  
        return op.toString("UTF-8");  
    }  

	/**
	 * 进行列化
	 * 
	 * @param jaxbEntity
	 *            可�?过JAXB序列化的对象
	 * @return String
	 * @throws JAXBException
	 * @throws IOException
	 */
	public static <T> String marshaller(T jaxbEntity) throws JAXBException, IOException {
		if (jaxbEntity == null)
			return null;
		ByteArrayOutputStream outMesg = null;
		String result = "";
		try {
			outMesg = new ByteArrayOutputStream();
			JAXBContext context = JAXBContext.newInstance(jaxbEntity.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, JAXBUtil.ECODING);
			marshaller.marshal(jaxbEntity, outMesg);
		} finally {
			if (outMesg != null)
				outMesg.close();
		}
		result = outMesg.toString(JAXBUtil.ECODING);
		return result;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	private static boolean isBlank(String str) {
		if (str == null || str.trim().equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * 对XML进行反序列化
	 * 
	 * @param clazz
	 * @param xml
	 *            必须是xml格式的字符串
	 * @return T
	 * @throws JAXBException
	 * @throws IOException
	 */
	public static <T> T unmarshal(Class<T> clazz, String xml) throws JAXBException, IOException {
		if (JAXBUtil.isBlank(xml))
			return null;

		StringReader reader = null;
		T unmarshal = null;
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			reader = new StringReader(xml);
			unmarshal = (T) unmarshaller.unmarshal(reader);
		} finally {
			if (reader != null)
				reader.close();
		}
		return unmarshal;
	}

	public static <T> T convertXmlInputStream(Class<T> entityClass, String context) throws JAXBException, IOException {
		InputStream inputStrem = null;
		T unmarshal = null;
		try {
			JAXBContext jc = JAXBContext.newInstance(entityClass);
			Unmarshaller u = jc.createUnmarshaller();
			inputStrem = Thread.currentThread().getContextClassLoader().getResourceAsStream(context);
			unmarshal = (T) u.unmarshal(inputStrem);
		} finally {
			if (inputStrem != null)
				inputStrem.close();
		}
		return unmarshal;
	}
}
