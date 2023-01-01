package devops.codereview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryFifteenTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printTopNCountriesTestEmpty()
    {
        ArrayList<City> country = new ArrayList<City>();
        app.printTopNCountries(country);
    }

    //print a list with a null value
    @Test
    void printTopNCountriesTestContainsNull()
    {
        ArrayList<City> country = new ArrayList<City>();
        country.add(null);
        app.printTopNCountries(country);
    }
    @Test
    void printTopNCountries()
    {
        ArrayList<City> country = new ArrayList<City>();
        City cty = new City();
        cty.setCityName("Moscow");
        cty.setCountryName("Russian Federation");
        cty.setCityDistrict("Moscow (City)");
        cty.setCityPopulation("8389200");
        country.add(cty);
        app.printTopNCountries(country);
    }
}