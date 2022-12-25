package devops.codereview;

import devops.codereview.App;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryOneTest
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
        app.printAllCountries(countries);
    }
    @Test
    void printCountriesTestContainsNull()
    {
        ArrayList<Countries> countries = new ArrayList<Countries>();
        countries.add(null);
        app.printAllCountries(countries);
    }
    @Test
    void printCountries()
    {
        ArrayList<Countries> countries = new ArrayList<Countries>();
        Countries info = new Countries();
        info.country_name = "Brazil";
        info.country_population = "170115000";
        countries.add(info);
        app.printAllCountries(countries);
    }
}