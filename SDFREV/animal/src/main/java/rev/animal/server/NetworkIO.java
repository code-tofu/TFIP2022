package rev.animal.server;

import java.io.*;
import java.net.Socket;

public class NetworkIO {
    private InputStream is;
    private DataInputStream dis;
    private OutputStream os;
//    private DataOutputStream dos;
    private ObjectOutputStream oos;

    public NetworkIO(Socket sock) throws IOException{
        is = sock.getInputStream();
        dis = new DataInputStream(is);
        os = sock.getOutputStream();
//        dos = new DataOutputStream(os);
        oos = new ObjectOutputStream(os);
    }

    public String readIS() throws IOException{
        return dis.readUTF();
    }

    public void writeOOS(Object output) throws IOException{
        oos.writeObject(output);
    }

    public void closeIO() throws IOException{
        dis.close();
        is.close();
//        dos.close();
        oos.close();
        os.close();

    }
}
