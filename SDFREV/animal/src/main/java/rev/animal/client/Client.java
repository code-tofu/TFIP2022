package rev.animal.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        //Get port number through CLI
//        String[] hostPort = args[0].split(":");
//        System.out.printf("Opening Animal Server on %s Port: %s",hostPort[0], hostPort[1]);
//        String host = hostPort[0];
//        int port = Integer.parseInt(hostPort[1]);

        //Get port number through Console
        System.out.println(">>Connect to Animal Server on <IP:Port>: ");
        Scanner client = new Scanner(System.in);
        String clientIn = client.nextLine();
        String[] hostPort = clientIn.split(":");
        String host = hostPort[0];
        int port = Integer.parseInt(hostPort[1]);
       

        try {
            while (true) {
                Socket sock = new Socket(host, port);

                InputStream is = sock.getInputStream();
//                DataInputStream dis = new DataInputStream(is);
                ObjectInputStream ois = new ObjectInputStream(is);
                OutputStream os = sock.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                System.out.printf(">>Connected to Animal Server on <%s:%d> \n",host,port);
                Object response = ois.readObject(); //how to simulate duplex
                System.out.print((response));
                clientIn = client.nextLine();
                dos.writeUTF(clientIn);

                response = ois.readObject();
                System.out.print((response)); //reads response
                response = ois.readObject();
                System.out.print((response).toString()); //reads object

                dos.flush();
                os.close();
                ois.close();
                is.close();
                sock.close();
            }
        } catch (NumberFormatException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        client.close();
    }
}

