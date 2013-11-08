package org.demo.plugin.exchange.protocol.transport.http;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.demo.plugin.exchange.base.ProtectInputStream;

import sun.net.www.http.HttpClient;

public class HTTPProtocol {
	private final static Integer PORT=8080;
	private final static String SERVLET_NAME = "exchangeServlet";
	public void send(String str){
		URL url = null;
		HttpURLConnection connection=null;
		try {
			url =new URL(getURL("127.0.0.1"));
			//打开连接
			connection = (HttpURLConnection) url.openConnection();
			//设置请求方式：POST/GET
			connection.setRequestMethod("POST");
            connection.setDoInput(true);
            //设置是否向connection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true
            connection.setDoOutput(true);
            connection.setChunkedStreamingMode(8192 * 2);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //进行连接
            connection.connect();
            OutputStream ops = null;
            
            try {
            	ops = new BufferedOutputStream(connection.getOutputStream(),8192);
				ops.write(str.getBytes("UTF-8"));
				ops.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				 ops.close();
			}
			
			ProtectInputStream pis = new ProtectInputStream(connection.getInputStream());
			System.out.println(toString(pis.getInputStream(),"UTF-8"));
			pis.trueClose();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			connection.disconnect();
		}
	}
	
	public void server(InputStream in,OutputStream ou){
		System.out.println(toString(in,"UTF-8"));
		try {
			ou.write("我是服务器".getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				ou.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String toString(InputStream in,String encoding){
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
		  
        byte[] data = new byte[1024];  
  
        int count = -1;  
  
        try {
			while ((count = in.read(data, 0, 1024)) != -1)
			    outStream.write(data, 0, count);
			data = null;  
	        return new String(outStream.toByteArray(), encoding);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
	protected String getURL(String ip){
        StringBuilder builder = new StringBuilder();
        builder.append("HTTP://" + ip + ":"+PORT+"/Demos" + "/" +SERVLET_NAME);
        return builder.toString();
    }
	public static void main(String[] args) {
		HTTPProtocol protocol = new HTTPProtocol();
		protocol.send("请问你是谁？");
	}
}
