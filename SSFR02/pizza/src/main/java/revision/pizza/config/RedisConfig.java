package revision.pizza.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    /*
     * spring.data.redis.host=${REDISHOST}
     * spring.data.redis.port=${REDISPORT}
     * spring.data.redis.username=${REDISUSER}
     * spring.data.redis.password=${REDISPASSWORD}
     * spring.data.redis.client.type=jedis
    */
    @Value("${spring.data.redis.host}") private String redisHost;
    @Value("${spring.data.redis.port}") private Optional<Integer> redisPort;
    @Value("${spring.data.redis.user}") private String redisUsername;
    @Value("${spring.data.redis.password}") private String redisPassword;

    @Bean("pizza")
    @Scope("singleton")
    // @Primary //PRIMARY?
    public RedisTemplate<String, String> redisTemplate(){

        //Set redis config parameters
        final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(redisHost);
        config.setPort(redisPort.get());

        if(!redisUsername.isEmpty() && !redisPassword.isEmpty()){
            config.setUsername(redisUsername);
            config.setPassword(redisPassword);
        }
        config.setDatabase(0);

        //Use redis config for jedis client
        final JedisClientConfiguration jedisClient =  JedisClientConfiguration.builder().build();
        final JedisConnectionFactory jedisFac = new JedisConnectionFactory(config,jedisClient);
        jedisFac.afterPropertiesSet();

        //Set up redistemplate and associated KV hashing
        RedisTemplate<String, String> r = new RedisTemplate<String,String>();
        r.setConnectionFactory(jedisFac);
        r.setKeySerializer(new StringRedisSerializer()); 
        r.setValueSerializer(r.getKeySerializer());

        System.out.println("Connected to Redis" + redisHost + ":" + redisPort + "-Database" + Integer.toString(config.getDatabase()));
        return r;
    }
}