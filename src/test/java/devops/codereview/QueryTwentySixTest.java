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
        ArrayList<PeoplePopulation> pop_world_empt = new ArrayList<PeoplePopulation>();
        app.printWorldPopulation(pop_world_empt);
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
        ArrayList<PeoplePopulation> pop_world_print = new ArrayList<PeoplePopulation>();
        PeoplePopulation pop_world_p   = new PeoplePopulation();
        pop_world_p.cap_cit_country       = "Africa";
        pop_world_p.world_population    = "10925000";
        pop_world_print.add(pop_world_p);
        app.printWorldPopulation(pop_world_print);
    }
}
