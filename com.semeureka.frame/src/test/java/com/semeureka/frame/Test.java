package com.semeureka.frame;

import java.net.InetSocketAddress;

public class Test {
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
		InetSocketAddress address = new InetSocketAddress("211.103.158.122", 8810);
//		System.out.println(System.currentTimeMillis());
//		System.out.println(address.getHostName());
		System.out.println(System.currentTimeMillis());
		System.out.println(address.getAddress().getHostAddress());
		System.out.println(System.currentTimeMillis());
	}
}
