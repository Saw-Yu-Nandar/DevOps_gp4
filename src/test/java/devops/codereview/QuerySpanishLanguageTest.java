package devops.codereview;

//Unit testing for Spanish language Percentage, Population
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QuerySpanishLanguageTest {
    static App app;

    @BeforeAll
    static void init() {
        app = new App();
    }

    @Test
    void printCountryLanguageSpanishTestEmpty() {
        ArrayList<CountryLanguage> countryLanguage4 = new ArrayList<CountryLanguage>();
        app.printCountryLanguage4(countryLanguage4);
    }

    //print a list with a null value
    @Test
    void printSpanishlanguageTestContainsNull() {
        ArrayList<CountryLanguage> countryLanguage4 = new ArrayList<CountryLanguage>();
        countryLanguage4.add(null);
        app.printCountryLanguage2(countryLanguage4);
    }

    @Test
    void printCountryLanguage2() {
        ArrayList<CountryLanguage> countryLanguage4 = new ArrayList<CountryLanguage>();
        CountryLanguage clanguage4 = new CountryLanguage();
        clanguage4.setLanguage("Spanish");
        clanguage4.setPercentage("100.0");
        clanguage4.setPopulation("11201000");
        countryLanguage4.add(clanguage4);
        app.printCountryLanguage4(countryLanguage4);
    }
}