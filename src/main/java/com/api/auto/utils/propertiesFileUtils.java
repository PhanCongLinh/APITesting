package com.api.auto.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class propertiesFileUtils {

	private static String input_path = "E:/Eclipse_workspace/ASM3/configuration/configs.properties";
	private static String token_path = "E:/Eclipse_workspace/ASM3/configuration/token.properties";
	public static String getProperties(String key) {
		Properties pro = new Properties();
		FileInputStream input = null;
		String value = null;
		try {
			input = new FileInputStream(input_path);
			pro.load(input);
			value = pro.getProperty(key);
		} catch (Exception e) {
			System.out.println("Xảy ra lỗi khi đọc giá trị của " + key);
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}

	public static void setToken(String key, String value) {
		Properties pro = new Properties();
		FileOutputStream output = null;
		try {
			output = new FileOutputStream(token_path);
			pro.setProperty(key, value);
			pro.store(output, "Set new value");
		} catch (Exception e) {
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static String getToken(){
		String token = null;
		Properties pro = new Properties();
		FileInputStream input = null;
		try {
			input = new FileInputStream(token_path);
			pro.load(input);
			token = pro.getProperty("token");
		}catch(Exception e) {
			System.out.print("Get token fail!");
			e.printStackTrace();
		}
		return token;
	}
}
