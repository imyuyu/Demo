package org.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 10; i++) {
			
		
		SocketAddress address = new InetSocketAddress("127.0.0.1", 8081);
		SocketChannel sc = SocketChannel.open();
		sc.configureBlocking(false);
		Selector selector = Selector.open();
		// 注册连接服务端socket动作
		sc.register(selector, SelectionKey.OP_CONNECT);
		// 连接
		sc.connect(address);

		ByteBuffer buffer = ByteBuffer.allocate(1024);
		isOK:
		while(true){
			if(selector.select(1)==0){
				continue;
			}
		    Iterator iter = selector.selectedKeys().iterator();
		    while (iter.hasNext()) {
		    	SelectionKey key = (SelectionKey) iter.next();
		    	iter.remove();
		    	if (key.isConnectable()) {
		    		SocketChannel channel = (SocketChannel) key.channel();
			    	if (channel.isConnectionPending())
			    		channel.finishConnect();
			    		channel.write(buffer.wrap(("我是客户端:"+i).getBytes()));
			    		channel.register(selector, SelectionKey.OP_READ);
		    	} else if (key.isReadable()) {
		    		SocketChannel channel = (SocketChannel) key.channel();
					try {
						System.out.println(new String(SerializableUtil.getBytes(channel),"UTF-8"));
					} catch (IOException e) {
						System.out.println("发送成功！");
						break isOK;
					}
		    		channel.register(selector, SelectionKey.OP_WRITE);
		    	} else if(key.isWritable()){
		    		SocketChannel channel = (SocketChannel) key.channel();
		    		buffer.clear();
		    		channel.write(buffer.wrap("OK!".getBytes()));
		    		channel.register(selector, SelectionKey.OP_READ);
		    	}
		    }
		}
		}
	}
}
