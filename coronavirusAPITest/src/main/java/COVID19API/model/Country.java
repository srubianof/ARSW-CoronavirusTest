package COVID19API.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Country implements Comparable<Country> {
    private String name;
    private int num_deaths = 0;
    private int num_infected = 0;
    private int num_cured = 0;
    private Location location = new Location(0, 0);
    private List<Province> provinces = new ArrayList<>();

    public Country(String name, int num_deaths, int num_infected, int num_cured, Location location) {
        this.name = name;
        this.num_deaths = num_deaths;
        this.num_infected = num_infected;
        this.num_cured = num_cured;
        this.location = location;
    }

    public Country() {
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum_deaths() {
        return num_deaths;
    }

    public void setNum_deaths(int num_deaths) {
        this.num_deaths = num_deaths;
    }

    public int getNum_infected() {
        return num_infected;
    }

    public void setNum_infected(int num_infected) {
        this.num_infected = num_infected;
    }

    public int getNum_cured() {
        return num_cured;
    }

    public void setNum_cured(int num_cured) {
        this.num_cured = num_cured;
    }

    public void addProvince(Province province) {
        this.provinces.add(province);
    }

    @Override
    public int compareTo(Country o) {
        int result = Comparator.comparing(Country::getNum_deaths).reversed()
                .thenComparing(Country::getNum_infected).reversed()
                .thenComparing(Country::getNum_cured).reversed().compare(this, o);
        return result;
    }
}
