package revision.pokemon.model;

import java.util.LinkedList;
import java.util.List;

public class Team {

    private List<Pokemon> team = new LinkedList<>();

    public List<Pokemon> getTeam() {
        return team;
    }
    public void setTeam(List<Pokemon> team) {
        this.team = team;
    }

    public void addPokemon(Pokemon pokemon){
        team.add(pokemon);
    }
    @Override
    public String toString() {
        return "Team [team=" + team + "]";
    }
    
}
