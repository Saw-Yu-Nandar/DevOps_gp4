package devops.codereview;

import devops.codereview.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryFiveTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printNPopulatedContinentsTestEmpty()
    {
        ArrayList<Countries> NPopulated_Continents = new ArrayList<Countries>();
        app.printNPopulatedContinents(NPopulated_Continents);
    }

    //print a list with a null value
    @Test
    void printNPopulatedContinentsTestContainsNull()
    {
        ArrayList<Countries> NPopulated_Continents = new ArrayList<Countries>();
        NPopulated_Continents.add(null);
        app.printNPopulatedContinents(NPopulated_Continents);
    }

    @Test
    void printNPopulatedContinents()
    {
        ArrayList<Countries> NPopulated_Continents = new ArrayList<Countries>();
        Countries npopcont = new Countries();
        npopcont.country_code ="REU";
        npopcont.country_name="Réunion";
        npopcont.country_cont="Africa";
        npopcont.country_reg="Eastern Africa";
        npopcont.country_population="699000";
        npopcont.country_cap="Saint-Denis";
        NPopulated_Continents.add(npopcont);
        app.printNPopulatedContinents(NPopulated_Continents);
    }
}
