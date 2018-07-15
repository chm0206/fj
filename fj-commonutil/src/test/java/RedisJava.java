import redis.clients.jedis.Jedis;

public class RedisJava {

    public static void main(String[] args) {
    	Jedis jedis = RedisPool.getJedis();
        System.out.println(RedisPool.getJedis().get("1userInfo"));
    }
}