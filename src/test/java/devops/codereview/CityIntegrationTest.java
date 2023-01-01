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
        assertEquals(cit.getCityName(), "Mumbai (Bombay)");
        assertEquals(cit.getCountryName(), "India");
        assertEquals(cit.getCityDistrict(),"Maharashtra");
        assertEquals(Integer.parseInt(cit.getCityPopulation()),10500000);
    }
    /*
     * Query 8: All the cities in a continent organised by largest population to smallest.
     */
    @Test
    void testGetAllCitiesContinent()
    {
        ArrayList<City> city = app.getAllCitiesContinent("Asia");
        City cit = city.get(0);
        assertEquals(cit.getCityName(), "Mumbai (Bombay)");
        assertEquals(cit.getCountryName(), "India");
        assertEquals(cit.getCityDistrict(),"Maharashtra");
        assertEquals(Integer.parseInt(cit.getCityPopulation()),10500000);
    }
    /*
     * Query 9: All the cities in a region organised by largest population to smallest.
     */
    @Test
    void testGetAllCitiesRegions()
    {
        ArrayList<City> city = app.getAllCitiesRegions("Caribbean");
        City cit = city.get(0);
        assertEquals(cit.getCityName(), "La Habana");
        assertEquals(cit.getCountryName(), "Cuba");
        assertEquals(cit.getCityDistrict(),"La Habana");
        assertEquals(Integer.parseInt(cit.getCityPopulation()),2256000);
    }
    /*
     * Query 10: All the cities in a country organised by largest population to smallest.
     */
    @Test
    void testGetAllCitiesCountries()
    {
        ArrayList<City> city = app.getAllCitiesCountries("Myanmar");
        City cit = city.get(0);
        assertEquals(cit.getCityName(), "Rangoon (Yangon)");
        assertEquals(cit.getCountryName(), "Myanmar");
        assertEquals(cit.getCityDistrict(),"Rangoon [Yangon]");
        assertEquals(Integer.parseInt(cit.getCityPopulation()),3361700);
    }
    /*
     * Query 11: All the cities in a district organised by largest population to smallest.
     */
    @Test
    void testGetAllCitiesDistrict()
    {
        ArrayList<City> city = app.getAllCitiesDistrict("Queensland");
        City cit = city.get(0);
        assertEquals(cit.getCityName(), "Brisbane");
        assertEquals(cit.getCountryName(), "Australia");
        assertEquals(cit.getCityDistrict(),"Queensland");
        assertEquals(Integer.parseInt(cit.getCityPopulation()),1291117);
    }
    /*
     * Query 12: The top N populated cities in the world where N is provided by the user.
     */
    @Test
    void testGetTopNPopulatedCities()
    {
        ArrayList<City> worlds = app.getTopNPopulatedCities(10);
        City wld = worlds.get(0);
        assertEquals(wld.getCityName(), "Mumbai (Bombay)");
        assertEquals(wld.getCountryName(), "India");
        assertEquals(wld.getCityDistrict(),"Maharashtra");
        assertEquals(Integer.parseInt(wld.getCityPopulation()),10500000);
    }
    /*
     * Query 13: The top N populated cities in a continent where N is provided by the user.
     */
    @Test
    void testGetTopNPopulatedContinent()
    {
        ArrayList<City> continent = app.getTopNPopulatedContinent("Europe",10);
        City cnt = continent.get(0);
        assertEquals(cnt.getCityName(), "Moscow");
        assertEquals(cnt.getCountryName(), "Russian Federation");
        assertEquals(cnt.getCityDistrict(),"Moscow (City)");
        assertEquals(Integer.parseInt(cnt.getCityPopulation()),8389200);
    }
    /*
     * Query 14: The top N populated cities in a region where N is provided by the user.
     */
    @Test
    void testGetTopNPopulatedRegion()
    {
        ArrayList<City> region = app.getTopNPopulatedRegion("Caribbean",10);
        City reg = region.get(0);
        assertEquals(reg.getCityName(), "La Habana");
        assertEquals(reg.getCountryName(), "Cuba");
        assertEquals(reg.getCityDistrict(),"La Habana");
        assertEquals(Integer.parseInt(reg.getCityPopulation()),2256000);
    }
    /*
     * Query 15: The top N populated cities in a country where N is provided by the user.
     */
    @Test
    void testGetTopNPopulatedCountries()
    {
        ArrayList<City> countries = app.getTopNPopulatedCountries("Argentina",10);
        City count = countries.get(0);
        assertEquals(count.getCityName(), "Buenos Aires");
        assertEquals(count.getCountryName(), "Argentina");
        assertEquals(count.getCityDistrict(),"Distrito Federal");
        assertEquals(Integer.parseInt(count.getCityPopulation()),2982146);
    }
    /*
     * Query 16: The top N populated cities in a district where N is provided by the user.
     */
    @Test
    void testGetTopNPopulatedDistrict()
    {
        ArrayList<City> district = app.getTopNPopulatedDistrict("Zuid-Holland",10);
        City dist = district.get(0);
        assertEquals(dist.getCityName(), "Rotterdam");
        assertEquals(dist.getCountryName(), "Netherlands");
        assertEquals(dist.getCityDistrict(),"Zuid-Holland");
        assertEquals(Integer.parseInt(dist.getCityPopulation()),593321);
    }
}
