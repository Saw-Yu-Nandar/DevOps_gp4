package devops.codereview;
//Unit testing for the population of people, people living in cities, and people not living in cities in each region.

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
        ArrayList<PeoplePopulation> popuRegEmp = new ArrayList<PeoplePopulation>();
        app.printPopulatedPeopleRegions(popuRegEmp);
    }

    //print a list with a null value
    @Test
    void printPopulatedPeopleRegionNull()
    {
        ArrayList<PeoplePopulation> popuRegNull = new ArrayList<PeoplePopulation>();
        popuRegNull.add(null);
        app.printPopulatedPeopleRegions(popuRegNull);
    }

    @Test
    void printPopulatedPeopleRegion()
    {
        ArrayList<PeoplePopulation> popuReg = new ArrayList<PeoplePopulation>();
        PeoplePopulation reg   = new PeoplePopulation();
        reg.setRegionsName("Middle East");
        reg.setRegionsPopulation("188380700");
        reg.setCityPopulation("70371374");
        popuReg.add(reg);
        app.printPopulatedPeopleRegions(popuReg);
    }
}

