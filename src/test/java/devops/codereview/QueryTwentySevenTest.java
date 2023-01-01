package devops.codereview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryTwentySevenTest {
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printPopContiTestEmpty()
    {
        ArrayList<PeoplePopulation> popContiEmpt = new ArrayList<PeoplePopulation>();
        app.printContinentPopulation(popContiEmpt);
    }

    //print a list with a null value
    @Test
    void printPopContiNull()
    {
        ArrayList<PeoplePopulation> popContiNull = new ArrayList<PeoplePopulation>();
        popContiNull.add(null);
        app.printContinentPopulation(popContiNull);
    }

    @Test
    void printPopConti()
    {
        ArrayList<PeoplePopulation> popContiPrint = new ArrayList<PeoplePopulation>();
        PeoplePopulation popConti   = new PeoplePopulation();
        popConti.setContinentName("Afghanistan");
        popConti.setContinentPopulation("22720000");
        popContiPrint.add(popConti);
        app.printContinentPopulation(popContiPrint);
    }
}
