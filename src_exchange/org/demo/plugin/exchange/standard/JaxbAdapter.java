package org.demo.plugin.exchange.standard;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * 格式化日期
 * <pre>
 * 时间格式：yyyy/MM/dd HH:mm:ss
 * -----如：2013/10/30 11:25:45
 * </pre>
 * @author Developer
 *
 */
public class JaxbAdapter extends XmlAdapter<String, Date> {

	@Override
	public String marshal(Date v) throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		return simple.format(v);
	}

	@Override
	public Date unmarshal(String v) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
