package devops.codereview;
//Unit testing for all the countries in the region organised by largest population to smallest.
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryThreeTest
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
        ArrayList<Country> regions = new ArrayList<Country>();
        app.printRegion(regions);
    }

    //print a list with a null value
    @Test
    void printRegionTestContainsNull()
    {
        ArrayList<Country> regions = new ArrayList<Country>();
        regions.add(null);
        app.printRegion(regions);
    }
    @Test
    void printRegion()
    {
        ArrayList<Country> regions = new ArrayList<Country>();
        Country reg = new Country();
        reg.setCountryReg("British Islands");
        reg.setCountryPopulation("59623400");
        regions.add(reg);
        app.printRegion(regions);
    }
}