package net.BadPlayer;

import java.io.IOException;
import java.net.HttpCookie;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

public class BotsMultiThread implements Runnable{
	
	
	
    private static ToUpperClientSocket socket = new ToUpperClientSocket();
	private static WebSocketClient client = new WebSocketClient();
	private static ClientUpgradeRequest request = new ClientUpgradeRequest();
	
	 public void run(){  
	    	try {
	    		System.out.println(Thread.currentThread().getName() + "is running");
	    		//TODO:Setting Method
	        	request.setMethod("GET");
	        	
	            //TODO:setting header
	            request.setHeader("Accept-Encoding", "gzip, deflate");
	            request.setHeader("Accept-Language", "en-US,en;q=0.8");
	            request.setHeader("Cache-Control", "no-cache");
	            request.setHeader("Origin", "http://agar.bio/");
	            request.setHeader("Pragma", "no-cache");
	            //request.setHeader("Sec-WebSocket-Extensions", "permessage-deflate; client_max_window_bits");
	            //request.setHeader("Sec-WebSocket-Version", "13");
	            request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
	            
	            //TODO:setting cookie
	            HttpCookie cookie1 = new HttpCookie("__cfduid","d85ddcd62a7297dc7296a1aca02f969c91494077994");
	            List<HttpCookie> cookies = new ArrayList<HttpCookie>();
	            cookies.add(cookie1);
	            request.setCookies(cookies);
	            
	            client.start();
	            URI echoUri = new URI(App.serverIp);
	            client.connect(socket, echoUri, request);
	            socket.getLatch().await();
	            socket.sendMessage(ByteBuffer.wrap(new byte[] {(byte) 254,(byte) 1,(byte) 0,(byte) 0,(byte) 0}));
	        	socket.sendMessage(ByteBuffer.wrap(new byte[] {(byte) 255,(byte) 114,(byte) 97,(byte) 103,(byte) 79}));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	System.out.println("Bot : " + "Connected");
	    	try {
				socket.sendMessage(ByteBuffer.wrap(new byte[]{(byte)0,(byte) 66,(byte) 0,(byte) 111,(byte) 0,(byte) 116,(byte) 0}));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	try {
				socket.sendMessage(ByteBuffer.wrap(new byte[] {(byte)16,(byte) 53,(byte) 225,(byte) 7,(byte) 1, (byte)215, (byte)108, (byte)196,(byte) 64, (byte)53, (byte)225,(byte) 7, (byte)1, (byte)215, (byte)108, (byte)196, (byte)64, (byte)0, (byte)0, (byte)0, (byte)0}));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	Timer timer = new Timer();
	        TimerTask task = new TimerTask() {
	        	public void run()
	            {
	            	try {
						socket.sendMessage(ByteBuffer.wrap(new byte[]{(byte)0,(byte) 66,(byte) 0,(byte) 111,(byte) 0,(byte) 116,(byte) 0}));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        		try {
						socket.sendMessage(ByteBuffer.wrap(new byte[] {(byte)16,(byte) 53,(byte) 225,(byte) 7,(byte) 1, (byte)215, (byte)108, (byte)196,(byte) 64, (byte)53, (byte)225,(byte) 7, (byte)1, (byte)215, (byte)108, (byte)196, (byte)64, (byte)0, (byte)0, (byte)0, (byte)0}));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        };
	        timer.schedule(task, 0L ,1000L); 
	    	
	    }
	 

}
