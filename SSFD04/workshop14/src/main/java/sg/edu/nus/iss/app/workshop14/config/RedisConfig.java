package sg.edu.nus.iss.app.workshop14.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
 
    @Value("${spring.data.redis.host}") //note no semicolon
    private String redisHost;
    

    @Value("${spring.data.redis.port}")
    private Optional<Integer> redisPort;
    //for null safety

    @Value("${spring.data.redis.username}")
    private String redisUsername;
    

    @Value("${spring.data.redis.password}")
    private String redisPassword;
    
    // What is a redistemplate?
    // Redis config is just boilerplate? Why singleton? Why bean?


    @Bean
    //everywhere that redisTemplate needs to be used, can be autowired. similar to @component. in this case because it is
    //different from javabean
    @Scope("singleton")
    public RedisTemplate<String, Object> redisTemplate(){
        final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(redisHost);
        config.setPort(redisPort.get());

        if(!redisUsername.isEmpty() && !redisPassword.isEmpty()){
            config.setUsername(redisUsername);
            config.setPassword(redisPassword);
        }
        config.setDatabase(0);
        //database is zero because there is one database. if there is more than one database, the database number is changed. you need one config file per database
        //The database index is the number you see at the end of a Redis URL: redis://localhost:6379/0 . 


        //singleton hence no "new"?

        //Jedis client sets up the interface between java and redis
        final JedisClientConfiguration jedisClient =  JedisClientConfiguration
                                .builder()
                                .build();

        final JedisConnectionFactory jedisFac = new JedisConnectionFactory(config, 
                jedisClient);
        jedisFac.afterPropertiesSet();
        
        //create redis template in order to access the redis connections and serializer methods 
        RedisTemplate<String, Object> r = new RedisTemplate<String,Object>();
        r.setConnectionFactory(jedisFac);

        //create new string serializer instances to set as key and has serializer? 
        r.setKeySerializer(new StringRedisSerializer());
        r.setHashKeySerializer(new StringRedisSerializer());

        RedisSerializer<Object> objSerializer 
                = new JdkSerializationRedisSerializer(getClass().getClassLoader());
        
        //return a serializer for valueserializer or hashvalue serialiser to use. e.g. getkeyserializer. in this case using the JDKSRS to create a contact? object serialiser?
        r.setValueSerializer(objSerializer);
        r.setHashValueSerializer(objSerializer);
        
        System.out.println("redisHost > " + redisHost);
        return r;
    }
}
