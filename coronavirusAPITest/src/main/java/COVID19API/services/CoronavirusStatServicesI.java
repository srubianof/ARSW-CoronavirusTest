package COVID19API.services;

import COVID19API.model.Country;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.*;

public interface CoronavirusStatServicesI {
    List<Country> getAllCases() throws UnirestException;

    Country getCasesByCountry(String country) throws UnirestException;
}
