package com.ping.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInputValidator {
	
	public static boolean address(String userAddress) {
		Pattern pattern = Pattern.compile("^[\\w\\d-]+\\.[\\w\\d]{2,}$");
        Matcher match = pattern.matcher(userAddress);  
        
        return match.matches();
	}
	
	public static boolean priority(String priority) {
		Pattern pattern = Pattern.compile("^[1-3]{1}$");
        Matcher match = pattern.matcher(priority);
        
        return match.matches();
	}
	
	public static boolean type(String type) {
		Pattern pattern = Pattern.compile("^[1-2]{1}$");
        Matcher match = pattern.matcher(type);  
        
        return match.matches();
	}
}
