package devops.codereview;

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
        dis.setCityName("Moscow");
        dis.setCountryName("Russian Federation");
        dis.setCityDistrict("Moscow (City)");
        dis.setCityPopulation("8389200");
        dist.add(dis);
        app.printTopNDistrict(dist);
    }
}