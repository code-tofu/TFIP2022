package revision.pokemon.model;

public class Pokemon {

    private String name;
    private int pokeNumber;
    private double height;
    private double weight;

    private String pokeJSON;

    @Override
    public String toString() {
        return "Pokemon [name=" + name + ", pokeNumber=" + pokeNumber + ", height=" + height + ", weight=" + weight
                + "]";
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getPokeNumber() {
        return pokeNumber;
    }

    public void setPokeNumber(int pokeNumber) {
        this.pokeNumber = pokeNumber;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    public String getPokeJSON() {
        return pokeJSON;
    }
    public void setPokeJSON(String pokeJSON) {
        this.pokeJSON = pokeJSON;
    }

    

    
    
}
