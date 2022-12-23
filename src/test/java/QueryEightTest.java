import devops.codereview.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryEightTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printContinentTestEmpty()
    {
        ArrayList<Cities> continent = new ArrayList<Cities>();
        app.printContinents(continent);
    }

    //print a list with a null value
    @Test
    void printAllContinentTestContainsNull()
    {
        ArrayList<Cities> continent = new ArrayList<Cities>();
        continent.add(null);
        app.printContinents(continent);
    }
    @Test
    void printContinent()
    {
        ArrayList<Cities> continent = new ArrayList<Cities>();
        Cities conti = new Cities();
        conti.cit_name = "Tilburg";
        conti.country_name = "Netherlands";
        conti.cit_district = "Noord-Brabant";
        conti.cit_population = "193238";
        continent.add(conti);
        app.printContinents(continent);

    }
}