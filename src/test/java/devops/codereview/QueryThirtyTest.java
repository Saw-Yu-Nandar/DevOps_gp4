package devops.codereview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryThirtyTest {
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printPopDistrictTestEmpty()
    {
        ArrayList<PeoplePopulation> pop_dst_empt = new ArrayList<PeoplePopulation>();
        app.printDistrictsPopulation(pop_dst_empt);
    }

    //print a list with a null value
    @Test
    void printPopDistrictNull()
    {
        ArrayList<PeoplePopulation> pop_dst_null = new ArrayList<PeoplePopulation>();
        pop_dst_null.add(null);
        app.printDistrictsPopulation(pop_dst_null);
    }

    @Test
    void printPopDistrict()
    {
        ArrayList<PeoplePopulation> pop_dst_print = new ArrayList<PeoplePopulation>();
        PeoplePopulation pop_dst_p   = new PeoplePopulation();
        pop_dst_p.district_name       = "Apeldoorn";
        pop_dst_p.district_population    = "153491";
        pop_dst_print.add(pop_dst_p);
        app.printDistrictsPopulation(pop_dst_print);
    }
}
