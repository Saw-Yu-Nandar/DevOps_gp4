package devops.codereview;

import devops.codereview.*;
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
        ArrayList<Countries> NPopulatedCountries = new ArrayList<Countries>();
        app.printNPopulatedCountries(NPopulatedCountries);
    }

    //print a list with a null value
    @Test
    void printNPopulatedCountriesTestContainsNull()
    {
        ArrayList<Countries> NPopulatedCountries = new ArrayList<Countries>();
        NPopulatedCountries.add(null);
        app.printNPopulatedCountries(NPopulatedCountries);
    }
    @Test
    void printNPopulatedCountries()
    {
        ArrayList<Countries> NPopulatedCountries = new ArrayList<Countries>();
        Countries npopctr = new Countries();
        npopctr.country_name = "China";
        npopctr.country_population = "1277558000";
        NPopulatedCountries.add(npopctr);
        app.printNPopulatedCountries(NPopulatedCountries);
    }
}