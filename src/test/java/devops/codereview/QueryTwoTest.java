package devops.codereview;

import devops.codereview.App;
import devops.codereview.Countries;
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
        cont.country_code ="JPN";
        cont.country_name="Japan";
        cont.country_cont="Asia";
        cont.country_reg="Eastern Asia";
        cont.country_population="126714000";
        cont.country_cap="Osaka";
        continent.add(cont);
        app.printContinent(continent);
    }
}