package playstore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        int catCol = 1;
        int ratingCol = 2;
        int nameCol = 0;
        float maxRating = 0f;
        float minRating = 5f;
        String maxApp ="";
        String minApp ="";
        int discard = 0;
        //must be defined in main function rather than class?
        // is there const? use final int catCol
        
        

        //read file name from args
        Path p = Paths.get(args[0]); //p is the file path
        File f = p.toFile();//returns file from specified path
        System.out.printf("Open file %s\n",args[0]);

        InputStream is = new FileInputStream(f);
        InputStreamReader isr = new InputStreamReader(is);
        // file reader only reads character by character
        BufferedReader br = new BufferedReader(isr);

        String appDetail;
        Map<String, Integer> catCount = new HashMap<>(); //catgegory (str) ,rating (int)
        Map<String, Float> ratingTotal = new HashMap<>();
        while ((appDetail = br.readLine()) != null){
            String[] details = appDetail.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); //cannot split by , alone because some titles have comma in them   

            //skip heading column - use .readLine()
            if(details[ratingCol].equals("Rating")){continue;}
            // skip rating NaN rows
            if(details[ratingCol].equals("NaN")){discard++;continue;}
            // skip rating move than 5.0 or less than 9
            if((Float.parseFloat(details[ratingCol])>5.0) || Float.parseFloat(details[ratingCol])<0.0){discard++;continue;}

            //finds and stores max and min
            if (Float.parseFloat(details[ratingCol]) > maxRating){
                maxRating = Float.parseFloat(details[ratingCol]);
                maxApp = details[nameCol];
            }
            if (Float.parseFloat(details[ratingCol]) < minRating){
                minRating = Float.parseFloat(details[ratingCol]);
                minApp = details[nameCol];
            }                

            //populates total count map
            if (!catCount.containsKey(details[catCol])) {
                catCount.put(details[catCol], 1); //if does not have key, create key
            } else {
                catCount.put(details[catCol], catCount.get(details[catCol]) + 1);
            }
            // //  populates total rating map
            if (!ratingTotal.containsKey(details[catCol])) {
                ratingTotal.put(details[catCol], Float.parseFloat(details[ratingCol])); //if does not have key, create key
            } else {
                ratingTotal.put(details[catCol], (ratingTotal.get(details[catCol]) + Float.parseFloat(details[ratingCol])));
            }
           
        }
        
        //print stats
        System.out.printf("\n**TABLE OF COUNTS**\n");
        Set<String> categories = catCount.keySet(); //use set because unique
        for (String c: categories) {
            System.out.printf(">%s : %d\n", c, catCount.get(c));
        }
        System.out.printf("\n**TABLE OF TOTALS**\n");
        Set<String> totalRatings = ratingTotal.keySet(); //use set because unique
        for (String r: totalRatings) {
            System.out.printf(">%s : %f\n", r, ratingTotal.get(r));
        }
        System.out.printf("\n**TABLE OF AVERAGES**\n");
        // Map<String, Float> ratingAverage = new HashMap<>();
        Set<String> average = catCount.keySet(); //use set because unique
        for (String a: average) {
            System.out.printf(">%s has an average rating of %f\n", a,(ratingTotal.get(a))/catCount.get(a));
        }
        System.out.printf("\n**MAX AND MIN**\n");
        System.out.printf(">Highest Rated App is %s at %.1f stars\n",maxApp,maxRating);
        System.out.printf(">Lowest Rated App is %s at %.1f stars\n",minApp,minRating);
        System.out.printf(">Discarded %d entries\n",discard);
    
    br.close();
    isr.close();
    is.close();
    
    }




}

    
/* 
System.out.printf("> %s\n", appDetail); //check read csv
System.out.printf("%s/",details[ratingCol]); 
System.out.printf("%s/",details[ratingCol]);                
System.out.printf("%s/",details[catCol]); //print the cat element of read string array
for(String s: details){ System.out.printf("%s/",s); //check string array

//"K-Spapp, the K-Space app",FAMILY,4.8,39,3.6M,"1,000+",Free,0,Everyone,Education,"February 9, 2017",1.53,4.0.3 and up




*/