package devops.codereview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class AppIntegrationTestCapitalCities {
    static App app;

    @BeforeAll
    static void init() {
        app = new App();
        app.connect("localhost:33060", 30000);
    }

    /*
     * Query 17: All the capital cities in the world organised by largest population to smallest.
     */
    @Test
    void testGetAllCapitalCities() {
        ArrayList<CapitalCities> capital_cities = app.getAllCapitalCities();
        CapitalCities capitalCities = capital_cities.get(0);
        assertEquals(capitalCities.getCap_cit_name(), "Peking");
        assertEquals(capitalCities.getCap_cit_country(), "China");
        assertEquals(Integer.parseInt(capitalCities.getCap_cit_population()), 1277558000);
    }

    /*
     * Query 18: All the capital cities in a continent organised by largest population to smallest.
     */
    @Test
    void testGetAllCapitalCitiesContinent() {
        ArrayList<CapitalCities> capcit_continent = app.getAllCapitalCitiesContinents("Asia");
        CapitalCities capitalCities = capcit_continent.get(0);
        assertEquals(capitalCities.getCap_cit_name(), "Peking");
        assertEquals(capitalCities.getCap_cit_country(), "China");
        assertEquals(capitalCities.getCap_cit_continent(), "Asia");
        ;
        assertEquals(Integer.parseInt(capitalCities.getCap_cit_population()), 1277558000);
    }

    /*
     * Query 19: All the capital cities in a region organised by largest to smallest.
     */
    @Test
    void testGetAllCapitalCitiesRegion() {
        ArrayList<CapitalCities> capcit_region = app.getAllCapitalCitiesRegions("Caribbean");
        CapitalCities capitalCities = capcit_region.get(0);
        assertEquals(capitalCities.getCap_cit_name(), "La Habana");
        assertEquals(capitalCities.getCap_cit_country(), "Cuba");
        assertEquals(capitalCities.getCap_cit_region(), "Caribbean");
        assertEquals(Integer.parseInt(capitalCities.getCap_cit_population()), 11201000);
    }
}
