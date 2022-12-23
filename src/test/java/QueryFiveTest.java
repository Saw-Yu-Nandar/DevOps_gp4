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
        ArrayList<NPopulatedContinents> NPopulated_Continents = new ArrayList<NPopulatedContinents>();
        app.printNPopulatedContinents(NPopulated_Continents);
    }

    //print a list with a null value
    @Test
    void printNPopulatedContinentsTestContainsNull()
    {
        ArrayList<NPopulatedContinents> NPopulated_Continents = new ArrayList<NPopulatedContinents>();
        NPopulated_Continents.add(null);
        app.printNPopulatedContinents(NPopulated_Continents);
    }

    @Test
    void printNPopulatedContinents()
    {
        ArrayList<NPopulatedContinents> NPopulated_Continents = new ArrayList<NPopulatedContinents>();
        NPopulatedContinents npopcont = new NPopulatedContinents();
        npopcont.cont_name = "South America";
        npopcont.cont_population = "170115000";
        NPopulated_Continents.add(npopcont);
        app.printNPopulatedContinents(NPopulated_Continents);
    }
}