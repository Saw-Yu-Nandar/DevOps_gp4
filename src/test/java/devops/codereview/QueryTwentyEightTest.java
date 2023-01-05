package devops.codereview;
//Unit testing for the population of a region.
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryTwentyEightTest {
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printPopRegionsTestEmpty()
    {
        ArrayList<PeoplePopulation> popRegEmpt = new ArrayList<PeoplePopulation>();
        app.printRegionsPopulation(popRegEmpt);
    }

    //print a list with a null value
    @Test
    void printPopRegionsNull()
    {
        ArrayList<PeoplePopulation> popRegNull = new ArrayList<PeoplePopulation>();
        popRegNull.add(null);
        app.printRegionsPopulation(popRegNull);
    }

    @Test
    void printPopRegions()
    {
        ArrayList<PeoplePopulation> popRegPrint = new ArrayList<PeoplePopulation>();
        PeoplePopulation popRegp   = new PeoplePopulation();
        popRegp.setRegionsName("Western Europe");
        popRegp.setRegionsPopulation("183247600");
        popRegPrint.add(popRegp);
        app.printRegionsPopulation(popRegPrint);
    }
}
