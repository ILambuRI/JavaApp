package com.ping.helper;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressHandler {
	private InetAddress address;
	private String host;
	
	public InetAddressHandler(String host) {
		this.host = host;
	}
	
	private InetAddress getInstance() {
		if (address == null) {
			try {
				this.address = InetAddress.getByName(host);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
		}
		
		return address;
	}
	
	public String getName() {
		if (getInstance() == null) {
			return "";
		}
		
		return getInstance().getHostName();
	}
	
	public String getIp() {
		if (getInstance() == null) {
			return "";
		}
		
		return getInstance().getHostAddress();
	}
	
	public boolean getAccess() {
		boolean access = false;
		
		if (getInstance() == null) {
			return access;
		}
		
		try {
			access = getInstance().isReachable(1000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
		return access;
	}
}
