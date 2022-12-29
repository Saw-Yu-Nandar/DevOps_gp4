//Unit testing for the population of people, people living in cities, and people not living in cities in each region.
package devops.codereview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
public class QueryTwentyFourTest {
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printPopulatedPeopleRegionTestEmpty()
    {
        ArrayList<PeoplePopulation> popu_reg_emp = new ArrayList<PeoplePopulation>();
        app.printPopulatedPeopleRegions(null);
    }

    //print a list with a null value
    @Test
    void printPopulatedPeopleRegionNull()
    {
        ArrayList<PeoplePopulation> popu_reg_null = new ArrayList<PeoplePopulation>();
        popu_reg_null.add(null);
        app.printPopulatedPeopleRegions(null);
    }

//    @Test
//    void printPopulatedPeopleRegion()
//    {
//        ArrayList<PeoplePopulation> popu_reg = new ArrayList<PeoplePopulation>();
//        PeoplePopulation reg   = new PeoplePopulation();
//        reg.cap_cit_name       = "Roma";
//        reg.cap_cit_country    = "Italy";
//        reg.cap_cit_population = "57680000";
//        popu_reg.add(reg);
//        app.printPopulatedPeopleRegions(popu_reg);
//    }
}

