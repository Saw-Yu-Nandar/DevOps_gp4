package devops.codereview;

//Unit testing for all the capital cities in a world organised by largest population to smallest.
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QuerySeventeenTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printCapitalCitiesTestEmpty()
    {
        ArrayList<CapitalCity> capitalCities = new ArrayList<CapitalCity>();
        app.printAllCapitalCities(capitalCities);
    }

    //print a list with a null value
    @Test
    void printAllContinentTestContainsNull()
    {
        ArrayList<CapitalCity> capitalCities = new ArrayList<CapitalCity>();
        capitalCities.add(null);
        app.printAllCapitalCities(capitalCities);
    }
    @Test
    void printContinent()
    {
        ArrayList<CapitalCity> capiCities = new ArrayList<CapitalCity>();
        CapitalCity capcit            = new CapitalCity();
        capcit.setCapCityName("Peking");
        capcit.setCapCityCountry("China");
        capcit.setCapCityPopulation("1277558000");
        capiCities.add(capcit);
        app.printAllCapitalCities(capiCities);
    }
}