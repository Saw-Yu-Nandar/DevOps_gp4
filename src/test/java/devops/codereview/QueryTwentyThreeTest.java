//Unit testing for the population of people, people living in cities, and people not living in cities in each continent.
package devops.codereview;

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
        ArrayList<PeoplePopulation> popu_cont_emp = new ArrayList<PeoplePopulation>();
        app.printPopulatedPeopleConitnent(popu_cont_emp);
    }

    //print a list with a null value
    @Test
    void printPopulatedPeopleConitnentNull()
    {
        ArrayList<PeoplePopulation> popu_cont_null = new ArrayList<PeoplePopulation>();
        popu_cont_null.add(null);
        app.printPopulatedPeopleConitnent(popu_cont_null);
    }

    @Test
    void printPopulatedPeopleConitnent()
    {
        ArrayList<PeoplePopulation> popu_cont = new ArrayList<PeoplePopulation>();
        PeoplePopulation cont   = new PeoplePopulation();
        cont.cap_cit_name       = "Moscow";
        cont.cap_cit_country    = "Russian Federation";
        cont.cap_cit_population = "146934000";
        popu_cont.add(cont);
        app.printPopulatedPeopleConitnent(popu_cont);
    }
}