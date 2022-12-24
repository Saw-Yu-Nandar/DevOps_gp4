package devops.codereview;

import devops.codereview.App;
import devops.codereview.Countries;
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
        ArrayList<Countries> regions = new ArrayList<Countries>();
        app.printRegion(regions);
    }

    //print a list with a null value
    @Test
    void printRegionTestContainsNull()
    {
        ArrayList<Countries> regions = new ArrayList<Countries>();
        regions.add(null);
        app.printRegion(regions);
    }
    @Test
    void printRegion()
    {
        ArrayList<Countries> regions = new ArrayList<Countries>();
        Countries reg = new Countries();
        reg.country_code ="GRL";
        reg.country_name="Greenland";
        reg.country_cont="North America";
        reg.country_reg="North America";
        reg.country_population="56000";
        reg.country_cap="Nuuk";
        regions.add(reg);
        app.printRegion(regions);
    }
}