package com.MeMezBots;

public class BotCounting extends Thread{

	public void run() {
		
		while(true){
		//System.out.println("actived Thread : " + ManagementFactory.getThreadMXBean().getThreadCount());
	    //System.out.println("Current Bots online: " + App.bots);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
}
