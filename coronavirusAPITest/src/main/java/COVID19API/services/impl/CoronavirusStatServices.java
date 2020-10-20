package COVID19API.services.impl;

import COVID19API.model.*;
import COVID19API.services.*;
import com.google.gson.Gson;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class CoronavirusStatServices implements CoronavirusStatServicesI {
    @Autowired
    HttpConnectionServicesI httpConnectionService;

    @Override
    public List<Country> getAllCases() throws UnirestException {
        World world = new World();
        JSONArray jsonArray = httpConnectionService.getAllCases().getJSONArray("covid19Stats");
        List<Case> cases = getCases(new ArrayList<>(), jsonArray);
        for (Case aCase : cases) {
            if (aCase.getProvince() == null) {
                int recovered = 0;
                if (aCase.getRecovered() != null) {
                    recovered = aCase.getRecovered();
                }
                world.addCountry(new Country(aCase.getCountry(), aCase.getDeaths(), aCase.getConfirmed(), recovered));
            } else {

                Country country = new Country();
                if (world.getCountries().stream().map(Country::getName).noneMatch(aCase.getCountry()::equals)) {
                    AtomicInteger death = new AtomicInteger(0);
                    AtomicInteger infected = new AtomicInteger(0);
                    AtomicInteger cured = new AtomicInteger(0);
                    country.setName(aCase.getCountry());
                    cases.forEach(country1 -> {
                        int recovered = 0;
                        if (country1.getCountry().equals(aCase.getCountry())) {
                            Province province = new Province();
                            province.setName(country1.getProvince());
                            if (country1.getRecovered() != null) {
                                recovered = (country1.getRecovered());
                            }
                            death.addAndGet(country1.getDeaths());
                            infected.addAndGet(country1.getConfirmed());
                            cured.addAndGet(recovered);
                            province.setCity(country1.getCity());
                            province.setNum_cured(recovered);
                            province.setNum_deaths(country1.getDeaths());
                            province.setNum_infected(country1.getConfirmed());
                            country.addProvince(province);
                        }
                    });
                    country.setNum_deaths(death.get());
                    country.setNum_infected(infected.get());
                    country.setNum_cured(cured.get());
                    world.addCountry(country);
                }
            }

        }
        return world.getCountries();
    }

    @Override
    public List<Country> getCasesByCountry(String country) throws UnirestException {
        System.out.println(country);
        return this.getAllCases().stream().filter(country1 -> country1.getName().equals(country)).collect(Collectors.toList());
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
