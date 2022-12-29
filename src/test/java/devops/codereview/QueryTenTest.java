package devops.codereview;//Unit testing for all the cities in a country organised by largest population to smallest.
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
        ArrayList<City> countries = new ArrayList<City>();
        app.printCountries(countries);
    }

    //print a list with a null value
    @Test
    void printAllCountriesTestContainsNull()
    {
        ArrayList<City> countries = new ArrayList<City>();
        countries.add(null);
        app.printCountries(countries);
    }
    @Test
    void printCountries()
    {
        ArrayList<City> countries = new ArrayList<City>();
        City co                   = new City();
        co.cit_name                 = "Europe";
        co.country_name             = "Greece";
        co.cit_district             = "Southern Europe";
        co.cit_population           = "94000";
        countries.add(co);
        app.printCountries(countries);
    }
}