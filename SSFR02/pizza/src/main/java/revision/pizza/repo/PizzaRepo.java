package revision.pizza.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import revision.pizza.model.Order;

@Repository
public class PizzaRepo {

    @Autowired 
    @Qualifier("pizza")
    private RedisTemplate<String,String> redisTemplate;

    public boolean saveOrderDB(Order order){
        this.redisTemplate.opsForValue().set(order.getOrderId(),order.toJSON().build().toString());
        return true;
    }

    public Order getOrderDB(String orderID){
        return Order.fromJSONString(this.redisTemplate.opsForValue().get(orderID));
    }
    
}
