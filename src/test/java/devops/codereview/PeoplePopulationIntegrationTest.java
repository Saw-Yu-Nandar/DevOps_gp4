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
     * Query 23: The population of people, people living in cities, and people not living in cities in each continent.
     */
    @Test
    void testGetPopulatedPeopleConitnent() {
        ArrayList<PeoplePopulation> popPeopleContinent = app.getPopulatedPeopleContinent();
        PeoplePopulation popPeopleCont = popPeopleContinent.get(0);
        assertEquals(popPeopleCont.getContinentName(), "North America");
        assertEquals(Integer.parseInt(popPeopleCont.getContinentPopulation()), 482993000);
        assertEquals(Integer.parseInt(popPeopleCont.getCityPopulation()), 168250381);
    }
    /*
     * Query 24: The population of people, people living in cities, and people not living in cities in each region.
     */
    @Test
    void testGetPopulatedPeopleRegion() {
        ArrayList<PeoplePopulation> popPeopleRegion = app.getPopulatedPeopleRegions();
        PeoplePopulation popPeopleReg = popPeopleRegion.get(0);
        assertEquals(popPeopleReg.getRegionsName(), "Caribbean");
        assertEquals(Integer.parseInt(popPeopleReg.getRegionsPopulation()), 38140000);
        assertEquals(Integer.parseInt(popPeopleReg.getCityPopulation()), 11067550);
    }

    /*
     * Query 25: The population of people, people living in cities, and people not living in cities in each country.
     */
    @Test
    void testGetPopulatedPeopleCountry() {
        ArrayList<PeoplePopulation> popPeopleCountry = app.getPopulatedPeopleCountry();
        PeoplePopulation popPeopleCount = popPeopleCountry.get(0);
        assertEquals(popPeopleCount.getCountriesName(), "Aruba");
        assertEquals(Integer.parseInt(popPeopleCount.getCountriesPopulation()), 103000);
        assertEquals(Integer.parseInt(popPeopleCount.getCityPopulation()), 29034);
    }
    /*
     * Query 26: The population of the world.
     */
    @Test
    void testGetPopWorld() {
        ArrayList<PeoplePopulation> popGetWld = app.getWorldPopulation();
        PeoplePopulation populationWld = popGetWld.get(0);
        assertEquals(populationWld.getWorldPopulation(), "6078749450");
    }

    /*
     * Query 27: The population of the continent.
     */
    @Test
    void testGetPopContinent() {
        ArrayList<PeoplePopulation> popGetCon = app.getContinentPopulation("Asia");
        PeoplePopulation populationCon = popGetCon.get(0);
        assertEquals(populationCon.getContinentName(), "Asia");
        assertEquals(populationCon.getContinentPopulation(), "3705025700");
    }

    /*
     * Query 28: The population of the regions.
     */
    @Test
    void testGetPopRegions() {
        ArrayList<PeoplePopulation> popGetReg = app.getRegionsPopulation("Caribbean");
        PeoplePopulation populationReg = popGetReg.get(0);
        assertEquals(populationReg.getRegionsName(), "Caribbean");
        assertEquals(populationReg.getRegionsPopulation(), "38140000");
    }

    /*
     * Query 29: The population of the countries.
     */
    @Test
    void testGetPopCountries() {
        ArrayList<PeoplePopulation> popGetCountr = app.getCountriesPopulation("Austria");
        PeoplePopulation populationCountr = popGetCountr.get(0);
        assertEquals(populationCountr.getCountriesName(), "Austria");
        assertEquals(populationCountr.getCountriesPopulation(), "8091800");
    }

    /*
     * Query 30: The population of the districts.
     */
    @Test
    void testGetPopDistricts() {
        ArrayList<PeoplePopulation> popGetDst = app.getDistrictPopulation("Kabol");
        PeoplePopulation populationDist = popGetDst.get(0);
        assertEquals(populationDist.getDistrictName(), "Kabol");
        assertEquals(populationDist.getDistrictPopulation(), "1780000");
    }
    /*
    * Query 31: The population of the cities.
    */
    @Test
    void testGetPopCity ()
    {
        ArrayList<PeoplePopulation> popGetCty = app.getCityPopulation("Haag");
        PeoplePopulation populationCty = popGetCty.get(0);
        assertEquals(populationCty.getCityName(), "Haag");
        assertEquals(populationCty.getCityPopulation(), "440900");
    }
    /*
     * Query 32: languages Percentage, Population
     */
    @Test
    void testGetLanguages ()
    {
        ArrayList<CountryLanguage> couLang = app.getCountryLanguage("Chinese","English","Hindi","Spanish","Arabic");
        CountryLanguage lang = couLang.get(0);
        assertEquals(lang.getCountryLanguage(), "Chinese");
        assertEquals(lang.getCountryPopulation(), "1968265500");

    }
}