package pathIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        //remove file not found exception
        
        Path p = Paths.get(args[0]);{ //provides the CLI argument into Path object p
            //note uppercase P, 
            }
        
        File f = p.toFile(); //returns the file object
        //f.exists(), f.isFile(), f.isDirectory(), f.canWrite(), f.getAbsolutePath() 

        InputStream is = new FileInputStream(f);
        OutputStream os = new FileOutputStream("new.txt");
        byte[] buffer = new byte[1024]; //create a bite array (instead of reading 1 by 1 byte?)
        int size = 0;
        //read returns the number of bytes read, and returns -1 for EOF
        // //you don't really know where the text line ends...better for binary data
        // while (-1 != (size = is.read(buffer))){
        //     System.out.print(buffer);
        //     os.write(buffer, 0, size); //0 is offset
        // }

        while (size >=0){
            size = is.read(buffer);
            if(size>0)
                os.write(buffer,0,size); //start from zero index
        }
        
        os.flush();
        os.close();
        is.close();
        
}
}