package rev.animal.server;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.Map.entry;

public class Zoo {
    public static final String csvpath = "C:\\Users\\ftc_b\\GIT\\TFIP\\SDFREV\\animal\\zoo.csv";

    public static Map<String,Animal> buildZoo() throws IOException{

        Path p = Paths.get(csvpath); //zoo.csv
        File f = p.toFile();

        FileInputStream is = new FileInputStream(f); //Inputstream is the abstract class of FIS
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String line;
        String[] header = new String [18];
        boolean isHeader = false;
        Map<String,Animal> zoo = new HashMap<>();

        while ((line = br.readLine()) != null){
            if(!isHeader) {
                header = line.split(",");
                isHeader = true;
//                System.out.println(Arrays.toString(header));
                continue;
            }
            String[] row = line.split(",");
//            System.out.println(Arrays.toString(row));
            zoo.put(row[0], copyAnimal(row));
        }
//      Zoo.printZoo(zoo);
//      System.out.printf( ((zoo.get("aardvark")).toString()));

        br.close();
        isr.close();
        is.close();

        return zoo;
    }

    public static Animal copyAnimal(String[] row){
        String name = row[0];
        int classType = Integer.parseInt(row[17]);
        int legs = Integer.parseInt(row[13]);
        HashMap<String,Boolean> copy = new HashMap<>();
        for(int i = 1; i < 17;i++){
            if (i == 13) continue;
            copy.put((propertyList.get(i)),(row[i]).equals("1"));
        }
        return new Animal(name,classType,legs,copy);
    }

    public static Animal returnAnimal(Map<String,Animal>zoo , String request){
        return zoo.get(request);
    }

    public static Animal returnAnimal(Map<String,Animal>zoo, int request){
        Set<String> keys = zoo.keySet();
        String[] animals = keys.toArray(new String[zoo.size()]);
        return zoo.get(animals[request]);
    }

    private static final Map<Integer,String> propertyList = Map.ofEntries(
            entry(1, "hair"),
            entry(2, "feathers"),
            entry(3, "eggs"),
            entry(4, "milk"),
            entry(5, "airborne"),
            entry(6, "aquatic"),
            entry(7, "predator"),
            entry(8, "toothed"),
            entry(9,"backbone"),
            entry(10,"breathes"),
            entry(11,"venomous"),
            entry(12, "fins"),
            entry(14,"tail"),
            entry(15,"domestic"),
            entry(16, "catsize")
    );

    public static String getType(int type){
        String classType = "";
        switch(type){
            case 1:
                classType = "Mammal";
                break;
            case 2:
                classType = "Bird";
                break;
            case 3:
                classType = "Reptile";
                break;
            case 4:
                classType = "Fish";
                break;
            case 5:
                classType = "Amphibian";
                break;
            case 6:
                classType = "Bug";
                break;
            case 7:
                classType = "Invertebrate";
                break;
            default:
                classType = "Invalid";
            }
        return classType;
    }

    public static void printZoo(Map<String,Boolean> zoo){
        for (Object animal: zoo.values()) {
            System.out.printf("Name: %s || ",((Animal)animal).getName());
            System.out.printf("Legs: %d || ",((Animal)animal).getLegs());
            System.out.printf("Type: %s \n",Zoo.getType(((Animal)animal).getClassType()));
        }
    }
}


