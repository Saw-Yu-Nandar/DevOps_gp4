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
        assertEquals(country.getCountry_code(), "CHN");
        assertEquals(country.getCountry_name(), "China");
        assertEquals(country.getCountry_cont(), "Asia");
        assertEquals(country.getCountry_reg(), "Eastern Asia");
        assertEquals(country.getCountry_cap(), "Shanghai");
        assertEquals(Integer.parseInt(country.getCountry_population()),1277558000);
    }

    /*
     * Query 2: All the countries in a continent organised by largest population to smallest.
     */
    @Test
    void testGetAllContinents()
    {
        ArrayList<Countries> continents = app.getAllContinents("Oceania");
        Countries continent = continents.get(0);
        assertEquals(continent.getCountry_code(), "AUS");
        assertEquals(continent.getCountry_name(), "Australia");
        assertEquals(continent.getCountry_cont(), "Oceania");
        assertEquals(continent.getCountry_reg(), "Australia and New Zealand");
        assertEquals(continent.getCountry_cap(), "Canberra");
        assertEquals(Integer.parseInt(continent.getCountry_population()), 18886000);
    }

    /*
     * Query 3: All the countries in a region organised by largest population to smallest.
     */
    @Test
    void testGetAllRegion()
    {
        ArrayList<Countries> regions = app.getAllRegion("Caribbean");
        Countries reg = regions.get(0);
        assertEquals(reg.getCountry_code(), "CUB");
        assertEquals(reg.getCountry_name(), "Cuba");
        assertEquals(reg.getCountry_cont(), "North America");
        assertEquals(reg.getCountry_reg(), "Caribbean");
        assertEquals(reg.getCountry_cap(), "La Habana");
        assertEquals(Integer.parseInt(reg.getCountry_population()), 11201000);
    }

    /*
     * Query 4: The top N populated countries in the world where N is provided by the user.
     */
    @Test
    void testGetAllNPopCountries()
    {
        ArrayList<Countries> npopctrs = app.getAllNPopulatedCountries(10);
        Countries npopctr = npopctrs.get(0);
        assertEquals(npopctr.getCountry_code(), "CHN");
        assertEquals(npopctr.getCountry_name(), "China");
        assertEquals(npopctr.getCountry_cont(), "Asia");
        assertEquals(npopctr.getCountry_reg(), "Eastern Asia");
        assertEquals(npopctr.getCountry_cap(), "Peking");
        assertEquals(Integer.parseInt(npopctr.getCountry_population()), 1277558000);
    }

    /*
     * Query 5: The top N populated countries in a continent where N is provided by the user.
     */
    @Test
    void testGetAllNPopContinents()
    {
        ArrayList<Countries> npopconts = app.getAllNPopulatedContinents("Europe", 10);
        Countries npopcont = npopconts.get(0);
        assertEquals(npopcont.getCountry_code(), "RUS");
        assertEquals(npopcont.getCountry_name(), "Russian Federation");
        assertEquals(npopcont.getCountry_cont(), "Europe");
        assertEquals(npopcont.getCountry_reg(), "Eastern Europe");
        assertEquals(npopcont.getCountry_cap(), "Moscow");
        assertEquals(Integer.parseInt(npopcont.getCountry_population()), 146934000);
    }

    /*
     * Query 6: The top N populated countries in a region where N is provided by the user.
     */
    @Test
    void testGetAllNPopRegions()
    {
        ArrayList<Countries> npopregns = app.getAllNPopulatedContinents("Southern and Central Asia", 10);
        Countries npopregn = npopregns.get(0);
        assertEquals(npopregn.country_code, "IND");
        assertEquals(npopregn.country_name, "India");
        assertEquals(npopregn.country_cont, "Asia");
        assertEquals(npopregn.country_reg, "Southern and Central Asia");
        assertEquals(npopregn.country_cap, "New Delhi");
        assertEquals(Integer.parseInt(npopregn.country_population), "1013662000");
    }
}