package com.kenny.service.logistics.pay.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串的工具类<br>
 * 
 * @author 行者
 * 
 */
public class StringUtil {
	final static String METHOD_SET = "set";
	final static String METHOD_GET = "get";

	/**
	 * 笨方法：String s = "你要去除的字符串"; <br>
	 * 1.去除空格：s = s.replace('\\s',''); <br>
	 * 2.去除回车：s =s.replace('\n',''); 这样也可以把空格和回车去掉，其他也可以照这样做。<br>
	 * 注：\n 回车 \t 水平制表符 \s空格 \r 换行
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	/**
	 * 将特殊字符转义再切分
	 * 
	 * @param source
	 * @param spliter
	 * @return
	 */
	public static String[] splitStr2Array(String source, String spliter) {

		String regex = spliter;
		if (regex.equals("?") || regex.equals("*") || regex.equals(")") || regex.equals("(") || regex.equals("{") || regex.equals("$") || regex.equals("+") || regex.equals(".") || regex.equals("|")) {

			regex = "[" + regex + "]";
		}

		return source.split(regex);
	}

	/**
	 * 将byte[] 转换成字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2Hex(byte[] srcBytes) {
		StringBuilder hexRetSB = new StringBuilder();
		for (byte b : srcBytes) {
			String hexString = Integer.toHexString(0x00ff & b);
			hexRetSB.append(hexString.length() == 1 ? 0 : "").append(hexString);
		}
		return hexRetSB.toString();
	}

	/**
	 * 将16进制字符串转为转换成字符串
	 * 
	 * @param source
	 * @return
	 */
	public static byte[] hex2Bytes(String source) {
		byte[] sourceBytes = new byte[source.length() / 2];
		for (int i = 0; i < sourceBytes.length; i++) {
			sourceBytes[i] = (byte) Integer.parseInt(source.substring(i * 2, i * 2 + 2), 16);
		}
		return sourceBytes;
	}
}
