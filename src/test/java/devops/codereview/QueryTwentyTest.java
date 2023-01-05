package devops.codereview;

//Unit testing for the top N populated capital cities in the world where N is provided by the user.
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryTwentyTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printTopNCapCitiesWorldTestEmpty()
    {
        ArrayList<CapitalCity> capWorldEmp = new ArrayList<CapitalCity>();
        app.printTopNCapCitiesWorld(capWorldEmp);
    }

    //print a list with a null value
    @Test
    void printTopNCapCitiesWorldNull()
    {
        ArrayList<CapitalCity> capWorldNull = new ArrayList<CapitalCity>();
        capWorldNull.add(null);
        app.printTopNCapCitiesWorld(capWorldNull);
    }

    @Test
    void printTopNCapCitiesWorld()
    {
        ArrayList<CapitalCity> capWorld = new ArrayList<CapitalCity>();
        CapitalCity capWld            = new CapitalCity();
        capWld.setCapCityName("Peking");
        capWld.setCapCityCountry("China");
        capWld.setCapCityPopulation("1277558000");
        capWorld.add(capWld);
        app.printTopNCapCitiesWorld(capWorld);
    }
}