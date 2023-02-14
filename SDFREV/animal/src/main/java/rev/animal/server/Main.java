package rev.animal.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.printf("Open Animal Server on Port = ");

        try{
            int serverPort = Integer.parseInt("3000"); //args[0]
            System.out.printf("\n>>Starting Animal Server on Port: %d \n", serverPort);
            //generate database of Zoo
            Map<String,Animal> zooDB = Zoo.buildZoo();
            Server zooServer = new Server(serverPort, zooDB);
        } catch (NumberFormatException NFErr){
            System.out.printf("Usage: Port = <Port Number> \n");
            System.exit(1);
        }


    }
}

