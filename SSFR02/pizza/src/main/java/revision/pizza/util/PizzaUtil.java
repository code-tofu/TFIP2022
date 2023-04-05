package revision.pizza.util;

import java.util.UUID;

public class PizzaUtil {

    public static String getUUID(){
        return UUID.randomUUID().toString().substring(0,8);
    }

    
}
