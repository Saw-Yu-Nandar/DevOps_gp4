package devops.codereview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class AppIntegrationTestCountry
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 30000);
    }

    /*
     * Query 1: All the countries in the world organised by largest population to smallest.
     */
    @Test
    void testGetAllCountries()
    {
        ArrayList<Countries> countries = app.getAllCountries();
        Countries country = countries.get(0);
        assertEquals(country.country_code, "CHN");
        assertEquals(country.country_name, "China");
        assertEquals(country.country_cont, "Asia");
        assertEquals(country.country_reg, "Eastern Asia");
        assertEquals(country.country_cap, "Shanghai");
        assertEquals(Integer.parseInt(country.country_population), "1277558000");
    }

    /*
     * Query 2: All the countries in a continent organised by largest population to smallest.
     */
    @Test
    void testGetAllContinents()
    {
        ArrayList<Countries> continents = app.getAllContinents("Oceania");
        Countries continent = continents.get(0);
        assertEquals(continent.country_code, "AUS");
        assertEquals(continent.country_name, "Australia");
        assertEquals(continent.country_cont, "Oceania");
        assertEquals(continent.country_reg, "Australia and New Zealand");
        assertEquals(continent.country_cap, "Sydney");
        assertEquals(Integer.parseInt(continent.country_population), "18886000");
    }

    /*
     * Query 3: All the countries in a region organised by largest population to smallest.
     */
    @Test
    void testGetAllRegion()
    {
        ArrayList<Countries> regions = app.getAllRegion("Caribbean");
        Countries reg = regions.get(0);
        assertEquals(reg.country_code, "CUB");
        assertEquals(reg.country_name, "Cuba");
        assertEquals(reg.country_cont, "North America");
        assertEquals(reg.country_reg, "Caribbean");
        assertEquals(reg.country_cap, "La Habana");
        assertEquals(Integer.parseInt(reg.country_population), "11201000");
    }

    /*
     * Query 4: The top N populated countries in the world where N is provided by the user.
     */
    @Test
    void testGetAllNPopCountries()
    {
        ArrayList<Countries> npopctrs = app.getAllNPopulatedCountries(10);
        Countries npopctr = npopctrs.get(0);
        assertEquals(npopctr.country_code, "CHN");
        assertEquals(npopctr.country_name, "China");
        assertEquals(npopctr.country_cont, "Asia");
        assertEquals(npopctr.country_reg, "Eastern Asia");
        assertEquals(npopctr.country_cap, "Shanghai");
        assertEquals(Integer.parseInt(npopctr.country_population), "1277558000");
    }

    /*
     * Query 5: The top N populated countries in a continent where N is provided by the user.
     */
    @Test
    void testGetAllNPopContinents()
    {
        ArrayList<Countries> npopconts = app.getAllNPopulatedContinents("Europe", 10);
        Countries npopcont = npopconts.get(0);
        assertEquals(npopcont.country_code, "RUS");
        assertEquals(npopcont.country_name, "Russian Federation");
        assertEquals(npopcont.country_cont, "Europe");
        assertEquals(npopcont.country_reg, "Eastern Europe");
        assertEquals(npopcont.country_cap, "Moscow");
        assertEquals(Integer.parseInt(npopcont.country_population), "146934000");
    }

    /*
     * Query 6: The top N populated countries in a region where N is provided by the user.
     */
    @Test
    void testGetAllNPopRegions()
    {
        ArrayList<Countries> npopregns = app.getAllNPopulatedContinents("Middle East", 10);
        Countries npopregn = npopregns.get(0);
        assertEquals(npopregn.country_code, "TUR");
        assertEquals(npopregn.country_name, "Turkey");
        assertEquals(npopregn.country_cont, "Asia");
        assertEquals(npopregn.country_reg, "Middle East");
        assertEquals(npopregn.country_cap, "Istanbul");
        assertEquals(Integer.parseInt(npopregn.country_population), "66591000");
    }
}