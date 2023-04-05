package revision.pokemon.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import revision.pokemon.model.Pokemon;
import revision.pokemon.model.Team;
import revision.pokemon.service.PokeService;

@Controller
@RequestMapping(path={"/","index.html","/buildTeam"})
public class PokeController {
    @Autowired
    private PokeService pokeSvc;
    
    @GetMapping
    public String index(Model model0, HttpSession session){

        Team team = (Team)session.getAttribute("team");
        if(null == team){
            team = new Team();
            System.out.println("New Team Created: Get");
            session.setAttribute("team",team);
        }
        model0.addAttribute("pokemon", new Pokemon());
        model0.addAttribute("team",team);
        return "view0";
    }

    @PostMapping
    public String view0(Model model0,@ModelAttribute @Valid Pokemon pokemon, BindingResult binding, HttpSession session){
        Team team = (Team)session.getAttribute("team");
        if(null == team){
            team = new Team();
            System.out.println("New Team Created: Post");
            session.setAttribute("team",team);
        }

        Optional<Pokemon> addpoke;
        addpoke = pokeSvc.createPokemon(pokemon.getName());
        if(addpoke.isEmpty()){
            binding.addError(new FieldError("pokemon","name","This Pokemon does not exist!"));
            System.out.println("Invalid Pokemon Requested");
            // binding.addError(new ObjectError("global","This Pokemon does not exist!"));
            model0.addAttribute("team",team);
            return "view0";
        } else {
            System.out.println("Added to Team");
            team.addPokemon(addpoke.get());
            System.out.println(team);
            model0.addAttribute("team",team);
        }
        model0.addAttribute("pokemon", new Pokemon());
        return "view0";
    }
    
}
