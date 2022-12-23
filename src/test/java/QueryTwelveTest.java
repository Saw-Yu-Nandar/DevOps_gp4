import devops.codereview.*;
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
        ArrayList<Cities> worlds = new ArrayList<Cities>();
        app.printTopNWorlds(worlds);
    }

    //print a list with a null value
    @Test
    void printTopNCitiesTestContainsNull()
    {
        ArrayList<Cities> worlds = new ArrayList<Cities>();
        worlds.add(null);
        app.printTopNWorlds(worlds);
    }
    @Test
    void printTopNCities()
    {
        ArrayList<Cities> worlds = new ArrayList<Cities>();
        Cities world = new Cities();
        world.cit_name = "Moscow";
        world.country_name = "Russian Federation";
        world.cit_district = "Moscow (City)";
        world.cit_population = "8389200";
        worlds.add(world);
        app.printTopNWorlds(worlds);
    }
}