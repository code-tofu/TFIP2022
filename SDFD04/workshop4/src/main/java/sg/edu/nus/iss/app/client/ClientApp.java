package sg.edu.nus.iss.app.client;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientApp {

    public static void main( String[] args ) {
        System.out.println( "Hello I'm Client!" );
        String[] argSplit = args[0].split(":");
        System.out.printf("Host: %s", argSplit[0]);
        System.out.printf(argSplit[1] + "\n");
        try{
            Socket sock = new Socket(argSplit[0], Integer.parseInt(argSplit[1]));
            InputStream inStream = sock.getInputStream();
            DataInputStream diStream = new DataInputStream(inStream);
            OutputStream outStream = sock.getOutputStream();
            DataOutputStream doStream = new DataOutputStream(outStream);

        Console cons = System.console();
        String input = cons.readLine("Send Command to Cookie Server:"); //
        
        doStream.writeUTF(input);
        doStream.flush(); //instead of close
        String response = diStream.readUTF();
        if (response.contains("cookie-text")){
            String[] cookieValArr = response.split(":");
            System.out.printf("Response from Cookie Server: %s\n", cookieValArr[1]);
        } else {
            System.out.print(response);
        }
        inStream.close();
        outStream.close();
        sock.close();

        } catch(IOException ioErr){
            ioErr.printStackTrace();
        }
    }
}