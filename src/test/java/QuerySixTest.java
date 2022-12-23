import devops.codereview.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QuerySixTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printNPopulatedRegionTestEmpty()
    {
        ArrayList<NPopulatedRegion> NPopulated_Region = new ArrayList<NPopulatedRegion>();
        app.printNPopulatedRegion(NPopulated_Region);
    }

    //print a list with a null value
    @Test
    void printNPopulatedRegionTestContainsNull()
    {
        ArrayList<NPopulatedRegion> NPopulated_Region = new ArrayList<NPopulatedRegion>();
        NPopulated_Region.add(null);
        app.printNPopulatedRegion(NPopulated_Region);
    }

    @Test
    void printNPopulatedRegion()
    {
        ArrayList<NPopulatedRegion> NPopulated_Region = new ArrayList<NPopulatedRegion>();
        NPopulatedRegion npopreg = new NPopulatedRegion();
        npopreg.reg_name = "Eastern Asia";
        npopreg.reg_population = "1277558000";
        NPopulated_Region.add(npopreg);
        app.printNPopulatedRegion(NPopulated_Region);
    }
}