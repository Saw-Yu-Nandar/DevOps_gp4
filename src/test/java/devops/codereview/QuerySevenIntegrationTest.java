package devops.codereview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class QuerySevenIntegrationTest
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
        Cities city = app.getAllCities().get(1780000);
        assertEquals(city.cit_name, "Kabul");
        assertEquals(city.country_name, "China");
        assertEquals(city.cit_district,"Kabol");
        assertEquals(city.cit_population,1780000);
    }
}
