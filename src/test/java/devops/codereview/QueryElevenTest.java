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
        cit.setCit_name("Kabul");
        cit.setCountry_name("Afghanistan");
        cit.setCit_district("Kabol");
        cit.setCit_population("22720000");
        district.add(cit);
        app.printDistrict(district);
    }
}