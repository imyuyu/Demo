package org.demo.plugin.exchange.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ExchangeUtil {
	private ExchangeUtil() {

	}

	/**
	 * ͳ尽量关闭所有可关闭的东西
	 * 传入参数时注意顺序
	 * @param objects
	 */
	public static void closeAll(Object... objects) {
		if (objects == null) {
			return;
		}
		for (Object obj : objects) {
			if (obj == null) {
				continue;
			}
			try {
				if (obj instanceof ResultSet) {
					((ResultSet) obj).close();
				} else if (obj instanceof Statement) {
					((Statement) obj).close();
				} else if (obj instanceof Connection) {
					((Connection) obj).close();
				} else if (obj instanceof Closeable) {
					((Closeable) obj).close();
				} else {
					throw new RuntimeException("无法识别的对象！obj=" + obj);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 拼接字符串
	 * @param objs
	 * @param flag 分隔符
	 * @return
	 */
	public static String arrayToString(Object[] objs, String flag) {
		if (objs == null || objs.length <= 0) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		for (Object obj : objs) {
			builder.append(obj == null ? "null" : obj.toString()).append(flag);
		}
		return builder.length() > 0 ? builder
				.substring(0, builder.length() - 1) : builder.toString();
	}

	/**
	 * 將對象轉為byte[]
	 * @param obj
	 * @return
	 */
	public static byte[] writObjectToByte(Serializable obj) {
		ByteArrayOutputStream bos = null;
		ObjectOutputStream oos = null;
		try {
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			return bos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			closeAll(bos, oos);
		}
	}
	
	/**
	 * 將流轉換為byte[]
	 * @param is
	 * @return
	 */
	public static byte[] readAllFromInsputStream(InputStream is) {
		ByteArrayOutputStream bos = null;
		try {
			bos = new ByteArrayOutputStream();
			byte[] bytes = new byte[8192];
			int len = 0;
			while ((len = is.read(bytes)) > 0) {
				bos.write(bytes, 0, len);
			}
			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeAll(bos);
		}
		return null;
	}
	
	public static Object readObjectByArray(byte[] b){
		Object obj = null;
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new ByteArrayInputStream(b));
			obj = ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
