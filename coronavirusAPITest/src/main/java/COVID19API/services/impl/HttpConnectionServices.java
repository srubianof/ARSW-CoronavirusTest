package COVID19API.services.impl;

import COVID19API.services.HttpConnectionServicesI;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class HttpConnectionServices implements HttpConnectionServicesI {

    @Override
    public JSONObject getAllCases() throws UnirestException {
        HttpResponse<String> response = Unirest
                .get("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats")
                .header("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
                .header("x-rapidapi-key", "619071a7efmshb6032a61efc2419p1df555jsnc91fcb67cc39")
                .asString();
        return new JSONObject(response.getBody()).getJSONObject("data");
    }

    @Override
    public JSONObject getCasesByCountry(String country) throws UnirestException {
        HttpResponse<String> response = Unirest
                .get("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats?country=" + country)
                .header("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
                .header("x-rapidapi-key", "34f05cff54msh30ba6f36c91c183p166499jsn555917ef62b8")
                .asString();
        return new JSONObject(response.getBody()).getJSONObject("data");
    }

    @Override
    public JSONArray getLocationCountry(String place) throws UnirestException {
        HttpResponse<String> response = Unirest.get("https://rapidapi.p.rapidapi.com/name/"+place)
                .header("x-rapidapi-host", "restcountries-v1.p.rapidapi.com")
                .header("x-rapidapi-key", "619071a7efmshb6032a61efc2419p1df555jsnc91fcb67cc39")
                .asString();
        if (response.getBody().contains("404")){
            return new JSONArray();
        }
        return new JSONArray(response.getBody()).getJSONObject(0).getJSONArray("latlng");
    }
}
