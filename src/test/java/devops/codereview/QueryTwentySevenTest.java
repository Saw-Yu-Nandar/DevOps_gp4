package devops.codereview;
//Unit testing for the population of a continent.
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
        PeoplePopulation popContip   = new PeoplePopulation();
        popContip.setContinentName("Oceania");
        popContip.setContinentPopulation("30401150");
        popContiPrint.add(popContip);
        app.printContinentPopulation(popContiPrint);
    }
}
