package devops.codereview;//Unit testing for all the cities in a district organised by largest population to smallest.
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryElevenTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printCountriesTestEmpty()
    {
        ArrayList<City> district = new ArrayList<City>();
        app.printDistrict(district);
    }

    //print a list with a null value
    @Test
    void printAllCountriesTestContainsNull()
    {
        ArrayList<City> district = new ArrayList<City>();
        district.add(null);
        app.printDistrict(district);
    }
    @Test
    void printDistrict()
    {
        ArrayList<City> district  = new ArrayList<City>();
        City cit                  = new City();
        cit.setCityName("Kabul");
        cit.setCountryName("Afghanistan");
        cit.setCityDistrict("Kabol");
        cit.setCityPopulation("22720000");
        district.add(cit);
        app.printDistrict(district);
    }
}