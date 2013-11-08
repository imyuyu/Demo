package org.demo.util;

import java.util.List;

public class ListUtil {
	
	public static <T> void addAll(List<T> list, T... ts){
		if(ts != null){
			for (T t : ts) {
				list.add(t);
			}
		}
	}
}
