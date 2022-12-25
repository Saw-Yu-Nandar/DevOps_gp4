package devops.codereview;

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
        ArrayList<City> region = new ArrayList<City>();
        app.printTopNRegion(region);
    }

    //print a list with a null value
    @Test
    void printTopNRegionsTestContainsNull()
    {
        ArrayList<City> region = new ArrayList<City>();
        region.add(null);
        app.printTopNRegion(region);
    }
    @Test
    void printTopNRegions()
    {
        ArrayList<City> region = new ArrayList<City>();
        City reg = new City();
        reg.cit_name = "Moscow";
        reg.country_name = "Russian Federation";
        reg.cit_district = "Moscow (City)";
        reg.cit_population = "8389200";
        region.add(reg);
        app.printTopNRegion(region);
    }
}