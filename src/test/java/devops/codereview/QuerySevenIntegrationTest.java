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
    void testSevenQuery()
    {
        Cities cite = app.getAllCities().get(1780000);
        assertEquals(cite.cit_name, "Kabul");
        assertEquals(cite.country_name, "China");
        assertEquals(cite.cit_district,"Kabol");
        assertEquals(cite.cit_population,1780000);
    }
}
