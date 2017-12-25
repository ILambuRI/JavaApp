package com.ping.app;

import java.util.ArrayList;

import com.ping.thread.MainThread;

public class —heck—onnection {
	private long startTime = System.currentTimeMillis() / 1000;
	private int durationMinutes = 1;
	private int checkInterval = 1 * 1000;
	
	public void play () {
		ArrayList<String> set1 = new ArrayList<>();
		set1.add("ya.ru");
		
		ArrayList<String> set2 = new ArrayList<>();
		set2.add("google.com");
		
		ArrayList<String> set3 = new ArrayList<>();
		set3.add("8.8.8.8");
		
		ArrayList<String> set4 = new ArrayList<>();
		set4.add("google.ru");
		
		ArrayList< ArrayList<String> > settings = new ArrayList<>();
		settings.add(set1);
		settings.add(set2);
		settings.add(set3);
		settings.add(set4);
		
		MainThread main = new MainThread(settings, checkInterval);
		main.start();
		
		System.out.println("I start checking the connection ...");
		
		do {
			System.out.println( main.getStatus() );
			
			try {
				Thread.sleep(checkInterval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while ( (System.currentTimeMillis() / 1000) <= (startTime + (durationMinutes * 60) - (checkInterval / 1000)) );
		
		main.closeThreads();
		main.interrupt();
				
		System.out.println("Finished check.");
	}

}
