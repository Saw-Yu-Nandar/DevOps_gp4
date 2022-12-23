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
        ArrayList<Cities> regions = new ArrayList<Cities>();
        app.printRegions(regions);
    }

    //print a list with a null value
    @Test
    void printAllRegionsTestContainsNull()
    {
        ArrayList<Cities> regions = new ArrayList<Cities>();
        regions.add(null);
        app.printRegions(regions);
    }
    @Test
    void printRegions()
    {
        ArrayList<Cities> regions = new ArrayList<Cities>();
        Cities reg = new Cities();
        reg.cit_name = "Luanda";
        reg.country_name = "Angola";
        reg.cit_district = "Luanda";
        reg.cit_population = "12878000";
        regions.add(reg);
        app.printRegions(regions);
    }
}