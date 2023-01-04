package devops.codereview;//Unit testing for all the cities in the world organised by largest population to smallest.
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QuerySevenTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printAllCitiesTestEmpty()
    {
        ArrayList<City> cities = new ArrayList<City>();
        app.printCities(cities);
    }

    //print a list with a null value
    @Test
    void printAllCitiesTestContainsNull()
    {
        ArrayList<City> cities = new ArrayList<City>();
        cities.add(null);
        app.printCities(cities);
    }

    @Test
    void printAllCities()
    {
        ArrayList<City> cities    = new ArrayList<City>();
        City cit                  = new City();
        cit.setCit_name("Seoul");
        cit.setCountry_name("South Korea");
        cit.setCit_district("Seoul");
        cit.setCit_population("9981619");
        cities.add(cit);
        app.printCities(cities);
    }
}