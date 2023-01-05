package devops.codereview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class CityIntegrationTest {
    static App app;
    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 30000);
    }
    /*
     * Query 7: All the cities in the world organised by largest population to smallest.
     */
    @Test
    void testGetAllCities()
    {
        ArrayList<City> city = app.getAllCities();
        City cit = city.get(0);
        assertEquals(cit.getCitName(), "Mumbai (Bombay)");
        assertEquals(cit.getCountryName(), "India");
        assertEquals(cit.getCitDistrict(),"Maharashtra");
        assertEquals(Integer.parseInt(cit.getCitPopulation()),10500000);
    }
    /*
     * Query 8: All the cities in a continent organised by largest population to smallest.
     */
    @Test
    void testGetAllCitiesContinent()
    {
        ArrayList<City> city = app.getAllCitiesContinent("Asia");
        City cit = city.get(0);
        assertEquals(cit.getCitName(), "Mumbai (Bombay)");
        assertEquals(cit.getCountryName(), "India");
        assertEquals(cit.getCitDistrict(),"Maharashtra");
        assertEquals(cit.getCitCont(),"Asia");
        assertEquals(Integer.parseInt(cit.getCitPopulation()),10500000);
    }
    /*
     * Query 9: All the cities in a region organised by largest population to smallest.
     */
    @Test
    void testGetAllCitiesRegions()
    {
        ArrayList<City> city = app.getAllCitiesRegions("Caribbean");
        City cit = city.get(0);
        assertEquals(cit.getCitName(), "La Habana");
        assertEquals(cit.getCountryName(), "Cuba");
        assertEquals(cit.getCitDistrict(),"La Habana");
        assertEquals(cit.getCitReg(),"Caribbean");
        assertEquals(Integer.parseInt(cit.getCitPopulation()),2256000);
    }
    /*
     * Query 10: All the cities in a country organised by largest population to smallest.
     */
    @Test
    void testGetAllCitiesCountries()
    {
        ArrayList<City> city = app.getAllCitiesCountries("Myanmar");
        City cit = city.get(0);
        assertEquals(cit.getCitName(), "Rangoon (Yangon)");
        assertEquals(cit.getCountryName(), "Myanmar");
        assertEquals(cit.getCitDistrict(),"Rangoon [Yangon]");
        assertEquals(Integer.parseInt(cit.getCitPopulation()),3361700);
    }
    /*
     * Query 11: All the cities in a district organised by largest population to smallest.
     */
    @Test
    void testGetAllCitiesDistrict()
    {
        ArrayList<City> city = app.getAllCitiesDistrict("Queensland");
        City cit = city.get(0);
        assertEquals(cit.getCitName(), "Brisbane");
        assertEquals(cit.getCountryName(), "Australia");
        assertEquals(cit.getCitDistrict(),"Queensland");
        assertEquals(Integer.parseInt(cit.getCitPopulation()),1291117);
    }
    /*
     * Query 12: The top N populated cities in the world where N is provided by the user.
     */
    @Test
    void testGetTopNPopulatedCities()
    {
        ArrayList<City> worlds = app.getTopNPopulatedCities(10);
        City wld = worlds.get(0);
        assertEquals(wld.getCitName(), "Mumbai (Bombay)");
        assertEquals(wld.getCountryName(), "India");
        assertEquals(wld.getCitDistrict(),"Maharashtra");
        assertEquals(Integer.parseInt(wld.getCitPopulation()),10500000);
    }
    /*
     * Query 13: The top N populated cities in a continent where N is provided by the user.
     */
    @Test
    void testGetTopNPopulatedContinent()
    {
        ArrayList<City> continent = app.getTopNPopulatedContinent("Europe",10);
        City cnt = continent.get(0);
        assertEquals(cnt.getCitName(), "Moscow");
        assertEquals(cnt.getCountryName(), "Russian Federation");
        assertEquals(cnt.getCitDistrict(),"Moscow (City)");
        assertEquals(cnt.getCitCont(),"Europe");
        assertEquals(Integer.parseInt(cnt.getCitPopulation()),8389200);
    }
    /*
     * Query 14: The top N populated cities in a region where N is provided by the user.
     */
    @Test
    void testGetTopNPopulatedRegion()
    {
        ArrayList<City> region = app.getTopNPopulatedRegion("Caribbean",10);
        City reg = region.get(0);
        assertEquals(reg.getCitName(), "La Habana");
        assertEquals(reg.getCountryName(), "Cuba");
        assertEquals(reg.getCitDistrict(),"La Habana");
        assertEquals(reg.getCitReg(), "Caribbean");
        assertEquals(Integer.parseInt(reg.getCitPopulation()),2256000);
    }
    /*
     * Query 15: The top N populated cities in a country where N is provided by the user.
     */
    @Test
    void testGetTopNPopulatedCountries()
    {
        ArrayList<City> countries = app.getTopNPopulatedCountries("Argentina",10);
        City count = countries.get(0);
        assertEquals(count.getCitName(), "Buenos Aires");
        assertEquals(count.getCountryName(), "Argentina");
        assertEquals(count.getCitDistrict(),"Distrito Federal");
        assertEquals(Integer.parseInt(count.getCitPopulation()),2982146);
    }
    /*
     * Query 16: The top N populated cities in a district where N is provided by the user.
     */
    @Test
    void testGetTopNPopulatedDistrict()
    {
        ArrayList<City> district = app.getTopNPopulatedDistrict("Zuid-Holland",10);
        City dist = district.get(0);
        assertEquals(dist.getCitName(), "Rotterdam");
        assertEquals(dist.getCountryName(), "Netherlands");
        assertEquals(dist.getCitDistrict(),"Zuid-Holland");
        assertEquals(Integer.parseInt(dist.getCitPopulation()),593321);
    }
}