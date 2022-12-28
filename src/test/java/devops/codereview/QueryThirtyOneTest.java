package devops.codereview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryThirtyOneTest {
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printPopCityTestEmpty()
    {
        ArrayList<PeoplePopulation> pop_city_empt = new ArrayList<PeoplePopulation>();
        app.printCityPopulation(pop_city_empt);
    }

    //print a list with a null value
    @Test
    void printPopCityNull()
    {
        ArrayList<PeoplePopulation> pop_city_null = new ArrayList<PeoplePopulation>();
        pop_city_null.add(null);
        app.printCityPopulation(pop_city_null);
    }

    @Test
    void printPopCity()
    {
        ArrayList<PeoplePopulation> pop_city_prinnt = new ArrayList<PeoplePopulation>();
        PeoplePopulation pop_city_p   = new PeoplePopulation();
        pop_city_p.cap_cit_name       = "London";
        pop_city_p.continent_population    = "7285000";
        pop_city_prinnt.add(pop_city_p);
        app.printCityPopulation(pop_city_prinnt);
    }
}
