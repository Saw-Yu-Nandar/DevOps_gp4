package devops.codereview;

//Unit testing for the top N populated capital cities in a region where N is provided by the user.
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryTwentyTwoTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printTopNCapCitiesRegTestEmpty()
    {
        ArrayList<CapitalCity> capRegionEmp = new ArrayList<CapitalCity>();
        app.printTopNCapCitiesReg(capRegionEmp);
    }

    //print a list with a null value
    @Test
    void printTopNCapCitiesContNull()
    {
        ArrayList<CapitalCity> capRegionNull = new ArrayList<CapitalCity>();
        capRegionNull.add(null);
        app.printTopNCapCitiesReg(capRegionNull);
    }

    @Test
    void printTopNCapCitiesCont()
    {
        ArrayList<CapitalCity> capRegion = new ArrayList<CapitalCity>();
        CapitalCity capReg            = new CapitalCity();
        capReg.setCapCityName("Ankara");
        capReg.setCapCityCountry("Turkey");
        capReg.setCapCityPopulation("66591000");
        capRegion.add(capReg);
        app.printTopNCapCitiesReg(capRegion);
    }
}
