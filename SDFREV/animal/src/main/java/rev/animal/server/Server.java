package rev.animal.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Random;

public class Server {
    private boolean isActive = false; //to allow server to terminate session
    public Server(int serverPort, Map<String, Animal> database) {
        try { //CATCH IO EXCEPTIONS
            ServerSocket server = new ServerSocket(serverPort);
            isActive = true;
            System.out.printf(">>Animal Server started on Port %s\n", serverPort);
            boolean breakFlag = true;
            while (breakFlag) { // loop to keep accepting clients
                try {
                    Socket sock = server.accept(); // do I have to create the socket before I create server socket?
                    NetworkIO netIO = new NetworkIO(sock);
                    netIO.writeOOS(">>Usage get-<random> / get-<animal> / end\n");
                    System.out.println(">>Client connected");
                    while (true) {  //loop to keep reading
                        try {
                            String clientInput = netIO.readIS();
                            if (clientInput.trim().equals("end")) {
                                System.out.println(">>Client terminated session\n");
                                netIO.closeIO();
                                sock.close();
                                breakFlag = false;
                                break;
                            }

                            if (clientInput.trim().equals("get-random")) {
                                netIO.writeOOS(">>Requested for random animal\n");
                                netIO.writeOOS(Zoo.returnAnimal(database, (new Random()).nextInt(database.size())));
                                //insert for non random
                            } else {
                                netIO.writeOOS(">>Invalid Command. Usage get-<random> / get-<animal> / end\n");
                            }
                        } catch (EOFException e) {
                            System.out.println(">>Client disconnected");
                            netIO.closeIO();
                            sock.close();
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            server.close();
        }catch (IOException e) {
            e.printStackTrace();

        }

    }
}
