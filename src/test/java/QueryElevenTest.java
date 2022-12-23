import devops.codereview.*;
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
        ArrayList<Cities> district = new ArrayList<Cities>();
        app.printCities(district);
    }

    //print a list with a null value
    @Test
    void printAllCountriesTestContainsNull()
    {
        ArrayList<Cities> district = new ArrayList<Cities>();
        district.add(null);
        app.printDistrict(district);
    }
    @Test
    void printCountries()
    {
        ArrayList<Cities> district = new ArrayList<Cities>();
        Cities cit = new Cities();
        cit.cit_name = "Kabul";
        cit.countryname = "Afghanistan";
        cit.district = "Kabol";
        cit.cit_population = "22720000";
        district.add(cit);
        app.printDistrict(district);
    }
}