package devops.codereview;

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
        ArrayList<Countries> NPopulated_Region = new ArrayList<Countries>();
        app.printNPopulatedRegion(NPopulated_Region);
    }

    //print a list with a null value
    @Test
    void printNPopulatedRegionTestContainsNull()
    {
        ArrayList<Countries> NPopulated_Region = new ArrayList<Countries>();
        NPopulated_Region.add(null);
        app.printNPopulatedRegion(NPopulated_Region);
    }

    @Test
    void printNPopulatedRegion()
    {
        ArrayList<Countries> NPopulated_Region = new ArrayList<Countries>();
        Countries npopreg = new Countries();
        npopreg.country_code ="SGP";
        npopreg.country_name="Singapore";
        npopreg.country_cont="Asia";
        npopreg.country_reg="Southeast Asia";
        npopreg.country_population="3567000";
        npopreg.country_cap="Hougang";
        NPopulated_Region.add(npopreg);
        app.printNPopulatedRegion(NPopulated_Region);
    }
}