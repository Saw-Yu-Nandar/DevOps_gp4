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
        ArrayList<CapitalCity> capcitContinent = new ArrayList<CapitalCity>();
        app.printAllCapitalCityContinent(capcitContinent);
    }

    //print a list with a null value
    @Test
    void printAllContinentTestContainsNull()
    {
        ArrayList<CapitalCity> capcitContinent = new ArrayList<CapitalCity>();
        capcitContinent.add(null);
        app.printAllCapitalCityContinent(capcitContinent);
    }

    @Test
    void printAllCapiticalCitiesContinent()
    {
        ArrayList<CapitalCity> capitalCities = new ArrayList<CapitalCity>();
        CapitalCity capcit            = new CapitalCity();
        capcit.setCapCityName("Peking");
        capcit.setCapCityCountry("China");
        capcit.setCapCityContinent("Asia");
        capcit.setCapCityPopulation("1277558000");
        capitalCities.add(capcit);
        app.printAllCapitalCityContinent(capitalCities);
    }
}