package com.yetthin.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import redis.clients.jedis.Jedis;

public class ReadIndexStock {
		public static void main(String[] args) {
			  String [] INDEX_STOCK={"0-0","0-1","0-2"};
			Jedis jedis= new Jedis("127.0.0.1", 6379);
			jedis.auth("keertem1911");
			jedis.select(2);
			String [] files={"./src/shangzheng.txt","./src/sz.txt","./src/cy.txt"};
			BufferedReader reader= null;
			try {
				for (int i = 0; i < files.length; i++) {
					reader= new BufferedReader(
							new InputStreamReader(new FileInputStream(files[i])));
					String str=null;
					while((str=reader.readLine())!=null){
						jedis.lpush(INDEX_STOCK[i], str);
					 
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(reader!=null)
					try {
						reader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
}
