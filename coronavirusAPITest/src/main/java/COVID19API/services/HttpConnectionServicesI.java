package COVID19API.services;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

public interface HttpConnectionServicesI {
    JSONObject getAllCases() throws UnirestException;

    JSONObject getCasesByCountry(String country) throws UnirestException;

    JSONArray getLocationCountry(String country) throws UnirestException;
}
