package revision.starbucks.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import revision.starbucks.model.Starbucks;

@Controller
@RequestMapping("/Starbucks")
public class StarbucksController {

    private static final String KENURL = "https://quotation-production.up.railway.app/quotation";
    private static final String USERNAME = "tester";
    private static final String POKIURL = "https://pokeapi.co/api/v2/pokemon/ditto";
    private static final String JSONURL = "https://jsonplaceholder.typicode.com/posts";


    //print the body
    @GetMapping("/Get1a")
    public static @ResponseBody String starbucksGet1a(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.getForEntity(JSONURL,String.class);
        return resp.toString();
    }


    //printing the full http 
    @GetMapping("/Get1b")
    public static @ResponseBody ResponseEntity<String> starbucksGet1b(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.getForEntity(JSONURL,String.class);
        return resp;
    }


    //extracting the headers
    @GetMapping("/Get1c")
    public static @ResponseBody String starbucksGet1c(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = restTemplate.headForHeaders(JSONURL);
        return httpHeaders.toString();
    }

    //get to object
    @GetMapping("/Get2")
    public static String starbucksGet2(Model model){
        RestTemplate restTemplate = new RestTemplate();
        Starbucks starbucks = restTemplate.getForObject(JSONURL, Starbucks.class);
        model.addAttribute("starbucks", starbucks);
        return "get2";
    }

    //exchange to response entity.
    @GetMapping("/Get3a")
    public static @ResponseBody ResponseEntity<String> starbucksGet3a(){
        RestTemplate restTemplate = new RestTemplate();
        //create a RequestEntity to be used with exchange() method and url builder, get method is embedded in RequestEntity
        RequestEntity<Void> req = RequestEntity.get(JSONURL).accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity<String> resp = restTemplate.exchange(req,String.class);
        return resp;
    }

    //exchange to object 
    @GetMapping("/Get3b")
    public static String starbucksGet3b(Model model){
        RestTemplate restTemplate = new RestTemplate();
        //create a RequestEntity to be used with exchange() method and url builder, get method is embedded in RequestEntity
        RequestEntity<Void> req = RequestEntity.get(JSONURL).accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity<Starbucks> resp = restTemplate.exchange(req,Starbucks.class);
        model.addAttribute("starbucks", resp.getBody());
        return "get2";
    }

    // using post request with exchange. post method an header is embeddedd in RequestEntity
    @GetMapping("/Get4")
    public static @ResponseBody ResponseEntity<String> starbucksGet4(){
        RestTemplate restTemplate = new RestTemplate();
        JsonObject jsonStarbucks = Json.createObjectBuilder()
                                        .add("name","Latte")
                                        .add("price","7.99")
                                        .add("username", USERNAME).build();
        System.out.println(jsonStarbucks.toString());
        RequestEntity<String> req = RequestEntity.post(KENURL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(jsonStarbucks.toString(),String.class);

        System.out.println(req.toString());
        ResponseEntity<String> resp = restTemplate.exchange(req, String.class);
        return resp;
    }

    @GetMapping("/{path}")
    public static @ResponseBody String starbucksPathParam(@PathVariable String path, @RequestParam(name="par1", defaultValue="default") String param1, @RequestParam(required= true, name="par2") String param2){
        System.out.println(param1 + param2 + path);
        return param1 + param2 + path;
    }

}
/*
{
	"name": "Milo",
	"price": "3.3",
	"username": "Change this !!"
}
 */