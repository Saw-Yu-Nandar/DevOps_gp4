package devops.codereview;
//Unit testing for the population of people, people living in cities, and people not living in cities in each country.

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
        ArrayList<PeoplePopulation> popuCouEmp = new ArrayList<PeoplePopulation>();
        app.printPopulatedPeopleCountry(popuCouEmp);
    }

    //print a list with a null value
    @Test
    void printPopulatedPeopleCountryNull()
    {
        ArrayList<PeoplePopulation> popuCouNull = new ArrayList<PeoplePopulation>();
        popuCouNull.add(null);
        app.printPopulatedPeopleCountry(popuCouNull);
    }

    @Test
    void printPopulatedPeopleCountry()
    {
        ArrayList<PeoplePopulation> popuCou = new ArrayList<PeoplePopulation>();
        PeoplePopulation cou   = new PeoplePopulation();
        cou.setCountriesName("Afghanistan");
        cou.setCountriesPopulation("22720000");
        cou.setCityPopulation("2332100");
        popuCou.add(cou);
        app.printPopulatedPeopleCountry(popuCou);
    }
}

