package devops.codereview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class QueryTenIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 30000);
    }
    @Test
    void testGetAllCitiesCountries()
    {
        ArrayList<City> city = app.getAllCitiesCountries("Myanmar");
        City cit = city.get(0);
        assertEquals(cit.getCit_name(), "Rangoon (Yangon)");
        assertEquals(cit.getCountry_name(), "Myanmar");
        assertEquals(cit.getCit_district(),"Rangoon [Yangon]");
        assertEquals(Integer.parseInt(cit.getCit_population()),3361700);
    }
}