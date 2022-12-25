package devops.codereview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class QueryEightIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 30000);
    }
    @Test
    void testGetAllCitiesContinent()
    {
        ArrayList<City> city = app.getAllCitiesContinent("Asia");
        City cit = city.get(0);
        assertEquals(cit.getCit_name(), "Mumbai (Bombay)");
        assertEquals(cit.getCountry_name(), "India");
        assertEquals(cit.getCit_district(),"Maharashtra");
        assertEquals(Integer.parseInt(cit.getCit_population()),10500000);
    }
}
