package com.ping.model;

import java.util.HashMap;

import com.app.main.AppConfig;
import com.ping.helper.InetAddressHandler;

public class ChildThread extends Thread {
	private int cnt;
	private int checkInterval;
	private String address;
	private String priority;
	HashMap<String, String> threadInfo = new HashMap<>();
	InetAddressHandler inetAddress;
	
	public ChildThread (String name, HashMap<String, String> sets, int checkInterval) {
		this.setName(name);
		
		this.checkInterval = checkInterval;
		this.address = sets.get("address");
		this.priority = sets.get("priority");
		
		setThreadPriority();
		setDefaultThreadInfo();
		
		inetAddress = new InetAddressHandler(address);
	}
	
	@Override
	public void run() {
		do {
			try {				
				cnt++;
				fillThreadInfo();
				
				Thread.sleep(checkInterval);
			} catch (InterruptedException e) {
				return;
			}
		} while (true);
	}
	
	public HashMap<String, String> getStatus() {
		return threadInfo;
	}
	
	private void setThreadPriority() {
		switch (priority) {
			case "1": this.setPriority(Thread.MIN_PRIORITY);
			break;
			
			case "2": this.setPriority(Thread.NORM_PRIORITY);
			break;
			
			case "3": this.setPriority(Thread.MAX_PRIORITY);
			break;
		}
	}
	
	private void setDefaultThreadInfo() {
		threadInfo.put("threadName", this.getName());
		threadInfo.put("threadPriority", Integer.toString(this.getPriority()));
		threadInfo.put("userPriority", priority);
		threadInfo.put("iteration", Integer.toString(cnt));
		threadInfo.put("hostName", "(" + address + ")");
		threadInfo.put("hostIp", AppConfig.NO_RESULT);
		threadInfo.put("hostAvailable", AppConfig.NO_RESULT);
		threadInfo.put("timeStamp", Long.toString(System.currentTimeMillis() / 1000));
	}
	
	private void fillThreadInfo() {
		threadInfo.put("timeStamp", Long.toString(System.currentTimeMillis() / 1000));
		threadInfo.put("iteration", Integer.toString(cnt));
		
		String name = inetAddress.getName();
		if (!name.equals("")) {
			threadInfo.put("hostName", inetAddress.getName());
		} else {
			threadInfo.put( "hostName", AppConfig.UNDEFINED + "(" + address + ")" );
		}
		
		String ip = inetAddress.getIp();
		if (!ip.equals("")) {
			threadInfo.put("hostIp", inetAddress.getIp());
		} else {
			threadInfo.put("hostIp", AppConfig.UNDEFINED);
		}
		
		if (inetAddress.getAccess()) {
			threadInfo.put("hostAvailable", "Yes");
		} else {
			threadInfo.put("hostAvailable", "No");
		}
	}
}
