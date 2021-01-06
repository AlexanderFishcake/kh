package com.collection.map.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesMain {
	
	public static void main(String[] args) {
		String fileName = "resources/config.properties";
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(prop);
	}
	

}
