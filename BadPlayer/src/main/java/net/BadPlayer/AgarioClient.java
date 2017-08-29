package net.BadPlayer;

import java.net.HttpCookie;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.websocket.api.UpgradeException;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

public class AgarioClient {
	
	private static ToUpperClientSocket socket = new ToUpperClientSocket();
	private static WebSocketClient client = new WebSocketClient();
	private static ClientUpgradeRequest request = new ClientUpgradeRequest();
	
	public static void connectshit(String dest) throws Exception{
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
            URI echoUri = new URI(dest);
            client.connect(socket, echoUri, request);
            socket.getLatch().await();
            
            /*ByteBuffer buf = ByteBuffer.allocate(5);
            buf.order(ByteOrder.LITTLE_ENDIAN);
            buf.put(0, (byte)254);
            buf.putInt(1, Integer.parseUnsignedInt("1"));
            //buf.wrap(new byte[] {(byte) 254, 1, 0, 0, 0});
            for(int i = 0; i < 5; i++){
            	System.out.println(buf.get(i) & 0xff);
            }
            socket.sendMessage(buf);*/
            
            socket.sendMessage(ByteBuffer.wrap(new byte[] {(byte) 254,(byte) 1,(byte) 0,(byte) 0,(byte) 0}));
            //System.out.println("Sent buf0");
            
            /*ByteBuffer buf1 = ByteBuffer.allocate(5);
        	buf1.order(ByteOrder.LITTLE_ENDIAN);
        	buf1.put(0, (byte)255);
        	buf1.putInt(1, Integer.parseUnsignedInt("1332175218"));
        	for(int i = 0; i < 5; i++){
            	System.out.println(buf1.get(i) & 0xff);
            }
        	socket.sendMessage(buf1);*/
            
        	//System.out.println("Sent buf1");
        	socket.sendMessage(ByteBuffer.wrap(new byte[] {(byte) 255,(byte) 114,(byte) 97,(byte) 103,(byte) 79}));
	}
	
	public static void spawnshit(String name){
        try {
            /*ByteBuffer buf2 = ByteBuffer.allocate(1 + 2*name.length());//
            buf2.order(ByteOrder.LITTLE_ENDIAN);
            buf2.put(0, (byte)0);
            for (int i=0;i<name.length();i++) {
            	short b = (short)name.charAt(i);
                buf2.putShort(1 + i*2 , b);
            }
            //buf2.wrap(new byte[] {(byte) 0, 84, 0, 101, 0, 115, 0, 116, 0});
            for (int i=0;i<1 + 2 * name.length();i++) {
            	System.out.println(buf2.get(i) & 0xFF);
            }
            socket.sendMessage(buf2);*/
        	socket.sendMessage(ByteBuffer.wrap(new byte[]{(byte)0,(byte) 66,(byte) 0,(byte) 111,(byte) 0,(byte) 116,(byte) 0}));
            //System.out.println("Sended buf2");
 
        } catch (Throwable t) {
            t.printStackTrace();
        }
	}
	
	public static void movetoshit(){
		/*ByteBuffer buf3 = ByteBuffer.allocate(21);
		buf3.order(ByteOrder.LITTLE_ENDIAN);
		buf3.put(0, (byte)16);
		buf3.putDouble(1, (double)10000);
		buf3.putDouble(1, (double)10000);
		buf3.putInt(17, Integer.parseUnsignedInt("0"));
		System.out.println("sent buf3");
		socket.sendMessage(buf3);*/
		socket.sendMessage(ByteBuffer.wrap(new byte[] {(byte)16,(byte) 53,(byte) 225,(byte) 7,(byte) 1, (byte)215, (byte)108, (byte)196,(byte) 64, (byte)53, (byte)225,(byte) 7, (byte)1, (byte)215, (byte)108, (byte)196, (byte)64, (byte)0, (byte)0, (byte)0, (byte)0}));
	}

}
