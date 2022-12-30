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
        ArrayList<PeoplePopulation> popu_emp = new ArrayList<PeoplePopulation>();
        app.printPopulatedPeopleConitnent(popu_cont_emp, popu_emp);
        //app.printPopulatedPeopleConitnent(null);
    }

    //print a list with a null value
    @Test
    void printPopulatedPeopleConitnentNull()
    {
        ArrayList<PeoplePopulation> popu_cont_null = new ArrayList<PeoplePopulation>();
        ArrayList<PeoplePopulation> popu_null = new ArrayList<PeoplePopulation>();
        popu_cont_null.add(null);
        popu_null.add(null);
        app.printPopulatedPeopleConitnent(popu_cont_null, popu_null);
        //app.printPopulatedPeopleConitnent(null);
    }

    @Test
    void printPopulatedPeopleConitnent()
    {
        ArrayList<PeoplePopulation> popu_cont = new ArrayList<PeoplePopulation>();
        PeoplePopulation cont   = new PeoplePopulation();
        cont.setCap_cit_continent("Europe");
        cont.setCap_cit_population("730074600");
        popu_cont.add(cont);

        ArrayList<PeoplePopulation> popu = new ArrayList<PeoplePopulation>();
        PeoplePopulation co = new PeoplePopulation();
        co.setWorld_population("6078749450");
        popu.add(co);
        app.printPopulatedPeopleConitnent(popu_cont, popu);

    }
}