package devops.codereview;
//Unit testing for the population of the city.
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
        ArrayList<PeoplePopulation> popCityEmpt = new ArrayList<PeoplePopulation>();
        app.printCityPopulation(popCityEmpt);
    }

    //print a list with a null value
    @Test
    void printPopCityNull()
    {
        ArrayList<PeoplePopulation> popCityNull = new ArrayList<PeoplePopulation>();
        popCityNull.add(null);
        app.printCityPopulation(popCityNull);
    }

    @Test
    void printPopCity()
    {
        ArrayList<PeoplePopulation> popCityPrinnt = new ArrayList<PeoplePopulation>();
        PeoplePopulation popCityp = new PeoplePopulation();
        popCityp.setCityName("London");
        popCityp.setCityPopulation("7285000");
        popCityPrinnt.add(popCityp);
        app.printCityPopulation(popCityPrinnt);
    }
}
