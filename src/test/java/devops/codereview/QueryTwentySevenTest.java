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
        ArrayList<PeoplePopulation> pop_conti_empt = new ArrayList<PeoplePopulation>();
        app.printContinentPopulation(pop_conti_empt);
    }

    //print a list with a null value
    @Test
    void printPopContiNull()
    {
        ArrayList<PeoplePopulation> pop_conti_null = new ArrayList<PeoplePopulation>();
        pop_conti_null.add(null);
        app.printContinentPopulation(pop_conti_null);
    }

    @Test
    void printPopConti()
    {
        ArrayList<PeoplePopulation> pop_conti_print = new ArrayList<PeoplePopulation>();
        PeoplePopulation pop_conti_p   = new PeoplePopulation();
        pop_conti_p.cap_cit_continent       = "Afghanistan";
        pop_conti_p.continent_population    = "22720000";
        pop_conti_print.add(pop_conti_p);
        app.printContinentPopulation(pop_conti_print);
    }
}
