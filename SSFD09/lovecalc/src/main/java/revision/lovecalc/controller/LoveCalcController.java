package revision.lovecalc.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import revision.lovecalc.model.Love;
import revision.lovecalc.service.LoveCalcService;

@Controller
@RequestMapping(path="/lovecalc")

public class LoveCalcController {
    @Autowired
    private LoveCalcService lcSvc;
    
    @GetMapping
    public String startLove(Model model){
        Love newLove = new Love();
        model.addAttribute("love",newLove);
        return "lovecalc";
    }

    @GetMapping("/result")
    public String getLove(@RequestParam(required=true) String sname,@RequestParam( required=true) String fname, Model model) throws IOException{
        System.out.println(">>Calling API");
        Optional<Love> newLove = lcSvc.getLove(sname, fname);
        model.addAttribute("love", newLove.get());
        return "result";
    }

    @GetMapping("/{loveId}")
    public String getMyLove(@PathVariable String loveId, Model model) throws IOException{
        System.out.println(">>Load:" + loveId);
        Optional<Love> myLove = lcSvc.loadLove(loveId);
        model.addAttribute("loveId", loveId);
        model.addAttribute("love", myLove.get());
        return "result";
    }

    @GetMapping("/allLove")
    public String allMyLove(Model model) throws IOException{
        System.out.println(">>Loading all loves");
        model.addAttribute("allLove", lcSvc.loadAllLove());
        return "all";
    }

    
}