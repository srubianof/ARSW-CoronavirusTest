package COVID19API.services;

import COVID19API.model.Case;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.*;

public interface CoronavirusStatServicesI {
    List<Case> getAllCases() throws UnirestException;

    List<Case> getCasesByCountry(String country) throws UnirestException;
}
