package COVID19API.services.impl;

import COVID19API.model.Case;
import COVID19API.services.CoronavirusStatServicesI;
import COVID19API.services.HttpConnectionServicesI;
import com.google.gson.Gson;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoronavirusStatServices implements CoronavirusStatServicesI {
    @Autowired
    HttpConnectionServicesI httpConnectionService;

    @Override
    public List<Case> getAllCases() throws UnirestException {
        ArrayList<Case> cases = new ArrayList<>();
        JSONArray jsonArray = httpConnectionService.getAllCases().getJSONArray("covid19Stats");
        return getCases(cases, jsonArray);
    }

    @Override
    public List<Case> getCasesByCountry(String country) throws UnirestException {
        ArrayList<Case> cases = new ArrayList<>();
        JSONArray jsonArray = httpConnectionService.getCasesByCountry(country).getJSONArray("covid19Stats");
        return getCases(cases, jsonArray);
    }

    private List<Case> getCases(ArrayList<Case> cases, JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Gson gson = new Gson();
            cases.add(gson.fromJson(String.valueOf(jsonObject), Case.class));
        }
        return cases;
    }
    
}
