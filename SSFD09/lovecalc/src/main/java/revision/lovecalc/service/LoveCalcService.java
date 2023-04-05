package revision.lovecalc.service;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import revision.lovecalc.model.Love;
import revision.lovecalc.repository.LoveRepo;

@Service
public class LoveCalcService {

    @Autowired
    private LoveRepo loveRepo;

    //define api details
    @Value("${revision.lovecalc.api.url}")
    private String loveCalcUrl;
    @Value("${revision.lovecalc.api.key}")
    private String loveCalcKey;

    public Optional<Love> getLove(String sname, String fname) throws IOException{
        System.out.println(">>URL:" + loveCalcUrl);
        System.out.println(">>KEY:" + loveCalcKey);

        String loveURL = UriComponentsBuilder.fromUriString("https://" + loveCalcUrl + "/getPercentage")
                                            .queryParam("sname",sname)
                                            .queryParam("fname",fname)
                                            .toUriString();

        System.out.println(">>URI:" + loveURL);

        RestTemplate restTemplate= new RestTemplate();
        RequestEntity req = RequestEntity.get(loveURL)
                                    .header("X-Rapidapi-Key",loveCalcKey)
                                    .header("X-Rapidapi-Host",loveCalcUrl)
                                    .build();

        ResponseEntity<String> rep  = restTemplate.exchange(req,String.class);
        System.out.println(rep.getBody());
        loveRepo.saveLoveDB(rep.getBody());
        Love myLove = Love.createLove(rep.getBody()); //also prints object
        if(myLove != null) return Optional.of(myLove);
        return Optional.empty(); 
    }

    public Optional<Love> loadLove(String loveId) throws IOException{
        return loveRepo.loadLoveDB(loveId);
     }

    public Map<String,Love> loadAllLove() throws IOException{
        return loveRepo.loadAllLoveDB();
    }
    
}
