package com.yetthin.test;

import java.util.Set;

import com.yetthin.web.commit.RedisUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisTest {
		public static void main(String[] args) {
			JedisPool re = RedisUtil.getInstanceSlave();
			Jedis redis = re.getResource();
			redis.select(2);
			Set<String> name = redis.keys("*n");
			for (String string : name) {
				System.out.println(redis.get(string));
			}
		}
}
