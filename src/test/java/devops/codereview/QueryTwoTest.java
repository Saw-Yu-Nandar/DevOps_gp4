package devops.codereview;

import devops.codereview.App;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryTwoTest
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
        ArrayList<Countries> continent = new ArrayList<Countries>();
        app.printContinent(continent);
    }

    //print a list with a null value
    @Test
    void printContinentTestContainsNull()
    {
        ArrayList<Countries> continent = new ArrayList<Countries>();
        continent.add(null);
        app.printContinent(continent);
    }
    @Test
    void printContinent()
    {
        ArrayList<Countries> continent = new ArrayList<Countries>();
        Countries cont = new Countries();
        cont.country_cont = "North America";
        cont.country_population = "103000";
        continent.add(cont);
        app.printContinent(continent);
    }
}