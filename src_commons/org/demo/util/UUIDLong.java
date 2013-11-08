package org.demo.util;

import java.util.UUID;


/**
 * UUID生成工具
 * @author huzy
 *
 */
public class UUIDLong{
	
    /**
     * 生成随机UUID
     * @return
     */
    public static long longUUID() {
    	return UUID.randomUUID().getMostSignificantBits();
    }
    
    /**
     * 生成非负数的UUID
     * @return
     */
    public static long absLongUUID() {
		for (;;) {
		    long r = longUUID();
		    if (r > 0L)
			return r;
		}
    }
}
