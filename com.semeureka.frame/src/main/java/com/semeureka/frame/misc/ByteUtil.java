package com.semeureka.frame.misc;

import javax.xml.bind.DatatypeConverter;

import org.apache.mina.core.buffer.IoBuffer;

public class ByteUtil {
	/**
	 * CRC16校验（Modbus），不消耗IoBuffer。
	 */
	public static int crc16(IoBuffer buf, int start, int len) {
		int crc = 0xFFFF;
		for (int pos = start; pos < start + len; pos++) {
			crc ^= buf.get(pos);
			for (int i = 8; i != 0; i--) {
				if ((crc & 0x0001) != 0) {
					crc >>= 1;
					crc ^= 0xA001;
				} else {
					crc >>= 1;
				}
			}
		}
		return crc;
	}

	/**
	 * CRC8校验（加和），不消耗IoBuffer。
	 */
	public static int crc8(IoBuffer buf, int start, int len) {
		byte crc = 0;
		for (int pos = start; pos < start + len; pos++) {
			crc += buf.get(pos);
		}
		return crc & 0xFF;
	}

	/**
	 * 从指定位置复制出一个指定长度字节数组，不消耗IoBuffer。
	 */
	public static byte[] copyBytes(IoBuffer buffer, int pos, int len) {
		byte[] bs = new byte[len];
		for (int i = 0; i < len; i++) {
			bs[i] = buffer.get(i + pos);
		}
		return bs;
	}

	/**
	 * 从当前位置复制出一个指定长度字节数组，消耗IoBuffer。
	 */
	public static byte[] copyBytes(IoBuffer buffer, int len) {
		byte[] bs = new byte[len];
		buffer.get(bs);
		return bs;
	}

	public static String toHex(byte[] bytes) {
		return DatatypeConverter.printHexBinary(bytes);
	}

	public static byte[] toBytes(String hex) {
		return DatatypeConverter.parseHexBinary(hex);
	}
}
