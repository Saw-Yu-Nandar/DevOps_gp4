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
        ArrayList<CapitalCity> capCitReg = new ArrayList<CapitalCity>();
        app.printAllCapitalCityContinent(capCitReg);
    }

    //print a list with a null value
    @Test
    void printAllRegionTestContainsNull()
    {
        ArrayList<CapitalCity> capCitReg = new ArrayList<CapitalCity>();
        capCitReg.add(null);
        app.printAllCapitalCityRegion(capCitReg);
    }

    @Test
    void printAllCapiticalCitiesRegion()
    {
        ArrayList<CapitalCity> capCitRegi = new ArrayList<CapitalCity>();
        CapitalCity capcit            = new CapitalCity();
        capcit.setCapCityName("La Habana");
        capcit.setCapCityCountry("Cuba");
        capcit.setCapCityRegion("Caribbean");
        capcit.setCapCityPopulation("11201000");
        capCitRegi.add(capcit);
        app.printAllCapitalCityRegion(capCitRegi);
    }
}
