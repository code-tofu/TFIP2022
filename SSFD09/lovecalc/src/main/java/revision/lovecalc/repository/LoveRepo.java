package revision.lovecalc.repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import revision.lovecalc.model.Love;

@Repository
public class LoveRepo {

    @Autowired @Qualifier("love")
    private RedisTemplate<String, String> redisTemplate;

    public boolean saveLoveDB(String jsonLove){
        String loveId = UUID.randomUUID().toString().substring(0,8);
        this.redisTemplate.opsForValue().set(loveId,jsonLove);
        return true;
    }

    public Optional<Love> loadLoveDB(String loveId) throws IOException{
        Love myLove = Love.createLove(this.redisTemplate.opsForValue().get(loveId));
        if(myLove != null) return Optional.of(myLove);
        return Optional.empty(); 
    }

    public Map<String,Love> loadAllLoveDB() throws IOException{
        Set<String> keys = this.redisTemplate.keys("*");
        Map<String,Love> allLove = new HashMap<>();
        for (String key : keys){
            allLove.put(key,Love.createLove(this.redisTemplate.opsForValue().get(key)));
        }
        return allLove;
    }
    
}
