package devops.codereview;
import devops.codereview.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class citiesQueryAppTest
{
    static App app;
    @BeforeAll
    static void init()
    {
        app = new App();
    }
    //Unit testing for all the cities in the world organised by largest population to smallest.
    @Test
    void printAllCitiesTestEmpty()
    {
        ArrayList<Cities> cittestempty = new ArrayList<Cities>();
        app.printCities(cittestempty);
    }
    //print a list with a null value
    @Test
    void printAllCitiesTestContainsNull()
    {
        ArrayList<Cities> citnull = new ArrayList<Cities>();
        citnull.add(null);
        app.printCities(citnull);
    }
    @Test
    void printAllCities()
    {
        ArrayList<Cities> allcities    = new ArrayList<Cities>();
        Cities cit                  = new Cities();
        cit.cit_name                = "Seoul";
        cit.country_name            = "South Korea";
        cit.cit_district            = "Seoul";
        cit.cit_population          = "9981619";
        allcities.add(cit);
        app.printCities(allcities);
    }
    //Unit testing for all the cities in a continent organised by largest population to smallest.
    @Test
    void printContinentTestEmpty()
    {
        ArrayList<Cities> conttestemp = new ArrayList<Cities>();
        app.printContinents(conttestemp);
    }
    //print a list with a null value
    @Test
    void printAllContinentTestContainsNull()
    {
        ArrayList<Cities> contnull = new ArrayList<Cities>();
        contnull.add(null);
        app.printContinents(contnull);
    }
    @Test
    void printContinent()
    {
        ArrayList<Cities> continent = new ArrayList<Cities>();
        Cities conti                = new Cities();
        conti.cit_name              = "Tilburg";
        conti.country_name          = "Netherlands";
        conti.cit_district          = "Noord-Brabant";
        conti.cit_population        = "193238";
        continent.add(conti);
        app.printContinents(continent);
    }
}