package devops.codereview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryFourTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printNPopulatedCountriesTestEmpty()
    {
        ArrayList<Country> nPopulatedCountries = new ArrayList<Country>();
        app.printNPopulatedCountries(nPopulatedCountries);
    }

    //print a list with a null value
    @Test
    void printNPopulatedCountriesTestContainsNull()
    {
        ArrayList<Country> nPopulatedCountries = new ArrayList<Country>();
        nPopulatedCountries.add(null);
        app.printNPopulatedCountries(nPopulatedCountries);
    }
    @Test
    void printNPopulatedCountries()
    {
        ArrayList<Country> nPopulatedCountries = new ArrayList<Country>();
        Country npopctr = new Country();
        npopctr.setCountryName("China");
        npopctr.setCountryPopulation("1277558000");
        nPopulatedCountries.add(npopctr);
        app.printNPopulatedCountries(nPopulatedCountries);
    }
}