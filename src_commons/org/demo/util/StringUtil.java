package org.demo.util;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;

public class StringUtil {
	public static final String REGEX_DIGIT ="^([-]?[\\d]*)[\\d]+$";
	public static final String REGEX_WORD  = "^[\\w]+$";
	public static final String REGEX_I18NKey  = "^[\\w.-]+$";
	public static final String REGEX_NOTCHARACTER = "[\\x00-\\xff]*[^\\x00-\\xff]+[\\x00-\\xff]*";
	public static final String REGEX_DECIMAL_EXCLUDEPLUS ="^([-]?)([\\d]*)([.]?)(\\d+)$";
	/**
	 * The number of bytes in a kilobyte.
	 */
	public static final long ONE_KB = 1024;

	/**
	 * The number of bytes in a megabyte.
	 */
	public static final long ONE_MB = ONE_KB * ONE_KB;

	/**
	 * The number of bytes in a gigabyte.
	 */
	public static final long ONE_GB = ONE_KB * ONE_MB;
	
	/**
	 * 检测是否是空字符串, 不允许空格
	 * 
     * <pre>
     * Strings.isBlank(null)      = true
     * Strings.isBlank("")        = true
     * Strings.isBlank(" ")       = true
     * Strings.isBlank("bob")     = false
     * Strings.isBlank("  bob  ") = false
     * </pre>
     * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0)
			return true;
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i)))
				return false;
		}
		return true;
	}
	/**
	 * 检测是否不是空字符串, 不允许空格
	 * 
     * <pre>
     * Strings.isNotBlank(null)      = false
     * Strings.isNotBlank("")        = false
     * Strings.isNotBlank(" ")       = false
     * Strings.isNotBlank("bob")     = true
     * Strings.isNotBlank("  bob  ") = true
     * </pre>
     * 
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}
	/**
	 * 检测是否为整形数字
	 * 
	 * <pre>
	 * Strings.isDigits("1234")    = true
	 * Strings.isDigits("a123")    = false
	 * Strings.isDigits(" 123")    = false
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDigits(String str){
		return Pattern.matches(REGEX_DIGIT, str);
	}
	
	/**
	 * 检测是否为纯单词，只有数字、字母、下划线
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isWord(String str){
		return Pattern.matches(REGEX_WORD, str);
	}
	
	/**
	 * 是否是标准的国际化资源的key，有数字/字母/下划线/点／横线组成
	 * 
	 * @param key
	 * @return
	 */
	public static boolean isI18NKey(String key){
		if(isBlank(key)){
			return false;
		}
		
		return Pattern.matches(REGEX_I18NKey, key);
	}
	/**
	 * 转换成标准的路径，同时创建文件夹
	 * 
	 * @param filepath
	 * @param isCreate 是否创建文件夹
	 * @return
	 * @see getCanonicalPath(String)
	 */
	public static String getCanonicalPath(String filepath, boolean isCreate){
		if(isBlank(filepath)){
			return null;
		}
		String path = FilenameUtils.normalize(filepath);
		File f = new File(path);
		try {
			File fc = f.getCanonicalFile();
			
			if(isCreate){
				fc.mkdirs();
			}
			
			return fc.getAbsolutePath();
		}
		catch (IOException e) {
		}
		
		return filepath;
	}
	/**
	 * 得到站点的基础路径：http://office.seeyon.com:80
	 * 
	 * @param request
	 * @return
	 */
	public static String getBaseHref(HttpServletRequest request){
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	}
	
