package org.test;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


public class Server {
	private final static int port = 8081;
	private	ServerSocketChannel serverChannel;
	private ExecutorService exectorService;
	private ServerSocket socketServer;
	private Selector selector;
	
	public Server(){
		try {
			serverChannel = ServerSocketChannel.open();
			socketServer = serverChannel.socket();
			socketServer.bind(new InetSocketAddress(port));
			serverChannel.configureBlocking(false);
			selector = Selector.open();
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void service() throws IOException{
		exectorService = Executors.newFixedThreadPool(20);
		int threadCount = ((ThreadPoolExecutor)exectorService).getActiveCount();
		System.out.println("线程池启动，当前空闲线程："+(20-threadCount));
		while(true){
			if(selector.select(1)>0){
				execute(selector);
				//exectorService.execute(new Handler(selector));
			}
		}
	}
	
	public void registerClient(SocketChannel socketChannel){
		try {
			socketChannel.configureBlocking(false);
			// 设置此通道使用非阻塞模式    
			socketChannel.register(selector, SelectionKey.OP_READ); // 将这个通道注册到选择器上
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	public void execute(Selector selector){
		try {
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				Iterator<SelectionKey> iterators = selectionKeys.iterator();
				while(iterators.hasNext()){
					SelectionKey key = iterators.next();
					iterators.remove();
					SocketChannel socketChannel = null;
					//新的请求
					if(key.isAcceptable()){
						ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
						socketChannel = serverChannel.accept();
						Socket socket = socketChannel.socket();
						socketChannel.configureBlocking(false);
						SelectionKey another = socketChannel.register(selector,SelectionKey.OP_READ|SelectionKey.OP_WRITE);
						System.out.println(socket.getRemoteSocketAddress()+" [counection]");
					}else if(key.isReadable()){
						// 有消息进来
						socketChannel = (SocketChannel) key.channel();
						String msg=null;
						try {
							msg = new String(SerializableUtil.getBytes(socketChannel));
						} catch (IOException e) {
							System.out.println("发送成功！");
							return;
						}
						System.out.println(msg);
						if(msg.equals("OK!")){
							socketChannel.close();
							selector.selectNow();
							return;
						}
						SelectionKey wKey = socketChannel.register(selector,SelectionKey.OP_WRITE);
						wKey.attach("hello world");
					}else if(key.isWritable()){
						// 写消息到客户端
						socketChannel = (SocketChannel) key.channel();
						socketChannel.configureBlocking(false);
						ByteBuffer byteBuffer = ByteBuffer.allocate(1000);
						if(key.attachment() != null){
							socketChannel.write(byteBuffer.wrap(((String)key.attachment()).getBytes()));
						}else{
							socketChannel.write(byteBuffer.wrap("第一次回执".getBytes("UTF-8")));
						}
						SelectionKey wKey = socketChannel.register(selector,SelectionKey.OP_READ);
					}
				}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		new Server().service();
	}
	
}

class Handler implements Runnable{
	private Selector selector;
	
	public Handler(Selector selector){
		this.selector=selector;
	}
	
	public void run() {
		try {
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				Iterator<SelectionKey> iterators = selectionKeys.iterator();
				while(iterators.hasNext()){
					SelectionKey key = iterators.next();
					iterators.remove();
					SocketChannel socketChannel = null;
					//新的请求
					if(key.isAcceptable()){
						ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
						socketChannel = serverChannel.accept();
						Socket socket = socketChannel.socket();
						socketChannel.configureBlocking(false);
						SelectionKey another = socketChannel.register(selector,SelectionKey.OP_READ|SelectionKey.OP_WRITE);
						System.out.println(socket.getRemoteSocketAddress()+" [counection]");
					}else if(key.isReadable()){
						// 有消息进来
						socketChannel = (SocketChannel) key.channel();
						String msg=null;
						try {
							msg = new String(SerializableUtil.getBytes(socketChannel));
						} catch (IOException e) {
							System.out.println("发送成功！");
							return;
						}
						System.out.println(msg);
						if(msg.equals("OK!")){
							socketChannel.close();
							selector.selectNow();
							return;
						}
						SelectionKey wKey = socketChannel.register(selector,SelectionKey.OP_WRITE);
						wKey.attach("hello world");
					}else if(key.isWritable()){
						// 写消息到客户端
						socketChannel = (SocketChannel) key.channel();
						socketChannel.configureBlocking(false);
						ByteBuffer byteBuffer = ByteBuffer.allocate(1000);
						if(key.attachment() != null){
							socketChannel.write(byteBuffer.wrap(((String)key.attachment()).getBytes()));
						}else{
							socketChannel.write(byteBuffer.wrap("第一次回执".getBytes("UTF-8")));
						}
						SelectionKey wKey = socketChannel.register(selector,SelectionKey.OP_READ);
					}
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}