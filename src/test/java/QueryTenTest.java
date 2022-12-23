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
        ArrayList<Cities> countries = new ArrayList<Cities>();
        app.printCountries(countries);
    }

    //print a list with a null value
    @Test
    void printAllCountriesTestContainsNull()
    {
        ArrayList<Cities> countries = new ArrayList<Cities>();
        countries.add(null);
        app.printCountries(countries);
    }
    @Test
    void printCountries()
    {
        ArrayList<Cities> countries = new ArrayList<Cities>();
        Cities co = new Cities();
        co.cit_name = "Europe";
        co.country_name = "Greece";
        co.cit_district = "Southern Europe";
        co.cit_population = "94000";
        countries.add(co);
        app.printCountries(countries);
    }
}