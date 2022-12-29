package devops.codereview;

//Unit testing for all the capital cities in a world organised by largest population to smallest.
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QuerySeventeenTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printCapitalCitiesTestEmpty()
    {
        ArrayList<CapitalCities> capital_cities = new ArrayList<CapitalCities>();
        app.printAllCapitalCities(capital_cities);
    }

    //print a list with a null value
    @Test
    void printAllContinentTestContainsNull()
    {
        ArrayList<CapitalCities> capital_cities = new ArrayList<CapitalCities>();
        capital_cities.add(null);
        app.printAllCapitalCities(capital_cities);
    }
    @Test
    void printContinent()
    {
        ArrayList<CapitalCities> capital_cities = new ArrayList<CapitalCities>();
        CapitalCities capcit            = new CapitalCities();
        capcit.cap_cit_name             = "Peking";
        capcit.cap_cit_country          = "China";
        capcit.cap_cit_population       = "1277558000";
        capital_cities.add(capcit);
        app.printAllCapitalCities(capital_cities);
    }
}