package devops.codereview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryTwoTest
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
        ArrayList<Country> continent = new ArrayList<Country>();
        app.printContinent(continent);
    }

    //print a list with a null value
    @Test
    void printContinentTestContainsNull()
    {
        ArrayList<Country> continent = new ArrayList<Country>();
        continent.add(null);
        app.printContinent(continent);
    }
    @Test
    void printContinent()
    {
        ArrayList<Country> continent = new ArrayList<Country>();
        Country cont = new Country();
        cont.setCountryCont("North America");
        cont.setCountryPopulation("103000");
        continent.add(cont);
        app.printContinent(continent);
    }
}