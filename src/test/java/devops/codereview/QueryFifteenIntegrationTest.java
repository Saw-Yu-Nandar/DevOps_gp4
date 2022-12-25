package devops.codereview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class QueryFifteenIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 30000);
    }
    @Test
    void testGetTopNPopulatedCountries()
    {
        ArrayList<City> countries = app.getTopNPopulatedCountries("Argentina",10);
        City count = countries.get(0);
        assertEquals(count.getCit_name(), "Buenos Aires");
        assertEquals(count.getCountry_name(), "Argentina");
        assertEquals(count.getCit_district(),"Distrito Federal");
        assertEquals(Integer.parseInt(count.getCit_population()),2982146);
    }
}
