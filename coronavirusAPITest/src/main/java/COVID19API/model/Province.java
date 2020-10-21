package COVID19API.model;

public class Province {
    private String name;
    private int num_deaths;
    private int num_infected;
    private int num_cured;
    private String city;

    public Province(String name, int num_deaths, int num_infected, int num_cured, String city) {
        this.name = name;
        this.num_deaths = num_deaths;
        this.num_infected = num_infected;
        this.num_cured = num_cured;
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
}
