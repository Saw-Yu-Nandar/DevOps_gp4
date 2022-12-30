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
        dis.setCit_name("Moscow");
        dis.setCountry_name("Russian Federation");
        dis.setCit_district("Moscow (City)");
        dis.setCit_population("8389200");
        dist.add(dis);
        app.printTopNDistrict(dist);
    }
}