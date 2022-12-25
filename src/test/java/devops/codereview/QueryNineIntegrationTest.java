package devops.codereview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class QueryNineIntegrationTest
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
        assertEquals(cit.getCit_name(), "La Habana");
        assertEquals(cit.getCountry_name(), "Cuba");
        assertEquals(cit.getCit_district(),"La Habana");
        assertEquals(Integer.parseInt(cit.getCit_population()),2256000);
    }
}
