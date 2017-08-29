package net.BadPlayer;

import java.util.Timer;
import java.util.TimerTask;

public class BotsMultiThread implements Runnable{
	
	 public void run(){  
	    	try {
				AgarioClient.connectshit(App.serverIp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	System.out.println("Bot : " + "Connected");
	    	AgarioClient.spawnshit("123");
	    	AgarioClient.movetoshit();
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
	                AgarioClient.spawnshit("123");
	                AgarioClient.movetoshit();
	            }
	        };
	        timer.schedule(task, 0L ,1000L); 
	    }
	 

}
