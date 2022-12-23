import devops.codereview.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryTwelveTest
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
        ArrayList<Cities> worlds = new ArrayList<Cities>();
        app.printDistrict(worlds);
    }

    //print a list with a null value
    @Test
    void printAllCountriesTestContainsNull()
    {
        ArrayList<Cities> worlds = new ArrayList<Cities>();
        worlds.add(null);
        app.printDistrict(worlds);
    }
    @Test
    void printCountries()
    {
        ArrayList<Cities> worlds = new ArrayList<Cities>();
        Cities world = new Cities();
        world.cit_name = "Moscow";
        world.countryname = "Russian Federation";
        world.district = "Moscow (City)";
        world.cit_population = "8389200";
        worlds.add(world);
        app.printCities(worlds);
    }
}