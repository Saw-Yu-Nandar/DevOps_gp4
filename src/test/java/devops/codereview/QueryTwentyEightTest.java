package devops.codereview;

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
        ArrayList<PeoplePopulation> pop_reg_empt = new ArrayList<PeoplePopulation>();
        app.printRegionsPopulation(pop_reg_empt);
    }

    //print a list with a null value
    @Test
    void printPopRegionsNull()
    {
        ArrayList<PeoplePopulation> pop_reg_null = new ArrayList<PeoplePopulation>();
        pop_reg_null.add(null);
        app.printRegionsPopulation(pop_reg_null);
    }

    @Test
    void printPopRegions()
    {
        ArrayList<PeoplePopulation> pop_reg_print = new ArrayList<PeoplePopulation>();
        PeoplePopulation pop_reg_p   = new PeoplePopulation();
        pop_reg_p.cap_cit_name       = "Yerevan";
        pop_reg_p.continent_population    = "3520000";
        pop_reg_print.add(pop_reg_p);
        app.printRegionsPopulation(pop_reg_print);
    }
}
