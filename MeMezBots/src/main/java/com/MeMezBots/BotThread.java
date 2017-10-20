package com.MeMezBots;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.neovisionaries.ws.client.ProxySettings;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;

public class BotThread implements Runnable {// + "| " + (0 + (int)(Math.random() * App.maxBot)
	String localBotName = App.botName;
	String[] Proxy = { "103.240.100.106", "103.194.90.27", "103.213.236.122", "103.250.144.42", "103.252.186.212",
			"103.192.64.10", "103.238.202.66" };
	String[] proxyArray = {};

	public static String getRandom(String[] array) {
		int rnd = new Random().nextInt(array.length);
		return array[rnd];
	}

	WebSocketFactory factory = new WebSocketFactory();
	ProxySettings settings = factory.getProxySettings();
	WebSocket ws = null;

	void setproxy() {
		while (true) {
			try {
				// settings.setHost(getRandom(proxyArray));
				// settings.setPort(8080);
				String asd = getRandom(proxyArray);
				String[] temps = asd.split(":");
				/*
				 * int randomPort = Integer.parseInt(temps[0]); String randomIp = temps[1];
				 * settings.setHost(randomIp); settings.setPort(randomPort);
				 */
				// settings.setHost(asd);
				// settings.setPort(123);
				settings.setHost(temps[0]);
				settings.setPort(Integer.parseInt(temps[1]));
				Thread.sleep(200);

			} catch (Exception e) {
				System.out.println(settings.getHost() + ":" + settings.getPort() + " is not working you fuck.");
			} finally {
				return;
			}
		}
	}

