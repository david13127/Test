package com.tank;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2019年06月03日 23:05
 * @version V1.0
 */

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
	static Properties props = new Properties();

	static {
		try {
			props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Object get(String key) {
		if (props == null)
			return null;
		return props.get(key);
	}

	public static void main(String[] args) {
		System.out.println(PropertyMgr.get("initTankCount"));
	}
}