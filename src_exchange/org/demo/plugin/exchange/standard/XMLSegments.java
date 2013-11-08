package org.demo.plugin.exchange.standard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.demo.plugin.exchange.org.ExAccount;
import org.demo.plugin.exchange.org.ExDepart;
import org.demo.plugin.exchange.org.ExMember;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import sun.font.EAttribute;

public class XMLSegments {
	/**xml文档对象*/
	private Document document;
	/**记录所有属性*/
    private Map<String, Object> propertyList = null;
    /** 记录所有子元素*/
    private Map<String, Object> subElements = null;
    private String displayName=null;
    
	public XMLSegments(String name){
		propertyList = new LinkedHashMap<String, Object>(5);
		subElements = new LinkedHashMap<String, Object>(10);
		document = DocumentHelper.createDocument();
    	document.setXMLEncoding("UTF-8");
    	document.addElement(displayName);
	}
    
    public void addElement(String key,Object value){
    	isBlank(key);
    	subElements.put(key, value);
    }
    
    public void addProperty(String key,String value){
    	isBlank(key);
    	propertyList.put(key, value);
    }
    public String asXML(){
    	return asXML(null);
    }
    
    private String asXML(Element d){
    	String s="";
    	Element root = document.getRootElement();
    	if(d!=null)
    		root = d.addElement(root.getName());
    	//添加XML参数
    	Set propertys = propertyList.keySet();
    	Iterator it=propertys.iterator();
    	while(it.hasNext()){
    		String key = (String) it.next();
    		root.addAttribute(key, (String) propertyList.get(key));
    	}
    	//添加节点
    	Set keys = subElements.keySet();
    	it=keys.iterator();
    	while(it.hasNext()){
    		String key = (String) it.next();
			Element e = root.addElement(key);
			if(subElements.get(key) instanceof Iterable){
				Object o[] = ((Collection)subElements.get(key)).toArray();
				for (int i=0; i<o.length ; i++) {
					if(o[i] instanceof XMLSegments){
						((XMLSegments)o[i]).asXML(e);
					}else{
						e.setText((String) subElements.get(key));
					}
				}
			}else if(subElements.get(key) instanceof XMLSegments){
				((XMLSegments)subElements.get(key)).asXML(e);
    		}else{
				e.setText((String) subElements.get(key));
			}
		}
    	s=this.document.asXML();
    	return s;
    }
    
    public void saveToFile(String path) throws IOException{
    	OutputFormat format = OutputFormat.createPrettyPrint(); 
    	format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream(path),format);
    	writer.write(this.document);
    	writer.flush();
    }
    
    public void parseFromXML(InputStream is) throws DocumentException{
    	 SAXReader saxReader = new SAXReader();
    	 document=saxReader.read(is);
    	 Element root = document.getRootElement();
    	 parseXML(root);
    }
    
    public void parseXML(Element e){
    	
			String name = e.getName();
			String value=e.getTextTrim();
			System.out.println(name+":"+value);
			List ss = e.elements();
			if(ss.size()>0){
				
			}
			for (int i = 0; i < ss.size(); i++) {
				parseXML((Element)ss.get(i));
			}
    }
    /**
     * 判断是否为空
     * <pre>
     * value=null     true
     * value=""       true
     * value="    "   true
     * value="null"   true
     * </pre>
     * @param value
     * @return
     */
    public void isBlank(String value){
    	if(value!=null||!value.trim().equals("")||!value.trim().equals("null")){
    		return;
    	}
    	throw new RuntimeException("变量名不能为空！");
    }
    
    public static void main(String[] args) throws IOException, DocumentException, JAXBException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
    	ExAccount account = new ExAccount();
    	account.setId(1231231231L);
    	account.setName("灿天科技");
    	account.setCreate_time(new Date());
    	ExDepart d1 = new ExDepart();
    	d1.setName("人事");
    	d1.setId(123123123L);
    	ExMember m1 = new ExMember();
    	m1.setId(123L);
    	m1.setName("你好");
    	ExMember m2 = new ExMember();
    	m2.setId(1234L);
    	m2.setName("世界");
    	List<ExMember> mx1 = new ArrayList<ExMember>();
    	mx1.add(m1);
    	mx1.add(m2);
    	d1.setMembers(mx1);
    	ExDepart d2 = new ExDepart();
    	d2.setName("不知");
    	d2.setId(12312233123L);
    	List<ExMember> mx2 = new ArrayList<ExMember>();
    	ExMember m3 = new ExMember();
    	m3.setId(12345L);
    	m3.setName("我来了");
    	mx2.add(m3);
    	d2.setMembers(mx2);
    	List<ExDepart> ex = new ArrayList<ExDepart>();
    	ex.add(d1);
    	ex.add(d2);
    	account.setDeps(ex);
    	XMLOperation.toXMLByDom(account, "e:/2.xml");
    	//XMLOperation.toXML(account, "e:/test.xml");
        
        
        ExAccount exAccount = (ExAccount) XMLOperation.toObject(ExAccount.class, "e:/test.xml");  
			System.out.println(exAccount.getName());
			List<ExDepart> ExDepart = exAccount.getDeps();
			for (ExDepart exDepart2 : ExDepart) {
				System.out.println(exDepart2.getName());
			}
	}
}
