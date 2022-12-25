package devops.codereview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class QueryForteenIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 30000);
    }
    @Test
    void testGetTopNPopulatedRegion()
    {
        ArrayList<City> region = app.getTopNPopulatedRegion("Caribbean",10);
        City reg = region.get(0);
        assertEquals(reg.getCit_name(), "La Habana");
        assertEquals(reg.getCountry_name(), "Cuba");
        assertEquals(reg.getCit_district(),"La Habana");
        assertEquals(Integer.parseInt(reg.getCit_population()),2256000);
    }
}
