package com.abd.mfc.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InitTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext_test.xml");

	}

}
