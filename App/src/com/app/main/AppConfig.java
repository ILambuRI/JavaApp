package com.app.main;

import com.xml.parser.XmlParser;

public class AppConfig {
	//(CheckConnection.java)
	public static final int WORKING_TIME_MINUTES = Integer.parseInt( AppConfig.getConstantFromXML("WORKING_TIME_MINUTES") );
	public static final int INTERVAL_TIME_SECONDS = Integer.parseInt( AppConfig.getConstantFromXML("INTERVAL_TIME_SECONDS") );
	
	//Start and end alerts (CheckConnection.java)
	public static final String TEXT_START_CHECK = AppConfig.getConstantFromXML("TEXT_START_CHECK");
	public static final String TEXT_END_CHECK = AppConfig.getConstantFromXML("TEXT_END_CHECK");
	
	//Alert if array is empty (Chatter.java)
	public static final String NO_RESULTS_YET = AppConfig.getConstantFromXML("NO_RESULTS_YET");
	
	//For result array in child thread (ChildeThread.java)
	public static final String NO_RESULT = AppConfig.getConstantFromXML("NO_RESULT");
	public static final String UNDEFINED = AppConfig.getConstantFromXML("UNDEFINED");
	public static final String HOST_AVAILABLE_TRUE = AppConfig.getConstantFromXML("HOST_AVAILABLE_TRUE");
	public static final String HOST_AVAILABLE_FALSE = AppConfig.getConstantFromXML("HOST_AVAILABLE_FALSE");
	
	//Text requests from the user (UserInputHandler.java)
	public static final String ENTER_ADDRESS = AppConfig.getConstantFromXML("ENTER_ADDRESS");
	public static final String ENTER_PRIORITY = AppConfig.getConstantFromXML("ENTER_PRIORITY");
	public static final String ENTER_ANOTHER = AppConfig.getConstantFromXML("ENTER_ANOTHER");
	public static final String ENTER_TYPE_OUTPUT = AppConfig.getConstantFromXML("ENTER_TYPE_OUTPUT");
	
	public static final String INVALID_ADDRESS = AppConfig.getConstantFromXML("INVALID_ADDRESS");
	public static final String INVALID_PRIORITY = AppConfig.getConstantFromXML("INVALID_PRIORITY");
	public static final String INVALID_TYPE = AppConfig.getConstantFromXML("INVALID_TYPE");
	
	private static String getConstantFromXML(String constName) {
		return XmlParser.getConstant("AppConfig.xml", constName);
	}
}
