import devops.codereview.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryTenTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printCountriesTestEmpty()
    {
        ArrayList<Countries> countries = new ArrayList<Countries>();
        app.printCountries(countries);
    }

    //print a list with a null value
    @Test
    void printAllCountriesTestContainsNull()
    {
        ArrayList<Countries> countries = new ArrayList<Countries>();
        countries.add(null);
        app.printCountries(countries);
    }
    @Test
    void printCountries()
    {
        ArrayList<Countries> countries = new ArrayList<Countries>();
        Countries co = new Countries();
        co.name = "Greece";
        co.cityname = "Europe";
        co.district = "Southern Europe";
        co.population = "94000";
        countries.add(co);
        app.printCountries(countries);
    }
}