	public void run() {

		Scanner inFile1 = null;
		try {
			inFile1 = new Scanner(new File("D:\\Users\\douglas2\\Desktop\\Proxt.txt"));
		} catch (FileNotFoundException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} // .useDelimiter(",\\s*");
		List<String> temps = new ArrayList<String>();
		String token1 = "";
		while (inFile1.hasNext()) {
			// find next line
			token1 = inFile1.next();
			temps.add(token1);
		}
		inFile1.close();
		proxyArray = temps.toArray(new String[0]);

		if (App.origin == null || App.serverIp == null) {
			return;
		}
		if (App.useProxy) {
			setproxy();
		}
		try {
			ws = factory.createSocket(App.serverIp);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		final ByteBuffer buf2 = ByteBuffer.allocate(1 + 2 * localBotName.length());//
		buf2.order(ByteOrder.LITTLE_ENDIAN);
		buf2.put(0, (byte) 0);
		for (int i = 0; i < localBotName.length(); i++) {
			short b = (short) localBotName.charAt(i);
			buf2.putShort(1 + i * 2, b);
		}

		final ByteBuffer buf3 = ByteBuffer.allocate(2 + 2 * App.botMsg.length());//
		buf3.order(ByteOrder.LITTLE_ENDIAN);
		int offset = 0;
		buf3.put(offset++, (byte) 99);
		buf3.put(offset++, (byte) 0);
		for (int i = 0; i < App.botMsg.length(); i++) {
			buf3.putShort(offset, (short) App.botMsg.charAt(i));
			offset += 2;
		}

		ws.addListener(new WebSocketAdapter() {
			@Override
			public void onDisconnected(WebSocket socket, WebSocketFrame serverCloseFrame,
					WebSocketFrame clientCloseFrame, boolean closedByServer)
					throws WebSocketException, IOException, InterruptedException {
				if (closedByServer) {
					System.out.println("Thread: " + Thread.currentThread().getId() + "'s get deteched by server");
					App.bots--;
					Thread.sleep(1000);
					ws.recreate().connect();
					App.bots++;
				} else {
					System.out.println("Thread: " + Thread.currentThread().getId() + "Disconnect");
					App.bots--;
					Thread.sleep(1000);
					ws.recreate().connect();
					App.bots++;
					ws.sendBinary(new byte[] { (byte) 254, (byte) 1, (byte) 0, (byte) 0, (byte) 0 });
					ws.sendBinary(new byte[] { (byte) 255, (byte) 114, (byte) 97, (byte) 103, (byte) 79 });
					ws.sendBinary(buf2.array());
				}
				// run();
				// ws.recreate().connect();
			}
		});

		ws.addHeader("Accept-Encoding", "gzip, deflate");
		ws.addHeader("Accept-Language", "en-US,en;q=0.8");
		ws.addHeader("Cache-Control", "no-cache");
		ws.addHeader("Connection", "Upgrade");
		// __cfduid=dca23eb0746aa651d87e6e7e65b06182b1494077994
		ws.addHeader("Cookie", "__cfduid=dca23eb0746aa651d87e6e7e65b06182b1494077994");
		ws.addHeader("Pragma", "no-cache");
		ws.addHeader("Host", App.serverIp);
		// ws.addHeader("Host", "agarioforums.io:451");
		ws.addHeader("Origin", App.origin);
		ws.addHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");

		try {
			ws.connect();
		} catch (WebSocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		/*
		 * ws.sendBinary(new byte[] {(byte) 254,(byte) 1,(byte) 0,(byte) 0,(byte) 0});
		 * ws.sendBinary(new byte[] {(byte) 255,(byte) 114,(byte) 97,(byte) 103,(byte)
		 * 79});
		 */
		if (App.origin.equals("http://agar.bio/")) {
			ws.sendBinary(new byte[] { (byte) 254, (byte) 1, (byte) 0, (byte) 0, (byte) 0 });
			ws.sendBinary(new byte[] { (byte) 255, (byte) 114, (byte) 97, (byte) 103, (byte) 79 });
		}
		if (App.origin.equals("http://play.agario0.com/")) {
			ws.sendBinary(new byte[] { (byte) 254, (byte) 1, (byte) 0, (byte) 0, (byte) 0 });
			ws.sendBinary(new byte[] { (byte) 255, (byte) 114, (byte) 97, (byte) 103, (byte) 79 });
		}
		if (App.origin.equals("http://mgar.io/")) {
			ws.sendBinary(new byte[] { (byte) 254, (byte) 5, (byte) 0, (byte) 0, (byte) 0 });
			ws.sendBinary(new byte[] { (byte) 255, (byte) 109, (byte) 103, (byte) 97, (byte) 114 });
		}
		if (App.origin.equals("http://agarios.org/")) {
			ws.sendBinary(new byte[] { (byte) 254, (byte) 1, (byte) 0, (byte) 0, (byte) 0 });
			ws.sendBinary(new byte[] { (byte) 255, (byte) 35, (byte) 18, (byte) 56, (byte) 9 });
		}
		// ws.sendBinary(new byte[]{(byte)0,(byte) 66,(byte) 0,(byte) 111,(byte)
		// 0,(byte) 116,(byte) 0});
		ws.sendBinary(buf2.array());
		ByteBuffer buf = ByteBuffer.allocate(21);
		buf.order(ByteOrder.LITTLE_ENDIAN);

		class Respawn extends Thread {
			public void run() {
				ws.sendBinary(buf2.array());
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		class Split extends Thread {
			public void run() {
				// NettyServer netty = new NettyServer();
				while (true) {
					if (App.needSplit) {
						ws.sendBinary(new byte[] { (byte) 17 });
						ws.sendBinary(new byte[] { (byte) 56 });
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					// }
				}

			}
		}
		class Eject extends Thread {
			public void run() {
				// NettyServer netty = new NettyServer();
				while (true) {
					if (App.needSplit) {
						ws.sendBinary(new byte[] { (byte) 21 });
						ws.sendBinary(new byte[] { (byte) 36 });
						ws.sendBinary(new byte[] { (byte) 57 });
						ws.sendBinary(new byte[] { (byte) 22 });
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					// }
				}

			}
		}
		class Spam extends Thread {
			public void run() {
				// NettyServer netty = new NettyServer();
				while (true) {
					if (App.needSpam) {
						ws.sendBinary(buf3.array());
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					// }
				}

			}
		}
		(new Respawn()).start();
		(new Split()).start();
		(new Eject()).start();
		(new Spam()).start();

		while (true) {

			// ws.sendBinary(new byte[]{(byte)0,(byte) 66,(byte) 0,(byte) 111,(byte)
			// 0,(byte) 116,(byte) 0});
			// move packet
			// ws.sendBinary(buf2.array());
			// ws.sendBinary(buf3.array());

			if (App.origin.equals("http://agarioforums.io/")) {

				buf.put(0, (byte) 10);
				buf.putInt(1, (int) (NettyServer.yPos32 + Math.random() * 30 - 15));
				buf.putInt(5, (int) (NettyServer.xPos32 + Math.random() * 30 - 15));
				buf.putInt(9, (int) 0);

			} else {
				buf.put(0, (byte) 16);
				// System.out.println("Client.y: " + NettyServer.xPos64); +
				buf.putDouble(1, NettyServer.xPos64 + Math.random() * 300 - 150);// Math.random() * 30 - 15
																					// Math.random() * 10000 - 2000
				buf.putDouble(9, NettyServer.yPos64 + Math.random() * 300 - 150);// Math.random() * 30 - 15
				// System.out.println("Client.x: " + );NettyServer.yPos64 +
				buf.putInt(17, 0);
			}
			// System.out.println(buf.array());
			ws.sendBinary(buf.array());// NettyServer.TEST
			// ws.sendBinary(new byte[]{(byte)16,(byte) 53,(byte) 225,(byte) 7,(byte) 1,
			// (byte)215, (byte)108, (byte)196,(byte) 64, (byte)53, (byte)225,(byte) 7,
			// (byte)1, (byte)215, (byte)108, (byte)196, (byte)64, (byte)0, (byte)0,
			// (byte)0, (byte)0}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} /*
				 * else if(App.origin.equals("http://agarios.org/")) { ws.sendBinary(new byte[]
				 * {(byte)90, (byte) 176,(byte) 33, (byte) 231,(byte) 85}); }
				 */
			buf.clear();
		}

	}

}
