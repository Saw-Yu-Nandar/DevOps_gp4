package devops.codereview;

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
        ArrayList<Country> NPopulated_Region = new ArrayList<Country>();
        app.printNPopulatedRegion(NPopulated_Region);
    }

    //print a list with a null value
    @Test
    void printNPopulatedRegionTestContainsNull()
    {
        ArrayList<Country> NPopulated_Region = new ArrayList<Country>();
        NPopulated_Region.add(null);
        app.printNPopulatedRegion(NPopulated_Region);
    }

    @Test
    void printNPopulatedRegion()
    {
        ArrayList<Country> NPopulated_Region = new ArrayList<Country>();
        Country npopreg = new Country();
        npopreg.setCountry_name("Eastern Asia");
        npopreg.setCountry_population("1277558000");
        NPopulated_Region.add(npopreg);
        app.printNPopulatedRegion(NPopulated_Region);
    }
}