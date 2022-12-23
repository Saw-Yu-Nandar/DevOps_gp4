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
        ArrayList<Cities> cities = new ArrayList<Cities>();
        Cities cit = new Cities();
        cit.cit_name = "Amsterdam";
        cit.cit_population = "731200";
        cities.add(cit);
        app.printCities(cities);
    }
}