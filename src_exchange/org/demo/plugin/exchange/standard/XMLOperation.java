package org.demo.plugin.exchange.standard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.demo.plugin.exchange.org.ExAccount;
import org.demo.plugin.exchange.util.ConvertUtil;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil.ToStringAdapter;

public class XMLOperation {
	
	/**
	 * XML还原为对象，仅限通过JAXB转换的XML
	 * @param c 待还原的类
	 * @param path XML文件保存路径
	 * @return
	 * @throws JAXBException
	 */
	public static Object toObject(Class c,String path) throws JAXBException{
		JAXBContext cxt = JAXBContext.newInstance(c);  
        Unmarshaller unmarshaller = cxt.createUnmarshaller();  
        return unmarshaller.unmarshal(new File(path));  
	}
	/**
	 * 对象转为XML，仅限通过JAXB转换的XML
	 * @param o 待转换的对象
	 * @param path XML文件保存路径
	 * @return
	 * @throws JAXBException
	 */
	public static void toXML(Object o,String path) throws JAXBException{
		JAXBContext context = JAXBContext.newInstance(o.getClass());  
        Marshaller marshaller = context.createMarshaller();  
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// 是否格式化生成的xml串  
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);// 默认false表示xml指令存在   
        marshaller.marshal(o , new File(path));
	}
	
	public static void toXMLByDom(Object o,String path) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		Document document = DocumentHelper.createDocument();
    	document.setXMLEncoding("UTF-8");
    	Element root = document.addElement("ExOrg");
    	addElement(o,root);
		OutputFormat format = OutputFormat.createPrettyPrint(); 
    	format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream(path),format);
    	writer.write(document);
    	writer.flush();
    	writer.close();
	}
	
	private static void addElement(Object o,Element e) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		Element newe = e.addElement(o.getClass().getSimpleName());
		Method m[] = o.getClass().getMethods();
		for (int i = 0; i < m.length; i++) {
			if(m[i].getName().indexOf("get")!=-1&&m[i].getName().indexOf("get")==0){
				String fieldName = m[i].getName().substring(m[i].getName().indexOf("get")+3);
				fieldName = ConvertUtil.lowWord(fieldName);
				if(!fieldName.equals("class")){
					Element ee = null;
					Object obj = m[i].invoke(o, null);
					if(!String.valueOf(obj).equals("null")){
						ee = newe.addElement(fieldName);
						if(obj instanceof Collection){
							List list = (List)obj;
							for (int j = 0; j < list.size(); j++) {
								addElement(list.get(j),ee);
							}
						}else if(obj instanceof Date) {
							String time = ConvertUtil.formatDate((Date)obj, null);
							ee.setText(time);
						}else{
							ee.setText(String.valueOf(obj).equals("null")?"":String.valueOf(obj));
						}
					}
				}
			}
		}
	}
}
