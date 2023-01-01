package devops.codereview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryTwentyNineTest {
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printPopCountryTestEmpty()
    {
        ArrayList<PeoplePopulation> popCounEmp = new ArrayList<PeoplePopulation>();
        app.printCountriesPopulation(popCounEmp);
    }

    //print a list with a null value
    @Test
    void printPopCountryNull()
    {
        ArrayList<PeoplePopulation> popCounNull = new ArrayList<PeoplePopulation>();
        popCounNull.add(null);
        app.printCountriesPopulation(popCounNull);
    }

    @Test
    void printPopCountry()
    {
        ArrayList<PeoplePopulation> popCounPrint = new ArrayList<PeoplePopulation>();
        PeoplePopulation popCoun  = new PeoplePopulation();
        popCoun.setCountriesName("Rangoon (Yangon)");
        popCoun.setCountriesPopulation("3361700");
        popCounPrint.add(popCoun);
        app.printCountriesPopulation(popCounPrint);
    }
}