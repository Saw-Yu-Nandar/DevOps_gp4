package devops.codereview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class CapitalCityIntegrationTest {
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
        ArrayList<CapitalCity> capiCities = app.getAllCapitalCities();
        CapitalCity capitalCities = capiCities.get(0);
        assertEquals(capitalCities.getCapCityName(), "Peking");
        assertEquals(capitalCities.getCapCityCountry(), "China");
        assertEquals(Integer.parseInt(capitalCities.getCapCityPopulation()), 1277558000);
    }

    /*
     * Query 18: All the capital cities in a continent organised by largest population to smallest.
     */
    @Test
    void testGetAllCapitalCitiesContinent() {
        ArrayList<CapitalCity> capcitContinent = app.getAllCapitalCitiesContinents("Asia");
        CapitalCity capitalCities = capcitContinent.get(0);
        assertEquals(capitalCities.getCapCityName(), "Peking");
        assertEquals(capitalCities.getCapCityCountry(), "China");
        assertEquals(capitalCities.getCapCityContinent(), "Asia");
        ;
        assertEquals(Integer.parseInt(capitalCities.getCapCityPopulation()), 1277558000);
    }

    /*
     * Query 19: All the capital cities in a region organised by largest to smallest.
     */
    @Test
    void testGetAllCapitalCitiesRegion() {
        ArrayList<CapitalCity> capcitRegion = app.getAllCapitalCitiesRegions("Caribbean");
        CapitalCity capitalCities = capcitRegion.get(0);
        assertEquals(capitalCities.getCapCityName(), "La Habana");
        assertEquals(capitalCities.getCapCityCountry(), "Cuba");
        assertEquals(capitalCities.getCapCityRegion(), "Caribbean");
        assertEquals(Integer.parseInt(capitalCities.getCapCityPopulation()), 11201000);
    }
    /*
     * Query 20: The top N populated capital cities in the world where N is provided by the user.
     */
    @Test
    void testGetTopNCapCitiesWorld() {
        ArrayList<CapitalCity> capWld = app.getTopNCapCitiesWorld(10);
        CapitalCity capWorld = capWld.get(0);
        assertEquals(capWorld.getCapCityName(), "Peking");
        assertEquals(capWorld.getCapCityCountry(), "China");
        assertEquals(Integer.parseInt(capWorld.getCapCityPopulation()), 1277558000);
    }
    /*
     * Query 21: The top N populated capital cities in a continent where N is provided by the user.
     */
    @Test
    void testGetTopNCapCitiesCont() {
        ArrayList<CapitalCity> contCapital = app.getTopNCapCitiesCont("North America",10);
        CapitalCity contCap = contCapital.get(0);
        assertEquals(contCap.getCapCityName(), "Washington");
        assertEquals(contCap.getCapCityCountry(), "United States");
        assertEquals(Integer.parseInt(contCap.getCapCityPopulation()), 278357000);
    }
    /*
     * Query 23: The top N populated capital cities in a region where N is provided by the user.
     */
    @Test
    void testGetTopNCapCitiesReg() {
        ArrayList<CapitalCity> regWld = app.getTopNCapCitiesReg("Middle East",10);
        CapitalCity regCap = regWld.get(0);
        assertEquals(regCap.getCapCityName(), "Ankara");
        assertEquals(regCap.getCapCityCountry(), "Turkey");
        assertEquals(Integer.parseInt(regCap.getCapCityPopulation()), 66591000);
    }
}