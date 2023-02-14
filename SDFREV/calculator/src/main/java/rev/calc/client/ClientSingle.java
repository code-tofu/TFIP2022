package rev.calc.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientSingle {
    
    public static void main(String[] args){
        try{
            if(args.length == 1){
                String[] hostPort = args[0].split(":");
                String host = hostPort[0];
                int port = Integer.parseInt(hostPort[1]);

                boolean isActive = true;
                try{
                    Scanner scan = new Scanner(System.in);
                    while(isActive){
                        Socket sock = new Socket(host, port);
                        InputStream is = sock.getInputStream();
                        DataInputStream dis = new DataInputStream(is);
                        OutputStream os = sock.getOutputStream();
                        DataOutputStream dos = new DataOutputStream(os);

                        System.out.printf(">>Connected to Calculator Server on <%s:%d> \n",host,port);
                        System.out.printf(dis.readUTF()); //1.READ
                        String consoleInput = scan.nextLine();
                        String[] command = consoleInput.split(" ");

                        if( (command[0].trim().equalsIgnoreCase("exit")) || (command[0].trim().equalsIgnoreCase("e")) ){
                            dos.writeUTF("exit"); 
                            isActive = false;
                            dis.close();
                            is.close();
                            dos.close();
                            os.close();
                            sock.close();
                            scan.close();
                            break;
                        }

                        dos.writeUTF(consoleInput); //2.WRITE
                        System.out.printf(dis.readUTF()); //3.READ //need to validate response
                        dis.close(); //keep opening closing sockets?
                        is.close();
                        dos.close();
                        os.close();
                        sock.close();
                    }
                    scan.close();
                } catch (IOException e) {
                    System.out.println(">>Server IO Error\n");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Usage: <Host>:<Port>");
                System.exit(1);
            }
        } catch (NumberFormatException NFerr){
            System.out.println("Usage: <Host>:<Port>");
            System.exit(1);
        }
    }
}