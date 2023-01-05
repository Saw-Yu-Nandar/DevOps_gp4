package devops.codereview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryFourteenTest
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
        reg.setCitName("La Habana");
        reg.setCountryName("Cuba");
        reg.setCitDistrict("La Habana");
        reg.setCitReg("Caribbean");
        reg.setCitPopulation("2256000");
        region.add(reg);
        app.printTopNRegion(region);
    }
}