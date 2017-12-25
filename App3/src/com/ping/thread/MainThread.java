package com.ping.thread;

import java.util.ArrayList;

public class MainThread extends Thread {
	private int threadNumber;
	private int checkInterval;
	private ArrayList< ArrayList<String> > settings;
	private static ArrayList<ChildThread> childTreads = new ArrayList<ChildThread>();
	
	public MainThread (ArrayList< ArrayList<String> > settings, int checkInterval) {
		this.settings = settings;
		this.checkInterval = checkInterval;
	}
	
	@Override
	public void run() {		
		int cntThreads = 1;
		for (ArrayList<String> element : settings) {
			childTreads.add( new ChildThread("Thread #" + cntThreads, element, checkInterval) );
			cntThreads++;
		}
		
		for (ChildThread child : childTreads) {
			child.start();
		}
	}
	
	public String getStatus() {
		String childStatus;
		
		if (childTreads.size() > 0) {
			childStatus = childTreads.get(threadNumber).getStatus();
		} else {
			childStatus = "No results yet!";
		}

		if ( ++threadNumber > (childTreads.size() - 1) ) {
			threadNumber = 0;
		}
		
		return childStatus;
	}
	
	public void closeThreads() {
		for (ChildThread child : childTreads) {
			child.interrupt();
		}	
	}
}