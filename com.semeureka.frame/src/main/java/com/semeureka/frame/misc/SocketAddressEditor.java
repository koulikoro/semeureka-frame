package com.semeureka.frame.misc;

import org.apache.mina.integration.beans.InetSocketAddressEditor;
import org.apache.mina.transport.serial.SerialAddress;
import org.apache.mina.transport.serial.SerialAddress.DataBits;
import org.apache.mina.transport.serial.SerialAddress.FlowControl;
import org.apache.mina.transport.serial.SerialAddress.Parity;
import org.apache.mina.transport.serial.SerialAddress.StopBits;

public class SocketAddressEditor extends InetSocketAddressEditor {
	private int bauds = 38400;;

	public void setBauds(int bauds) {
		this.bauds = bauds;
	}

	@Override
	protected String toText(Object value) {
		if (value instanceof SerialAddress) {
			return ((SerialAddress) value).getName();
		}
		return super.toText(value);
	}

	@Override
	protected Object toValue(String text) throws IllegalArgumentException {
		if (text.matches("\\d+\\.\\d+\\.\\d+\\.\\d+:\\d+")) {
			return super.toValue(text);
		} else {
			return new SerialAddress(text, bauds, DataBits.DATABITS_8, StopBits.BITS_1,
					Parity.NONE, FlowControl.NONE);
		}
	}
}
