package com.ping.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInputValidator {
	
	public static boolean address(String userAddress) {
		Pattern addressPattern = Pattern.compile("^[\\w\\d-]+\\.[\\w\\d]{2,}$");
        Matcher addressMatch = addressPattern.matcher(userAddress);  
        
        if (addressMatch.matches()) {
        	return true;
        }
        
        Pattern ipPattern = Pattern.compile("\\b(?:(?:2(?:[0-4][0-9]|5[0-5])|[0-1]?[0-9]?[0-9])\\.){3}(?:(?:2([0-4][0-9]|5[0-5])|[0-1]?[0-9]?[0-9]))\\b");
        Matcher ipMatch = ipPattern.matcher(userAddress);  
        
        if (ipMatch.matches()) {
        	return true;
        }
        
        return false;
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
