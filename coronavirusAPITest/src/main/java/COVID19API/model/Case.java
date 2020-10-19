package COVID19API.model;

public class Case {
    private String city;
    private String province;
    private String country;
    private String keyId;
    private Integer confirmed;
    private Integer deaths;

    public Case(Object city, Object province, String country, String keyId, Integer confirmed, Integer deaths, Integer recovered) {
        this.city = ((city == null) ? "" : (String) city);
        this.province = ((city == null) ? "" : (String) province);
        this.country = country;
        this.keyId = keyId;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = ((city == null) ? "" : city);
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = ((province == null) ? "" : province);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public Integer getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Integer confirmed) {
        this.confirmed = confirmed;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getRecovered() {
        return recovered;
    }

    public void setRecovered(Integer recovered) {
        this.recovered = recovered;
    }

    private Integer recovered;

}
