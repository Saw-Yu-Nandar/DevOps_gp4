package devops.codereview;

import java.sql.*;
import java.util.ArrayList;

public class App
{
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
//    public void connect()
//    {
//        try
//        {
//            // Load Database driver
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        }
//        catch (ClassNotFoundException e)
//        {
//            System.out.println("Could not load SQL driver");
//            System.exit(-1);
//        }
//
//        int retries = 10;
//        for (int i = 0; i < retries; ++i)
//        {
//            System.out.println("Connecting to database...");
//            try
//            {
//                // Wait a bit for db to start
//                Thread.sleep(30000);
//                // Connect to database
//                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "Team_4");
//                System.out.println("Successfully connected");
//                break;
//            }
//            catch (SQLException sqle)
//            {
//                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
//                System.out.println(sqle.getMessage());
//            }
//            catch (InterruptedException ie)
//            {
//                System.out.println("Thread interrupted? Should not happen.");
//            }
//        }
//    }

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
        System.out.println(String.format("%-20s %-50s %-50s %-50s %-50s %-30s", "Code","Name","Continent","Region","Population","Capital"));
        // Loop over all countries in the list
        for (Country c : countries)
        {
            if (c == null)
                continue;
            String ctr_string =
                    String.format("%-20s %-50s %-50s %-50s %-50s %-30s",
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
            System.out.println("Failed to get all the countries in a continent organised by largest population to smallest.");
            return null;
        }
    }

    /**
     * 2. All the countries in a continent organised by largest population to smallest.
     * F

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
        System.out.println(String.format("%-30s %-30s %-30s %-30s %-30s %-30s","Code","Country Name","Continent","Region","Population","Capital"));
        // Loop over all continent in the list
        for (Country cont : continent)
        {
            if (cont == null)
                continue;
            String ctr_string =
                    String.format("%-30s %-30s %-30s %-30s %-30s %-30s",
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
            System.out.println("Failed to get all the countries in a region organised by largest population to smallest.");
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
        System.out.println(String.format("%-30s %-40s %-30s %-40s %-30s %-30s", "Code","Country Name","Continent","Region","Population","Capital"));
        // Loop over all region in the list
        for (Country r : region)
        {
            //printRegion to check if an region is null
            if (r == null)
                continue;
            String reg_string =
                    String.format("%-30s %-40s %-30s %-40s %-30s %-30s",
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
            System.out.println("Failed to get the top N populated countries in the world where N is provided by the user.");
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
        System.out.println(String.format("%-30s %-30s %-30s %-30s %-30s %-30s", "Code", "Country Name", "Continent", "Region", "Population", "Capital"));
        // Loop over all countries in the list
        for (Country npopc : NPopulatedC)
        {
            //printcontinent to check if an Continent is null
            if (npopc == null)
                continue;
            String npopctr_string =
                    String.format("%-30s %-30s %-30s %-30s %-30s %-30s",
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
            System.out.println("Failed to get the top N populated countries in a continent where N is provided by the user.");
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
        System.out.println(String.format("%-30s %-30s %-30s %-30s %-30s %-30s","Code","Country Name","Continent","Region","Population","Capital"));
        // Loop over all continent in the list
        for (Country npopconti : NPopulatedContinents)
        {
            //printRegion to check if an Continent is null
            if (npopconti == null)
                continue;
            String npopcont_string =
                    String.format("%-30s %-30s %-30s %-30s %-30s %-30s",
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
            System.out.println("Failed to get the top N populated countries in a region where N is provided by the user.");
            return null;
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
        System.out.println(String.format("%-40s %-40s %-30s %-30s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (City cit : cities)
        {
            if (cit == null)
                continue;
            String cit_string =
                    String.format("%-40s %-40s %-30s %-30s",
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
            System.out.println("Failed to get all the cities in a continent organised by largest population to smallest.");
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
        System.out.println(String.format("%-20s %-40s %-20s %-20s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (City c : continent)
        {
            if (c == null)
                continue;
            String conti_string =
                    String.format("%-20s %-40s %-20s %-20s",
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
            System.out.println("Failed to get all the cities in a region organised by largest population to smallest.");
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
        System.out.println(String.format("%-30s %-30s %-50s %-30s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (City r : regions)
        {
            if (r == null)
                continue;
            String reg_string =
                    String.format("%-30s %-30s %-50s %-30s",
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
            System.out.println("Failed to get all the cities in a country organised by largest population to smallest.");
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
        System.out.println(String.format("%-30s %-30s %-50s %-30s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (City cou : countries)
        {
            if (cou == null)
                continue;
            String c_string =
                    String.format("%-30s %-30s %-50s %-30s",
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
            System.out.println("Failed to get all the cities in a district organised by largest population to smallest.");
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
        System.out.println(String.format("%-30s %-50s %-50s %-30s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (City di : district)
        {
            if (di == null)
                continue;
            String d_string =
                    String.format("%-30s %-50s %-50s %-30s",
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
            System.out.println("Failed to get the top N populated cities in the world where N is provided by the user.");
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
        System.out.println(String.format("%-30s %-50s %-50s %-30s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (City w : wld)
        {
            if (w == null)
                continue;
            String w_string =
                    String.format("%-30s %-50s %-50s %-30s",
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
            System.out.println("Failed to get the top N populated cities in the continent where N is provided by the user.");
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
        System.out.println(String.format("%-30s %-30s %-30s %-30s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (City cont : cnt)
        {
            if (cont == null)
                continue;
            String cont_string =
                    String.format("%-30s %-30s %-30s %-30s",
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
            System.out.println("Failed to get the top N populated cities in a region where N is provided by the user.");
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
        System.out.println(String.format("%-40s %-40s %-40s %-30s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (City cont : regn)
        {
            if (cont == null)
                continue;
            String cont_string =
                    String.format("%-40s %-40s %-40s %-30s",
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
            System.out.println("Failed to get the top N populated cities in a country where N is provided by the user.");
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
        System.out.println(String.format("%-20s %-35s %-35s %-30s","Country","City Name","District","Population"));
        // Loop over all cities in the list
        for (City cont : count)
        {
            if (cont == null)
                continue;
            String cont_string =
                    String.format("%-20s %-35s %-35s %-30s",
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
            System.out.println("Failed to get the top N populated cities in a district where N is provided by the user.");
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
        System.out.println(String.format("%-30s %-20s %-20s %-30s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (City cont : dists)
        {
            if (cont == null)
                continue;
            String cont_string =
                    String.format("%-30s %-20s %-20s %-30s",
                            cont.cit_name,cont.country_name,cont.cit_district,cont.cit_population);
            System.out.println(cont_string);
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
        System.out.println(String.format("%-30s %-30s %-30s %-30s %-30s %-30s","Code","Country Name","Continent","Region","Population","Capital"));
        // Loop over all region in the list
        for (Country npopreg : NPopulatedRegion)
        {
            //printNpopulatedRegion to check if an region is null
            if (npopreg == null)
                continue;
            String npopreg_string =
                    String.format("%-30s %-30s %-30s %-30s %-30s %-30s",
                            npopreg.country_code,npopreg.country_name,npopreg.country_cont,npopreg.country_reg,npopreg.country_population,npopreg.country_cap);
            System.out.println(npopreg_string);
        }
    }

/**    23.The population of people, people living in cities, and people not living in cities in each continent.
      Query execution and pass the array list to format the return value.
     Function is called in main.**/

    public ArrayList<PeoplePopulation> getPopulatedPeopleContinent(String intput_pop_conti, int input_limited)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 23.The population of people, people living in cities, and people not living in cities in each continent.
            String strQueryTwentyThree =
                    "SELECT city.Name as 'CityName', country.Name as 'CountryName', country.Population FROM city INNER JOIN country WHERE city.ID = country.Capital AND country.Code=city.CountryCode AND country.Continent='"+intput_pop_conti+"' ORDER BY country.Population DESC LIMIT "+input_limited+";";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryTwentyThree);
            // Extract continent information
            ArrayList<PeoplePopulation> population_cont = new ArrayList<PeoplePopulation>();
            while (rset.next())
            {
                PeoplePopulation pop_cont   = new PeoplePopulation();
                pop_cont.cap_cit_name       = rset.getString("CityName");
                pop_cont.cap_cit_country    = rset.getString("CountryName");
                pop_cont.cap_cit_population = rset.getString("Population");
                population_cont.add(pop_cont);
            }
            return population_cont;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the population of people, people living in cities, and people not living in cities in each continent.");
            return null;
        }
    }
    /**
     * 23. The population of people, people living in cities, and people not living in cities in each continent.
     * Formatting the output data from the list.
     **/
    public void printPopulatedPeopleConitnent(ArrayList<PeoplePopulation> Popu_Conti)
    {
        // Check continent is not null
        if (Popu_Conti == null)
        {
            System.out.println("There is no population of people, people living in cities, and people not living in cities in each continent.");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s %30s %30s", "Capital City Name","Country Name", "Population", "People Not Living (%)", "People Living (%)"));
        ArrayList<PeoplePopulation> world_pop_arr = getWorldPopulation();
        PeoplePopulation wpop = world_pop_arr.get(0);
        float worldpop = Float.parseFloat(wpop.world_population);
        // Loop over all capital cities in a continent
        for (PeoplePopulation pcon : Popu_Conti)
        {
            //print the list to check if capital cities in a continent is null
            if (pcon == null)
                continue;
            float ccp = Integer.parseInt(pcon.cap_cit_population);
            float finalnotres = 100 * (ccp / worldpop);
            float finalres = 100 - finalnotres;
            String resString = finalres+"%";
            String resNotString = finalnotres+"%";

            String pcon_string =
                    String.format("%-30s %-30s %-30s %30s %30s",
                            pcon.cap_cit_name, pcon.cap_cit_country, pcon.cap_cit_population, resNotString, resString);
            System.out.println(pcon_string);
        }
    }

    /**
     * 24.The population of people, people living in cities, and people not living in cities in each region.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<PeoplePopulation> getPopulatedPeopleRegions(String intput_pop_reg, int input_limited)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 24.The population of people, people living in cities, and people not living in cities in each region.
            String strQueryTwentyFour =
                    "SELECT city.Name as 'CityName', country.Name as 'CountryName', country.Population FROM city INNER JOIN country WHERE city.ID = country.Capital AND country.Code=city.CountryCode AND country.Region='"+intput_pop_reg+"' ORDER BY country.Population DESC LIMIT "+input_limited+";";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryTwentyFour);
            // Extract region information
            ArrayList<PeoplePopulation> population_reg = new ArrayList<PeoplePopulation>();
            while (rset.next())
            {
                PeoplePopulation pop_reg    = new PeoplePopulation();
                pop_reg.cap_cit_name        = rset.getString("CityName");
                pop_reg.cap_cit_country     = rset.getString("CountryName");
                pop_reg.cap_cit_population  = rset.getString("Population");
                population_reg.add(pop_reg);
            }
            return population_reg;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the population of people, people living in cities, and people not living in cities in each region.");
            return null;
        }
    }
    /**
     * 24. The population of people, people living in cities, and people not living in cities in each region.
     * Formatting the output data from the list.
     **/
    public void printPopulatedPeopleRegions(ArrayList<PeoplePopulation> Popu_Regs)
    {
        // Check region is not null
        if (Popu_Regs == null)
        {
            System.out.println("There is no population of people, people living in cities, and people not living in cities in each region.");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s %-30s %-30s", "Capital City Name","Country Name", "Population", "People Not Living (%)", "People Living (%)"));
        ArrayList<PeoplePopulation> contient_pop_arr = getRegionsPopulation();
        PeoplePopulation conpop = contient_pop_arr.get(3);
        float continentpop = Float.parseFloat(conpop.regions_population);
        // Loop over all capital cities in a region
        for (PeoplePopulation preg : Popu_Regs)
        {
            //print the list to check if capital cities in a region is null
            if (preg == null)
                continue;
            float rp = Integer.parseInt(preg.cap_cit_population);
            float finalresregion = 100 * (rp / continentpop);
            float finalnotresregions = 100 - finalresregion;
            String resNotStringRegion = finalresregion+"%";
            String resStringRegion = finalnotresregions+"%";
            String preg_string =
                    String.format("%-30s %-30s %-30s %-30s %-30s",
                            preg.cap_cit_name,preg.cap_cit_country, preg.cap_cit_population, resNotStringRegion, resStringRegion);
            System.out.println(preg_string);
        }
    }

    /**
     * 25.The population of people, people living in cities, and people not living in cities in each country.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<PeoplePopulation> getPopulatedPeopleCountry(String intput_pop_cou, int input_limited)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 25.The population of people, people living in cities, and people not living in cities in each country.
            String strQueryTwentyFive =
                    "SELECT city.Name as 'CityName', country.Name as 'CountryName', country.Population FROM city INNER JOIN country WHERE city.ID = country.Capital AND country.Code=city.CountryCode AND country.Name='"+intput_pop_cou+"' ORDER BY country.Population DESC LIMIT "+input_limited+";";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryTwentyFive);
            // Extract country information
            ArrayList<PeoplePopulation> population_cou = new ArrayList<PeoplePopulation>();
            while (rset.next())
            {
                PeoplePopulation pop_cou    = new PeoplePopulation();
                pop_cou.cap_cit_name        = rset.getString("CityName");
                pop_cou.cap_cit_country     = rset.getString("CountryName");
                pop_cou.cap_cit_population  = rset.getString("Population");
                population_cou.add(pop_cou);
            }
            return population_cou;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the population of people, people living in cities, and people not living in cities in each country.");
            return null;
        }
    }
    /**
     * 25. The population of people, people living in cities, and people not living in cities in each country.
     * Formatting the output data from the list.
     **/
    public void printPopulatedPeopleCountry(ArrayList<PeoplePopulation> Popu_Coun)
    {
        // Check country is not null
        if (Popu_Coun == null)
        {
            System.out.println("There is no population of people, people living in cities, and people not living in cities in each country.");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s %-30s %-30s", "Capital City Name","Country Name", "Population", "People Not Living(%)", "People Living(%)"));
        ArrayList<PeoplePopulation> country_pop_arr = getCountriesPopulation();
        PeoplePopulation counpop = country_pop_arr.get(140);
        float countrypop = Float.parseFloat(counpop.countries_population);
        // Loop over all capital cities in a country
        for (PeoplePopulation pcou : Popu_Coun)
        {
            //print the list to check if capital cities in a country is null
            if (pcou == null)
                continue;
            float conunp = Integer.parseInt(pcou.cap_cit_population);
            float finalrescountry = 100 * (conunp / countrypop);
            float finalnotrescountry = 100 - finalrescountry;
            String resStringCountry = finalrescountry+"%";
            String resNotStringCountry = finalnotrescountry+"%";
            String pcou_string =
                    String.format("%-30s %-30s %-30s %-30s %-30s",
                            pcou.cap_cit_name,pcou.cap_cit_country, pcou.cap_cit_population, resNotStringCountry, resStringCountry);
            System.out.println(pcou_string);
        }
    }

    /**
     * 26.The population of the world.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<PeoplePopulation> getWorldPopulation()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 26.The population of the world.
            String strQueryTwentySix =
                    "SELECT SUM(country.Population) as 'Population' FROM country;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryTwentySix);
            // Extract country information
            ArrayList<PeoplePopulation> population_world = new ArrayList<PeoplePopulation>();
            while (rset.next())
            {
                PeoplePopulation pop_world    = new PeoplePopulation();
                //pop_world.world_name      = rset.getString("Name");
                pop_world.world_population      = rset.getString("Population");
                population_world.add(pop_world);
            }
            return population_world;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the population of the world.");
            return null;
        }
    }
    /**
     * 26. The population of the world.
     * Formatting the output data from the list.
     **/
    public void printWorldPopulation(ArrayList<PeoplePopulation> pop_world)
    {

        // Check country is not null
        if (pop_world == null) {
            System.out.println("There is no population of the world.");
            return;
        }
        // Print header
        //System.out.println(String.format("%-40s", "Population"));
        // Loop over all capital cities in a country
        // int total_word_pop = 0;
        //NumberFormat world_res_format = NumberFormat.getInstance();
        for (PeoplePopulation wpop : pop_world) {
            //print the list to check if capital cities in a country is null
            if (wpop == null)
                continue;
            String pworld_string =
                    String.format("%-40s",
                            wpop.world_population);
            System.out.println("Total World Population: "+pworld_string);
            //System.out.println("Total World Population: "+world_res_format.format(pworld_string));
            //total_word_pop = Integer.parseInt(wpop.world_population) + total_word_pop;
        }
        //NumberFormat world_res_format = NumberFormat.getInstance();
        ///world_res_format.setGroupingUsed(true);
        //String result_world = String.format("The total number of world population: "+world_res_format.format(total_word_pop));
        //System.out.println(result_world);
    }

    /**
     * 27.The population of the continent.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<PeoplePopulation> getContinentPopulation()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 27.The population of the continent.
            String strQueryTwentySeven =
                    "SELECT country.Continent, SUM(country.Population) AS 'Population' FROM country GROUP BY country.Continent";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryTwentySeven);
            // Extract continent information
            ArrayList<PeoplePopulation> population_cont = new ArrayList<PeoplePopulation>();
            while (rset.next())
            {
                PeoplePopulation pop_continent   = new PeoplePopulation();
                pop_continent.continent_name = rset.getString("Continent");
                pop_continent.continent_population      = rset.getString("Population");
                population_cont.add(pop_continent);
            }
            return population_cont;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the population of the continent.");
            return null;
        }
    }
    /**
     * 27. The population of the continent.
     * Formatting the output data from the list.
     **/
    public void printContinentPopulation(ArrayList<PeoplePopulation> pop_conti)
    {

        // Check country is not null
        if (pop_conti == null) {
            System.out.println("There is no population of the continent.");
            return;
        }
        // Print header
        System.out.println(String.format("%-40s %-40s", "Continent Name", "Population"));
        // Loop over all capital cities in a country
        //int total_cont_pop = 0;
        for (PeoplePopulation continent_population : pop_conti)
        {
            //print the list to check if capital cities in a country is null
            if (continent_population == null)
                continue;
            String pworld_string =
                    String.format("%-40s %-40s",
                            continent_population.continent_name, continent_population.continent_population);
            System.out.println(pworld_string);
            //total_cont_pop = Integer.parseInt(continent_population.continent_population) + total_cont_pop;
        }
        //NumberFormat conti_res_format = NumberFormat.getInstance();
        //conti_res_format.setGroupingUsed(true);
        //String result_conti = String.format("The total number of continent population: "+conti_res_format.format(total_cont_pop));
        //System.out.println(result_conti);
    }

    /**
     * 28.The population of the regions.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<PeoplePopulation> getRegionsPopulation()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 27.The population of the continent.
            String strQueryTwentyEight =
                    "SELECT country.Region, SUM(country.Population) AS 'Population' FROM country GROUP BY country.Region;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryTwentyEight);
            // Extract regions information
            ArrayList<PeoplePopulation> population_regions = new ArrayList<PeoplePopulation>();
            while (rset.next())
            {
                PeoplePopulation pop_regions   = new PeoplePopulation();
                pop_regions.regions_name      = rset.getString("Region");
                pop_regions.regions_population     = rset.getString("Population");
                population_regions.add(pop_regions);
            }
            return population_regions;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the population of the continent.");
            return null;
        }
    }
    /**
     * 28. The population of the regions.
     * Formatting the output data from the list.
     **/
    public void printRegionsPopulation(ArrayList<PeoplePopulation> pop_regs)
    {

        // Check country is not null
        if (pop_regs == null) {
            System.out.println("There is no population of the regions.");
            return;
        }
        // Print header
        System.out.println(String.format("%-40s %-40s", "Region Name", "Population"));
        // Loop over population from region
        //int total_reg_pop = 0;
        for (PeoplePopulation regions_population : pop_regs)
        {
            //print the list to check if capital cities in a country is null
            if (regions_population == null)
                continue;
            String pworld_string =
                    String.format("%-40s %-40s",
                            regions_population.regions_name, regions_population.regions_population);
            System.out.println(pworld_string);
            //total_reg_pop = Integer.parseInt(regions_population.regions_population) + total_reg_pop;
        }
        //NumberFormat reg_res_format = NumberFormat.getInstance();
        //reg_res_format.setGroupingUsed(true);
        //String result_conti = String.format("The total number of continent population: "+reg_res_format.format(total_reg_pop));
        //System.out.println(result_conti);
    }

    /**
     * 29.The population of the country.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<PeoplePopulation> getCountriesPopulation()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 29.The population of the country.
            String strQueryTwentyNine =
                    "SELECT country.Name, SUM(country.Population) AS 'Population' FROM country GROUP BY country.Name;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryTwentyNine);
            // Extract countries information
            ArrayList<PeoplePopulation> population_countries = new ArrayList<PeoplePopulation>();
            while (rset.next())
            {
                PeoplePopulation pop_country   = new PeoplePopulation();
                pop_country.countries_name      = rset.getString("Name");
                pop_country.countries_population     = rset.getString("Population");
                population_countries.add(pop_country);
            }
            return population_countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the population of the continent.");
            return null;
        }
    }
    /**
     * 29. The population of the countries.
     * Formatting the output data from the list.
     **/
    public void printCountriesPopulation(ArrayList<PeoplePopulation> pop_contr)
    {

        // Check country is not null
        if (pop_contr == null) {
            System.out.println("There is no population of the countries.");
            return;
        }
        // Print header
        System.out.println(String.format("%-40s %-40s", "Name", "Population"));
        // Loop over population from region
        //int total_con_pop = 0;
        for (PeoplePopulation countr_population : pop_contr)
        {
            //print the list to check if capital cities in a country is null
            if (countr_population == null)
                continue;
            String pworld_string =
                    String.format("%-40s %-40s",
                            countr_population.countries_name, countr_population.countries_population);
            System.out.println(pworld_string);
            //total_con_pop = Integer.parseInt(countr_population.countries_population) + total_con_pop;
        }
        //NumberFormat con_res_format = NumberFormat.getInstance();
        //con_res_format.setGroupingUsed(true);
        //String result_conti = String.format("The total number of country population: "+con_res_format.format(total_con_pop));
        //System.out.println(result_conti);
    }

    /**
     * 30.The population of the district.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<PeoplePopulation> getDistrictPopulation()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 29.The population of the country.
            String strQueryThirty =
                    "SELECT city.District, SUM(city.Population) AS 'Population' FROM city GROUP BY city.District";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryThirty);
            // Extract countries information
            ArrayList<PeoplePopulation> population_districts = new ArrayList<PeoplePopulation>();
            while (rset.next())
            {
                PeoplePopulation population_district   = new PeoplePopulation();
                population_district.district_name      = rset.getString("District");
                population_district.district_population     = rset.getString("Population");
                population_districts.add(population_district);
            }
            return population_districts;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the population of the districts.");
            return null;
        }
    }
    /**
     * 30. The population of the districts.
     * Formatting the output data from the list.
     **/
    public void printDistrictsPopulation(ArrayList<PeoplePopulation> pop_dist)
    {

        // Check country is not null
        if (pop_dist == null) {
            System.out.println("There is no population of the districts.");
            return;
        }
        // Print header
        System.out.println(String.format("%-40s %-40s", "District Name", "Population"));
        // Loop over population from region
        //int total_dist_pop = 0;
        for (PeoplePopulation dst_population : pop_dist)
        {
            //print the list to check if capital cities in a country is null
            if (dst_population == null)
                continue;
            String pworld_string =
                    String.format("%-40s %-40s",
                            dst_population.district_name, dst_population.district_population);
            System.out.println(pworld_string);
            //total_dist_pop = Integer.parseInt(dst_population.district_population) + total_dist_pop;
        }
        //NumberFormat dis_res_format = NumberFormat.getInstance();
        //dis_res_format.setGroupingUsed(true);
        //String result_dist = String.format("The total number of district population: "+dis_res_format.format(total_dist_pop));
        //System.out.println(result_dist);
    }

    /**
     * 31.The population of the city.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<PeoplePopulation> getCityPopulation()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 31.The population of the city.
            String strQueryThirtyOne =
                    "SELECT city.Name, SUM(city.Population) AS 'Population' FROM city GROUP BY city.Name;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryThirtyOne);
            // Extract city information
            ArrayList<PeoplePopulation> population_cities = new ArrayList<PeoplePopulation>();
            while (rset.next())
            {
                PeoplePopulation population_city   = new PeoplePopulation();
                population_city.city_name      = rset.getString("Name");
                population_city.city_population     = rset.getString("Population");
                population_cities.add(population_city);
            }
            return population_cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the population of the city.");
            return null;
        }
    }
    /**
     * 31. The population of the cities.
     * Formatting the output data from the list.
     **/
    public void printCityPopulation(ArrayList<PeoplePopulation> pop_city)
    {

        // Check country is not null
        if (pop_city == null) {
            System.out.println("There is no population of the cities.");
            return;
        }
        // Print header
        System.out.println(String.format("%-40s %-40s", "City Name", "Population"));
        // Loop over population from region
        //int total_city_pop = 0;
        for (PeoplePopulation city_population : pop_city)
        {
            //print the list to check if capital cities in a country is null
            if (city_population == null)
                continue;
            String pworld_string =
                    String.format("%-40s %-40s",
                            city_population.city_name, city_population.city_population);
            System.out.println(pworld_string);
            //total_city_pop = Integer.parseInt(city_population.city_population) + total_city_pop;
        }
        //NumberFormat city_res_format = NumberFormat.getInstance();
        //city_res_format.setGroupingUsed(true);
        //String result_city = String.format("The total number of city population: "+city_res_format.format(total_city_pop));
        //System.out.println(result_city);
    }

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
        System.out.println("2: All the countries in a continent organised by largest population to smallest.\n");
        ArrayList<Country> continents = a.getAllContinents("Oceania");
        a.printAllCountries(continents);
        System.out.println("\n");

        // Display all the countries in a region organised by largest population to smallest.
        System.out.println("3: All the countries in a region organised by largest population to smallest.\n");
        ArrayList<Country> regions = a.getAllRegion("Caribbean");
        a.printAllCountries(regions);
        System.out.println("\n");

        // Display the top N populated countries in the world where N is provided by the user.
        System.out.println("4: The top N populated countries in the world where N is provided by the user.\n");
        ArrayList<Country> NPopulatedCountries = a.getAllNPopulatedCountries(10);
        a.printNPopulatedCountries(NPopulatedCountries);
        System.out.println("\n");

        // Display all the countries in a continent organised by largest population to smallest.
        System.out.println("5. The top N populated countries in a continent where N is provided by the user.\n");
        ArrayList<Country> NPopulated_Continents = a.getAllNPopulatedContinents("Europe", 10);
        a.printContinent(NPopulated_Continents);
        System.out.println("\n");

        // Display the top N populated countries in a region where N is provided by the user.
        System.out.println("6: The top N populated countries in a region where N is provided by the user.\n");
        ArrayList<Country> NPopulated_Region = a.getAllNPopulatedRegion("Caribbean", 10);
        a.printNPopulatedRegion(NPopulated_Region);
        System.out.println("\n");

// Display all the cities in the world organised by largest population to smallest.
        System.out.println("7: All the cities in the world organised by largest population to smallest.\n");
        ArrayList<City> cou = a.getAllCities();
        a.printCities(cou);
        System.out.println("\n");

        // Display all the cities in a continent organised by largest population to smallest.
        System.out.println("8. All the cities in a continent organised by largest population to smallest.\n");
        ArrayList<City> continent = a.getAllCitiesContinent("Asia");
        a.printContinents(continent);
        System.out.println("\n");

        // Display all the cities in a region organised by largest population to smallest.
        System.out.println("9: All the cities in a region organised by largest population to smallest.\n");
        ArrayList<City> reg = a.getAllCitiesRegions("Caribbean");
        a.printRegions(reg);
        System.out.println("\n");

        // Display all the cities in a country organised by largest population to smallest.
        System.out.println("10: All the cities in a country organised by largest population to smallest.\n");
        ArrayList<City> ctr = a.getAllCitiesCountries("Myanmar");
        a.printCountries(ctr);
        System.out.println("\n");

        // Diaplay all the cities in a country organised by largest population to smallest.
        System.out.println("11: All the cities in a district organised by largest population to smallest.\n");
        ArrayList<City> dist = a.getAllCitiesDistrict("Queensland");
        a.printDistrict(dist);
        System.out.println("\n");

        // Display the top N populated cities in the world where N is provided by the user.
        System.out.println("12: the top N populated cities in the world where N is provided by the user.\n");
        ArrayList<City> city = a.getTopNPopulatedCities(10);
        a.printTopNWorlds(city);
        System.out.println("\n");

        // Display the top N populated cities in a continent where N is provided by the user.
        System.out.println("13. The top N populated cities in a continent where N is provided by the user.\n");
        ArrayList<City> topcnt = a.getTopNPopulatedContinent("Europe",10);
        a.printTopNContinent(topcnt);
        System.out.println("\n");

        // Display the top N populated cities in a region where N is provided by the user.
        System.out.println("14: The top N populated cities in a region where N is provided by the user.\n");
        ArrayList<City> regs = a.getTopNPopulatedRegion("Caribbean",10);
        a.printTopNRegion(regs);
        System.out.println("\n");

        // Display the top N populated cities in a country where N is provided by the user.
        System.out.println("15: The top N populated cities in a country where N is provided by the user.\n");
        ArrayList<City> ctys = a.getTopNPopulatedCountries("Argentina",10);
        a.printTopNCountries(ctys);
        System.out.println("\n");

        // Display the top N populated cities in a district where N is provided by the user.
        System.out.println("16: The top N populated cities in a district where N is provided by the user.\n");
        ArrayList<City> district = a.getTopNPopulatedDistrict("Zuid-Holland",10);
        a.printTopNDistrict(district);
        System.out.println("\n");

        // The population of people, people living in cities, and people not living in cities in each continent.
        System.out.println("23.The population of people, people living in cities, and people not living in cities in each continent. \n");
        ArrayList<PeoplePopulation> PopCont = a.getPopulatedPeopleContinent("Europe",10);
        a.printPopulatedPeopleConitnent(PopCont);
        System.out.println("\n");

        // The population of people, people living in cities, and people not living in cities in each region.
        System.out.println("24.The population of people, people living in cities, and people not living in cities in each region. \n");
        ArrayList<PeoplePopulation> PopReg = a.getPopulatedPeopleRegions("Southern Europe",10);
        a.printPopulatedPeopleRegions(PopReg);
        System.out.println("\n");

        // The population of people, people living in cities, and people not living in cities in each country.
        System.out.println("25.The population of people, people living in cities, and people not living in cities in each country. \n");
        ArrayList<PeoplePopulation> PopCoun = a.getPopulatedPeopleCountry("Myanmar",10);
        a.printPopulatedPeopleCountry(PopCoun);
        System.out.println("\n");

        // The population of the world.
        System.out.println("26. The population of the world.");
        ArrayList<PeoplePopulation> pop_world = a.getWorldPopulation();
        a.printWorldPopulation(pop_world);
        System.out.println("\n");

        // The population of the continent.
        System.out.println("27. The population of the continent.");
        ArrayList<PeoplePopulation> pop_contin = a.getContinentPopulation();
        a.printContinentPopulation(pop_contin);
        System.out.println("\n");

        // The population of the regions.
        System.out.println("28. The population of the regions.");
        ArrayList<PeoplePopulation> pop_regions = a.getRegionsPopulation();
        a.printRegionsPopulation(pop_regions);
        System.out.println("\n");

        // The population of the countries.
        System.out.println("29. The population of the countries.");
        ArrayList<PeoplePopulation> pop_countries = a.getCountriesPopulation();
        a.printCountriesPopulation(pop_countries);
        System.out.println("\n");

        // The population of the districts.
        System.out.println("30. The population of the districts.");
        ArrayList<PeoplePopulation> pop_disct = a.getDistrictPopulation();
        a.printDistrictsPopulation(pop_disct);
        System.out.println("\n");

        // The population of the cities.
        System.out.println("31. The population of the cities.");
        ArrayList<PeoplePopulation> pop_cities = a.getCityPopulation();
        a.printCityPopulation(pop_cities);
        System.out.println("\n");


        // Disconnect from database
        a.disconnect();
    }
}