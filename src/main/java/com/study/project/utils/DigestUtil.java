package com.study.project.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * 编码加密工具类
 * 
 * @author SenWu
 *
 */
public class DigestUtil {

	/**
	 * 默认盐
	 */
	private static final String DEFAULT_KEY = "1qXbT4iaPsy";
	/**
	 * 编码格式
	 */
	public static final String ENCODING = "utf-8";

	/**
	 * 对输入字符串进行md5散列（不加密）.
	 */
	public static String md5(String str, String charset) {

		return DigestUtils.md5Hex(getContentBytes(str, charset));
	}

	/**
	 * 对输入字符串进行md5散列（加密）.
	 */
	public static String md5Encrypt(String str, String key, String charset) {
		if (StringUtils.isNotBlank(key)) {
			str = str + key;
		}
		return md5(str, charset);
	}

	/**
	 * 对输入字符串进行sha1散列(不加密).
	 */
	public static String sha1(String str, String charset) {

		return DigestUtils.sha1Hex(getContentBytes(str, charset));
	}

	/**
	 * 对输入字符串进行sha1散列（加密）.
	 */
	public static String sha1Encrypt(String str, String key, String charset) {
		if (StringUtils.isNotBlank(key)) {
			str = str + key;
		}
		return sha1(str, charset);
	}

	/**
	 * 对md5散列进行校验.
	 * 
	 * @param encryptStr
	 * @param str
	 * @param key
	 * @param charset
	 * @return
	 */
	public static boolean md5Verify(String encryptStr, String str, String key, String charset) {
		if (md5Encrypt(str, key, charset).equals(encryptStr)) {
			return true;
		}
		return false;
	}

	/**
	 * 对sha1散列进行校验.
	 * 
	 * @param encryptStr
	 * @param str
	 * @param key
	 * @param charset
	 * @return
	 */
	public static boolean sha1Verify(String encryptStr, String str, String key, String charset) {
		if (sha1Encrypt(str, key, charset).equals(encryptStr)) {
			return true;
		}
		return false;
	}

	private static byte[] getContentBytes(String content, String charset) {
		if (charset == null || "".equals(charset)) {
			return content.getBytes();
		}
		try {
			return content.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("MD5 or SHA1 doesn't support " + charset);
		}
	}

	/**
	 * base64编码
	 * 
	 * @param input
	 * @return
	 */
	public static String base64Encode(byte[] input) {
		return Base64.encodeBase64String(input);
	}

	/**
	 * base64编码(加密)
	 * 
	 * @param str
	 * @param key
	 * @param charset
	 * @return
	 */
	public static String base64Encode(String str, String key, String charset) {
		if (StringUtils.isNotBlank(key)) {
			str = key + str;
		}
		return base64Encode(getContentBytes(str, charset));
	}

	/**
	 * base64编码(加密)
	 * 
	 * @param str
	 * @param key
	 * @param charset
	 * @return
	 */
	public static String base64Encode(String str, String charset) {
		return base64Encode(str, DEFAULT_KEY, charset);
	}

	/**
	 * base64解码
	 * 
	 * @param base64Str
	 * @return
	 */
	public static byte[] base64Decode(String base64Str) {
		return Base64.decodeBase64(base64Str);
	}

	/**
	 * base64解码(含密)
	 * 
	 * @param base64Str
	 * @return
	 */
	public static String base64Decode(String base64Str, String key, String charset) {
		String str = null;
		try {
			if (StringUtils.isNotBlank(charset)) {
				str = new String(base64Decode(base64Str), charset);
			} else {
				str = new String(base64Decode(base64Str));
			}
			if (StringUtils.isBlank(key)) {
				return str;
			}
			if (str.length() <= key.length()) {
				throw new RuntimeException("content is wrong");
			}
			str = str.substring(key.length());
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(" Doesn't support " + charset);
		}
		return str;
	}

	/**
	 * base64解码(含密)
	 * 
	 * @param base64Str
	 * @return
	 */
	public static String base64Decode(String base64Str, String charset) {
		return base64Decode(base64Str, DEFAULT_KEY, charset);
	}
}
