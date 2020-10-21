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
                world.addCountry(new Country(aCase.getCountry(), aCase.getDeaths(), aCase.getConfirmed(), recovered, new Location(0, 0)));

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
                            if (country1.getRecovered() != null) {
                                recovered = (country1.getRecovered());
                            }
                            Province province = new Province(country1.getProvince(), country1.getDeaths(), country1.getConfirmed(), recovered, country1.getCity());
                            death.addAndGet(country1.getDeaths());
                            infected.addAndGet(country1.getConfirmed());
                            cured.addAndGet(recovered);
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
        Collections.sort(world.getCountries());
        return world.getCountries();
    }

    @Override
    public Country getCasesByCountry(String country) throws UnirestException {
        List<Country> countries = this.getAllCases().stream().filter(country1 -> country1.getName().equals(country)).collect(Collectors.toList());
        System.out.println(country);
        countries.forEach(country1 -> {
            JSONArray coords = null;
            try {
                System.out.println(country1.getName());
                coords = httpConnectionService.getLocationCountry(country1.getName());
                System.out.println(coords);
            } catch (UnirestException e) {
                e.printStackTrace();
            }
            assert coords != null;
            country1.setLocation(new Location(coords.getDouble(0), coords.getDouble(1)));
        });
        return countries.get(0);
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
