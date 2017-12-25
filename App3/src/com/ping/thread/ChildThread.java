package com.ping.thread;

import java.util.ArrayList;

public class ChildThread extends Thread {
	private int cnt;
	private ArrayList<String> sets;
	private int checkInterval;
	
	public ChildThread (String name, ArrayList<String> sets, int checkInterval) {
		this.setName(name);
		this.checkInterval = checkInterval;
		
		if ( name.equalsIgnoreCase("Thread #1") ) {
			this.setPriority(MAX_PRIORITY);
		}
		
		this.sets = sets;
	}
	
	public String getStatus () {
		return "I am " + this.getName() + " whith priority " + this.getPriority() + " " + sets.get(0) + " whith " + cnt + " iterations";
	}
	
	@Override
	public void run() {
		do {
			try {
				cnt++;
				Thread.sleep(checkInterval);
			} catch (InterruptedException e) {
				return;
			}
		} while ( true );
	}
}
