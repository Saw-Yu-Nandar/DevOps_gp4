import devops.codereview.*;
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
        ArrayList<Countries> country = new ArrayList<Countries>();
        app.printTopNCountries(country);
    }

    //print a list with a null value
    @Test
    void printTopNCountriesTestContainsNull()
    {
        ArrayList<Countries> country = new ArrayList<Countries>();
        country.add(null);
        app.printTopNCountries(country);
    }
    @Test
    void printTopNCountries()
    {
        ArrayList<Countries> country = new ArrayList<Countries>();
        Countries cty = new Countries();
        cty.cityname = "Moscow";
        cty.name = "Russian Federation";
        cty.district = "Moscow (City)";
        cty.population = "8389200";
        country.add(cty);
        app.printTopNCountries(country);
    }
}