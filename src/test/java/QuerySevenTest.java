//Unit testing for all the cities in the world organised by largest population to smallest.
import devops.codereview.*;
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
}