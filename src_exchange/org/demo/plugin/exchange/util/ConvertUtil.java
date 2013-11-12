package org.demo.plugin.exchange.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

	/**
	 * 转换工具类
	 */
	public class ConvertUtil {
		
		  /**
	     * 判断要str在propertyMap中是否存在，如果存在且值是整型则返回该值
	     * @param propertyMap
	     * @param arg
	     * @return
	     */
	    public static int getIntValue(Map propertyMap, String arg) {
	        int intValue = 0;
	        Object obj = propertyMap.get(arg);
	        if(obj != null) {
	            intValue = Integer.parseInt(obj.toString());
	        }
	        return intValue;
	    }

	    /**
	     * 判断要key在propertyMap中是否存在，如果存在且值是整型则返回该值
	     * @param propertyMap
	     * @param arg
	     * @return
	     */
	    public static long getLongValue(Map map, String key) {
	    	Long value = 0L;
			Object obj = map.get(key);
			if (obj != null) {
				value = Long.parseLong(obj.toString());
			}
			return value;
	    }
	   
		
		
	    /**
	     * 判断要str在propertyMap中是否存在，如果存在且值是字符串则返回该值
	     * @param propertyMap
	     * @param arg
	     * @return
	     */
	    public static String getStringValue(Map propertyMap, String arg) {
	        String stringValue = "";
	        Object obj = propertyMap.get(arg);
	        if(obj != null ) {
	            stringValue = obj.toString();
	        }
	        /**当数据什么都没有的时候，会被解析成XMLSegments对象，这个不合法*/
	        if (stringValue.indexOf("com.seeyon.v3x.plugin.exchange.standard.XMLSegments")!=-1) {
	        	stringValue = "";
			}
	        return stringValue;
	    }

	    /**
	     * 判断要str在propertyMap中是否存在，如果存在且值是浮点型则返回该值
	     * @param propertyMap
	     * @param arg
	     * @return
	     */
	    public static float getFloatValue(Map propertyMap, String arg) {
	        float floatValue = 0.0f;
	        if(propertyMap.get(arg) != null) {
	            floatValue = Float.valueOf(propertyMap.get(arg).toString());
	        }
	        return floatValue;
	    }

	    /**
	     * 判断要str在propertyMap中是否存在，如果存在且值是浮点型则返回该值
	     * @param propertyMap
	     * @param arg
	     * @return
	     */
	    public static double getDoubleValue(Map propertyMap, String arg) {
	        double doubleValue = 0.0f;
	        if(propertyMap.get(arg) != null) {
	            doubleValue = Float.valueOf(propertyMap.get(arg).toString());
	        }
	        return doubleValue;
	    }

	    /**
	     * 判断要str在propertyMap中是否存在，如果存在且值是byte类型则返回该值
	     * @param propertyMap
	     * @param arg
	     * @return
	     */
	    public static byte getByteValue(Map propertyMap, String arg) {
	        byte byteValue = (byte)'n';
	        if(propertyMap.get(arg) != null) {
	            byteValue = Byte.valueOf(propertyMap.get(arg).toString());
	        }
	        return byteValue;
	    }

	    /**
	     * 判断要str在propertyMap中是否存在，如果存在且值是Boolean类型则返回该值
	     * @param propertyMap
	     * @param arg
	     * @return
	     */
	    public static boolean getBooleanValue(Map propertyMap, String arg) {
	        boolean booleanValue = false;
	        if(propertyMap.get(arg) != null) {
	            booleanValue = Boolean.valueOf((propertyMap.get(arg).toString()));
	        }
	        return booleanValue;
	    }
		
	    /**
	     * 检查子元素列表Map中是否存在以elementName为key的子元素。
	     * @param elementMap
	     * @param elementName
	     */
	    public static boolean isExists(Map elementMap, String elementName) {
	        boolean exist = false;
	        if(elementMap.get(elementName) != null) {
	            exist = true;
	        }
	        return exist;
	    }
	    
	    /**
	     * 判断要date在propertyMap中是否存在，如果存在且值是时间类型则返回该值
	     * 由于在CaseRunDao封装类中的构造方法中会 自己new Data()时间，所以传递的任何参数都无效。
	     * @param propertyMap
	     * @param arg
	     * @return
	     */
	    @Deprecated
	    public static Date getDateValue(Map propertyMap, String arg) {
	        Date value =null;
	        /*String strDate = (String) propertyMap.get(arg);
	        SimpleDateFormat sdf =  new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy",Locale.US);
	        try {
				Date date = sdf.parse(strDate);
				value = date.getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}*/
	        
			return value;
	    }
	    /**
		 * @param list
		 * @return
		 * @description 判断list 是否为空对象
		 * @version 1.0
		 * @author xiew
		 * @update 2012-10-15 上午11:54:31
		 */
		public static boolean listIsEmpty(List list){
			boolean flag = false;
			if(list!=null && list.size()!=0){
				flag = true;
			}
			return flag;
		}
		/**
		 * @param map
		 * @return
		 * @description 判断Map是否为空对象
		 * @version 1.0
		 * @author xiew
		 * @update 2012-10-15 上午11:54:55
		 */
		public static boolean mapIsEmpty(Map map){
			boolean flag = false;
			if(map!=null && map.size()!=0){
				flag = true;
			}
			return flag;
		}
		/**
		 * @param str
		 * @return
		 * @description 将字符串时间转换为Date
		 * @version 1.0
		 * @author xiew
		 * @update 2012-10-31 下午02:23:54
		 */
		public static Date getDateFromString(String str){
			Date value= null;
			if(!"".equals(str)){
		        SimpleDateFormat sdf =  new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy",Locale.US);
		        try {
					value = sdf.parse(str);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			return value;
		}
		/**
		 * 将map转换为string
		 * <pre>key 和value之间以'分割，每对键值对之间用^分割</pre>
		 * @param map
		 * @return
		 */
		public static String transMapToString(Map map){  
			  java.util.Map.Entry entry;  
			StringBuffer sb = new StringBuffer();  
			for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)  
			 {  
			 entry = (java.util.Map.Entry)iterator.next();  
			     sb.append(entry.getKey().toString()).append( "'" ).append(null==entry.getValue()?"":  
			      entry.getValue().toString()).append (iterator.hasNext() ? "^" : "");  
			  }  
			 return sb.toString();  
			}  
		/**
		 * 将字符串转为Map
		 * <pre>key 和value之间以'分割，每对键值对之间用^分割</pre>
		 * @param mapString
		 * @return
		 */
		public static Map transStringToMap(String mapString){  
			 Map map = new HashMap();  
			 java.util.StringTokenizer items;  
			 for(StringTokenizer entrys = new StringTokenizer(mapString, "^");entrys.hasMoreTokens();   
			    map.put(items.nextToken(), items.hasMoreTokens() ? ((Object) (items.nextToken())) : null))  
			      items = new StringTokenizer(entrys.nextToken(), "'");  
			  return map;  
		}
		
		/**
		 * 按照指定格式格式化时间，传空使用默认格式。如下：
		 * <pre>
		 * 格式：yyyy-MM-dd HH:mm:ss
		 * 结果：2013-10-23 14:34:54
		 * 以上为默认格式
		 * </pre>
		 * @param date
		 * @param pattern
		 * @return
		 */
		public static String formatDate(Date date,String pattern){
			if(pattern==null||pattern.trim().equals("")) pattern="yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		}
		/**
		 * 首字母转小写
		 * @param s
		 * @return
		 */
		public static String lowWord(String s){
			String s1 = s.substring(0,1);
			s1 = s1.toLowerCase();
			s = s1+(s.substring(1));
			return s;
		}
}