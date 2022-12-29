package devops.codereview;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
public class AppIntegrationTestPeoplePopulation {
    static App app;

    @BeforeAll
    static void init() {
        app = new App();
        app.connect("localhost:33060", 30000);
    }

    /*
     * Query 23: The population of people, people living in cities, and people not living in cities in each continent.
     */
    @Test
    void testGetPopulatedPeopleConitnent() {
        ArrayList<PeoplePopulation> pop_continent = app.getPopulatedPeopleContinent("Europe",10);
        PeoplePopulation population_continent = pop_continent.get(0);
        assertEquals(population_continent.getCap_cit_name(), "Moscow");
        assertEquals(population_continent.getCap_cit_country(), "Russian Federation");
        assertEquals(Integer.parseInt(population_continent.getCap_cit_population()), 146934000);
    }
    /*
     * Query 24: The population of people, people living in cities, and people not living in cities in each region.
     */
    @Test
    void testGetPopulatedPeopleRegion() {
        ArrayList<PeoplePopulation> pop_region = app.getPopulatedPeopleRegions("Southern Europe",10);
        PeoplePopulation population_region = pop_region.get(0);
        assertEquals(population_region.getCap_cit_name(), "Roma");
        assertEquals(population_region.getCap_cit_country(), "Italy");
        assertEquals(Integer.parseInt(population_region.getCap_cit_population()), 57680000);
    }

    /*
     * Query 25: The population of people, people living in cities, and people not living in cities in each country.
     */
    @Test
    void testGetPopulatedPeopleCountry() {
        ArrayList<PeoplePopulation> pop_country = app.getPopulatedPeopleCountry("Myanmar",10);
        PeoplePopulation population_country = pop_country.get(0);
        assertEquals(population_country.getCap_cit_name(), "Rangoon (Yangon)");
        assertEquals(population_country.getCap_cit_country(), "Myanmar");
        assertEquals(Integer.parseInt(population_country.getCap_cit_population()), 45611000);
    }

    /*
     * Query 26: The population of the world.
     */
    @Test
    void testGetPopWorld() {
        ArrayList<PeoplePopulation> pop_wld = app.getWorldPopulation();
        PeoplePopulation population_wld = pop_wld.get(0);
        assertEquals(population_wld.getWorld_population(), "6078749450");
    }

    /*
     * Query 27: The population of the continent.
     */
    @Test
    void testGetPopContinent() {
        ArrayList<PeoplePopulation> pop_con = app.getContinentPopulation();
        PeoplePopulation population_con = pop_con.get(0);
        assertEquals(population_con.getContinent_name(), "North America");
        assertEquals(population_con.getContinent_population(), "482993000");
    }

    /*
     * Query 28: The population of the regions.
     */
    @Test
    void testGetPopRegions() {
        ArrayList<PeoplePopulation> pop_reg = app.getRegionsPopulation();
        PeoplePopulation population_reg = pop_reg.get(0);
        assertEquals(population_reg.getRegions_name(), "Caribbean");
        assertEquals(population_reg.getRegions_population(), "38140000");
    }

    /*
     * Query 29: The population of the countries.
     */
    @Test
    void testGetPopCountries() {
        ArrayList<PeoplePopulation> pop_countr = app.getCountriesPopulation();
        PeoplePopulation population_countr = pop_countr.get(0);
        assertEquals(population_countr.getCountries_name(), "Aruba");
        assertEquals(population_countr.getCountries_population(), "103000");
    }

    /*
     * Query 30: The population of the districts.
     */
    @Test
    void testGetPopDistricts() {
        ArrayList<PeoplePopulation> pop_dst = app.getDistrictPopulation();
        PeoplePopulation population_dist = pop_dst.get(0);
        assertEquals(population_dist.getDistrict_name(), "Kabol");
        assertEquals(population_dist.getDistrict_population(), "1780000");
    }

        /*
         * Query 31: The population of the cities.
         */
    @Test
    void testGetPopCity ()
    {
        ArrayList<PeoplePopulation> pop_st = app.getCityPopulation();
        PeoplePopulation population_city = pop_st.get(0);
        assertEquals(population_city.getCity_name(), "Kabul");
        assertEquals(population_city.getCity_population(), "1780000");
    }
}