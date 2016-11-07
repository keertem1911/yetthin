package com.yetthin.web.commit;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
		private RedisUtil(){};
		private static JedisPool pool_Master=null;
		private static JedisPool pool_Slave=null;
		
		public static JedisPool getInstanceMsater(){
			if(pool_Master==null){
				synchronized(RedisUtil.class){
					if(pool_Master==null){
						JedisPoolConfig config=new JedisPoolConfig();
						config.setMaxActive(1000);
						config.setMaxIdle(10);
						config.setMaxWait(100*1000);
						pool_Master=new JedisPool(config, "127.0.0.1", 6379);
						
					}
				}
			}
			return pool_Master;
		}
	public static void RealseJedis_M(Jedis jedis){
		if(jedis!=null)
			pool_Master.returnResourceObject(jedis);
	}
	public static JedisPool getInstanceSlave(){
		if(pool_Slave==null){
			synchronized(RedisUtil.class){
				if(pool_Slave==null){
					JedisPoolConfig config=new JedisPoolConfig();
					config.setMaxActive(10000);
					config.setMaxIdle(10);
					config.setMaxWait(100*1000);
					pool_Slave=new JedisPool(config, "127.0.0.1", 6379);
					
				}
			}
		}
		return pool_Slave;
	}
		public static void RealseJedis_S(Jedis jedis){
			if(jedis!=null)
				pool_Slave.returnResourceObject(jedis);
			}
}
