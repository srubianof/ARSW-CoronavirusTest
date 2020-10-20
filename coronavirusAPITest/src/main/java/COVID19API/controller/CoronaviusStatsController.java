package COVID19API.controller;

import COVID19API.model.Case;
import COVID19API.model.Country;
import COVID19API.services.CoronavirusStatServicesI;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("v1")
public class CoronaviusStatsController {

    @Autowired
    CoronavirusStatServicesI coronavirusStatServices;

    @GetMapping()
    public ResponseEntity<?> getAllCases() {
        List<Country> cases;
        try {
            cases = coronavirusStatServices.getAllCases();
        } catch (UnirestException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<Country>>(cases, HttpStatus.OK);
    }

    @GetMapping("stats")
    public ResponseEntity<?> getCasesByCountry(@RequestParam String country) {
        List<Country> cases = new ArrayList<>();
        try {
            cases = coronavirusStatServices.getCasesByCountry(country);
        } catch (UnirestException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<Country>>(cases, HttpStatus.OK);
    }
}
