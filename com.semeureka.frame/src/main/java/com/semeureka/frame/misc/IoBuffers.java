package com.semeureka.frame.misc;

import javax.xml.bind.DatatypeConverter;

import org.apache.mina.core.buffer.IoBuffer;

public class IoBuffers {
	/**
	 * Modbus CRC16
	 */
	public static int crc16(IoBuffer buf, int start, int length) {
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

	public static String hex(byte[] val) {
		return DatatypeConverter.printHexBinary(val);
	}

	/**
	 * 从 <b>指定位置</b> 复制创建一个指定长度字节数组，复制完成后 <b>不改变</b> 当前位置值。
	 */
	public static byte[] bytes(IoBuffer buffer, int pos, int length) {
		byte[] bs = new byte[length];
		for (int i = 0; i < length; i++) {
			bs[i] = buffer.get(i + pos);
		}
		return bs;
	}

	/**
	 * 从 <b>当前位置</b> 复制创建一个指定长度字节数组，复制完成后 <b>改变</b> 当前位置值。
	 */
	public static byte[] bytes(IoBuffer buffer, int length) {
		byte[] bs = new byte[length];
		buffer.get(bs);
		return bs;
	}

	public static void main(String[] args) {
		IoBuffer buffer = IoBuffer.wrap("Hello World!".getBytes());
		byte[] bs = new byte[5];
		buffer.get(bs, 2, 3);
		System.out.println(buffer.position());
	}
}
