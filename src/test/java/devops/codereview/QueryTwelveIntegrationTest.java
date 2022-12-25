package devops.codereview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class QueryTwelveIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 30000);
    }
    @Test
    void testGetTopNPopulatedCities()
    {
        ArrayList<City> worlds = app.getTopNPopulatedCities(10);
        City wld = worlds.get(0);
        assertEquals(wld.getCit_name(), "Mumbai (Bombay)");
        assertEquals(wld.getCountry_name(), "India");
        assertEquals(wld.getCit_district(),"Maharashtra");
        assertEquals(Integer.parseInt(wld.getCit_population()),10500000);
    }
}
