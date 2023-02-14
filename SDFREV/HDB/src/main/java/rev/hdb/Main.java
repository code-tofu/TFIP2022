package rev.hdb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class Main {

    public static void main(String[] args) {
        if(args.length<1){
            System.out.println("Usage <file.csv>");//HDB_Resale_With_Geocoordinates.csv
            System.exit(1);            
        }

        try{
            System.out.println("Reading:" + args[0]);
            Path p = Paths.get(args[0]);
            File f = p.toFile();
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            
    // Using ArrayList of String Arrays
            ArrayList <String[]> hdbList = new ArrayList<>();
            String csvline;
            String[] header = new String[14];
            // br.readLine(); //skipheaders
            boolean isHeader = false;
            Map<Integer,String> headerMap = new HashMap<>();
            double resalePriceTotalSum = 0.0;
            try{
                while ((csvline = br.readLine()) != null) {
                    if(!isHeader) {
                        header = csvline.split(",");
                        for(int i=0 ; i<header.length;i++) headerMap.put(i,header[i]);
                        isHeader = true;
                        System.out.println(Arrays.toString(header));
                        System.out.println(headerMap);
                        continue;
                    }
                    String[] cols = csvline.split(",");
                    
                    resalePriceTotalSum =  resalePriceTotalSum + Double.parseDouble(cols[10]);

                    hdbList.add(cols);
                }

                //Stats
                System.out.println(Integer.toString(hdbList.size()) + "Lines Read");
                System.out.println("Total Resale Value:" + Double.toString(resalePriceTotalSum));
                double resalePriceAverage = resalePriceTotalSum/hdbList.size();
                System.out.println("Average Resale Value:" + Double.toString(resalePriceAverage));
                System.out.println("StdDev Resale Value:" + Double.toString(resaleStdDev(hdbList,resalePriceAverage)));
                long twentyFivePctile = Math.round(0.25 * (hdbList.size()));

                

                //Create a Map of flats per Town
                Map<String, Integer> townFlats = flatsPerTown(hdbList);
                System.out.println(townFlats);
                int avgTownFlatNum = hdbList.size()/townFlats.size();
                System.out.println("Avg Flat Per Town:" + Integer.toString(avgTownFlatNum));
                //Flats by Value
                List<String> townFlatsByKey = new ArrayList<>(townFlats.keySet());
                Collections.sort(townFlatsByKey);
                System.out.println(townFlatsByKey);
                
                //Sorting the Hashmap - apparently doesn't work. perhaps it is ordered by the hash value
                //Use Treemap instead. Populate Treemap
                Map<String, Integer> townFlatsSortedByKey = new TreeMap<>();
                for(String townName: townFlatsByKey){
                    townFlatsSortedByKey.put(townName,townFlats.get(townName));
                }
                System.out.println(townFlatsSortedByKey); //By Key, not by value

                //Reverse Sorting of List
                Collections.sort(townFlatsByKey,Collections.reverseOrder());
                System.out.println(townFlatsByKey);

                //Sorting of Map Ascending
                List<Map.Entry<String,Integer>> entriesAsc  = new ArrayList<>(townFlats.entrySet());
                Collections.sort(entriesAsc, new Comparator<Entry<String, Integer>>() {
                    @Override
                    public int compare(
                      Entry<String, Integer> o1, Entry<String, Integer> o2) {
                        return o1.getValue().compareTo(o2.getValue());
                    }
                });
                Map<String, Integer> townFlatsSortedByValueAsc = new LinkedHashMap<>();
                for (Map.Entry<String, Integer> entry : entriesAsc) {
                    townFlatsSortedByValueAsc.put(entry.getKey(), entry.getValue());
                }
                System.out.println(townFlatsSortedByValueAsc);

                //Sorting of Map Descending
                List<Map.Entry<String,Integer>> entriesDesc  = new ArrayList<>(townFlats.entrySet());
                Collections.sort(entriesDesc, new Comparator<Entry<String, Integer>>() {
                    @Override
                    public int compare(
                      Entry<String, Integer> o1, Entry<String, Integer> o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                });
                Map<String, Integer> townFlatsSortedByValueDesc = new LinkedHashMap<>();
                for (Map.Entry<String, Integer> entry : entriesDesc) {
                    townFlatsSortedByValueDesc.put(entry.getKey(), entry.getValue());
                }
                System.out.println(townFlatsSortedByValueDesc);

                br.close();
                fr.close();
            } catch(IOException IOerr){
                IOerr.printStackTrace();
            }
        } catch (FileNotFoundException FNFerr){
            FNFerr.printStackTrace();
        }
    }

    public static Map<String, Integer> flatsPerTown(ArrayList<String[]> hdbList){
        Map<String, Integer> flatsPerTown = new HashMap<>();

        for(int i = 0; i < hdbList.size();i++){
        
            String[] row =  hdbList.get(i);
            String town = row[1];//row[1] is "town"
            if (!flatsPerTown.containsKey(town)) { 
                flatsPerTown.put(town, 1);
            } else {
                flatsPerTown.put(town, flatsPerTown.get(town) + 1);
            }
        }
        return flatsPerTown;
    }

    public static double resaleStdDev(ArrayList<String[]> hdbList, double mean){
        double stddev = 0.0;
        for(int i = 0; i < hdbList.size(); i++){
            String[] col = hdbList.get(i);
            stddev += Math.pow((Double.parseDouble(col[10]) - mean), 2);
        }
        return Math.sqrt(stddev /= hdbList.size());
    }


}
