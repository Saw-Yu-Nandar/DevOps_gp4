package devops.codereview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryThirteenTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printTopNContinentTestEmpty()
    {
        ArrayList<City> continent = new ArrayList<City>();
        app.printTopNContinent(continent);
    }

    //print a list with a null value
    @Test
    void printTopNContinentTestContainsNull()
    {
        ArrayList<City> continent = new ArrayList<City>();
        continent.add(null);
        app.printTopNContinent(continent);
    }
    @Test
    void printTopNContinent()
    {
        ArrayList<City> continent = new ArrayList<City>();
        City conti = new City();
        conti.setCitName("Moscow");
        conti.setCountryName("Russian Federation");
        conti.setCitDistrict("Moscow (City)");
        conti.setCitCont("Europe");
        conti.setCitPopulation("8389200");
        continent.add(conti);
        app.printTopNContinent(continent);
    }
}