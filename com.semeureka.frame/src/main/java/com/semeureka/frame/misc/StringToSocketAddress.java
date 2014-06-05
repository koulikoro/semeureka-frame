package com.semeureka.frame.misc;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import org.apache.mina.transport.serial.SerialAddress;
import org.apache.mina.transport.serial.SerialAddress.DataBits;
import org.apache.mina.transport.serial.SerialAddress.FlowControl;
import org.apache.mina.transport.serial.SerialAddress.Parity;
import org.apache.mina.transport.serial.SerialAddress.StopBits;
import org.springframework.core.convert.converter.Converter;

public class StringToSocketAddress implements Converter<String, SocketAddress> {
	@Override
	public SocketAddress convert(String source) {
		if (source.matches("\\d+\\.\\d+\\.\\d+\\.\\d+:\\d+")) {
			int sep = source.indexOf(":");
			return new InetSocketAddress(source.substring(0, sep), Integer.parseInt(source
					.substring(sep + 1)));
		} else if (source.toUpperCase().matches("COM\\d+")) {
			return new SerialAddress(source.toUpperCase(), 38400, DataBits.DATABITS_8,
					StopBits.BITS_1, Parity.NONE, FlowControl.NONE);
		}
		throw new IllegalArgumentException();
	}
}