	/**
	 * 得到站点的基础路径+contextPath：http://office.seeyon.com:80/seeyon
	 * 
	 * @param request
	 * @return
	 */
	public static String getBaseContext(HttpServletRequest request){		
		return getBaseHref(request) + request.getContextPath();
	}
	/**
	 * 获取客户端访问的IP地址
	 * @param request
	 * @return
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	public static String toXmlStr(String srcStr)
	{
		String xml=srcStr;
		xml=xml.replaceAll("&","&amp;");
		xml=xml.replaceAll("<","&lt;");
		xml=xml.replaceAll(">","&gt;");		
		xml=xml.replaceAll("'","&apos;");
		xml=xml.replaceAll("\"","&quot;");
		return xml;
	}
	/**
	 * 判断是否包括汉字等非字符
	 * isIncludeNotCharacter("234ds,提供电所")=true
	 * isIncludeNotCharacter("234ds, ?\\ff3")=false
	 * @param str
	 * @return
	 */
	public static boolean isIncludeNotCharacter(String str) {
		if(str==null)
			return false;
		return Pattern.matches(REGEX_NOTCHARACTER, str);
	}
	/**
	 * 判断是否是数字，包括-号开始，小数点，小数部分；
	 * 整数部分能以0开始，小数部分能以0结尾，整个数字不能以.结尾；
	 * 主要用于表单数据校验
	 * 34342.02   true
	 * 02425.6    true
	 * 232.60     true
	 * 0.2323     true
	 * 00.325     true
	 * +656.32    false
	 * -233       true
	 * -.32       true
	 * .32        true
	 * @param str
	 * @return
	 */
	public static boolean isDecimalExcludePlus(String str){
		if(str == null)
			return false;
		return Pattern.matches(REGEX_DECIMAL_EXCLUDEPLUS, str);
	}
	/**
	 * 把一个大List截成多个小List
	 * 
	 * @param <T>
	 * @param list
	 * @param num 必须大于1
	 * @return
	 */
	public static <T> List<T>[] splitList(List<T> list, int num){
		if(isEmpty(list)){
			return new ArrayList[0];
		}
		if(num < 2){
			throw new IllegalArgumentException("Argument num [" + num + "] must greater then 2");
		}
		
		int length = (int)Math.ceil((double)list.size() / (double)num);
		List<T>[] result = new ArrayList[length];
		
		for (int i = 0; i < length; i++) {
			int first = i * num;
			int max = Math.min(list.size(), first + num);
			
			List<T> temp = list.subList(first, max);
			result[i] = new ArrayList<T>(temp);
		}
		
		return result;
	}
	/**
	 * 将不换行空格（NO-BREAK SPACE，Unicode 0x00a0，UTF-8编码：0xC2A0）替换为普通空格。 
	 * 用于避免因数据库字符集不兼容导致这个字符变为问号“?”的情况。
	 */
	public static String nobreakSpaceToSpace(String str) {
		if (str == null) {
			return null;
		}
		char nbsp = 0x00a0;
		return str.replace(nbsp, ' ');
	}
	/**
	 * 
	 * @param text
	 * @param isEscapeSpace 是否转换空格
	 * @return
	 */
	public static String toHTML(String text, boolean isEscapeSpace) {
		if (text == null || text.equals("")) {
			return "";
		}

		char content[] = new char[text.length()];
		text.getChars(0, text.length(), content, 0);
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < content.length; i++) {
			switch (content[i]) {
			case '\n':
				result.append("<br/>");
				break;
			case '\r':
				result.append("");
				break;
			case '<':
				result.append("&lt;");
				break;
			case '>':
				result.append("&gt;");
				break;
			case '&':
				result.append("&amp;");
				break;
			case '\'':
				result.append("&#039;");
				break;
			case '"':
				result.append("&quot;");
				break;
			case ' ':
				if(isEscapeSpace){
					result.append("&nbsp;");
				}
				else{
					result.append(content[i]);
				}
				break;
			default:
				result.append(content[i]);
			}
		}
		return result.toString();
	}
	/**
	 * 把带有\r\n\t等字符的清理掉，只保留文字；但不转换HTML的标签<br>
	 * 只适合于textarea输入的文本
	 * @param text
	 * @return
	 */
	public static String toText(String text) {
		if (text == null || text.equals("")) {
			return "";
		}

		char content[] = new char[text.length()];
		text.getChars(0, text.length(), content, 0);
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < content.length; i++) {
			switch (content[i]) {
			case '\n':
				break;
			case '\r':
				break;
			case '\t':
				break;
			case '\b':
				break;
			case '\f':
				break;
			default:
				result.append(content[i]);
			}
		}

		return result.toString();
	}
	/**
	 * 将文件大小格式化输出<br>
	 * 如： Strings.formatFileSize(123456L, false) --> 120.47 KB<br>
	 * Strings.formatFileSize(12345678L, false) --> 11.77 MB<br>
	 * Strings.formatFileSize(18L, true) --> 1 KB<br>
	 * Strings.formatFileSize(18L, false) --> 18 B<br>
	 * @param fileSize 单位为字节
	 * @param isUseMinValue 是否采用最小值显示，即：最小显示1KB
	 * @return
	 * @throws ArithmeticException  fileSize less than zero
	 */
	public static String formatFileSize(long fileSize, boolean isUseMinValue) {
		if (fileSize < 0) {
			throw new ArithmeticException("fileSize less than zero");
		}

		NumberFormat format = NumberFormat.getInstance();
		format.setMaximumFractionDigits(2);
		format.setMinimumFractionDigits(0);

		if (fileSize >= ONE_GB) {
			float filesize = fileSize / new Long(ONE_GB).floatValue();

			return format.format(filesize) + " GB";
		}
		else if (fileSize >= ONE_MB) {
			float filesize = fileSize / new Long(ONE_MB).floatValue();

			return format.format(filesize) + " MB";
		}
		else if (fileSize >= ONE_KB) {
			float filesize = fileSize / new Long(ONE_KB).floatValue();
			return format.format(filesize) + " KB";
		}
		else if (fileSize == 0) {
			return "0 KB";
		}
		else {
			if (isUseMinValue) {
				return "1 KB";
			}
			else {
				return format.format(fileSize) + " B";
			}
		}
	}
	/**
	 * 将字符串转换成Javascript，将对\r \n < > & 空格进行转换
	 * 
	 * @param text 
	 * @return
	 */
	public static String escapeJavascript(String str) {
		
		if (str == null) {
			return str;
		}		

		StringBuffer out = new StringBuffer();

		int sz;
		sz = str.length();
		for (int i = 0; i < sz; i++) {
			char ch = str.charAt(i);

			if (ch < 32) {
				switch (ch) {
				case '\b':
					out.append('\\');
					out.append('b');
					break;
				case '\n':
					out.append('\\');
					out.append('n');
					break;
				case '\t':
					out.append('\\');
					out.append('t');
					break;
				case '\f':
					out.append('\\');
					out.append('f');
					break;
				case '\r':
					out.append('\\');
					out.append('r');
					break;
				default:
					if (ch > 0xf) {
						out.append("\\u00" + hex(ch));
					}
					else {
						out.append("\\u000" + hex(ch));
					}
					break;
				}
			}
			else {
				switch (ch) {
				case '\'':
					out.append('\\');
					out.append('\'');
					break;
				case '"':
					out.append("\\\"");
					break;
				case '\\':
					out.append('\\');
					out.append('\\');
					break;
				case '/':
					out.append("\\/");
					break;
				default:
					out.append(ch);
					break;
				}
			}
		}

		return out.toString();
	}
	private static String hex(char ch) {
		return Integer.toHexString(ch).toUpperCase();
	}
	/**
	 * 检测是否是null字符串, 允许空格
	 * 
	 * <pre>
     * Strings.isEmpty(null)      = true
     * Strings.isEmpty("")        = true
     * Strings.isEmpty(" ")       = false
     * Strings.isEmpty("bob")     = false
     * Strings.isEmpty("  bob  ") = false
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
	/**
	 * 检测是否不是null字符串, 允许空格
	 * 
     * <pre>
     * Strings.isNotEmpty(null)      = false
     * Strings.isNotEmpty("")        = false
     * Strings.isNotEmpty(" ")       = true
     * Strings.isNotEmpty("bob")     = true
     * Strings.isNotEmpty("  bob  ") = true
     * </pre>
     * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	/**
	 * 检测集合是否有数据
     * 
	 * @param c
	 * @return
	 */
	public static boolean isEmpty(Collection c){
		return c == null || c.isEmpty();
	}
	
	public static boolean isNotEmpty(Collection c){
		return !isEmpty(c);
	}
	public static String substringAfter(String str, String separator) {
		if (isEmpty(str))
		    return str;
		if (separator == null)
		    return "";
		int pos = str.indexOf(separator);
		if (pos == -1)
		    return "";
		return str.substring(pos + separator.length());
	}
}
