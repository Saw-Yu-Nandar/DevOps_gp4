package devops.codereview;

//Unit testing for all the capital cities in a region organised by largest population to smallest.
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryTwentyTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printTopNCapCities_WorldTestEmpty()
    {
        ArrayList<CapitalCities> cap_world = new ArrayList<CapitalCities>();
        app.printTopNCapCities_World(cap_world);
    }

    //print a list with a null value
    @Test
    void printTopNCapCities_WorldNull()
    {
        ArrayList<CapitalCities> cap_world = new ArrayList<CapitalCities>();
        cap_world.add(null);
        app.printTopNCapCities_World(cap_world);
    }

    @Test
    void printTopNCapCities_WorldRegion()
    {
        ArrayList<CapitalCities> cap_world = new ArrayList<CapitalCities>();
        CapitalCities capWld            = new CapitalCities();
        capWld.cap_cit_name             = "Peking";
        capWld.cap_cit_country          = "China";
        capWld.cap_cit_population       = "1277558000";
        cap_world.add(capWld);
        app.printAllCapitalCityRegion(cap_world);
    }
}
