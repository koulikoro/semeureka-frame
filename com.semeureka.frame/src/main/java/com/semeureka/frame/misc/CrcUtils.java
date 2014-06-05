package com.semeureka.frame.misc;

import org.apache.mina.core.buffer.IoBuffer;

public class CrcUtils {
	public static int modbus(byte[] buf) {
		int crc = 0xFFFF;
		for (int pos = 0; pos < buf.length; pos++) {
			crc ^= (int) buf[pos];
			for (int i = 8; i != 0; i--) {
				if ((crc & 0x0001) != 0) {
					crc >>= 1;
					crc ^= 0xA001;
				}
				else {
					crc >>= 1;
				}
			}
		}
		return crc;
	}

	public static int modbus(IoBuffer buf, int start, int length) {
		int crc = 0xFFFF;
		for (int pos = start; pos < start + length; pos++) {
			crc ^= (int) buf.get(pos);
			for (int i = 8; i != 0; i--) {
				if ((crc & 0x0001) != 0) {
					crc >>= 1;
					crc ^= 0xA001;
				}
				else {
					crc >>= 1;
				}
			}
		}
		return crc;
	}
}
