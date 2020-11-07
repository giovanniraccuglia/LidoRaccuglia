package com.raccuglia.utils;

import java.security.SecureRandom;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class LidoUtil {
	
	public static boolean checkInput(String val) {
		if(val != null && !(val.trim().equals("")))
			return true;
		else
			return false;
	}
	
	public static String genPassword(int len) { //Soluzione provvisoria
    	final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    	final String chars1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	final String chars2 = "abcdefghijklmnopqrstuvwxyz";
    	final String chars3 = "0123456789";
    	SecureRandom random = new SecureRandom();
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0; i < len; i++) {
    		int randomIndex = random.nextInt(chars.length());
    		sb.append(chars.charAt(randomIndex));
    	}
    	int randomIndex1 = random.nextInt(chars1.length());
    	int randomIndex2 = random.nextInt(chars2.length());
    	int randomIndex3 = random.nextInt(chars3.length());
		sb.append(chars1.charAt(randomIndex1));
		sb.append(chars2.charAt(randomIndex2));
		sb.append(chars3.charAt(randomIndex3));
    	return sb.toString();
    }
	
	public static String setDate(String time) {
		Date today = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int day = 86400000;
		String result = null;
		try {
			if(today.after(sdf2.parse(sdf1.format(today) + " " + time))) {
				Date tomorrow =	new Date(Date.valueOf(sdf1.format(today)).getTime() + day);
				result = sdf1.format(tomorrow);
				return result;
			}else {
				result = sdf1.format(today);
				return result;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static boolean checkDate(String start, String end) {
		Date today = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		boolean result = false;
		try {
			if(today.after(sdf2.parse(sdf1.format(today) + " " + start)) && today.before(sdf2.parse(sdf1.format(today) + " " + end))) {
				result = true;
				return result;
			}else {
				return result;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
}