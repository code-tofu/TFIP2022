package revision.lovecalc.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Love implements Serializable{

    private String fname;
    private String sname;
    private int percentage;
    private String result;

    //GETTERS AND SETTERS
    public String getFname() {
        return fname;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    public String getSname() {
        return sname;
    }
    public void setSname(String sname) {
        this.sname = sname;
    }
    public int getPercentage() {
        return percentage;
    }
    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }


    //TOSTRING
    @Override
    public String toString() {
        return "Love [fname=" + fname + ", sname=" + sname + ", percentage=" + percentage + ", result=" + result + "]";
    }
    
    //JSON
    public static Love createLove(String jsonStrLove) throws IOException{
        Love newLove = new Love();
        try(InputStream is = new ByteArrayInputStream(jsonStrLove.getBytes())){
            JsonReader jsonR = Json.createReader(is);
            JsonObject jsonLove = jsonR.readObject();
            newLove.setFname(jsonLove.getString("fname"));
            newLove.setSname(jsonLove.getString("sname"));
            newLove.setPercentage(Integer.parseInt(jsonLove.getString("percentage")));
            newLove.setResult(jsonLove.getString("result"));
        }
            System.out.println(newLove);
            return newLove;   
        }


}

/*JSON format from API
fname:"John"
sname:"Alice"
percentage:"46"
result:"Can choose someone better."
 */