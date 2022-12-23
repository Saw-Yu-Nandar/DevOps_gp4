import devops.codereview.App;
import devops.codereview.Countries;
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
        app.printCountries(countries);
    }
    @Test
    void printCountriesTestContainsNull()
    {
        ArrayList<Countries> countries = new ArrayList<Countries>();
        countries.add(null);
        app.printCountries(countries);
    }
    @Test
    void printCountries()
    {
        ArrayList<Countries> countries = new ArrayList<Countries>();
        Countries info = new Countries();
        info.name = "Brazil";
        info.population = "170115000";
        countries.add(info);
        app.printCountries(countries);
    }
}