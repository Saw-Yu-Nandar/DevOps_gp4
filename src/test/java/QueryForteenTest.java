import devops.codereview.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryForteenTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printTopNRegionsTestEmpty()
    {
        ArrayList<Regions> region = new ArrayList<Regions>();
        app.printTopNRegion(region);
    }

    //print a list with a null value
    @Test
    void printTopNRegionsTestContainsNull()
    {
        ArrayList<Regions> region = new ArrayList<Regions>();
        region.add(null);
        app.printTopNRegion(region);
    }
    @Test
    void printTopNRegions()
    {
        ArrayList<Regions> region = new ArrayList<Regions>();
        Regions reg = new Regions();
        reg.region = "Eastern Europe";
        reg.cityname = "Moscow";
        reg.countryname = "Russian Federation";
        reg.district = "Moscow (City)";
        reg.population = "8389200";
        region.add(reg);
        app.printTopNRegion(region);
    }
}