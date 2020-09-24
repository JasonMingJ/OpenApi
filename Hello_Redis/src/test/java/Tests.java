import com.alibaba.fastjson.JSON;
import com.fucode.entity.User;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import org.springframework.util.SerializationUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.Date;

import static java.lang.System.currentTimeMillis;

/**
 * @ClassName Tests
 * @Description: TODO
 * @Author user
 * @Date 2020/9/18
 * @Version V1.0
 **/
public class Tests {

    @Test
    public void RedisSet(){

        Jedis jedis = new Jedis("192.168.80.188", 6379);

        jedis.set("Hello Jedis!","Jedis Yes!");

        jedis.close();
    }

    @Test
    public void RedisGet(){

        Jedis jedis = new Jedis("192.168.80.188", 6379);

        String value = jedis.get("Hello Jedis!");

        System.out.println(value);

        jedis.close();
    }

    @Test
    public void RedisByte(){
        //连接Redis
        Jedis jedis = new Jedis("192.168.80.188", 6379);
        /*##################################################*/
        //设置key-value对
        String key = "user";
        User value = new User(1,"雷迪斯",new Date());
        //将key和value转换为字节数组byte[]
        byte[] byteKey = SerializationUtils.serialize(key);
        byte[] byteValue = SerializationUtils.serialize(value);;
        //将key和value的序列化字节数组存入Redis
        jedis.set(byteKey,byteValue);

        jedis.close();
    }

    @Test
    public void getByteArray(){
        Jedis jedis = new Jedis("192.168.80.188", 6379);

        //准备字节化的key值
        String key = "user";
        //获取key值序列化之后的字节数组
        byte[] byteKey = SerializationUtils.serialize(key);
        //在Redis中获取key对应的value的字节数组,并反序列化成User对象
        User user = (User)SerializationUtils.deserialize(jedis.get(byteKey));

        System.out.println(user);

        jedis.close();

    }

    @Test
    public void RedisJsonSet(){
        Jedis jedis = new Jedis("192.168.80.188",6379);
        //准备key(String) - value(JSON的User)
        String key = "JSONUser";
        User user = new User(2, "JsonRedis", new Date());

        String jsonString = JSON.toJSONString(user);

        jedis.set(key,jsonString);

        jedis.close();
    }

    @Test
    public void RedisJsonGet(){
        Jedis jedis = new Jedis("192.168.80.188",6379);

        String key = "JSONUser";
        String userJson = jedis.get(key);

        User user = JSON.parseObject(userJson,User.class);

        System.out.println(user);
    }

    @Test
    public void RedisPool(){
        //连接池的配置
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();

        genericObjectPoolConfig.setMaxTotal(1000);
        genericObjectPoolConfig.setMaxIdle(10);
        genericObjectPoolConfig.setMinIdle(5);
        genericObjectPoolConfig.setMaxWaitMillis(3000);

        //Redis连接池的创建
        JedisPool jedisPool = new JedisPool(genericObjectPoolConfig,"192.168.80.188", 6379);

        Jedis resource = jedisPool.getResource();

        String jsonUser = resource.get("JSONUser");
        System.out.println(jsonUser);
    }

    @Test
    public void RedisPipline(){

        JedisPool jedisPool = new JedisPool("192.168.80.188", 6379);

        Jedis resource = jedisPool.getResource();

        System.out.println(currentTimeMillis());

        //普通方法执行较慢
        // for (int i=0;i<100000;i++){
        //     resource.incr("pipe");
        // }

        //创建管道,一次性执行多条操作命令，并一次性返回所有操作结果，大幅提升速度
        Pipeline pipelined = resource.pipelined();
        //值自增100000次的操作放到管道中
        for (int i=0;i<100000;i++){
            pipelined.incr("pipe");
        }
        //执行命令
        pipelined.syncAndReturnAll();

        jedisPool.close();

        System.out.println(currentTimeMillis());
    }

    @Test
    public void RedisPass(){
        //连接池配置对象
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxWaitMillis(3000);
        //新建一个连接池
        JedisPool jedisPool = new JedisPool(genericObjectPoolConfig, "192.168.80.188" , 6379 , 1000,"123");
        //获得Redis数据
        Jedis jedis = jedisPool.getResource();
        //获取key的value
        System.out.println(jedis.get("job"));
    }
}
