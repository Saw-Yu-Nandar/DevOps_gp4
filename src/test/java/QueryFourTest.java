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
        ArrayList<NPopulatedCountries> NPopulatedCountries = new ArrayList<NPopulatedCountries>();
        app.printNPopulatedCountries(NPopulatedCountries);
    }

    //print a list with a null value
    @Test
    void printNPopulatedCountriesTestContainsNull()
    {
        ArrayList<NPopulatedCountries> NPopulatedCountries = new ArrayList<NPopulatedCountries>();
        NPopulatedCountries.add(null);
        app.printNPopulatedCountries(NPopulatedCountries);
    }
    @Test
    void printNPopulatedCountries()
    {
        ArrayList<NPopulatedCountries> NPopulatedCountries = new ArrayList<NPopulatedCountries>();
        NPopulatedCountries npopctr = new NPopulatedCountries();
        npopctr.name = "China";
        npopctr.population = "1277558000";
        NPopulatedCountries.add(npopctr);
        app.printNPopulatedCountries(NPopulatedCountries);
    }
}