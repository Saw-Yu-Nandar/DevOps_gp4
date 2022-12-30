package devops.codereview;//Unit testing all the cities in a region organised by largest population to smallest.
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryNineTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printRegionTestEmpty()
    {
        ArrayList<City> regions = new ArrayList<City>();
        app.printRegions(regions);
    }

    //print a list with a null value
    @Test
    void printAllRegionsTestContainsNull()
    {
        ArrayList<City> regions = new ArrayList<City>();
        regions.add(null);
        app.printRegions(regions);
    }
    @Test
    void printRegions()
    {
        ArrayList<City> regions   = new ArrayList<City>();
        City reg                  = new City();
        reg.setCit_name("Luanda");
        reg.setCountry_name("Angola");
        reg.setCit_district("Luanda");
        reg.setCit_population("12878000");
        regions.add(reg);
        app.printRegions(regions);
    }
}