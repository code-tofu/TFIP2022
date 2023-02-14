package rev.calc.server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class LogWriter {

    private String logFilePath = null;

    

    public LogWriter(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public void logToFile(String clientInput, String calcResult) throws IOException{

        
        Path p = Paths.get(logFilePath);
        File f = p.toFile();

        FileWriter fw = new FileWriter(f,true);
        Date date = new Date();
        fw.write("<Client-" + date.toString() +"> " + clientInput + "\n");
        fw.write("<Server-" + date.toString() +"> Result:" + calcResult + "\n");
        fw.close();
    }
    
}
