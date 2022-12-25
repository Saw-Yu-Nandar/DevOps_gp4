package devops.codereview;//Unit testing for all the cities in a continent organised by largest population to smallest.
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryEightTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printContinentTestEmpty()
    {
        ArrayList<City> continent = new ArrayList<City>();
        app.printContinents(continent);
    }

    //print a list with a null value
    @Test
    void printAllContinentTestContainsNull()
    {
        ArrayList<City> continent = new ArrayList<City>();
        continent.add(null);
        app.printContinents(continent);
    }
    @Test
    void printContinent()
    {
        ArrayList<City> continent = new ArrayList<City>();
        City conti                = new City();
        conti.cit_name              = "Tilburg";
        conti.country_name          = "Netherlands";
        conti.cit_district          = "Noord-Brabant";
        conti.cit_population        = "193238";
        continent.add(conti);
        app.printContinents(continent);
    }
}