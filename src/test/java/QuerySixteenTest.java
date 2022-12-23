import devops.codereview.*;
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
        ArrayList<Cities> dist = new ArrayList<Cities>();
        app.printTopNDistrict(dist);
    }

    //print a list with a null value
    @Test
    void printTopNDistrictTestContainsNull()
    {
        ArrayList<Cities> dist = new ArrayList<Cities>();
        dist.add(null);
        app.printTopNDistrict(dist);
    }
    @Test
    void printTopNDistrict()
    {
        ArrayList<Cities> dist = new ArrayList<Cities>();
        Cities dis = new Cities();
        dis.cit_name = "Moscow";
        dis.countryname = "Russian Federation";
        dis.district = "Moscow (City)";
        dis.cit_population = "8389200";
        dist.add(dis);
        app.printTopNDistrict(dist);
    }
}