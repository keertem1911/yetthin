package com.yetthin.web.commit;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5UnitTool {
	private static Md5UnitTool instance;
	
	public static Md5UnitTool getInstance(){
		synchronized(Md5UnitTool.class){
			if(instance==null){
				instance=new Md5UnitTool();
			}
		}
		return instance;
	}
	public synchronized String encrypt(String plainText,String salt){
		return encrypt(plainText+salt);
	}
	public String encrypt(String plainText){
		MessageDigest md=null;
		
		try {
			md=MessageDigest.getInstance("md5");
			
			md.update(plainText.getBytes());
			
			byte[] raw =md.digest();
			
			StringBuffer sb=new StringBuffer();
			for (int i = 0; i < raw.length; i++) {
				sb.append(Integer.toString((raw[i] & 0xff)+0x0100,16).substring(1));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
