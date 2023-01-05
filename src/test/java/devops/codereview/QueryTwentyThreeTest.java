package devops.codereview;
//Unit testing for the population of people, people living in cities, and people not living in cities in each continent.

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
public class QueryTwentyThreeTest {
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printPopulatedPeopleConitnentTestEmpty()
    {
        ArrayList<PeoplePopulation> popuContEmp = new ArrayList<PeoplePopulation>();
        app.printPopulatedPeopleConitnent(popuContEmp);
    }

    //print a list with a null value
    @Test
    void printPopulatedPeopleConitnentNull()
    {
        ArrayList<PeoplePopulation> popuContNull = new ArrayList<PeoplePopulation>();
        popuContNull.add(null);
        app.printPopulatedPeopleConitnent(popuContNull);
    }

    @Test
    void printPopulatedPeopleConitnent()
    {
        ArrayList<PeoplePopulation> popuCont = new ArrayList<PeoplePopulation>();
        PeoplePopulation cont   = new PeoplePopulation();
        cont.setContinentName("South America");
        cont.setContinentPopulation("345780000");
        cont.setCityPopulation("172037859");
        popuCont.add(cont);
        app.printPopulatedPeopleConitnent(popuCont);
    }
}