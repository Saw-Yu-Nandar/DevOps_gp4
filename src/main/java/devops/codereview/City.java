package devops.codereview;

public class City {
    public String cit_name;
    public String country_name;
    public String cit_district;
    public String cit_population;

    public String getCit_name() {
        return cit_name;
    }

    public String getCountry_name() {
        return country_name;
    }

    public String getCit_district() {
        return cit_district;
    }

    public String getCit_population() {
        return cit_population;
    }

    public void setCit_name(String cit_name) {
        this.cit_name = cit_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public void setCit_district(String cit_district) {
        this.cit_district = cit_district;
    }

    public void setCit_population(String cit_population) {
        this.cit_population = cit_population;
    }
}