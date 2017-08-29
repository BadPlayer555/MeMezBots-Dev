package net.BadPlayer;

	import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;

	import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WriteCallback;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
	import org.eclipse.jetty.websocket.api.annotations.WebSocket;

	@WebSocket
	public class ToUpperClientSocket {

		private Session session;
		
		CountDownLatch latch= new CountDownLatch(1);

		@OnWebSocketMessage
		public void onText(Session session, String message) throws IOException {
			System.out.println("Message received from server:" + message);
		}
		
		
		
		@OnWebSocketMessage
		public void onBinary(Session session, byte buf[], int offset, int length){
			//System.out.println("Spam : " + buf);
		}
		
		@OnWebSocketClose
	    public void onClose(int statusCode, String reason)
	    {
	        System.out.printf("Connection closed for fuck say: %d - %s%n",statusCode,reason);
	        this.session = null;
	        latch.countDown(); // trigger latch
	    }

		@OnWebSocketConnect
		public void onConnect(Session session) {
			//System.out.println("Connected to server");
			this.session=session;
			latch.countDown();
		}
		
	    @OnWebSocketError
	    public void onError(Throwable t) {
	    	System.out.println(t);
	    }
		public void sendMessage(String str) {
			try {
				session.getRemote().sendString(str);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void sendMessage(ByteBuffer buf) throws IOException {
			/*session.getRemote().sendBytes(buf, new WriteCallback() {
                public void writeSuccess() {
                    //assertThat(true, is(true));
                }

                public void writeFailed(Throwable x) {
                    //assertThat(true, is(false));
                }
            });*/
			session.getRemote().sendBytes(buf);
		}
		
		public CountDownLatch getLatch() {
			return latch;
		}

	}


