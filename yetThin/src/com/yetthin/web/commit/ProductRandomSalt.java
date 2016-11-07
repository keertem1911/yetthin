package com.yetthin.web.commit;

import java.util.Random;

public class ProductRandomSalt {
	private static final String []saltArratys=new String[]{
		"1","3","5","7","0",
		"2","4","6","8","9"
	};
	private static final int MAX_LENGTH=6;

	public static String getString(int len){
		StringBuffer sb=new StringBuffer();
		Random rand=new Random(System.currentTimeMillis());
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(len>0){
			int  index=rand.nextInt(saltArratys.length);
			String temp=saltArratys[index];
			sb.append(temp);
			len--;
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
	 public static void main(String[] args) {
		ProductRandomSalt p=new ProductRandomSalt();
			for (int i = 0; i < 10; i++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				p.getString(6);
			}
	 
	 
	 }
}
