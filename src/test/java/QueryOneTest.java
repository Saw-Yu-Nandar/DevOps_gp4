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
        info.country_code ="MMR";
        info.country_name="Myanmar";
        info.country_cont="Asia";
        info.country_reg="Southeast Asia";
        info.country_population="2662000";
        info.country_cap="Yangon";
        countries.add(info);
        app.printAllCountries(countries);
    }
}