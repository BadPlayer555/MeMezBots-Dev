package com.MeMezBots;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.neovisionaries.ws.client.WebSocketException;


	/**
	 * Hello world!
	 *
	 */
	public class App 
	{
		public static boolean shutdown = false;
		static int maxBot = 500;
		
		public static String botName = "MoreBots.tk";
		public static String botMsg = "Fucking You";
		public static String[] proxyArray = null;
		public static boolean useProxy = true;
		public static String serverIp = null;
		public static String origin = null;
		public static boolean needSplit = false;
		public static boolean needEject = false;
		public static boolean needSpam = false;
		public static int bots = 0;
		static ExecutorService mySampleExecutor = Executors.newFixedThreadPool(maxBot);
		
		
	    public static void start() throws WebSocketException, IOException, InterruptedException
	    {
	    	Scanner inFile1 = new Scanner(new File("D:\\Users\\douglas2\\Desktop\\100% Not virus 1!!11!!\\MEMEMEEMEMEMEME\\Proxy.txt"));//.useDelimiter(",\\s*");
	    	List<String> temps = new ArrayList<String>();
	    	String token1 = "";
	    	while (inFile1.hasNext()) {
	    	      // find next line
	    	      token1 = inFile1.next();
	    	      temps.add(token1);
	    	    }
	    	inFile1.close();
	    	proxyArray = temps.toArray(new String[0]);

	        /*for (String s : proxyArray) {
	          System.out.println(s);
	        }*/
	        int i = 0;
	        (new BotCounting()).start();
	        while (i <= maxBot) {
	            mySampleExecutor.execute(new BotThread());
	            bots++;
	            i++;
	        }
	        
	    	
	    }

		
	

}
