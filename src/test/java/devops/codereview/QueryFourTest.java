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
        npopctr.country_code ="VAT";
        npopctr.country_name="Holy See (Vatican City State)";
        npopctr.country_cont="Europe";
        npopctr.country_reg="Southern Europe";
        npopctr.country_population="1000";
        npopctr.country_cap="Vatican";
        NPopulatedCountries.add(npopctr);
        app.printNPopulatedCountries(NPopulatedCountries);
    }
}

