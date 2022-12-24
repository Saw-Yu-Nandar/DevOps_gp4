package devops.codereview;//Unit testing for all the cities in a district organised by largest population to smallest.
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
        app.printDistrict(district);
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
    void printDistrict()
    {
        ArrayList<Cities> district  = new ArrayList<Cities>();
        Cities cit                  = new Cities();
        cit.cit_name                = "Kabul";
        cit.country_name            = "Afghanistan";
        cit.cit_district            = "Kabol";
        cit.cit_population          = "22720000";
        district.add(cit);
        app.printDistrict(district);
    }
}