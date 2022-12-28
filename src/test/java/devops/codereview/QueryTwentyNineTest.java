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
        ArrayList<PeoplePopulation> pop_coun_empt = new ArrayList<PeoplePopulation>();
        app.printCountriesPopulation(pop_coun_empt);
    }

    //print a list with a null value
    @Test
    void printPopCountryNull()
    {
        ArrayList<PeoplePopulation> pop_coun_null = new ArrayList<PeoplePopulation>();
        pop_coun_null.add(null);
        app.printCountriesPopulation(pop_coun_null);
    }

    @Test
    void printPopCountry()
    {
        ArrayList<PeoplePopulation> pop_coun_print = new ArrayList<PeoplePopulation>();
        PeoplePopulation pop_coun_p   = new PeoplePopulation();
        pop_coun_p.cap_cit_name       = "Rangoon (Yangon)";
        pop_coun_p.continent_population    = "3361700";
        pop_coun_print.add(pop_coun_p);
        app.printCountriesPopulation(pop_coun_print);
    }
}
