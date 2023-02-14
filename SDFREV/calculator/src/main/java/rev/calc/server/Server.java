package rev.calc.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String[] args){
        ArrayList <String> commands = new ArrayList<>(List.of(
        "add","a", "subtract","s","multiply","m","divide","d","e","Exit"));
        try{
            if(args.length == 2){
                int serverPort = Integer.parseInt(args[0]);
                System.out.printf(">>Starting Calculator Server on Port %d\n",serverPort);
                String logPath = args[1];
                System.out.printf(">>Lpgging Calculations at File: %s\n",logPath);

                ServerSocket server = new ServerSocket(serverPort);
                boolean isActive = true;
                while(isActive){
                    try{
                        System.out.println(">>Waiting for Client");
                        Socket sock = server.accept(); 
                        InputStream is = sock.getInputStream();
                        DataInputStream dis =  new DataInputStream(is);
                        OutputStream os = sock.getOutputStream();
                        DataOutputStream dos = new DataOutputStream(os);
                        LogWriter lw = new LogWriter(logPath);
                        System.out.println(">>Client connected");
                        dos.writeUTF("Usage: <command> <list of numbers> <command>: (A)dd, (S)ubtract, (M)ultiply, (D)ivide, (E)xit\n"); //1. WRITE
                        double answer;
                        while(true){
                            try{
                                dos.writeUTF(">>");
                                String clientInput = dis.readUTF(); //2.READ
                                String[] command = clientInput.split(" ",2);
                                if ((command[0].trim().equalsIgnoreCase("exit")) || (command[0].trim().equalsIgnoreCase("e"))){
                                    System.out.println(">>Client terminated session\n");
                                    isActive = false;
                                    break;
                                } else if((command.length<2)){
                                    dos.writeUTF("<Input Err> Usage: <command> <list of numbers>\n>>"); //3.WRITE
                                    continue;
                                } else if (!commands.contains(command[0].trim().toLowerCase())){
                                    dos.writeUTF("Invalid Command.\n<command>: (A)dd, (S)ubtract, (M)ultiply, (D)ivide, (E)xit\n>>"); //3.WRITE
                                    continue;
                                } else {
                                    String[] strArr = command[1].split(",");
                                    double[] doubArr = new double[strArr.length];
                                    if(strArr.length<2){
                                        dos.writeUTF("<Too little arguments> Usage: <command> <list of numbers>\n");
                                        break;
                                    }
                                    for (int i =0;i<strArr.length;i++){
                                        doubArr[i] = Double.parseDouble(strArr[i].trim());
                                    }
                                    System.out.println("Recieved Query. Responding");
                                    answer = Calc.calculate(command[0].trim().toLowerCase(), doubArr);
                                    dos.writeUTF("Result: " + Double.toString(answer) + "\n"); //3.WRITE
                                    lw.logToFile(clientInput,Double.toString(answer));
                                }
                            } catch (EOFException EOFerr) {
                                System.out.println("<EOFErr> >>Client disconnected");
                                dis.close();
                                is.close();
                                dos.close();
                                os.close();
                                sock.close();
                                break;
                            } catch (NumberFormatException NFerr){
                                dos.writeUTF("<NFerr> Usage: <command> <list of numbers>\n"); //3.WRITE
                                continue;
                        }
                        }
                        dis.close();
                        is.close();
                        dos.close();
                        os.close();
                        sock.close();
                        } catch (IOException e) {
                            System.out.println(">>Client IO Error");
                            e.printStackTrace();
                        }
                    }
                server.close();
                System.out.printf(">>Stopping Calculator Server on Port %d\n",serverPort);    
            } else {
                System.out.println("Usage: <Server Port> <Log File Path>");
                System.exit(1);
            }
        } catch (NumberFormatException NFerr){
        System.out.println("Usage: <Server Port>");
        System.exit(1);
        } catch (IOException e) {
            System.out.println(">>Client IO Error");
            e.printStackTrace();
        }
    }
}