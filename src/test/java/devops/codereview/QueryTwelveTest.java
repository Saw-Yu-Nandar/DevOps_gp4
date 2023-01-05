package devops.codereview;
//Unit testing for the top N populated cities in the world where N is provided by the user.
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryTwelveTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printTopNCitiesTestEmpty()
    {
        ArrayList<City> worlds = new ArrayList<City>();
        app.printTopNWorlds(worlds);
    }

    //print a list with a null value
    @Test
    void printTopNCitiesTestContainsNull()
    {
        ArrayList<City> worlds = new ArrayList<City>();
        worlds.add(null);
        app.printTopNWorlds(worlds);
    }
    @Test
    void printTopNCities()
    {
        ArrayList<City> worlds = new ArrayList<City>();
        City world = new City();
        world.setCitName("Moscow");
        world.setCountryName("Russian Federation");
        world.setCitDistrict("Moscow (City)");
        world.setCitPopulation("8389200");
        worlds.add(world);
        app.printTopNWorlds(worlds);
    }
}