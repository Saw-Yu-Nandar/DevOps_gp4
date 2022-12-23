import devops.codereview.*;
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
        ArrayList<Continent> continent = new ArrayList<Continent>();
        app.printTopNContinent(continent);
    }

    //print a list with a null value
    @Test
    void printTopNContinentTestContainsNull()
    {
        ArrayList<Continent> continent = new ArrayList<Continent>();
        continent.add(null);
        app.printTopNContinent(continent);
    }
    @Test
    void printTopNContinent()
    {
        ArrayList<Continent> continent = new ArrayList<Continent>();
        Continent conti = new Continent();
        conti.continent = "Europe";
        conti.cityname = "Moscow";
        conti.countryname = "Russian Federation";
        conti.district = "Moscow (City)";
        conti.population = "8389200";
        continent.add(conti);
        app.printTopNContinent(continent);
    }
}