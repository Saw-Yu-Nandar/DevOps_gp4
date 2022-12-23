import devops.codereview.App;
import devops.codereview.Continent;
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
        ArrayList<Continent> continent = new ArrayList<Continent>();
        app.printContinent(continent);
    }

    //print a list with a null value
    @Test
    void printContinentTestContainsNull()
    {
        ArrayList<Continent> continent = new ArrayList<Continent>();
        continent.add(null);
        app.printContinent(continent);
    }
    @Test
    void printContinent()
    {
        ArrayList<Continent> continent = new ArrayList<Continent>();
        Continent cont = new Continent();
        cont.continent = "North America";
        cont.population = "103000";
        continent.add(cont);
        app.printContinent(continent);
    }
}