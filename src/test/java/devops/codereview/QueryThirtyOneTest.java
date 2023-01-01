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
        ArrayList<PeoplePopulation> popCityEmp = new ArrayList<PeoplePopulation>();
        app.printCityPopulation(popCityEmp);
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
        ArrayList<PeoplePopulation> popCityPrint = new ArrayList<PeoplePopulation>();
        PeoplePopulation popCity   = new PeoplePopulation();
        popCity.setCityName("London");
        popCity.setCityPopulation("7285000");
        popCityPrint.add(popCity);
        app.printCityPopulation(popCityPrint);
    }
}