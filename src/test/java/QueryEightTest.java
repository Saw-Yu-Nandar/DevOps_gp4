import devops.codereview.*;
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
        ArrayList<Continent> continent = new ArrayList<Continent>();
        app.printContinents(continent);
    }

    //print a list with a null value
    @Test
    void printAllContinentTestContainsNull()
    {
        ArrayList<Continent> continent = new ArrayList<Continent>();
        continent.add(null);
        app.printContinents(continent);
    }
    @Test
    void printContinent()
    {
        ArrayList<Continent> continent = new ArrayList<Continent>();
        Continent conti = new Continent();
        conti.continent = "Europe";
        conti.cityname = "Tilburg";
        conti.countryname = "Netherlands";
        conti.district = "Noord-Brabant";
        conti.population = "193238";
        conti.countrycode = "NLD";
        continent.add(conti);
        app.printContinents(continent);

    }
}