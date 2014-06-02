package com.semeureka.frame.misc;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import org.apache.mina.transport.serial.SerialAddress;
import org.springframework.core.convert.converter.Converter;

public class StringToSocketAddress implements Converter<SocketAddress, String> {
	@Override
	public String convert(SocketAddress source) {
		if (source instanceof InetSocketAddress) {
			InetSocketAddress address = (InetSocketAddress) source;
			return address.getHostName() + ":" + address.getPort();
		} else if (source instanceof SerialAddress) {
			SerialAddress address = (SerialAddress) source;
			return address.getName();
		}
		throw new IllegalArgumentException();
	}
}
