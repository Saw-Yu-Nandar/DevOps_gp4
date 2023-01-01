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
        ArrayList<PeoplePopulation> popworld = new ArrayList<PeoplePopulation>();
        popworld.add(null);
        app.printWorldPopulation(popworld);
    }

    @Test
    void printPopWorld()
    {
        ArrayList<PeoplePopulation> popWorldPrint = new ArrayList<PeoplePopulation>();
        PeoplePopulation popWorld   = new PeoplePopulation();
        popWorld.setWorldName("Africa");
        popWorld.setWorldPopulation("10925000");
        //pop_world_p.world_population    = "10925000";
        popWorldPrint.add(popWorld);
        app.printWorldPopulation(popWorldPrint);
    }
}