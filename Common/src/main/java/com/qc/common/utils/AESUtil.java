/**
The MIT License (MIT) * Copyright (c) 2016 閾绉戞妧(mingsoft.net)

 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.qc.common.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * AES鍔犲瘑瑙ｅ瘑
 * @author 鐜嬪ぉ鍩筈Q:78750478
 * @version 
 * 鐗堟湰鍙凤細100-000-000<br/>
 * 鍒涘缓鏃ユ湡锛�2012-03-15<br/>
 * 鍘嗗彶淇锛�<br/>
 */
@Slf4j
public class AESUtil {
	
	/**
	 * AES瑙ｅ瘑瀛楃涓�
	 * @param decryptStr 瑕佽В瀵嗙殑瀛楃涓�
	 * @param strKey key鍊�
	 * @return 杩斿洖瑙ｅ瘑鍚庣殑瀛楃涓�
	 */
	public static String decrypt(String decryptStr, String strKey) {
		try {
			// 鍒ゆ柇Key鏄惁姝ｇ‘
			if (strKey == null) {
				log.debug("Key涓虹┖null");
				return null;
			}
			// 鍒ゆ柇Key鏄惁涓�16浣�
			if (strKey.length() != 16) {
				log.debug("Key闀垮害涓嶆槸16浣�");
				return null;
			}
			byte[] raw = strKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] encrypted1 = hex2byte(decryptStr);
			try {
				byte[] original = cipher.doFinal(encrypted1);
				String originalString = new String(original);
				return originalString;
			} catch (Exception e) {
				System.out.println(e.toString());
				return null;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}

	/**
	 * AES鍔犲瘑瀛楃涓�
	 * @param encryptStr 瑕佸姞瀵嗙殑瀛楃涓�
	 * @param strKey key鍊�
	 * @return 杩斿洖瑙ｅ瘑鍚庣殑瀛楃涓�
	 */
	public static String encrypt(String encryptStr, String strKey) {
		if (strKey == null) {
			log.debug("Key涓虹┖null");
			return null;
		}
		// 鍒ゆ柇Key鏄惁涓�16浣�
		if (strKey.length() != 16) {
			log.debug("Key闀垮害涓嶆槸16浣�");
			return null;
		}
		byte[] encrypted = null;
		try {
			byte[] raw = strKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			encrypted = cipher.doFinal(encryptStr.getBytes());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		} catch (NoSuchPaddingException  e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return byte2hex(encrypted).toLowerCase();
	}

	/**
	 * 瀛楃涓茶浆浜岃鍒�
	 * @param str 瀛楃涓�
	 * @return 杩斿洖杞崲鍚庣殑浜岃繘鍒舵暟缁�
	 */
	public static byte[] hex2byte(String str) {
		if (str == null) {
			return null;
		}
		int l = str.length();
		if (l % 2 == 1) {
			return null;
		}
		byte[] bytes = new byte[l / 2];
		for (int i = 0; i != l / 2; i++) {
			bytes[i] = (byte) Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
		}
		return bytes;
	}

	/**
	 * 浜岃鍒惰浆瀛楃涓�
	 * @param bytes 浜岃繘鍒舵暟缁�
	 * @return 杩斿洖杞崲鍚庣殑瀛楃涓�
	 */
	public static String byte2hex(byte[] bytes) {
		if (bytes == null) {
			return "";
		}
		String hs = "";
		String stmp = "";
		for (int n = 0; n < bytes.length; n++) {
			stmp = (Integer.toHexString(bytes[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}
}