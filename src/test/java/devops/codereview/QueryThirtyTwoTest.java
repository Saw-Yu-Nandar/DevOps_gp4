package devops.codereview;
////Unit testing for different languages Percentage, Population
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryThirtyTwoTest {
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printLanguagesTestEmpty()
    {
        ArrayList<CountryLanguage> countryLangEmp = new ArrayList<CountryLanguage>();
        app.printCountryLanguage(countryLangEmp);
    }

    //print a list with a null value
    @Test
    void printLanguagesTestContainsNull()
    {
        ArrayList<CountryLanguage> countryLangNull = new ArrayList<CountryLanguage>();
        countryLangNull.add(null);
        app.printCountryLanguage(countryLangNull);
    }
    @Test
    void printCountryLanguage()
    {
        ArrayList<CountryLanguage> countryLanguage = new ArrayList<CountryLanguage>();
        CountryLanguage langu            = new CountryLanguage();
        langu.setCountryLanguage("Chinese");
        langu.setCountryPopulation("1968265500");
        langu.setCountryPercent("39.808548%");
        langu.setCountryLanguage("Hindi");
        langu.setCountryPopulation("1046303000");
        langu.setCountryPercent("21.161678%");
        langu.setCountryLanguage("Spanish");
        langu.setCountryPopulation("750296800");
        langu.setCountryPercent("15.174898%");
        langu.setCountryLanguage("English");
        langu.setCountryPopulation("627418300");
        langu.setCountryPercent("12.689656%");
        langu.setCountryLanguage("Arabic");
        langu.setCountryPopulation("552045100");
        langu.setCountryPercent("11.165218%");
        countryLanguage.add(langu);
        app.printCountryLanguage(countryLanguage);
    }
}