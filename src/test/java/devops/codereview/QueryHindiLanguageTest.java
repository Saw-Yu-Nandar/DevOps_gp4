package devops.codereview;

//Unit testing for Hindi language Percentage, Population
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryHindiLanguageTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printCountryLanguageHindiTestEmpty()
    {
        ArrayList<CountryLanguage> countryLanguage3 = new ArrayList<CountryLanguage>();
        app.printCountryLanguage3(countryLanguage3);
    }

    //print a list with a null value
    @Test
    void printChineselanguageTestContainsNull()
    {
        ArrayList<CountryLanguage> countryLanguage3 = new ArrayList<CountryLanguage>();
        countryLanguage3.add(null);
        app.printCountryLanguage2(countryLanguage3);
    }
    @Test
    void printCountryLanguage2()
    {
        ArrayList<CountryLanguage> countryLanguage3 = new ArrayList<CountryLanguage>();
        CountryLanguage clanguage3            = new CountryLanguage();
        clanguage3.language             = "Hindi";
        clanguage3.percentage          = "43.7";
        clanguage3.population       = "817000";
        countryLanguage3.add(clanguage3);
        app.printCountryLanguage3(countryLanguage3);
    }
}