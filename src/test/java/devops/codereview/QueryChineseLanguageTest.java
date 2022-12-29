package devops.codereview;
////Unit testing for Chinese language Percentage, Population
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryChineseLanguageTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printCountryLanguageChineseTestEmpty()
    {
        ArrayList<CountryLanguage> countrylanguage1 = new ArrayList<CountryLanguage>();
        app.printCountryLanguage1(countrylanguage1);
    }

    //print a list with a null value
    @Test
    void printChineselanguageTestContainsNull()
    {
        ArrayList<CountryLanguage> countrylanguage1 = new ArrayList<CountryLanguage>();
        countrylanguage1.add(null);
        app.printCountryLanguage1(countrylanguage1);
    }
    @Test
    void printCountryLanguage1()
    {
        ArrayList<CountryLanguage> countrylanguage1 = new ArrayList<CountryLanguage>();
        CountryLanguage clanguage            = new CountryLanguage();
        clanguage.language             = "Chinese";
        clanguage.percentage          = "92.0";
        clanguage.population       = "1277558000";
        countrylanguage1.add(clanguage);
        app.printCountryLanguage1(countrylanguage1);
    }
}