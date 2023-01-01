package devops.codereview;

//Unit testing for English language Percentage, Population
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryEnglishLanguageTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printCountryLanguageEnglishTestEmpty()
    {
        ArrayList<CountryLanguage> countrylanguage2 = new ArrayList<CountryLanguage>();
        app.printCountryLanguage2(countrylanguage2);
    }

    //print a list with a null value
    @Test
    void printChineselanguageTestContainsNull()
    {
        ArrayList<CountryLanguage> countrylanguage2 = new ArrayList<CountryLanguage>();
        countrylanguage2.add(null);
        app.printCountryLanguage2(countrylanguage2);
    }
    @Test
    void printCountryLanguage2()
    {
        ArrayList<CountryLanguage> countrylanguage2 = new ArrayList<CountryLanguage>();
        CountryLanguage clanguage2            = new CountryLanguage();
        clanguage2.setLanguage("English");
        clanguage2.setPercentage("100.0");
        clanguage2.setPopulation("65000");
        countrylanguage2.add(clanguage2);
        app.printCountryLanguage2(countrylanguage2);
    }
}

