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
        ArrayList<Country> NPopulatedCountries = new ArrayList<Country>();
        app.printNPopulatedCountries(NPopulatedCountries);
    }

    //print a list with a null value
    @Test
    void printNPopulatedCountriesTestContainsNull()
    {
        ArrayList<Country> NPopulatedCountries = new ArrayList<Country>();
        NPopulatedCountries.add(null);
        app.printNPopulatedCountries(NPopulatedCountries);
    }
    @Test
    void printNPopulatedCountries()
    {
        ArrayList<Country> NPopulatedCountries = new ArrayList<Country>();
        Country npopctr = new Country();
        npopctr.setCountry_name("China");
        npopctr.setCountry_population("1277558000");
        NPopulatedCountries.add(npopctr);
        app.printNPopulatedCountries(NPopulatedCountries);
    }
}