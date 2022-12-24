package devops.codereview;

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
        ArrayList<Cities> country = new ArrayList<Cities>();
        app.printTopNCountries(country);
    }

    //print a list with a null value
    @Test
    void printTopNCountriesTestContainsNull()
    {
        ArrayList<Cities> country = new ArrayList<Cities>();
        country.add(null);
        app.printTopNCountries(country);
    }
    @Test
    void printTopNCountries()
    {
        ArrayList<Cities> country = new ArrayList<Cities>();
        Cities cty = new Cities();
        cty.cit_name = "Moscow";
        cty.country_name = "Russian Federation";
        cty.cit_district = "Moscow (City)";
        cty.cit_population = "8389200";
        country.add(cty);
        app.printTopNCountries(country);
    }
}