package sg.edu.nus.iss.app.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static sg.edu.nus.iss.app.server.Cookie.*; //importing as a static instead of calling instance method

public class ServerApp {

    private static final String argErrorMsg = "Usage: sg.edu.nus.iss.app.server.ServerApp <TCPPort> <cookies_file.txt>";
    public static void main(String[] args){
        System.out.println( "Hello I'm Server!\n");
        try{
            String serverPort = args[0]; //args<0 or args < 2?
            System.out.printf("Server Port: %s\n",serverPort);

            String cookieName = args[1];
            System.out.printf("Cookie File Name: %s\n",cookieName);

            String cookieWrite = args[2];
            System.out.printf("Cookie File Write to: %s\n",cookieWrite);

            String cookieMerge = args[3];
            System.out.printf("Cookie MailMerge File Write to: %s\n",cookieMerge);

            ServerSocket serversoc =  new ServerSocket(Integer.parseInt(serverPort));
            Socket sock = serversoc.accept(); //server will accept socket connection from client


            InputStream inStream = sock.getInputStream();
            DataInputStream diStream = new DataInputStream(inStream);
            /*
             Reads in a string that has been encoded using a modified UTF-8 format. The general contract of readUTF is that it reads a representation of a Unicode character string encoded in modified UTF-8 format; this string of characters is then returned as a String.
             */

            OutputStream outStream = sock.getOutputStream();
            DataOutputStream doStream = new DataOutputStream(outStream);
            System.out.printf("Waiting for Cookie Request>>\n");
            String cookieRequest = diStream.readUTF(); 
            if(cookieRequest.equals("get-cookie")){
                String randomCookie = getRandomCookie(cookieName,cookieWrite,cookieMerge);
                doStream.writeUTF("cookie-text:" + randomCookie);                
            } else {
                doStream.writeUTF("Invalid Command");
            } 

            //close all sockets (scope?)
            inStream.close();
            outStream.close();
            sock.close();
            // fostream.close();
        // handle exceptions    
        }catch(IndexOutOfBoundsException indexErr){
            System.out.println(argErrorMsg);
            System.exit(1);
        }catch(IOException ioErr){ //vs throw?
           ioErr.printStackTrace();
        }
    }
}