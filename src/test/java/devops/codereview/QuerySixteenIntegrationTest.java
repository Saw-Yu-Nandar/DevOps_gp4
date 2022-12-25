package devops.codereview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class QuerySixteenIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 30000);
    }
    @Test
    void testGetTopNPopulatedDistrict()
    {
        ArrayList<City> district = app.getTopNPopulatedDistrict("Zuid-Holland",10);
        City dist = district.get(0);
        assertEquals(dist.getCit_name(), "Rotterdam");
        assertEquals(dist.getCountry_name(), "Netherlands");
        assertEquals(dist.getCit_district(),"Zuid-Holland");
        assertEquals(Integer.parseInt(dist.getCit_population()),593321);
    }
}
