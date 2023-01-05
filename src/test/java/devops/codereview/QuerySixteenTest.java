package devops.codereview;
//Unit testing for the top N populated cities in a district where N is provided by the user.
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QuerySixteenTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printTopNDistrictTestEmpty()
    {
        ArrayList<City> dist = new ArrayList<City>();
        app.printTopNDistrict(dist);
    }

    //print a list with a null value
    @Test
    void printTopNDistrictTestContainsNull()
    {
        ArrayList<City> dist = new ArrayList<City>();
        dist.add(null);
        app.printTopNDistrict(dist);
    }
    @Test
    void printTopNDistrict()
    {
        ArrayList<City> dist = new ArrayList<City>();
        City dis = new City();
        dis.setCitName("Haag");
        dis.setCountryName("Netherlands");
        dis.setCitDistrict("Zuid-Holland");
        dis.setCitPopulation("440900");
        dist.add(dis);
        app.printTopNDistrict(dist);
    }
}