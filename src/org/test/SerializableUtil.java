package org.test;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractInterruptibleChannel;

public class SerializableUtil{
	private static final int BUFFER_SIZE=1024;
	/**
	 * 将byte数组转为文件
	 * @param path
	 * @param b
	 * @return
	 */
	public static File toFile(String path,byte[] b){
		File file = new File(path);
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		try {
			createNewFile(file);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(b, 0, b.length);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(fos,bos);
		}
		return file;
	}
	
	public static byte[] toBytes(File file){
		if(file == null && !file.exists())
			return null;
		byte b[] = new byte[BUFFER_SIZE];
		ByteArrayOutputStream bos = null;
		FileInputStream fis = null;
		try {
			bos = new ByteArrayOutputStream(BUFFER_SIZE);
			fis = new FileInputStream(file);
			int i = 0;
			while((i = fis.read())>0){
				bos.write(b, 0, b.length);
			}
			return bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static byte[] toBytesByNio(File file){
		if(file == null || !file.exists())return null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			FileChannel fc = fis.getChannel();
			ByteBuffer b = ByteBuffer.allocate(BUFFER_SIZE);
			fc.read(b);
			return b.array();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 服务器接收文件
	 * @param socketChannel 客户端请求
	 * @param file
	 * @throws IOException 
	 * @throws IOException
	 */
	public static byte[] getBytes(SocketChannel socketChannel) throws IOException {
        ByteArrayOutputStream bas = new ByteArrayOutputStream();  
        byte[] bytes = new byte[BUFFER_SIZE]; 
        try { 
            ByteBuffer buffer = ByteBuffer.allocateDirect(BUFFER_SIZE);
            int count = 0;  
            while ((count = socketChannel.read(buffer)) > 0 && count != -1) {  
            	buffer.flip();  
            	bytes = new byte[count];  
            	buffer.get(bytes);  
            	bas.write(bytes);  
                buffer.clear();  
            }  
            bytes = bas.toByteArray();  
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {  
            close(bas);  
        } 
		return bytes;
    }
	
	
    /**
     * 把byte[]转为对象
     * @param objBytes
     * @return
     * @throws Exception
     */
    public static Object getObjectFromBytes(byte[] objBytes) throws Exception {
        if (objBytes == null || objBytes.length == 0) {
            return null;
        }
        ByteArrayInputStream bi = new ByteArrayInputStream(objBytes);
        ObjectInputStream oi = new ObjectInputStream(bi);
        return oi.readObject();
    }

    
    /**
     * 将对象转为byte數組
     * @param obj
     * @return
     * @throws Exception
     */
    public static byte[] getBytesFromObject(Serializable obj) throws Exception {
        if (obj == null) {
            return null;
        }
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(obj);
        return bo.toByteArray();
    }
	/**
	 *  判断文件是否存在，如果不存在则创建新的文件，如果是目录，则创建目录
	 * @param file 
	 * @return
	 * @throws IOException
	 */
	public static boolean createNewFile(File file) throws IOException{
		boolean flag = false;
		if(file.exists()){//如果文件存在
			file.delete();
		}
		//文件创建
		if(file.isDirectory()){//如果是目录
			flag = file.mkdirs();
		}else
			flag = file.createNewFile();
		return flag;
	}
	
	/**
	 * 关闭一切可以关闭的
	 * @param obj
	 */
	public static void close(Object... obj){
		try {
			for (Object object : obj) {
				if(object != null){
					if(object instanceof OutputStream) ((OutputStream)object).close();
					if(object instanceof InputStream) ((InputStream)object).close();
					if(object instanceof Reader) ((Writer)object).close();
					if(object instanceof Writer) ((Writer)object).close();
					if(object instanceof AbstractInterruptibleChannel) ((AbstractInterruptibleChannel)object).close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
