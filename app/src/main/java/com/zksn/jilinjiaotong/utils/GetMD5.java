package com.zksn.jilinjiaotong.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetMD5 {
	public static String getMd5(String action) {
		try {
			String aa = action;
			String string = getMD5(aa);
			return string;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;

	}
//	public static String getMd5(String action) {
//		try {
//			String aa = action + getTime() + "#@$!%*";
//			String string = getMD5(aa);
//			return string;
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
//		return null;
//		
//	}

	// md5����
	public static String getMD5(String val) throws NoSuchAlgorithmException {
		try {

			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] a;
			a = val.getBytes();
			md5.update(a);
			StringBuffer buf = new StringBuffer();
			byte[] m = md5.digest();
			for (int offset = 0; offset < m.length; offset++) {
				int i = m[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			String aa = new String(m);

			return buf.toString();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;

	}

	private static String getTime() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}
}
