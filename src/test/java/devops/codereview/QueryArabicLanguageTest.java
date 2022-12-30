package devops.codereview;
////Unit testing for Arabic language Percentage, Population
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryArabicLanguageTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printArabicLanguageTestEmpty()
    {
        ArrayList<CountryLanguage> countrylanguage5 = new ArrayList<CountryLanguage>();
        app.printArabicLanguage(countrylanguage5);
    }

    //print a list with a null value
    @Test
    void printArabicLanguageTestContainsNull()
    {
        ArrayList<CountryLanguage> countrylanguage5 = new ArrayList<CountryLanguage>();
        countrylanguage5.add(null);
        app.printArabicLanguage(countrylanguage5);
    }
    @Test
    void printArabicLanguage()
    {
        ArrayList<CountryLanguage> countrylanguage5 = new ArrayList<CountryLanguage>();
        CountryLanguage arabic_lan                  = new CountryLanguage();
        arabic_lan.setLanguage("Arabic");
        arabic_lan.setPercentage("100.0");
        arabic_lan.setPopulation("293000");
        countrylanguage5.add(arabic_lan);
        app.printCountryLanguage1(countrylanguage5);
    }
}