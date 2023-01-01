package devops.codereview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class CountryIntegrationTest {
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
        ArrayList<Country> countries = app.getAllCountries();
        Country country = countries.get(0);
        assertEquals(country.getCountryCode(), "CHN");
        assertEquals(country.getCountryName(), "China");
        assertEquals(country.getCountryCont(), "Asia");
        assertEquals(country.getCountryReg(), "Eastern Asia");
        assertEquals(country.getCountryCap(), "Shanghai");
        assertEquals(Integer.parseInt(country.getCountryPopulation()),1277558000);
    }

    /*
     * Query 2: All the countries in a continent organised by largest population to smallest.
     */
    @Test
    void testGetAllContinents()
    {
        ArrayList<Country> continents = app.getAllContinents("Oceania");
        Country continent = continents.get(0);
        assertEquals(continent.getCountryCode(), "AUS");
        assertEquals(continent.getCountryName(), "Australia");
        assertEquals(continent.getCountryCont(), "Oceania");
        assertEquals(continent.getCountryReg(), "Australia and New Zealand");
        assertEquals(continent.getCountryCap(), "Canberra");
        assertEquals(Integer.parseInt(continent.getCountryPopulation()), 18886000);
    }

    /*
     * Query 3: All the countries in a region organised by largest population to smallest.
     */
    @Test
    void testGetAllRegion()
    {
        ArrayList<Country> regions = app.getAllRegion("Caribbean");
        Country reg = regions.get(0);
        assertEquals(reg.getCountryCode(), "CUB");
        assertEquals(reg.getCountryName(), "Cuba");
        assertEquals(reg.getCountryCont(), "North America");
        assertEquals(reg.getCountryReg(), "Caribbean");
        assertEquals(reg.getCountryCap(), "La Habana");
        assertEquals(Integer.parseInt(reg.getCountryPopulation()), 11201000);
    }

    /*
     * Query 4: The top N populated countries in the world where N is provided by the user.
     */
    @Test
    void testGetAllNPopCountries()
    {
        ArrayList<Country> npopctrs = app.getAllNPopulatedCountries(10);
        Country npopctr = npopctrs.get(0);
        assertEquals(npopctr.getCountryCode(), "CHN");
        assertEquals(npopctr.getCountryName(), "China");
        assertEquals(npopctr.getCountryCont(), "Asia");
        assertEquals(npopctr.getCountryReg(), "Eastern Asia");
        assertEquals(npopctr.getCountryCap(), "Peking");
        assertEquals(Integer.parseInt(npopctr.getCountryPopulation()), 1277558000);
    }

    /*
     * Query 5: The top N populated countries in a continent where N is provided by the user.
     */
    @Test
    void testGetAllNPopContinents()
    {
        ArrayList<Country> npopconts = app.getAllNPopulatedContinents("Europe", 10);
        Country npopcont = npopconts.get(0);
        assertEquals(npopcont.getCountryCode(), "RUS");
        assertEquals(npopcont.getCountryName(), "Russian Federation");
        assertEquals(npopcont.getCountryCont(), "Europe");
        assertEquals(npopcont.getCountryReg(), "Eastern Europe");
        assertEquals(npopcont.getCountryCap(), "Moscow");
        assertEquals(Integer.parseInt(npopcont.getCountryPopulation()), 146934000);
    }

    /*
     * Query 6: The top N populated countries in a region where N is provided by the user.
     */
    @Test
    void testGetAllNPopRegions()
    {
        ArrayList<Country> npopregns = app.getAllNPopulatedRegion("Caribbean", 10);
        Country npopregn = npopregns.get(0);
        assertEquals(npopregn.getCountryCode(), "CUB");
        assertEquals(npopregn.getCountryName(), "Cuba");
        assertEquals(npopregn.getCountryCont(), "North America");
        assertEquals(npopregn.getCountryReg(), "Caribbean");
        assertEquals(npopregn.getCountryCap(), "La Habana");
        assertEquals(Integer.parseInt(npopregn.getCountryPopulation()), 11201000);
    }
}


