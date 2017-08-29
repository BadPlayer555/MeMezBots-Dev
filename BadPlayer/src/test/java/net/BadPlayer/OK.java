package net.BadPlayer;

import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

public class OK {
	public void LOLOLOL(){
	 System.out.println( "Hello World!" );
     String dest = "ws://localhost:8080/jsr356toUpper";
             WebSocketClient client = new WebSocketClient();
             try {
                  
                 ToUpperClientSocket socket = new ToUpperClientSocket();
                 client.start();
                 URI echoUri = new URI(dest);
                 ClientUpgradeRequest request = new ClientUpgradeRequest();
                 client.connect(socket, echoUri, request);
                 socket.getLatch().await();
                 ByteBuffer buf = ByteBuffer.allocate(5);
                 buf.put(0, (byte)254);
                 buf.order(ByteOrder.LITTLE_ENDIAN);
                 buf.putInt(1, 5);
                 socket.sendMessage(buf);
                 Thread.sleep(10000l);
                 String name = "BadPlayer";
                 ByteBuffer buf1 = ByteBuffer.allocate(1 + 2*name.length());
                 buf1.put(0, (byte)0);
                 for (int i=0;i<name.length();i++) {
                 	buf1.order(ByteOrder.LITTLE_ENDIAN);
                     buf.putShort(1 + i*2 ,(short)name.charAt(i));
                 }
      
             } catch (Throwable t) {
                 t.printStackTrace();
             } finally {
                 try {
                     client.stop();
                 } catch (Exception e) {
                     e.printStackTrace();
                 }

             }

	}
}
