package com.ping.view;

import java.util.ArrayList;
import java.util.HashMap;

import com.app.main.AppConfig;

public class Chatter {
	
	
	public static void say(Object words) {
		System.out.print(words);
	}
	
	public static void sayLn(Object words) {
		System.out.println(words);
	}
	
	public static void sayOneThreadStatusLn(HashMap<String, String> status) {
		if (status.size() < 1) {
			System.out.println(AppConfig.NO_RESULTS_YET);
		} else {
			System.out.println(
				"Thread#" + status.get("threadName") + " (Priority: " + status.get("userPriority") + ")" +
				" --- " + status.get("hostName") + " (" + status.get("hostIp") + ")" +
				" --- TimeStamp: " + status.get("timeStamp") +
				" --- Available: " + status.get("hostAvailable")
			);
		}
	}
	
	public static void sayAllThreadStatusLn(ArrayList< HashMap<String, String> > statuses) {
		if (statuses.size() < 1) {
			System.out.println(AppConfig.NO_RESULTS_YET);
		} else {
			for (HashMap<String, String> status : statuses) {
				System.out.println(
					"Thread#" + status.get("threadName") + " (Priority: " + status.get("userPriority") + ")" +
					" --- " + status.get("hostName") + " (" + status.get("hostIp") + ")" +
					" --- TimeStamp: " + status.get("timeStamp") +
					" --- Available: " + status.get("hostAvailable")
				);
			}
		}
	}
}
