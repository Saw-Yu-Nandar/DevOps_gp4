package devops.codereview;

//Unit testing for all the capital cities in a continent organised by largest population to smallest.
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryEighteenTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printCapitalCitiesContinentTestEmpty()
    {
        ArrayList<CapitalCities> capcit_continent = new ArrayList<CapitalCities>();
        app.printAllCapitalCityContinent(capcit_continent);
    }

    //print a list with a null value
    @Test
    void printAllContinentTestContainsNull()
    {
        ArrayList<CapitalCities> capcit_continent = new ArrayList<CapitalCities>();
        capcit_continent.add(null);
        app.printAllCapitalCityContinent(capcit_continent);
    }

    @Test
    void printAllCapiticalCitiesContinent()
    {
        ArrayList<CapitalCities> capital_cities = new ArrayList<CapitalCities>();
        CapitalCities capcit            = new CapitalCities();
        capcit.setCap_cit_name("Peking");
        capcit.setCap_cit_country("China");
        capcit.setCap_cit_continent("Asia");
        capcit.setCap_cit_population("1277558000");
        capital_cities.add(capcit);
        app.printAllCapitalCityContinent(capital_cities);
    }
}
