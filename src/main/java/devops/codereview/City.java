package devops.codereview;

public class City {
    public String cit_name;

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

    public String country_name;

    @Override
    public String toString() {
        return "City{" +
                "cit_name='" + cit_name + '\'' +
                ", country_name='" + country_name + '\'' +
                ", cit_district='" + cit_district + '\'' +
                ", cit_population='" + cit_population + '\'' +
                '}';
    }

    public String cit_district;
    public String cit_population;
}