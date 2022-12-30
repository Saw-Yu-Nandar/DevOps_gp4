package devops.codereview;

//Unit testing for all the capital cities in a region organised by largest population to smallest.
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryNineteenTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printCapitalCitiesRegionTestEmpty()
    {
        ArrayList<CapitalCities> capcit_region = new ArrayList<CapitalCities>();
        app.printAllCapitalCityContinent(capcit_region);
    }

    //print a list with a null value
    @Test
    void printAllRegionTestContainsNull()
    {
        ArrayList<CapitalCities> capcit_region = new ArrayList<CapitalCities>();
        capcit_region.add(null);
        app.printAllCapitalCityRegion(capcit_region);
    }

    @Test
    void printAllCapiticalCitiesRegion()
    {
        ArrayList<CapitalCities> capcit_region = new ArrayList<CapitalCities>();
        CapitalCities capcit            = new CapitalCities();
        capcit.setCap_cit_name("La Habana");
        capcit.setCap_cit_country("Cuba");
        capcit.setCap_cit_region("Caribbean");
        capcit.setCap_cit_population("11201000");
        capcit_region.add(capcit);
        app.printAllCapitalCityRegion(capcit_region);
    }
}
