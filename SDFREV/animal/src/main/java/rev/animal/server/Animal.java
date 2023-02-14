package rev.animal.server;

import java.util.HashMap;

public class Animal {
    String name;
    int classType;
    int legs;
    HashMap<String,Boolean> properties;



    public Animal(String name, int classType, int legs, HashMap<String,Boolean>properties) {
        this.name = name;
        this.classType = classType;
        this.legs = legs;
        this.properties = properties;
    }



    //TODO: how do i use this? might have to use arraylist with get/set methods for each index
    public void setProperties(HashMap<String,Boolean> properties) {
        this.properties = properties;

    }

    public String getName() {
        return name;
    }

    public int getClassType() {
        return classType;
    }

    public int getLegs() {
        return legs;
    }

    public HashMap<String,Boolean> getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", classType=" + Zoo.getType(classType) +
                ", legs=" + legs +
                ", properties=" + properties +
                '}';
    }


}