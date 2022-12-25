package devops.codereview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTestCity {
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
        assertEquals(cit.getCit_name(), "Mumbai (Bombay)");
        assertEquals(cit.getCountry_name(), "India");
        assertEquals(cit.getCit_district(),"Maharashtra");
        assertEquals(Integer.parseInt(cit.getCit_population()),10500000);
    }
    /*
     * Query 8: All the cities in a continent organised by largest population to smallest.
     */
    @Test
    void testGetAllCitiesContinent()
    {
        ArrayList<City> city = app.getAllCitiesContinent("Asia");
        City cit = city.get(0);
        assertEquals(cit.getCit_name(), "Mumbai (Bombay)");
        assertEquals(cit.getCountry_name(), "India");
        assertEquals(cit.getCit_district(),"Maharashtra");
        assertEquals(Integer.parseInt(cit.getCit_population()),10500000);
    }
    /*
     * Query 9: All the cities in a region organised by largest population to smallest.
     */
    @Test
    void testGetAllCitiesRegions()
    {
        ArrayList<City> city = app.getAllCitiesRegions("Caribbean");
        City cit = city.get(0);
        assertEquals(cit.getCit_name(), "La Habana");
        assertEquals(cit.getCountry_name(), "Cuba");
        assertEquals(cit.getCit_district(),"La Habana");
        assertEquals(Integer.parseInt(cit.getCit_population()),2256000);
    }
    /*
     * Query 10: All the cities in a country organised by largest population to smallest.
     */
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
    /*
     * Query 11: All the cities in a district organised by largest population to smallest.
     */
    @Test
    void testGetAllCitiesDistrict()
    {
        ArrayList<City> city = app.getAllCitiesDistrict("Queensland");
        City cit = city.get(0);
        assertEquals(cit.getCit_name(), "Brisbane");
        assertEquals(cit.getCountry_name(), "Australia");
        assertEquals(cit.getCit_district(),"Queensland");
        assertEquals(Integer.parseInt(cit.getCit_population()),1291117);
    }
    /*
     * Query 12: The top N populated cities in the world where N is provided by the user.
     */
    @Test
    void testGetTopNPopulatedCities()
    {
        ArrayList<City> worlds = app.getTopNPopulatedCities(10);
        City wld = worlds.get(0);
        assertEquals(wld.getCit_name(), "Mumbai (Bombay)");
        assertEquals(wld.getCountry_name(), "India");
        assertEquals(wld.getCit_district(),"Maharashtra");
        assertEquals(Integer.parseInt(wld.getCit_population()),10500000);
    }
    /*
     * Query 13: The top N populated cities in a continent where N is provided by the user.
     */
    @Test
    void testGetTopNPopulatedContinent()
    {
        ArrayList<City> continent = app.getTopNPopulatedContinent("Europe",10);
        City cnt = continent.get(0);
        assertEquals(cnt.getCit_name(), "Moscow");
        assertEquals(cnt.getCountry_name(), "Russian Federation");
        assertEquals(cnt.getCit_district(),"Moscow (City)");
        assertEquals(Integer.parseInt(cnt.getCit_population()),8389200);
    }
    /*
     * Query 14: The top N populated cities in a region where N is provided by the user.
     */
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
    /*
     * Query 15: The top N populated cities in a country where N is provided by the user.
     */
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
    /*
     * Query 16: The top N populated cities in a district where N is provided by the user.
     */
    @Test
    void testGetTopNPopulatedDistrict()
    {
        ArrayList<City> district = app.getTopNPopulatedDistrict("Zuid-Holland",10);
        City dist = district.get(0);
        assertEquals(dist.getCit_name(), "Rotterdam");
        assertEquals(dist.getCountry_name(), "Netherlands");
        assertEquals(dist.getCit_district(),"Zuid-Holland");
        assertEquals(Integer.parseInt(dist.getCit_population()),593321);
    }
}
