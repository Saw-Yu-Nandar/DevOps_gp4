package devops.codereview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class QueryElevenIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 30000);
    }
    @Test
    void testGetAllCities()
    {
        ArrayList<City> city = app.getAllCities();
        City cit = city.get(0);
        assertEquals(cit.getCit_name(), "Brisbane");
        assertEquals(cit.getCountry_name(), "Vietnam");
        assertEquals(cit.getCit_district(),"Queensland");
        assertEquals(Integer.parseInt(cit.getCit_population()),1291117);
    }
}
