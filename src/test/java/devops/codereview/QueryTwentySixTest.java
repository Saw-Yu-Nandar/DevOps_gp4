package devops.codereview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class QueryTwentySixTest {
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printPopWorldTestEmpty()
    {
        ArrayList<PeoplePopulation> popWorldEmpt = new ArrayList<PeoplePopulation>();
        app.printWorldPopulation(popWorldEmpt);
    }

    //print a list with a null value
    @Test
    void printPopWorldNull()
    {
        ArrayList<PeoplePopulation> popWorld = new ArrayList<PeoplePopulation>();
        popWorld.add(null);
        app.printWorldPopulation(popWorld);
    }

    @Test
    void printPopWorld()
    {
        ArrayList<PeoplePopulation> popWorldPrint = new ArrayList<PeoplePopulation>();
        PeoplePopulation popWorldp   = new PeoplePopulation();
        popWorldp.setWorldPopulation("10925000");
        popWorldPrint.add(popWorldp);
        app.printWorldPopulation(popWorldPrint);
    }
}
