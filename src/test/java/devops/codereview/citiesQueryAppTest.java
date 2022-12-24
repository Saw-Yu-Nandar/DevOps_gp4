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
        ArrayList<Cities> cities = new ArrayList<Cities>();
        System.out.println("7: Unit test empty for all the cities in the world organised by largest population to smallest.\n");
        app.printCities(cities);
    }

    //print a list with a null value
    @Test
    void printAllCitiesTestContainsNull()
    {
        ArrayList<Cities> cities = new ArrayList<Cities>();
        cities.add(null);
        System.out.println("7: Unit test contains 'Null' for all the cities in the world organised by largest population to smallest.\n");
        app.printCities(cities);
    }

    @Test
    void printAllCities()
    {
        ArrayList<Cities> cities    = new ArrayList<Cities>();
        Cities cit                  = new Cities();
        cit.cit_name                = "Seoul";
        cit.country_name            = "South Korea";
        cit.cit_district            = "Seoul";
        cit.cit_population          = "9981619";
        cities.add(cit);
        System.out.println("7: Unit testing for all the cities in the world organised by largest population to smallest.\n");
        app.printCities(cities);
    }
    //Unit testing for all the cities in a continent organised by largest population to smallest.
    @Test
    void printContinentTestEmpty()
    {
        ArrayList<Cities> continent = new ArrayList<Cities>();
        System.out.println("8. Unit test empty for all the cities in a continent organised by largest population to smallest.\n");
        app.printContinents(continent);
    }

    //print a list with a null value
    @Test
    void printAllContinentTestContainsNull()
    {
        ArrayList<Cities> continent = new ArrayList<Cities>();
        continent.add(null);
        System.out.println("8. Unit test contains 'Null' for all the cities in a continent organised by largest population to smallest.\n");
        app.printContinents(continent);
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
        System.out.println("8. Unit testing for all the cities in a continent organised by largest population to smallest.\n");
        app.printContinents(continent);
    }
    //Unit testing all the cities in a region organised by largest population to smallest.
    @Test
    void printRegionTestEmpty()
    {
        ArrayList<Cities> regions = new ArrayList<Cities>();
        System.out.println("9: Unit test empty for all the cities in a region organised by largest population to smallest.\n");
        app.printRegions(regions);
    }

    //print a list with a null value
    @Test
    void printAllRegionsTestContainsNull()
    {
        ArrayList<Cities> regions = new ArrayList<Cities>();
        regions.add(null);
        System.out.println("9: Unit test contains 'Null' for all the cities in a region organised by largest population to smallest.\n");
        app.printRegions(regions);
    }
    @Test
    void printRegions()
    {
        ArrayList<Cities> regions   = new ArrayList<Cities>();
        Cities reg                  = new Cities();
        reg.cit_name                = "Luanda";
        reg.country_name            = "Angola";
        reg.cit_district            = "Luanda";
        reg.cit_population          = "12878000";
        regions.add(reg);
        System.out.println("9: Unit testing for all the cities in a region organised by largest population to smallest.\n");
        app.printRegions(regions);
    }
    //Unit testing for all the cities in a country organised by largest population to smallest.
    @Test
    void printCountriesTestEmpty()
    {
        ArrayList<Cities> countries = new ArrayList<Cities>();
        System.out.println("10: Unit test empty for all the cities in a country organised by largest population to smallest.\n");
        app.printCountries(countries);
    }

    //print a list with a null value
    @Test
    void printAllCountriesTestContainsNull()
    {
        ArrayList<Cities> countries = new ArrayList<Cities>();
        countries.add(null);
        System.out.println("10: Unit test contains 'Null' for all the cities in a country organised by largest population to smallest.\n");
        app.printCountries(countries);
    }
    @Test
    void printCountries()
    {
        ArrayList<Cities> countries = new ArrayList<Cities>();
        Cities co                   = new Cities();
        co.cit_name                 = "Europe";
        co.country_name             = "Greece";
        co.cit_district             = "Southern Europe";
        co.cit_population           = "94000";
        countries.add(co);
        System.out.println("10: Unit testing for all the cities in a country organised by largest population to smallest.\n");
        app.printCountries(countries);
    }
    //Unit testing for all the cities in a district organised by largest population to smallest.
    @Test
    void printDistrictTestEmpty()
    {
        ArrayList<Cities> district = new ArrayList<Cities>();
        System.out.println("11: Unit test empty for all the cities in a district organised by largest population to smallest.\n");
        app.printDistrict(district);
    }

    //print a list with a null value
    @Test
    void printAllDistrictTestContainsNull()
    {
        ArrayList<Cities> district = new ArrayList<Cities>();
        district.add(null);
        System.out.println("11: Unit test contains 'Null' for all the cities in a district organised by largest population to smallest.\n");
        app.printDistrict(district);
    }
    @Test
    void printDistrict()
    {
        ArrayList<Cities> district  = new ArrayList<Cities>();
        Cities cit                  = new Cities();
        cit.cit_name                = "Kabul";
        cit.country_name            = "Afghanistan";
        cit.cit_district            = "Kabol";
        cit.cit_population          = "22720000";
        district.add(cit);
        System.out.println("11: Unit testing for all the cities in a district organised by largest population to smallest.\n");
        app.printDistrict(district);
    }
}