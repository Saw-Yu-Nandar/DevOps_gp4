import devops.codereview.*;
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
        ArrayList<Regions> regions = new ArrayList<Regions>();
        app.printRegions(regions);
    }

    //print a list with a null value
    @Test
    void printAllRegionsTestContainsNull()
    {
        ArrayList<Regions> regions = new ArrayList<Regions>();
        regions.add(null);
        app.printRegions(regions);
    }
    @Test
    void printRegions()
    {
        ArrayList<Regions> regions = new ArrayList<Regions>();
        Regions reg = new Regions();
        reg.region = "Central Africa";
        reg.cityname = "Luanda";
        reg.countryname = "Angola";
        reg.district = "Luanda";
        reg.population = "12878000";
        reg.countrycode = "AGO";
        regions.add(reg);
        app.printRegions(regions);
    }
}