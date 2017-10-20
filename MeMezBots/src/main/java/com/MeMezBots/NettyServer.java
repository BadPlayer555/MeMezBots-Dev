package com.MeMezBots;

import java.io.IOException;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.neovisionaries.ws.client.WebSocketException;

public class NettyServer {
	public static double xPos64;
	public static double yPos64;
	
	public static int xPos32;
	public static int yPos32;

	///static String signal  = "split";
	static BotThread instance = new BotThread();
	
    public static void main(String[] args) throws InterruptedException {
    	System.out.println("");
    	System.out.println("_|      _|            _|      _|                      ");
    	System.out.println("_|_|  _|_|    _|_|    _|_|  _|_|    _|_|    _|_|_|_|  ");
    	System.out.println("_|  _|  _|  _|_|_|_|  _|  _|  _|  _|_|_|_|      _|    ");
    	System.out.println("_|      _|  _|        _|      _|  _|          _|     ");
    	System.out.println("_|      _|    _|_|_|  _|      _|    _|_|_|  _|_|_|_|  ");
    	System.out.println("");

        Configuration config = new Configuration();
        config.setHostname("localhost");
        System.out.println(" [Server] Server HostName: " + config.getHostname());
        
        
        config.setPort(8080);
        System.out.println(" [Server] Server Port: " + config.getPort());

        SocketIOServer server = new SocketIOServer(config);
        server.addEventListener("movement", ChatObject.class, new DataListener<ChatObject>() {
            public void onData(SocketIOClient client, ChatObject data, AckRequest ackRequest) throws InterruptedException {
                //server.getBroadcastOperations().sendEvent("chatevent", data);
            	String xPos = data.x;
            	String yPos = data.y;
            	int byteLength = data.byteLength;
            	
            	if(byteLength == 21){
            		xPos64 = Double.parseDouble(xPos);
            		//System.out.println(xPos64);
            		yPos64 = Double.parseDouble(yPos);
            		//System.out.println(yPos64);
            	}
            	
            	if(byteLength == 13){
            		xPos32 = Integer.parseInt(xPos);
            		//System.out.println(xPos64);
            		yPos32 = Integer.parseInt(yPos);
            	}
            	//System.out.println(xPos);
            	//System.out.println(yPos);
            	
            	//System.out.println(byteLength);
            }
        });
        
        server.addEventListener("start", ChatObject.class, new DataListener<ChatObject>() {
            public void onData(SocketIOClient client, ChatObject data, AckRequest ackRequest) throws WebSocketException, IOException, InterruptedException {
                //server.getBroadcastOperations().sendEvent("chatevent", data);
            	App.serverIp = data.ip;
            	App.origin = data.origin;
            	App.start();
            	System.out.println(" [Server] Client request bots on ws : "+App.serverIp);
            	System.out.println(" [Server] Client request bots on orgin : "+App.origin);
            }
        });
        
        server.addEventListener("split", ChatObject.class, new DataListener<ChatObject>() {
            public void onData(SocketIOClient client, ChatObject data, AckRequest ackRequest) throws WebSocketException, IOException, InterruptedException {
                //server.getBroadcastOperations().sendEvent("chatevent", data);
            	System.out.println("split");
            	//App.needSplit = true;
            	//instance.notifyAll();
            	App.needSplit = true;
            	Thread.sleep(65);
            	App.needSplit = false;
            	//Thread.sleep(500);
            	//App.needSplit = false;
            }
        });
        
        server.addDisconnectListener(new DisconnectListener() {
            public void onDisconnect(SocketIOClient client) {

             System.out.println(" [Server] User Disconnected");
             //server.getBroadcastOperations().sendJsonObject(data);

            };
        });
        
        server.addEventListener("stop", ChatObject.class, new DataListener<ChatObject>() {
            public void onData(SocketIOClient client, ChatObject data, AckRequest ackRequest) throws WebSocketException, IOException, InterruptedException {
                //server.getBroadcastOperations().sendEvent("chatevent", data);
            	System.exit(0);
            }
        });
        
        server.addConnectListener(new ConnectListener() {
            public void onConnect(SocketIOClient client) {

             System.out.println(" [Server] User Connected");
             //server.getBroadcastOperations().sendJsonObject(data);

            }
        });
        
        server.addEventListener("eject", ChatObject.class, new DataListener<ChatObject>() {
            public void onData(SocketIOClient client, ChatObject data, AckRequest ackRequest) throws InterruptedException {
                //server.getBroadcastOperations().sendEvent("chatevent", data);
            	App.needEject = true;
            	Thread.sleep(65);
            	App.needEject = false;
            	//System.out.println(xPos);
            	//System.out.println(yPos);
            	//System.out.println(byteLength);
            }
        });
        
        server.addEventListener("spam", ChatObject.class, new DataListener<ChatObject>() {
            public void onData(SocketIOClient client, ChatObject data, AckRequest ackRequest) throws InterruptedException {
                //server.getBroadcastOperations().sendEvent("chatevent", data);
            	App.needSpam = true;
            	Thread.sleep(65);
            	App.needSpam = false;
            	//System.out.println(xPos);
            	//System.out.println(yPos);
            	//System.out.println(byteLength);
            }
        });
        server.start();
        //server.startAsync();
        System.out.println(" [Server] Server is now starting");
        while(true){
        server.getBroadcastOperations().sendEvent("botCount", App.bots);
        Thread.sleep(2000);
        }

        

    }

}
