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
        ArrayList<Country> nPopulatedRegion = new ArrayList<Country>();
        app.printNPopulatedRegion(nPopulatedRegion);
    }

    //print a list with a null value
    @Test
    void printNPopulatedRegionTestContainsNull()
    {
        ArrayList<Country> nPopulatedRegion = new ArrayList<Country>();
        nPopulatedRegion.add(null);
        app.printNPopulatedRegion(nPopulatedRegion);
    }

    @Test
    void printNPopulatedRegion()
    {
        ArrayList<Country> nPopulatedRegion = new ArrayList<Country>();
        Country npopreg = new Country();
        npopreg.setCountryName("Eastern Asia");
        npopreg.setCountryPopulation("1277558000");
        nPopulatedRegion.add(npopreg);
        app.printNPopulatedRegion(nPopulatedRegion);
    }
}