package devops.codereview;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
public class PeoplePopulationIntegrationTest {
    static App app;

    @BeforeAll
    static void init() {
        app = new App();
        app.connect("localhost:33060", 30000);
    }

    /*
     * Query 26: The population of the world.
     */
    @Test
    void testGetPopWorld() {
        ArrayList<PeoplePopulation> popWld = app.getWorldPopulation();
        PeoplePopulation populationWld = popWld.get(0);
        assertEquals(populationWld.getWorldPopulation(), "6078749450");
    }

    /*
     * Query 27: The population of the continent.
     */
    @Test
    void testGetPopContinent() {
        ArrayList<PeoplePopulation> popCon = app.getContinentPopulation();
        PeoplePopulation populationCon = popCon.get(0);
        assertEquals(populationCon.getContinentName(), "North America");
        assertEquals(populationCon.getContinentPopulation(), "482993000");
    }

    /*
     * Query 28: The population of the regions.
     */
    @Test
    void testGetPopRegions() {
        ArrayList<PeoplePopulation> popReg = app.getRegionsPopulation();
        PeoplePopulation populationReg = popReg.get(0);
        assertEquals(populationReg.getRegionsName(), "Caribbean");
        assertEquals(populationReg.getRegionsPopulation(), "38140000");
    }

    /*
     * Query 29: The population of the countries.
     */
    @Test
    void testGetPopCountries() {
        ArrayList<PeoplePopulation> popCountr = app.getCountriesPopulation();
        PeoplePopulation populCountr = popCountr.get(0);
        assertEquals(populCountr.getCountriesName(), "Aruba");
        assertEquals(populCountr.getCountriesPopulation(), "103000");
    }

    /*
     * Query 30: The population of the districts.
     */
    @Test
    void testGetPopDistricts() {
        ArrayList<PeoplePopulation> popDst = app.getDistrictPopulation();
        PeoplePopulation populationDistr = popDst.get(0);
        assertEquals(populationDistr.getDistrictName(), "Kabol");
        assertEquals(populationDistr.getDistrictPopulation(), "1780000");
    }

        /*
         * Query 31: The population of the cities.
         */
    @Test
    void testGetPopCity ()
    {
        ArrayList<PeoplePopulation> popCt = app.getCityPopulation();
        PeoplePopulation populCity = popCt.get(0);
        assertEquals(populCity.getCityName(), "Kabul");
        assertEquals(populCity.getCityPopulation(), "1780000");
    }
}