package org.demo.common.script;

import org.demo.util.StringUtil;

public class Strings {
	public static boolean isBlank(String str){
		return StringUtil.isBlank(str);
		
	}
	
	public static boolean isDecimalExcludePlus(String str){
		return StringUtil.isDecimalExcludePlus(str);
	}
	
	public static boolean isWord(String str){
		return StringUtil.isWord(str);
	}
}
