package zcom.yetthin.web.base;

import com.yetthin.web.commit.RedisUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisBase {
	private static JedisPool poolM=RedisUtil.getInstanceMsater();
	protected static Jedis jedis_M=null;
	static{
		 
		jedis_M=poolM.getResource();
	}
}
