package devops.codereview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class QueryThirteenIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 30000);
    }
    @Test
    void testGetTopNPopulatedContinent()
    {
        ArrayList<City> continent = app.getTopNPopulatedContinent("Moscow",10);
        City cnt = continent.get(0);
        assertEquals(cnt.getCit_name(), "Moscow");
        assertEquals(cnt.getCountry_name(), "Russian Federation");
        assertEquals(cnt.getCit_district(),"Moscow (City)");
        assertEquals(Integer.parseInt(cnt.getCit_population()),8389200);
    }
}
