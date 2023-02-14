package sg.edu.nus.iss.app.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Cookie {

    public static String getRandomCookie(String pathStr, String copyPath, String Mergepath) throws IOException{
        String randomCookie = "";
        File cookieFile = new File(pathStr);
        List<String> cookies = new LinkedList<>();
        
        try{
        Scanner scanner = new Scanner(cookieFile);
        while(scanner.hasNextLine()){
            cookies.add(scanner.nextLine()); //don't have to split String, since you are  splitting by lines
        }
        scanner.close(); //scope is only in try?? don't need to close?
        } catch(FileNotFoundException fnfErr){
            fnfErr.printStackTrace();
        }

        Random rand = new Random();
        int cookieInt = rand.nextInt(cookies.size());
        randomCookie = cookies.get(cookieInt); //bound exclusive
        System.out.println("RANDOM COOKIE SENT>>" + randomCookie);
        
        writeCookieArray(copyPath,cookieInt,randomCookie); //Method 1
        writeCookieLines(copyPath,cookieInt,randomCookie); //Method 2
        return randomCookie;

    }
    
    //  Method 1: Using a Copied Array
    public static void writeCookieArray(String copyPath, int cookieInt, String randomCookie) throws IOException{
        System.out.printf("Cookie Array Writing to Cookie Copy:");
        Path p = Paths.get(copyPath);
        File f = p.toFile();
        InputStream is = new FileInputStream(f);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr); //buffered reader reads until next line
        String cookieLine;
        ArrayList<String> cookieCopy = new ArrayList<String>();
        while ((cookieLine = br.readLine()) != null){
            cookieCopy.add(cookieLine + "\n");
        }

        int numlines = cookieInt - cookieCopy.size();
        for(int i = 0; i < numlines; i++){ //will not execute if \n is negative
            cookieCopy.add("\n");
        }
        
        if(cookieInt<cookieCopy.size()){
            cookieCopy.set(cookieInt,randomCookie + "\n");
        } else {
            cookieCopy.add(randomCookie);
        }
       
        FileWriter cookieWriter = new FileWriter(f);
        for(int i = 0; i < cookieCopy.size();i++){
            cookieWriter.write(cookieCopy.get(i));
        }
        cookieWriter.close(); //cookiewriter must close file to save?
        br.close();
        isr.close();
        is.close();
    }

    // Method 2 Writing Line by Line to File
    public static void writeCookieLines(String copyPath, int cookieInt, String randomCookie) throws IOException{
        //has to keep writing a new file? 
            Path p = Paths.get(copyPath);
            File f = p.toFile();
            InputStream is = new FileInputStream(f);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
    
            FileWriter cookieWriter = new FileWriter("cookies-copy.txt");
            //only works when you write to another file
            //file write wipes entire file when opened with append false
            String cookieLine;
            int linecount = 0;
            int maxCookies = 27;
            System.out.printf("Cookie Lines Writing to Cookie Copy:");
            while (linecount<= maxCookies){ //CookieInt is zeroindexed, size is 27
                cookieLine = br.readLine();
                //readLine returns null if no char read, does not return \n
                System.out.print("\n>");
                linecount++;
                if ((linecount == cookieInt+1)){ //rewriting file so will just rewrite old existing cookie
                    System.out.print("<");
                    cookieWriter.write(randomCookie + "\n");
                }else if((cookieLine == null)&&(linecount<cookieInt+1)){
                    System.out.print("N1");
                    cookieWriter.write("\n");
                }else if((cookieLine == null)&&(linecount>cookieInt+1)){
                    //has to be infront because isempty als returns false on null
                System.out.print("N2");
                cookieWriter.write("");
                }else if(cookieLine.isEmpty()){
                    System.out.print("/");
                    cookieWriter.write(cookieLine + "\n");
                }else if(!cookieLine.isEmpty()){
                    System.out.print("-");
                    cookieWriter.write(cookieLine + "\n");

                }
            }
            cookieWriter.close();
            br.close();
            isr.close();
            is.close();
    
        }
    
}