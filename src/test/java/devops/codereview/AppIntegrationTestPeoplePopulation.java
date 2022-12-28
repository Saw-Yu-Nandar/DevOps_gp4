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
}
