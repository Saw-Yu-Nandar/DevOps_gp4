package devops.codereview;

import devops.codereview.App;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.synth.Region;
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
        reg.country_reg = "British Islands";
        reg.country_population = "59623400";
        regions.add(reg);
        app.printRegion(regions);
    }
}