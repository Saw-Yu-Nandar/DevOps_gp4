package devops.codereview;

//Unit testing for the top N populated capital cities in a continent where N is provided by the user.
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryTwentyOneTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printTopNCapCitiesContTestEmpty()
    {
        ArrayList<CapitalCity> capContEmp = new ArrayList<CapitalCity>();
        app.printTopNCapCitiesCont(capContEmp);
    }

    //print a list with a null value
    @Test
    void printTopNCapCitiesContNull()
    {
        ArrayList<CapitalCity> capContNull = new ArrayList<CapitalCity>();
        capContNull.add(null);
        app.printTopNCapCitiesCont(capContNull);
    }

    @Test
    void printTopNCapCitiesCont()
    {
        ArrayList<CapitalCity> capContPrint = new ArrayList<CapitalCity>();
        CapitalCity capCont            = new CapitalCity();
        capCont.setCapCityName("Washington");
        capCont.setCapCityCountry("United States");
        capCont.setCapCityPopulation("278357000");
        capContPrint.add(capCont);
        app.printTopNCapCitiesCont(capContPrint);
    }
}
