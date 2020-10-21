package COVID19API.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class World {
    private List<Country> countries = new ArrayList<>();

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public void addCountry(Country country) {
        this.countries.add(country);
    }



}
