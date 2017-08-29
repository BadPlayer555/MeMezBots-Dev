package net.BadPlayer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Hello world!
 *
 */
public class App 
{
	public static String serverIp = "ws://37.187.76.129:11022/";
    public static void main( String[] args ) throws Exception
    {
    	Thread t1 = new Thread(new BotsMultiThread(), "Thread1"); 
    	t1.start();
    	/*Thread t2 = new Thread(new BotsMultiThread(), "Thread2");
    	t2.start();
    	Thread t3 = new Thread(new BotsMultiThread(), "Thread3");
    	t3.start();
    	Thread t4 = new Thread(new BotsMultiThread(), "Thread4");
    	t4.start();
    	Thread t5 = new Thread(new BotsMultiThread(), "Thread5");
    	t5.start();*/
    	/*for(int i = 0; i < 50; i++){
    	AgarioClient.connectshit(serverIp);
    	System.out.println("Bot : " + i + "Connected");
    	AgarioClient.spawnshit("123");
    	AgarioClient.movetoshit();
    	Thread.sleep(200);
    	}
    	Timer timer = new Timer();
        TimerTask task = new TimerTask() {
        	public void run()
            {
                AgarioClient.spawnshit("123");
                
            }
        };
        timer.schedule(task, 0L ,1000L);*/
    	
    	
    }
}
