//Unit testing for the population of people, people living in cities, and people not living in cities in each country.
package devops.codereview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
public class QueryTwentyFiveTest {
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printPopulatedPeopleCountryTestEmpty()
    {
        ArrayList<PeoplePopulation> popu_cou_emp = new ArrayList<PeoplePopulation>();
        app.printPopulatedPeopleCountry(null);
    }

    //print a list with a null value
    @Test
    void printPopulatedPeopleCountryNull()
    {
        ArrayList<PeoplePopulation> popu_cou_null = new ArrayList<PeoplePopulation>();
        popu_cou_null.add(null);
        app.printPopulatedPeopleCountry(null);
    }

    @Test
    void printPopulatedPeopleCountry()
    {
        ArrayList<PeoplePopulation> popu_cou = new ArrayList<PeoplePopulation>();
        PeoplePopulation cou   = new PeoplePopulation();
        cou.setCap_cit_country("Myanmar");
        cou.setCap_cit_population("45611000");
        popu_cou.add(cou);
        app.printPopulatedPeopleCountry(popu_cou);
    }
}

