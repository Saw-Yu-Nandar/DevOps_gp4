package devops.codereview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppIntegrationTestCountryLanguage {
    static App app;

    @BeforeAll
    static void init() {
        app = new App();
        app.connect("localhost:33060", 30000);
    }

    /*
     * Query 31: List the population of people who speak Chinese in descending order
     */
    @Test
    void testGetAllCountryLanguage1() {
        ArrayList<CountryLanguage> countrylanguage1 = app.getCountryLanguage1("Chinese");
        CountryLanguage countryLanguage1 = countrylanguage1.get(0);
        assertEquals(countryLanguage1.getLanguage(), "Chinese");
        assertEquals(countryLanguage1.getPercentage(), "92.0");
        assertEquals(Integer.parseInt(countryLanguage1.getPopulation()), 1277558000);
    }

    /*
     * Query 32: List the population of people who speak English in descending order
     */
    @Test
    void testGetAllCountryLanguage2() {
        ArrayList<CountryLanguage> countrylanguage2 = app.getCountryLanguage2("English");
        CountryLanguage countryLanguage2 = countrylanguage2.get(0);
        assertEquals(countryLanguage2.getLanguage(), "English");
        assertEquals(countryLanguage2.getPercentage(), "100.0");
        assertEquals(Integer.parseInt(countryLanguage2.getPopulation()), 65000);
    }

    /*
     * Query 33: List the population of people who speak Hindi in descending order
     */
    @Test
    void testGetAllCountryLanguage3() {
        ArrayList<CountryLanguage> countLanguage3 = app.getCountryLanguage3("Hindi");
        CountryLanguage countryLanguage3 = countLanguage3.get(0);
        assertEquals(countryLanguage3.getLanguage(), "Hindi");
        assertEquals(countryLanguage3.getPercentage(), "43.7");
        assertEquals(Integer.parseInt(countryLanguage3.getPopulation()), 817000);
    }

    /*
     * Query 34: List the population of people who speak Spanish in descending order
     */
    @Test
    void testGetAllCountryLanguage4() {
        ArrayList<CountryLanguage> countLanguage4 = app.getCountryLanguage4("Spanish");
        CountryLanguage countryLanguage4 = countLanguage4.get(0);
        assertEquals(countryLanguage4.getLanguage(), "Spanish");
        assertEquals(countryLanguage4.getPercentage(), "100.0");
        assertEquals(Integer.parseInt(countryLanguage4.getPopulation()), 11201000);
    }

}
