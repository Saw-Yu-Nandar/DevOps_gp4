import devops.codereview.App;
import devops.codereview.Continent;
import devops.codereview.Countries;
import devops.codereview.Regions;
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
        ArrayList<Regions> regions = new ArrayList<Regions>();
        app.printRegion(regions);
    }

    //print a list with a null value
    @Test
    void printRegionTestContainsNull()
    {
        ArrayList<Regions> regions = new ArrayList<Regions>();
        regions.add(null);
        app.printRegion(regions);
    }
    @Test
    void printRegion()
    {
        ArrayList<Regions> regions = new ArrayList<Regions>();
        Regions reg = new Regions();
        reg.region = "British Islands";
        reg.population = "59623400";
        regions.add(reg);
        app.printRegion(regions);
    }
}