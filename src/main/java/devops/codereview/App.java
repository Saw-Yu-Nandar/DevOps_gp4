package devops.codereview;

import java.sql.*;
import java.util.ArrayList;

public class App
{
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;
    public void connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(delay);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location
                                + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "Team_4");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " +                                  Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /**
     * 1. All the countries in the world organised by largest population to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<Country> getAllCountries()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 1: All the countries in the world organised by largest population to smallest.
            String strQueryOne =
                    "SELECT country.Code, country.Name as 'CountryName', country.Continent, country.Region, city.Name as 'CityName', country.Population  FROM country INNER JOIN city WHERE country.Code = city.CountryCode ORDER BY country.Population DESC;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryOne);
            // Extract countries information
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country ctr = new Country();
                ctr.country_code        = rset.getString("Code");
                ctr.country_name        = rset.getString("CountryName");
                ctr.country_cont        = rset.getString("Continent");
                ctr.country_reg         = rset.getString("Region");
                ctr.country_population  = rset.getString("Population");
                ctr.country_cap         = rset.getString("CityName");
                countries.add(ctr);
            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get all the countries in the world organised by largest population to smallest.");
            return null;
        }
    }
    /**
     * 1. All the countries in the world organised by largest population to smallest.
     * Formatting the output data from the list.
     **/
    public void printAllCountries(ArrayList<Country> countries)
    {
        // Check countries is not null
        if (countries == null)
        {
            System.out.println("No employees");
            return;
        }
        // Print header
        System.out.println(String.format("%-10s %-30s %-30s %-30s %-30s %-20s", "Code","Name","Continent","Region","Population","Capital"));
        // Loop over all countries in the list
        for (Country c : countries)
        {
            if (c == null)
                continue;
            String ctr_string =
                    String.format("%-10s %-30s %-30s %-30s %-30s %-20s",
                            c.country_code,c.country_name,c.country_cont,c.country_reg,c.country_population,c.country_cap);
            System.out.println(ctr_string);
        }
    }

    /**
     * 2. All the countries in a continent organised by largest population to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<Country> getAllContinents(String input_continent)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 2: All the countries in a continent organised by largest population to smallest.
            String strQueryTwo =
                    "SELECT country.Code, country.Name as 'CountryName', country.Continent, country.Region, city.Name as 'CityName', country.Population FROM country INNER JOIN city WHERE country.Code = city.CountryCode AND country.Capital=city.ID AND country.Continent = '"+input_continent+"' ORDER BY country.Population DESC;";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryTwo);
            // Extract continent information
            ArrayList<Country> continent = new ArrayList<Country>();
            while (rset.next())
            {
                Country contnt = new Country();
                contnt.country_code         = rset.getString("Code");
                contnt.country_name         = rset.getString("CountryName");
                contnt.country_cont         = rset.getString("Continent");
                contnt.country_reg          = rset.getString("Region");
                contnt.country_population   = rset.getString("Population");
                contnt.country_cap          = rset.getString("CityName");
                continent.add(contnt);
            }
            return continent;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get all the countries in the Oceania organised by largest population to smallest.");
            return null;
        }
    }

    /**
     * 2. All the countries in a continent organised by largest population to smallest.
     *

     Su Hnin, [12/25/2022 11:38 PM]
     ormatting the output data from the list.
     **/
    public void printContinent(ArrayList<Country> continent)
    {
        // Check continent is not null
        if (continent == null)
        {
            System.out.println("No employees");
            return;
        }
        // Print header
        System.out.println(String.format("%-10s %-30s %-30s %-30s %-30s %-20s","Code","Country Name","Continent","Region","Population","Capital"));
        // Loop over all continent in the list
        for (Country cont : continent)
        {
            if (cont == null)
                continue;
            String ctr_string =
                    String.format("%-10s %-30s %-30s %-30s %-30s %-20s",
                            cont.country_code,cont.country_name,cont.country_cont,cont.country_reg,cont.country_population,cont.country_cap);
            System.out.println(ctr_string);
        }
    }

    /**
     * 3. All the countries in a region organised by largest population to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<Country> getAllRegion(String input_region)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 3: All the countries in a region organised by largest population to smallest.
            String strQueryThree =
                    "SELECT country.Code, country.Name as 'CountryName', country.Continent, country.Region, city.Name as 'CityName', country.Population FROM country INNER JOIN city WHERE country.Code = city.CountryCode AND country.Capital=city.ID AND country.Region = '"+input_region+"' ORDER BY country.Population DESC;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryThree);
            // Extract region information
            ArrayList<Country> regions = new ArrayList<Country>();
            while (rset.next())
            {
                Country reg           = new Country();
                reg.country_code        = rset.getString("Code");
                reg.country_name        = rset.getString("CountryName");
                reg.country_cont        = rset.getString("Continent");
                reg.country_reg         = rset.getString("Region");
                reg.country_population  = rset.getString("Population");
                reg.country_cap         = rset.getString("CityName");
                regions.add(reg);
            }
            return regions;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get all the countries in the Caribbean organised by largest population to smallest.");
            return null;
        }
    }
    /**
     * 3. All the countries in a region organised by largest population to smallest.
     * Formatting the output data from the list.
     **/
    public void printRegion(ArrayList<Country> region)
    {
        // Check region is not null
        if (region == null)
        {
            System.out.println("No region");
            return;
        }
        // Print header
        System.out.println(String.format("%-10s %-30s %-30s %-30s %-30s %-20s", "Code","Country Name","Continent","Region","Population","Capital"));
        // Loop over all region in the list
        for (Country r : region)
        {
            //printRegion to check if an region is null
            if (r == null)
                continue;
            String reg_string =
                    String.format("%-10s %-30s %-30s %-30s %-30s %-20s",
                            r.country_code,r.country_name,r.country_cont,r.country_reg,r.country_population,r.country_cap);
            System.out.println(reg_string);
        }
    }

    /**
     * 4. The top N populated countries in the world where N is provided by the user.
     * Query execution by user input and pass the array list to format the return value.
     * Function is called in ma
     **/
    public ArrayList<Country> getAllNPopulatedCountries(int input_limit)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 4: The top N populated countries in the world where N is provided by the user.
            String strQueryFour =
                    "SELECT country.Code, country.Name as 'CountryName', country.Continent, country.Region, city.Name as 'CityName',country.Population FROM country INNER JOIN city WHERE country.Code = city.CountryCode AND country.Capital=city.ID ORDER BY country.Population DESC LIMIT "+input_limit+";";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryFour);
            // Extract countries information
            ArrayList<Country> NPopulatedCountries = new ArrayList<Country>();
            while (rset.next())
            {
                Country npopctr = new Country();
                npopctr.country_code        = rset.getString("Code");
                npopctr.country_name        = rset.getString("CountryName");
                npopctr.country_cont        = rset.getString("Continent");
                npopctr.country_reg         = rset.getString("Region");
                npopctr.country_population  = rset.getString("Population");
                npopctr.country_cap         = rset.getString("CityName");
                NPopulatedCountries.add(npopctr);
            }
            return NPopulatedCountries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top 10 populated countries in the world.");
            return null;
        }
    }

    /**
     * 4. The top N populated countries in the world where N is provided by the user.
     * Formatting the output data from the list.
     **/
    public void printNPopulatedCountries(ArrayList<Country> NPopulatedC)
    {
        // Check npopulatedcountries is not null
        if (NPopulatedC == null)
        {
            System.out.println("No region");
            return;
        }
        // Print header
        System.out.println(String.format("%-10s %-30s %-30s %-30s %-30s %-20s", "Code", "Country Name", "Continent", "Region", "Population", "Capital"));
        // Loop over all countries in the list
        for (Country npopc : NPopulatedC)
        {
            //printcontinent to check if an Continent is null
            if (npopc == null)
                continue;
            String npopctr_string =
                    String.format("%-10s %-30s %-30s %-30s %-30s %-20s",
                            npopc.country_code, npopc.country_name, npopc.country_cont, npopc.country_reg, npopc.country_population, npopc.country_cap);
            System.out.println(npopctr_string);
        }
    }

    /**
     * 5. The top N populated countries in a continent where N is provided by the user.
     * Query execution by user input and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<Country> getAllNPopulatedContinents(String input_topCon, int input_numb)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 5: The top N populated countries in a continent where N is provided by the user.
            String strQueryFive =
                    "SELECT country.Code, country.Name as 'CountryName', country.Continent, country.Region, city.Name as 'CityName', country.Population FROM country INNER JOIN city WHERE country.Code = city.CountryCode AND country.Capital=city.ID AND country.Continent = '"+ input_topCon+"' ORDER BY country.Population DESC LIMIT "+input_numb+";";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryFive);
            // Extract continent information
            ArrayList<Country> NPopulated_Continents = new ArrayList<Country>();
            while (rset.next())
            {
                Country npopcont = new Country();
                npopcont.country_code       = rset.getString("Code");
                npopcont.country_name       = rset.getString("CountryName");
                npopcont.country_cont       = rset.getString("Continent");
                npopcont.country_reg        = rset.getString("Region");
                npopcont.country_population = rset.getString("Population");
                npopcont.country_cap        = rset.getString("CityName");
                NPopulated_Continents.add(npopcont);
            }
            return NPopulated_Continents;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top 10 populated countries in the Europe.");
            return null;
        }
    }
    /**
     * 5. The top N populated countries in a continent where N is provided by the user.
     * Query execution by user input and pass the array list to format the return value.
     * Function is called in main.
     **/
    public void printNPopulatedContinents(ArrayList<Country> NPopulatedContinents)
    {
        // Check npopulatedcontinent is not null
        if (NPopulatedContinents == null)
        {
            System.out.println("No region");
            return;
        }
        // Print header
        System.out.println(String.format("%-10s %-30s %-30s %-30s %-30s %-20s","Code","Country Name","Continent","Region","Population","Capital"));
        // Loop over all continent in the list
        for (Country npopconti : NPopulatedContinents)
        {
            //printRegion to check if an Continent is null
            if (npopconti == null)
                continue;
            String npopcont_string =
                    String.format("%-10s %-30s %-30s %-30s %-30s %-20s",
                            npopconti.country_code,npopconti.country_name,npopconti.country_cont,npopconti.country_reg,npopconti.country_population,npopconti.country_cap);
            System.out.println(npopcont_string);
        }
    }

    /**
     * 6. The top N populated countries in a region where N is provided by the user.
     * Query execution by user input and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<Country> getAllNPopulatedRegion(String input_topReg, int input_num)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 6: The top N populated countries in a region where N is provided by the user.
            String strQuerySix =
                    "SELECT country.Code, country.Name as 'CountryName', country.Continent, country.Region, city.Name as 'CityName', country.Population FROM country INNER JOIN city WHERE country.Code = city.CountryCode AND country.Capital=city.ID AND country.Region = '"+ input_topReg+"' ORDER BY country.Population DESC LIMIT "+input_num+";";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQuerySix);
            // Extract region information
            ArrayList<Country> NPopulated_Region = new ArrayList<Country>();
            while (rset.next())
            {
                Country npopreg           = new Country();
                npopreg.country_code        = rset.getString("Code");
                npopreg.country_name        = rset.getString("CountryName");
                npopreg.country_cont        = rset.getString("Continent");
                npopreg.country_reg         = rset.getString("Region");
                npopreg.country_population  = rset.getString("Population");
                npopreg.country_cap         = rset.getString("CityName");
                NPopulated_Region.add(npopreg);
            }
            return NPopulated_Region;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top 10 populated countries in the Caribbean.");
            return null;
        }
    }
    /**
     * 6. The top N populated countries in a region where N is provided by the user
     * Query execution by user input and pass the array list to format the return value.
     * Function is called in main.
     **/
    public void printNPopulatedRegion(ArrayList<Country> NPopulatedRegion)
    {
        // Check npopulatedregion is not null
        if (NPopulatedRegion == null)
        {
            System.out.println("No region");
            return;
        }
        // Print header
        System.out.println(String.format("%-10s %-30s %-30s %-30s %-30s %-20s","Code","Country Name","Continent","Region","Population","Capital"));
        // Loop over all region in the list
        for (Country npopreg : NPopulatedRegion)
        {
            //printNpopulatedRegion to check if an region is null
            if (npopreg == null)
                continue;
            String npopreg_string =
                    String.format("%-10s %-30s %-30s %-30s %-30s %-20s",
                            npopreg.country_code,npopreg.country_name,npopreg.country_cont,npopreg.country_reg,npopreg.country_population,npopreg.country_cap);
            System.out.println(npopreg_string);
        }
    }
    /**
     * 7. All the cities in the world organised by largest population to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<City> getAllCities()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 7: All the cities in the world organised by largest population to smallest.
            String strQuerySeven =
                    "SELECT city.Name as 'CityName',country.Name as 'CountryName',city.District,city.Population FROM city INNER JOIN country WHERE country.Code = city.CountryCode ORDER BY city.Population DESC;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQuerySeven);
            // Extract cities information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City cit = new City();
                cit.cit_name        = rset.getString("CityName");
                cit.country_name    = rset.getString("CountryName");
                cit.cit_district    = rset.getString("District");
                cit.cit_population  = rset.getString("Population");
                cities.add(cit);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get all the cities in the world organised by largest population to smallest.");
            return null;
        }
    }
    /**
     * 7. All the cities in the world organised by largest population to smallest.
     * Formatting the output data from the list.
     **/
    public void printCities(ArrayList<City> cities)
    {
        if (cities == null){
            System.out.println("There is no city in the world!");
        }
        // Print header
        System.out.println(String.format("%-20s %-25s %-25s %-25s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (City cit : cities)
        {
            if (cit == null)
                continue;
            String cit_string =
                    String.format("%-20s %-25s %-25s %-25s",
                            cit.cit_name,cit.country_name,cit.cit_district,cit.cit_population);
            System.out.println(cit_string);
        }
    }
    /**
     * 8. All the cities in a continent organised by largest population to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<City> getAllCitiesContinent(String input_continent)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 8: All the cities in a continent organised by largest population to smallest.
            String strQueryEight =
                    "SELECT city.Name as 'CityName', country.Name as 'CountryName', city.District, city.Population FROM city INNER JOIN country on city.CountryCode = country.Code WHERE country.Continent = '"+input_continent+"' ORDER BY city.Population DESC;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryEight);
            // Extract continent information
            ArrayList<City> continent = new ArrayList<City>();
            while (rset.next())
            {
                City conti = new City();
                conti.cit_name          = rset.getString("CityName");
                conti.country_name      = rset.getString("CountryName");
                conti.cit_district      = rset.getString("District");
                conti.cit_population    = rset.getString("Population");
                continent.add(conti);
            }
            return continent;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get all the cities in the Asia organised by largest population to smallest.");
            return null;
        }
    }

    /**
     * 8. All the cities in a continent organised by largest population to smallest.
     * Formatting the output data from the list.
     **/
    public void printContinents(ArrayList<City> continent)
    {
        if (continent == null){
            System.out.println("There is no city in a continent!");
        }
        // Print header
        System.out.println(String.format("%-20s %-25s %-25s %-25s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (City c : continent)
        {
            if (c == null)
                continue;
            String conti_string =
                    String.format("%-20s %-25s %-25s %-25s",
                            c.cit_name,c.country_name,c.cit_district,c.cit_population);
            System.out.println(conti_string);
        }
    }
    /**
     * 9. All the cities in a region organised by largest population to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<City> getAllCitiesRegions(String input_regions)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 9: All the cities in a region organised by largest population to smallest.
            String strQueryEight =
                    "SELECT city.Name as 'CityName',country.Name as 'CountryName', city.District, city.Population FROM city INNER JOIN country on city.CountryCode = country.Code WHERE country.Region = '"+input_regions+"' ORDER BY city.Population DESC;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryEight);
            // Extract regions information
            ArrayList<City> regions = new ArrayList<City>();
            while (rset.next())
            {
                City reg          = new City();
                reg.cit_name        = rset.getString("CityName");
                reg.country_name    = rset.getString("CountryName");
                reg.cit_district    = rset.getString("District");
                reg.cit_population  = rset.getString("Population");
                regions.add(reg);
            }
            return regions;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get all the cities in the Caribbean organised by largest population to smallest.");
            return null;
        }
    }

    /**
     * 9. All the cities in a region organised by largest population to smallest.
     * Formatting the output data from the list.
     **/
    public void printRegions(ArrayList<City> regions)
    {
        if (regions == null){
            System.out.println("There is no city in a region!");
        }
        // Print header
        System.out.println(String.format("%-20s %-25s %-25s %-25s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (City r : regions)
        {
            if (r == null)
                continue;
            String reg_string =
                    String.format("%-20s %-25s %-25s %-25s",
                            r.cit_name,r.country_name,r.cit_district,r.cit_population);
            System.out.println(reg_string);
        }
    }
    /**
     * 10. All the cities in a country organised by largest population to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<City> getAllCitiesCountries(String input_countries)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 10: All the cities in a country organised by largest population to smallest.
            String strQueryEight =
                    "SELECT city.Name as 'CityName',country.Name as 'CountryName',city.District, city.Population FROM country INNER JOIN city WHERE country.Code = city.CountryCode AND country.Name='"+input_countries+"' ORDER BY city.Population DESC;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryEight);
            // Extract country information
            ArrayList<City> countries = new ArrayList<City>();
            while (rset.next())
            {
                City c1           = new City();
                c1.cit_name         = rset.getString("CityName");
                c1.country_name     = rset.getString("CountryName");
                c1.cit_district     = rset.getString("District");
                c1.cit_population   = rset.getString("Population");
                countries.add(c1);
            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get all the cities in the Myanmar organised by largest population to smallest.");
            return null;
        }
    }

    /**
     * 10. All the cities in a country organised by largest population to smallest.
     * Formatting the output data from the list.
     **/
    public void printCountries(ArrayList<City> countries)
    {
        if (countries == null){
            System.out.println("There is no city in a country!");
        }
        // Print header
        System.out.println(String.format("%-20s %-25s %-25s %-25s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (City cou : countries)
        {
            if (cou == null)
                continue;
            String c_string =
                    String.format("%-20s %-25s %-25s %-25s",
                            cou.cit_name,cou.country_name,cou.cit_district,cou.cit_population);
            System.out.println(c_string);
        }
    }
    /**
     * 11. All the cities in a district organised by largest population to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<City> getAllCitiesDistrict(String input_district)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 11: All the cities in a district organised by largest population to smallest.
            String strQueryEight =
                    "SELECT city.Name as 'CityName', country.Name as 'CountryName', city.District as 'District', city.Population FROM country INNER JOIN city WHERE city.District='"+input_district+"' AND country.Code=city.CountryCode ORDER BY city.Population DESC;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryEight);
            // Extract district information
            ArrayList<City> district = new ArrayList<City>();
            while (rset.next())
            {
                City dist         = new City();
                dist.cit_name       = rset.getString("CityName");
                dist.country_name   = rset.getString("CountryName");
                dist.cit_district   = rset.getString("District");
                dist.cit_population = rset.getString("Population");
                district.add(dist);
            }
            return district;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get all the cities in the Queensland organised by largest population to smallest.");
            return null;
        }
    }

    /**
     * 11. All the cities in a district organised by largest population to smallest.
     * Formatting the output data from the list.
     **/
    public void printDistrict(ArrayList<City> district)
    {
        if (district == null){
            System.out.println("There is no cities in a district!");
        }
        // Print header
        System.out.println(String.format("%-20s %-25s %-25s %-25s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (City di : district)
        {
            if (di == null)
                continue;
            String d_string =
                    String.format("%-20s %-25s %-25s %-25s",
                            di.cit_name,di.country_name,di.cit_district,di.cit_population);
            System.out.println(d_string);
        }
    }
    /**
     * 12. The top N populated cities in the world where N is provided by the user.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<City> getTopNPopulatedCities(int input_world)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 12: The top N populated cities in the world where N is provided by the user.
            String strQueryEight =
                    "SELECT city.Name as 'Cityname', country.Name as 'Countryname', city.District, city.Population FROM city INNER JOIN country WHERE city.CountryCode=country.Code ORDER BY city.Population DESC LIMIT "+input_world+";";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryEight);
            // Extract cities information
            ArrayList<City> worlds = new ArrayList<City>();
            while (rset.next())
            {
                City world = new City();
                world.cit_name = rset.getString("Cityname");
                world.country_name = rset.getString("Countryname");
                world.cit_district = rset.getString("District");
                world.cit_population = rset.getString("Population");
                worlds.add(world);
            }
            return worlds;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top 10 populated cities in the world.");
            return null;
        }
    }

    /** 12. The top N populated cities in the world where N is provided by the user.
     * Formatting the output data from the list.
     **/
    public void printTopNWorlds(ArrayList<City> wld)
    {
        if (wld == null){
            System.out.println("There is no top N populated cities in the world!");
        }
        // Print header
        System.out.println(String.format("%-20s %-25s %-25s %-25s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (City w : wld)
        {
            if (w == null)
                continue;
            String w_string =
                    String.format("%-20s %-25s %-25s %-25s",
                            w.cit_name,w.country_name,w.cit_district,w.cit_population);
            System.out.println(w_string);
        }
    }
    /**
     * 13. The top N populated cities in a continent where N is provided by the user.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<City>getTopNPopulatedContinent(String input_Continent, int input_limited)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 13: The top N populated cities in the continent where N is provided by the user.
            String strQueryEight =
                    "SELECT city.Name as 'CityName', country.Name as 'CountryName', city.District, city.Population FROM city INNER JOIN country on city.CountryCode = country.Code WHERE country.Continent = '"+input_Continent+"' ORDER BY city.Population DESC LIMIT "+input_limited+";";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryEight);
            // Extract continent information
            ArrayList<City> continent = new ArrayList<City>();
            while (rset.next())
            {
                City conti            = new City();
                conti.cit_name          = rset.getString("CityName");
                conti.country_name      = rset.getString("CountryName");
                conti.cit_district      = rset.getString("District");
                conti.cit_population    = rset.getString("Population");
                continent.add(conti);
            }
            return continent;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top N populated cities in the Europe.");
            return null;
        }
    }

    /**
     * 13. The top N populated cities in a continent where N is provided by the user.
     * Formatting the output data from the list.
     **/
    public void printTopNContinent(ArrayList<City> cnt)
    {
        if (cnt == null){
            System.out.println("No cities in a continent");
        }
        // Print header
        System.out.println(String.format("%-20s %-25s %-25s %-25s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (City cont : cnt)
        {
            if (cont == null)
                continue;
            String cont_string =
                    String.format("%-20s %-25s %-25s %-25s",
                            cont.cit_name,cont.country_name,cont.cit_district,cont.cit_population);
            System.out.println(cont_string);
        }
    }
    /**
     * 14. The top N populated cities in a region where N is provided by the user.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<City> getTopNPopulatedRegion(String input_Region, int input_limited)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 14: The top N populated cities in a region where N is provided by the user.
            String strQueryEight =
                    "SELECT country.Region, city.Name as 'CityName', country.Name as 'CountryName', city.District, city.Population FROM city INNER JOIN country on city.CountryCode = country.Code WHERE country.Region = '"+input_Region+"' ORDER BY city.Population DESC LIMIT "+input_limited+";";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryEight);
            // Extract region information
            ArrayList<City> region = new ArrayList<City>();
            while (rset.next())
            {
                City reg          = new City();
                reg.cit_name        = rset.getString("CityName");
                reg.country_name    = rset.getString("CountryName");
                reg.cit_district    = rset.getString("District");
                reg.cit_population  = rset.getString("Population");
                region.add(reg);
            }
            return region;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top 10 populated cities in the Caribbean.");
            return null;
        }
    }

    /**
     * 14. The top N populated cities in a region where N is provided by the user.
     * Formatting the output data from the list.
     **/
    public void printTopNRegion(ArrayList<City> regn)
    {
        if (regn == null){
            System.out.println("No cities in a region");
        }
        // Print header
        System.out.println(String.format("%-20s %-25s %-25s %-25s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (City cont : regn)
        {
            if (cont == null)
                continue;
            String cont_string =
                    String.format("%-20s %-25s %-25s %-25s",
                            cont.cit_name,cont.country_name,cont.cit_district,cont.cit_population);
            System.out.println(cont_string);
        }
    }

    /**
     * 15. The top N populated cities in a country where N is provided by the user.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<City> getTopNPopulatedCountries(String input_Country, int input_limited)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 13: The top N populated cities in a country where N is provided by the user.
            String strQueryEight =
                    "SELECT city.Name as 'CityName',country.Name as 'CountryName',city.District, city.Population FROM city INNER JOIN country on city.CountryCode = country.Code WHERE country.Name = '"+input_Country+"' ORDER BY city.Population DESC LIMIT "+input_limited+";";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryEight);
            // Extract country information
            ArrayList<City> country = new ArrayList<City>();
            while (rset.next())
            {
                City cty = new City();
                cty.cit_name        = rset.getString("CityName");
                cty.country_name    = rset.getString("CountryName");
                cty.cit_district    = rset.getString("District");
                cty.cit_population  = rset.getString("Population");
                country.add(cty);
            }
            return country;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top 10 populated cities in the Argentina.");
            return null;
        }
    }
    /**
     * 15. The top N populated cities in a country where N is provided by the user.
     * Formatting the output data from the list.
     **/
    public void printTopNCountries(ArrayList<City> count)
    {
        if (count == null){
            System.out.println("No cities in a country");
        }
        // Print header
        System.out.println(String.format("%-20s %-25s %-25s %-30s","Country","City Name","District","Population"));
        // Loop over all cities in the list
        for (City cont : count)
        {
            if (cont == null)
                continue;
            String cont_string =
                    String.format("%-20s %-25s %-25s %-30s",
                            cont.cit_name,cont.country_name,cont.cit_district,cont.cit_population);
            System.out.println(cont_string);
        }
    }

    /**
     * 16. The top N populated cities in a district where N is provided by the user.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<City> getTopNPopulatedDistrict(String input_District, int input_limited)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 13: The top N populated cities in a district  where N is provided by the user.
            String strQueryEight =
                    "SELECT city.Name as 'CityName', country.name as 'CountryName', city.District, city.Population FROM city INNER JOIN country on city.CountryCode = country.Code WHERE city.District = '"+input_District+"' ORDER BY city.Population DESC LIMIT "+input_limited+";";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryEight);
            // Extract district information
            ArrayList<City> dist = new ArrayList<City>();
            while (rset.next())
            {
                City dis = new City();
                dis.cit_name = rset.getString("CityName");
                dis.country_name = rset.getString("CountryName");
                dis.cit_district = rset.getString("District");
                dis.cit_population = rset.getString("Population");
                dist.add(dis);
            }
            return dist;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top 10 populated cities in the Zuid-Holland.");
            return null;
        }
    }

    /**
     * 16. The top N populated cities in a district where N is provided by the user.
     * Formatting the output data from the list.
     **/
    public void printTopNDistrict(ArrayList<City> dists)
    {
        if (dists == null){
            System.out.println("No cities in a region");
        }
        // Print header
        System.out.println(String.format("%-30s %-25s %-25s %-30s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (City cont : dists)
        {
            if (cont == null)
                continue;
            String cont_string =
                    String.format("%-30s %-25s %-25s %-30s",
                            cont.cit_name,cont.country_name,cont.cit_district,cont.cit_population);
            System.out.println(cont_string);
        }

    }
    /**
     * 17.All the capital cities in the world organised by largest population to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<CapitalCities> getAllCapitalCities()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 17.All the capital cities in the world organised by largest population to smallest.
            String strQuerySeventeen =
                    "SELECT city.Name as 'CapitalCity', country.Name as 'CountryName', country.Population FROM city INNER JOIN country WHERE city.ID = country.Capital AND country.Code=city.CountryCode ORDER BY country.Population DESC;";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQuerySeventeen);
            // Extract capitalcities information
            ArrayList<CapitalCities> capital_cities = new ArrayList<CapitalCities>();
            while (rset.next())
            {
                CapitalCities capcit = new CapitalCities();
                capcit.cap_cit_name        = rset.getString("CapitalCity");
                capcit.cap_cit_country        = rset.getString("CountryName");
                capcit.cap_cit_population        = rset.getString("Population");
                capital_cities.add(capcit);
            }
            return capital_cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get all the capital cities in the world organised by largest population to smallest.");
            return null;
        }
    }
    /**
     * 17.All the capital cities in the world organised by largest population to smallest.
     * Formatting the output data from the list.
     **/
    public void printAllCapitalCities(ArrayList<CapitalCities> capital_cities)
    {
        // Check countries is not null
        if (capital_cities == null)
        {
            System.out.println("No Capital Cities");
            return;
        }
        // Print header
        System.out.println(String.format("%-25s %-25s %-25s", "CapitalCity","Country","Population"));
        // Loop over all countries in the list
        for (CapitalCities cc : capital_cities)
        {
            if (cc == null)
                continue;

            String ctr_string =
                    String.format("%-25s %-25s %-25s",
                            cc.cap_cit_name,cc.cap_cit_country,cc.cap_cit_population);
            System.out.println(ctr_string);
        }
    }

    /**
     * 18.All the capital cities in a continent organised by largest population to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<CapitalCities> getAllCapitalCitiesContinents(String input_continent)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 18.All the capital cities in a continent organised by largest population to smallest.
            String strQueryEighteen =
                    "SELECT city.Name as 'CapitalCity', country.Name as 'CountryName', country.Continent, country.Population FROM city INNER JOIN country WHERE city.ID = country.Capital AND country.Code=city.CountryCode AND country.Continent = '"+input_continent+"' ORDER BY country.Population DESC;";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryEighteen);
            // Extract continent information
            ArrayList<CapitalCities> capcit_continent = new ArrayList<CapitalCities>();
            while (rset.next())
            {
                CapitalCities capcitCont = new CapitalCities();
                capcitCont.cap_cit_name         = rset.getString("CapitalCity");
                capcitCont.cap_cit_country         = rset.getString("CountryName");
                capcitCont.cap_cit_continent       = rset.getString("Continent");
                capcitCont.cap_cit_population         = rset.getString("Population");
                capcit_continent.add(capcitCont);
            }
            return capcit_continent;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get all the capitalcities in a Asia organised by largest population to smallest.");
            return null;
        }
    }

    /**
     * 18.All the capital cities in a continent organised by largest population to smallest.
     * Formatting the output data from the list.
     **/
    public void printAllCapitalCityContinent(ArrayList<CapitalCities> capcit_continent)
    {
        // Check continent is not null
        if (capcit_continent == null)
        {
            System.out.println("No Capital City Continent");
            return;
        }
        // Print header
        System.out.println(String.format("%-25s %-25s %-25s %-25s", "CapitalCity", "Country", "Continent", "Population"));
        // Loop over all continent in the list
        for (CapitalCities cccon : capcit_continent)
        {
            if (cccon == null)
                continue;
            String ctr_string =
                    String.format("%-25s %-25s %-25s %-25s",
                            cccon.cap_cit_name, cccon.cap_cit_country, cccon.cap_cit_continent, cccon.cap_cit_population);
            System.out.println(ctr_string);
        }
    }

    /**
     * 19.All the capital cities in a region organised by largest to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<CapitalCities> getAllCapitalCitiesRegions(String input_region)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 19.All the capital cities in a region organised by largest to smallest.
            String strQueryNineteen =
                    "SELECT city.Name as 'CapitalCity', country.Name as 'CountryName', country.Region, country.Population FROM city INNER JOIN country WHERE city.ID = country.Capital AND country.Code=city.CountryCode AND country.Region = '"+input_region+"' ORDER BY country.Population DESC;";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryNineteen);
            // Extract region information
            ArrayList<CapitalCities> capcit_region = new ArrayList<CapitalCities>();
            while (rset.next())
            {
                CapitalCities capcitreg          = new CapitalCities();
                capcitreg.cap_cit_name           = rset.getString("CapitalCity");
                capcitreg.cap_cit_country        = rset.getString("CountryName");
                capcitreg.cap_cit_region         = rset.getString("Region");
                capcitreg.cap_cit_population     = rset.getString("Population");
                capcit_region.add(capcitreg);
            }
            return capcit_region;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get all the capitalcities in a Caribbean organised by largest population to smallest.");
            return null;
        }
    }
    /**
     * 19. All the capital cities in a region organised by largest to smallest.
     * Formatting the output data from the list.
     **/
    public void printAllCapitalCityRegion(ArrayList<CapitalCities> capcit_region)
    {
        // Check region is not null
        if (capcit_region == null)
        {
            System.out.println("No CapitalCity region");
            return;
        }
        // Print header
        System.out.println(String.format("%-25s %-25s %-25s %-25s", "CapitalCity","Country","Region", "Population"));
        // Loop over all region in the list
        for (CapitalCities ccr : capcit_region)
        {
            //printRegion to check if an region is null
            if (ccr == null)
                continue;
            String reg_string =
                    String.format("%-25s %-25s %-25s %-25s",
                            ccr.cap_cit_name,ccr.cap_cit_country,ccr.cap_cit_region, ccr.cap_cit_population);
            System.out.println(reg_string);
        }
    }

    /**
     * 20.The top N populated capital cities in the world where N is provided by the user.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<CapitalCities> getTopNCapCities_World(int input_limited)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 20.The top N populated capital cities in the world where N is provided by the user.
            String strQueryTwenty =
                    "SELECT city.Name as 'CityName', country.Name as 'CountryName', country.Population FROM city INNER JOIN country WHERE city.ID = country.Capital AND country.Code=city.CountryCode ORDER BY country.Population DESC LIMIT "+input_limited+";";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryTwenty);
            // Extract region information
            ArrayList<CapitalCities> cap_world = new ArrayList<CapitalCities>();
            while (rset.next())
            {
                CapitalCities cap_wld          = new CapitalCities();
                cap_wld.cap_cit_name           = rset.getString("CityName");
                cap_wld.cap_cit_country        = rset.getString("CountryName");
                cap_wld.cap_cit_population     = rset.getString("Population");
                cap_world.add(cap_wld);
            }
            return cap_world;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top N populated capital cities in the world where N is provided by the user.");
            return null;
        }
    }
    /**
     * 20. The top N populated capital cities in the world where N is provided by the user.
     * Formatting the output data from the list.
     **/
    public void printTopNCapCities_World(ArrayList<CapitalCities> Cap_Wor)
    {
        // Check region is not null
        if (Cap_Wor == null)
        {
            System.out.println("There is no Capital City in the world");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s", "Capital City Name","Country Name", "Population"));
        // Loop over all capital cities in the world
        for (CapitalCities ccr : Cap_Wor)
        {
            //print the list to check if capital cities in the world is null
            if (ccr == null)
                continue;
            String reg_string =
                    String.format("%-30s %-30s %-30s",
                            ccr.cap_cit_name,ccr.cap_cit_country, ccr.cap_cit_population);
            System.out.println(reg_string);
        }
    }

    /**
     * 21.The top N populated capital cities in a continent where N is provided by the user.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<CapitalCities> getTopNCapCities_cont(String input_continent,int input_limited)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 20.The top N populated capital cities in a continent where N is provided by the user.
            String strQueryTwentyOne =
                    "SELECT city.Name as 'CityName', country.Name as 'CountryName', country.Population FROM city INNER JOIN country WHERE city.ID = country.Capital AND country.Code=city.CountryCode AND country.Continent = '"+input_continent+"' ORDER BY country.Population DESC LIMIT "+input_limited+";";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryTwentyOne);
            // Extract region information
            ArrayList<CapitalCities> cap_cont = new ArrayList<CapitalCities>();
            while (rset.next())
            {
                CapitalCities cap_cnt          = new CapitalCities();
                cap_cnt.cap_cit_name           = rset.getString("CityName");
                cap_cnt.cap_cit_country        = rset.getString("CountryName");
                cap_cnt.cap_cit_population     = rset.getString("Population");
                cap_cont.add(cap_cnt);
            }
            return cap_cont;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top N populated capital cities in a continent where N is provided by the user.");
            return null;
        }
    }
    /**
     * 21. The top N populated capital cities in a continent where N is provided by the user.
     * Formatting the output data from the list.
     **/
    public void printTopNCapCities_cont(ArrayList<CapitalCities> Cont_Cap)
    {
        // Check region is not null
        if (Cont_Cap == null)
        {
            System.out.println("There is no Capital City in the world");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s", "Capital City Name","Country Name", "Population"));
        // Loop over all capital cities in a continent
        for (CapitalCities ccr : Cont_Cap)
        {
            //print the list to check if capital cities in a continent is null
            if (ccr == null)
                continue;
            String reg_string =
                    String.format("%-30s %-30s %-30s",
                            ccr.cap_cit_name,ccr.cap_cit_country, ccr.cap_cit_population);
            System.out.println(reg_string);
        }
    }

    /**
     * 22.The top N populated capital cities in a region where N is provided by the user.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<CapitalCities> getTopNCapCities_Reg(String input_region,int input_limited)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 20.The top N populated capital cities in a region where N is provided by the user.
            String strQueryTwentyTwo =
                    "SELECT city.Name as 'CityName', country.Name as 'CountryName', country.Population FROM city INNER JOIN country WHERE city.ID = country.Capital AND country.Code=city.CountryCode AND country.Region='"+input_region+"' ORDER BY country.Population DESC LIMIT "+input_limited+";";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryTwentyTwo);
            // Extract region information
            ArrayList<CapitalCities> cap_region = new ArrayList<CapitalCities>();
            while (rset.next())
            {
                CapitalCities cap_reg          = new CapitalCities();
                cap_reg.cap_cit_name           = rset.getString("CityName");
                cap_reg.cap_cit_country        = rset.getString("CountryName");
                cap_reg.cap_cit_population     = rset.getString("Population");
                cap_region.add(cap_reg);
            }
            return cap_region;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top N populated capital cities in a region where N is provided by the user.");
            return null;
        }
    }
    /**
     * 22. The top N populated capital cities in a continent where N is provided by the user.
     * Formatting the output data from the list.
     **/
    public void printTopNCapCities_Reg(ArrayList<CapitalCities> Reg_Cap)
    {
        // Check region is not null
        if (Reg_Cap == null)
        {
            System.out.println("There is no Capital City in the world");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s", "Capital City Name","Country Name", "Population"));
        // Loop over all capital cities in a continent
        for (CapitalCities ccr : Reg_Cap)
        {
            //print the list to check if capital cities in a region is null
            if (ccr == null)
                continue;
            String reg_string =
                    String.format("%-30s %-30s %-30s",
                            ccr.cap_cit_name,ccr.cap_cit_country, ccr.cap_cit_population);
            System.out.println(reg_string);
        }
    }

    /**
     * 31. List the population of people who speak Chinese in descending order
     * Query execution by user input and pass the array list to format the return value.
     * Function is called in ma
     **/
    public ArrayList<CountryLanguage> getCountryLanguage1(String input_language)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 31. List the population of people who speak Chinese in descending order
            String strQuerylanguage1 =
                    "SELECT countrylanguage.Language, countrylanguage.Percentage, country.Population FROM countrylanguage INNER JOIN country WHERE countrylanguage.CountryCode = country.Code AND countrylanguage.Language='"+input_language+"' ORDER BY countrylanguage.Percentage DESC;";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQuerylanguage1);
            // Extract countries information
            ArrayList<CountryLanguage> countrylanguage1 = new ArrayList<CountryLanguage>();
            while (rset.next())
            {
                CountryLanguage language1 = new CountryLanguage();
                language1.language       = rset.getString("Language");
                language1.percentage        = rset.getString("Percentage");
                language1.population        = rset.getString("Population");
                countrylanguage1.add(language1);
            }
            return countrylanguage1;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top N populated countries in the world where N is provided by the user.");
            return null;
        }
    }

    /**
     * 31. List the population of people who speak Chinese in descending order
     * Formatting the output data from the list.
     **/
    public void printCountryLanguage1(ArrayList<CountryLanguage> countrylanguage1)
    {
        // Check country language is not null
        if (countrylanguage1 == null)
        {
            System.out.println("No Chinese");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s", "Language", "Percentage", "Population"));
        // Loop over all countries in the list
        for (CountryLanguage cl1 : countrylanguage1)
        {
            //print language to check if an language is null
            if (cl1 == null)
                continue;
            String language_string =
                    String.format("%-30s %-30s %-30s",
                            cl1.language, cl1.percentage, cl1.population);
            System.out.println(language_string);
        }
    }

    /**
     * 32. List the population of people who speak English in descending order
     * Query execution by user input and pass the array list to format the return value.
     * Function is called in ma
     **/
    public ArrayList<CountryLanguage> getCountryLanguage2(String input_language)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 31. List the population of people who speak Chinese in descending order
            String strQueryFour =
                    "SELECT countrylanguage.Language, countrylanguage.Percentage, country.Population FROM countrylanguage INNER JOIN country WHERE countrylanguage.CountryCode = country.Code AND countrylanguage.Language='"+input_language+"' ORDER BY countrylanguage.Percentage DESC;";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryFour);
            // Extract countries information
            ArrayList<CountryLanguage> countrylanguage2 = new ArrayList<CountryLanguage>();
            while (rset.next())
            {
                CountryLanguage language2 = new CountryLanguage();
                language2.language       = rset.getString("Language");
                language2.percentage        = rset.getString("Percentage");
                language2.population        = rset.getString("Population");
                countrylanguage2.add(language2);
            }
            return countrylanguage2;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top N populated countries in the world where N is provided by the user.");
            return null;
        }
    }

    /**
     * 32. List the population of people who speak Chinese in descending order
     * Formatting the output data from the list.
     **/
    public void printCountryLanguage2(ArrayList<CountryLanguage> countrylanguage2)
    {
        // Check country language is not null
        if (countrylanguage2 == null)
        {
            System.out.println("No English");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s", "Language", "Percentage", "Population"));
        // Loop over all countries in the list
        for (CountryLanguage cl2 : countrylanguage2)
        {
            //print language to check if an language is null
            if (cl2 == null)
                continue;
            String language_string =
                    String.format("%-30s %-30s %-30s",
                            cl2.language, cl2.percentage, cl2.population);
            System.out.println(language_string);
        }
    }

    /**
     * 33. List the population of people who speak Hindi in descending order
     * Query execution by user input and pass the array list to format the return value.
     * Function is called in ma
     **/
    public ArrayList<CountryLanguage> getCountryLanguage3(String input_language)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 33. List the population of people who speak Hindi in descending order
            String strQueryLanguage3 =
                    "SELECT countrylanguage.Language, countrylanguage.Percentage, country.Population FROM countrylanguage INNER JOIN country WHERE countrylanguage.CountryCode = country.Code AND countrylanguage.Language='"+input_language+"' ORDER BY countrylanguage.Percentage DESC;";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryLanguage3);
            // Extract countries information
            ArrayList<CountryLanguage> countryLanguage3 = new ArrayList<CountryLanguage>();
            while (rset.next())
            {
                CountryLanguage language3 = new CountryLanguage();
                language3.language       = rset.getString("Language");
                language3.percentage        = rset.getString("Percentage");
                language3.population        = rset.getString("Population");
                countryLanguage3.add(language3);
            }
            return countryLanguage3;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get list the population of people who speak Hindi in descending order.");
            return null;
        }
    }

    /**
     * 33. List the population of people who speak Hindi in descending order
     * Formatting the output data from the list.
     **/
    public void printCountryLanguage3(ArrayList<CountryLanguage> country_language3)
    {
        // Check country language is not null
        if (country_language3 == null)
        {
            System.out.println("No Hindi");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s", "Language", "Percentage", "Population"));
        // Loop over all countries in the list
        for (CountryLanguage cl3 : country_language3)
        {
            //print language to check if a language is null
            if (cl3 == null)
                continue;
            String language_string =
                    String.format("%-30s %-30s %-30s",
                            cl3.language, cl3.percentage, cl3.population);
            System.out.println(language_string);
        }
    }

    //start
    /**
     * 34. List the population of people who speak Spanish in descending order
     * Query execution by user input and pass the array list to format the return value.
     * Function is called in ma
     **/
    public ArrayList<CountryLanguage> getCountryLanguage4(String input_language)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 33. List the population of people who speak Spanish in descending order
            String strQueryLanguage4 =
                    "SELECT countrylanguage.Language, countrylanguage.Percentage, country.Population FROM countrylanguage INNER JOIN country WHERE countrylanguage.CountryCode = country.Code AND countrylanguage.Language='"+input_language+"' ORDER BY countrylanguage.Percentage DESC;";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryLanguage4);
            // Extract countries information
            ArrayList<CountryLanguage> countryLanguage4 = new ArrayList<CountryLanguage>();
            while (rset.next())
            {
                CountryLanguage language4 = new CountryLanguage();
                language4.language       = rset.getString("Language");
                language4.percentage        = rset.getString("Percentage");
                language4.population        = rset.getString("Population");
                countryLanguage4.add(language4);
            }
            return countryLanguage4;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get list the population of people who speak Spanish in descending order.");
            return null;
        }
    }

    /**
     * 34. List the population of people who speak Spanish in descending order
     * Formatting the output data from the list.
     **/
    public void printCountryLanguage4(ArrayList<CountryLanguage> country_language4)
    {
        // Check country language is not null
        if (country_language4 == null)
        {
            System.out.println("No Spanish");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s", "Language", "Percentage", "Population"));
        // Loop over all countries in the list
        for (CountryLanguage cl4 : country_language4)
        {
            //print language to check if a language is null
            if (cl4 == null)
                continue;
            String language_string =
                    String.format("%-30s %-30s %-30s",
                            cl4.language, cl4.percentage, cl4.population);
            System.out.println(language_string);
        }
    }
    //end
    /** start
     * 35. List the population of people who speak Arabic in descending order.
     * Query execution by user input and pass the array list to format the return value.
     * Function is called in ma
     **/
    public ArrayList<CountryLanguage> getArabicLanguage(String input_language)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 35. List the population of people who speak Arabic in descending order
            String strQueryLanguage5 =
                    "SELECT countrylanguage.Language, countrylanguage.Percentage, country.Population FROM countrylanguage INNER JOIN country WHERE countrylanguage.CountryCode = country.Code AND countrylanguage.Language='"+input_language+"' ORDER BY countrylanguage.Percentage DESC;";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryLanguage5);
            // Extract countries information
            ArrayList<CountryLanguage> country_lan = new ArrayList<CountryLanguage>();
            while (rset.next())
            {
                CountryLanguage language5 = new CountryLanguage();
                language5.language       = rset.getString("Language");
                language5.percentage        = rset.getString("Percentage");
                language5.population        = rset.getString("Population");
                country_lan.add(language5);
            }
            return country_lan;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the list the population of people who speak Abrabic in descending order.");
            return null;
        }
    }

    /**
     * 35. List the population of people who speak Arabic in descending order
     * Formatting the output data from the list.
     **/
    public void printArabicLanguage(ArrayList<CountryLanguage> country_lang)
    {
        // Check country language is not null
        if (country_lang == null)
        {
            System.out.println("There is no one who speak Abrabic.");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s", "Language", "Percentage", "Population"));
        // Loop over all countries in the list
        for (CountryLanguage cl5 : country_lang)
        {
            //print language to check if an language is null
            if (cl5 == null)
                continue;
            String lan_string =
                    String.format("%-30s %-30s %-30s",
                            cl5.language, cl5.percentage, cl5.population);
            System.out.println(lan_string);
        }
    }
    //end
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();
        // Connect to database
        //a.connect();
        if(args.length < 1){
            a.connect("localhost:33060", 30000);
        }else{
            a.connect(args[0], Integer.parseInt(args[1]));
        }
        System.out.println("\n");

        // Display all the countries in the world organised by largest population to smallest.
        System.out.println("1: All the countries in the world organised by largest population to smallest.\n");
        ArrayList<Country> countries = a.getAllCountries();
        a.printAllCountries(countries);
        System.out.println("\n");

        // Display all the countries in a continent organised by largest population to smallest.
        System.out.println("2: All the countries in the Oceania organised by largest population to smallest.\n");
        ArrayList<Country> continents = a.getAllContinents("Oceania");
        a.printAllCountries(continents);
        System.out.println("\n");

        // Display all the countries in a region organised by largest population to smallest.
        System.out.println("3: All the countries in the Caribbean organised by largest population to smallest.\n");
        ArrayList<Country> regions = a.getAllRegion("Caribbean");
        a.printAllCountries(regions);
        System.out.println("\n");

        // Display the top N populated countries in the world where N is provided by the user.
        System.out.println("4: The top 10 populated countries in the world.\n");
        ArrayList<Country> NPopulatedCountries = a.getAllNPopulatedCountries(10);
        a.printNPopulatedCountries(NPopulatedCountries);
        System.out.println("\n");

        // Display all the countries in a continent organised by largest population to smallest.
        System.out.println("5. The top 10 populated countries in the Europe.\n");
        ArrayList<Country> NPopulated_Continents = a.getAllNPopulatedContinents("Europe", 10);
        a.printContinent(NPopulated_Continents);
        System.out.println("\n");

        // Display the top N populated countries in a region where N is provided by the user.
        System.out.println("6: The top 10 populated countries in the Caribbean.\n");
        ArrayList<Country> NPopulated_Region = a.getAllNPopulatedRegion("Caribbean", 10);
        a.printNPopulatedRegion(NPopulated_Region);
        System.out.println("\n");
// Display all the cities in the world organised by largest population to smallest.
        System.out.println("7: All the cities in the world organised by largest population to smallest.\n");
        ArrayList<City> cou = a.getAllCities();
        a.printCities(cou);
        System.out.println("\n");

        // Display all the cities in a continent organised by largest population to smallest.
        System.out.println("8. All the cities in the Asia organised by largest population to smallest.\n");
        ArrayList<City> continent = a.getAllCitiesContinent("Asia");
        a.printContinents(continent);
        System.out.println("\n");

        // Display all the cities in a region organised by largest population to smallest.
        System.out.println("9: All the cities in the Caribbean organised by largest population to smallest.\n");
        ArrayList<City> res = a.getAllCitiesRegions("Caribbean");
        a.printRegions(res);
        System.out.println("\n");

        // Display all the cities in a country organised by largest population to smallest.
        System.out.println("10: All the cities in the Myanmar organised by largest population to smallest.\n");
        ArrayList<City> cous = a.getAllCitiesCountries("Myanmar");
        a.printCountries(cous);
        System.out.println("\n");

        // Diaplay all the cities in a country organised by largest population to smallest.
        System.out.println("11: All the cities in the Queensland organised by largest population to smallest.\n");
        ArrayList<City> dist = a.getAllCitiesDistrict("Queensland");
        a.printDistrict(dist);
        System.out.println("\n");

        // Display the top N populated cities in the world where N is provided by the user.
        System.out.println("12: the top 10 populated cities in the world.\n");
        ArrayList<City> city = a.getTopNPopulatedCities(10);
        a.printTopNWorlds(city);
        System.out.println("\n");

        // Display the top N populated cities in a continent where N is provided by the user.
        System.out.println("13. The top 10 populated cities in the Europe.\n");
        ArrayList<City> topcnt = a.getTopNPopulatedContinent("Europe",10);
        a.printTopNContinent(topcnt);
        System.out.println("\n");

        // Display the top N populated cities in a region where N is provided by the user.
        System.out.println("14: The top 10 populated cities in the Caribbean.\n");
        ArrayList<City> regs = a.getTopNPopulatedRegion("Caribbean",10);
        a.printTopNRegion(regs);
        System.out.println("\n");

        // Display the top N populated cities in a country where N is provided by the user.
        System.out.println("15: The top 10 populated cities in the Argentina.\n");
        ArrayList<City> ctys = a.getTopNPopulatedCountries("Argentina",10);
        a.printTopNCountries(ctys);
        System.out.println("\n");

        // Display the top N populated cities in a district where N is provided by the user.
        System.out.println("16: The top 10 populated cities in the Zuid-Holland.\n");
        ArrayList<City> district = a.getTopNPopulatedDistrict("Zuid-Holland",10);
        a.printTopNDistrict(district);
        System.out.println("\n");
        // All the capital cities in the world organised by largest population to smallest.
        System.out.println("17: All the capital cities in the world organised by largest population to smallest.\n");
        ArrayList<CapitalCities> capital_cities = a.getAllCapitalCities();
        a.printAllCapitalCities(capital_cities);
        System.out.println("\n");

        // All the capital cities in a continent organised by largest population to smallest.
        System.out.println("18: All the capital cities in a Oceania continent organised by largest population to smallest.\n");
        ArrayList<CapitalCities> capcit_continent = a.getAllCapitalCitiesContinents("Asia");
        a.printAllCapitalCityContinent(capcit_continent);
        System.out.println("\n");

        // All the capital cities in a region organised by largest to smallest.
        System.out.println("19.All the capital cities in a Caribbean region organised by largest to smallest.\n");
        ArrayList<CapitalCities> capcit_region = a.getAllCapitalCitiesRegions("Caribbean");
        a.printAllCapitalCityRegion(capcit_region);
        System.out.println("\n");

        // The top N populated capital cities in the world where N is provided by the user.
        System.out.println("20.The top 10 populated capital cities in the world \n");
        ArrayList<CapitalCities> CapWld = a.getTopNCapCities_World(10);
        a.printTopNCapCities_World(CapWld);
        System.out.println("\n");

        // The top N populated capital cities in a continent where N is provided by the user.
        System.out.println("21.The top 10 populated capital cities in North America \n");
        ArrayList<CapitalCities> cont_capital = a.getTopNCapCities_cont("North America",10);
        a.printTopNCapCities_cont(cont_capital);
        System.out.println("\n");

        // The top N populated capital cities in a region where N is provided by the user.
        System.out.println("22.The top 10 populated capital cities in Middle East \n");
        ArrayList<CapitalCities> RegWld = a.getTopNCapCities_Reg("Middle East",10);
        a.printTopNCapCities_Reg(RegWld);
        System.out.println("\n");

        // List the population of people who speak Chinese in descending order.
        System.out.println("31: List the population of people who speak Chinese in descending order.\n");
        ArrayList<CountryLanguage> countrylanguage1 = a.getCountryLanguage1("Chinese");
        a.printCountryLanguage1(countrylanguage1);
        System.out.println("\n");

        // List the population of people who speak English in descending order.
        System.out.println("32: List the population of people who speak English in descending order.\n");
        ArrayList<CountryLanguage> countrylanguage2 = a.getCountryLanguage2("English");
        a.printCountryLanguage2(countrylanguage2);
        System.out.println("\n");

        // List the population of people who speak Hindi in descending order.
        System.out.println("33: List the population of people who speak Hindi in descending order.\n");
        ArrayList<CountryLanguage> countLanguage3 = a.getCountryLanguage3("Hindi");
        a.printCountryLanguage3(countLanguage3);
        System.out.println("\n");

        // List the population of people who speak Spanish in descending order.
        System.out.println("34: List the population of people who speak Spanish in descending order.\n");
        ArrayList<CountryLanguage> countLanguage4 = a.getCountryLanguage4("Spanish");
        a.printCountryLanguage4(countLanguage4);
        System.out.println("\n");

        // List the population of people who speak Arabic in descending order.
        System.out.println("35: List the population of people who speak Arabic in descending order.\n");
        ArrayList<CountryLanguage> countLanguage5 = a.getArabicLanguage("Arabic");
        a.printArabicLanguage(countLanguage5);
        System.out.println("\n");

        // Disconnect from database
        a.disconnect();
    }
}