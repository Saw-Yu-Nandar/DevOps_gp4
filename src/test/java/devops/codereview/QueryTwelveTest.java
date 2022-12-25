package devops.codereview;

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
        world.cit_name = "Moscow";
        world.country_name = "Russian Federation";
        world.cit_district = "Moscow (City)";
        world.cit_population = "8389200";
        worlds.add(world);
        app.printTopNWorlds(worlds);
    }
}