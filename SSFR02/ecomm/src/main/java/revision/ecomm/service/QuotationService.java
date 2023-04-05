package revision.ecomm.service;

import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import revision.ecomm.model.Order;
import revision.ecomm.model.Quotation;
import revision.ecomm.util.EcommUtil;

@Service
public class QuotationService {

    @Value("${revision.ecomm.typi.api.url}")
    private String qsysURLtypi;

    @Value("${revision.ecomm.nut.api.url}")
    private String qsysURLnut;


    public Quotation getQuotations(List<String> items) throws Exception{

        RestTemplate restTemplate = new RestTemplate();

        //assemble JSON array from items
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder(items);
            // for(String item:items){
            //     arrBuilder.add(item);
            // }
        JsonArray itemsJsonArr = arrBuilder.build();

        System.out.println(itemsJsonArr.toString());
        System.out.println(">>ARRAY BUILT");

        RequestEntity<String> req = RequestEntity.post(qsysURLnut)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(itemsJsonArr.toString(),String.class);

        //TODO-implement optional
        ResponseEntity<String> resp  = restTemplate.exchange(req,String.class);

        String respJsonStr = resp.getBody();
        JsonObject quoteJson = EcommUtil.getJSONObj(respJsonStr);
        try{
            String errorMsg = quoteJson.getString("error");
            throw new HttpServerErrorException(HttpStatusCode.valueOf(500), errorMsg);

        }catch(NullPointerException npErr){

            JsonArray quotations = quoteJson.getJsonArray("quotations");

            Quotation quoteObj = new Quotation();
            quoteObj.setQuoteId(quoteJson.getString("quoteId"));

            for(int i=0; i < quotations.size(); i++){
                JsonObject quote = quotations.getJsonObject(i); //	getJsonObject(int index)
                quoteObj.addQuotation(items.get(i),(float)quote.getJsonNumber(items.get(i)).doubleValue());
            }
            return quoteObj;
            }
        }
		// json.getJsonArray("quotations").stream()
		// 	.map(i -> i.asJsonObject()) //asJsonObject is from Javax, Glassfish inherits from Javax
		// 	.forEach(i -> {
		// 		quotation.addQuotation(i.getString("item"), (float)i.getJsonNumber("unitPrice").doubleValue());
		// 	});

        // "quotations": [
        // { 	"name": "apple",
        // 	"unitprice" : 1.5
        // },
        // { 	"name": "orange",
        // 	"unitprice" : 1.8
        // },
        // ....]


        public String getQuotationTest(List<String> items){

            RestTemplate restTemplate = new RestTemplate();
    
            //assemble JSON array from items
            JsonArrayBuilder arrBuilder = Json.createArrayBuilder(items);
                // for(String item:items){
                //     arrBuilder.add(item);
                // }
            JsonArray itemsJsonArr = arrBuilder.build();
    
            System.out.println(itemsJsonArr.toString());
            System.out.println(">>ARRAY BUILT");
    
            RequestEntity<String> req = RequestEntity.post(qsysURLnut)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(itemsJsonArr.toString(),String.class);
    
            ResponseEntity<String> resp  = restTemplate.exchange(req,String.class);
            return resp.getBody();
        }

        public static double calculateTotal(Order myOrder, Quotation myQuote){
            double total = 0.0;
            for(String item : myQuote.getQuotations().keySet()){
                //qty*price
                int qty = myOrder.getOrderList().get(item);
                float unitPrice = myQuote.getQuotations().get(item);
                total += (double)(qty * unitPrice);
            }
            return total;
        }
}

