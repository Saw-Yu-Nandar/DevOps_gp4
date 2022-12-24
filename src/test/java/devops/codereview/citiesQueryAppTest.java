package devops.codereview;
import devops.codereview.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class citiesQueryAppTest
{
    static App app;
    @BeforeAll
    static void init()
    {
        app = new App();
    }
    //Unit testing for all the cities in the world organised by largest population to smallest.
    @Test
    void printAllCitiesTestEmpty()
    {
        ArrayList<Cities> cities = new ArrayList<Cities>();
        app.printCities(cities);
    }
    //print a list with a null value
    @Test
    void printAllCitiesTestContainsNull()
    {
        ArrayList<Cities> cities = new ArrayList<Cities>();
        cities.add(null);
        app.printCities(cities);
    }
    @Test
    void printAllCities()
    {
        ArrayList<Cities> cities    = new ArrayList<Cities>();
        Cities cit                  = new Cities();
        cit.cit_name                = "Seoul";
        cit.country_name            = "South Korea";
        cit.cit_district            = "Seoul";
        cit.cit_population          = "9981619";
        cities.add(cit);
        app.printCities(cities);
    }
    //Unit testing for all the cities in a continent organised by largest population to smallest.
    @Test
    void printContinentTestEmpty()
    {
        ArrayList<Cities> continent = new ArrayList<Cities>();
        app.printContinents(continent);
    }
    //print a list with a null value
    @Test
    void printAllContinentTestContainsNull()
    {
        ArrayList<Cities> continent = new ArrayList<Cities>();
        continent.add(null);
        app.printContinents(continent);
    }
    @Test
    void printContinent()
    {
        ArrayList<Cities> continent = new ArrayList<Cities>();
        Cities conti                = new Cities();
        conti.cit_name              = "Tilburg";
        conti.country_name          = "Netherlands";
        conti.cit_district          = "Noord-Brabant";
        conti.cit_population        = "193238";
        continent.add(conti);
        app.printContinents(continent);
    }
}