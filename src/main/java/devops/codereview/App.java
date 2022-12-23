package devops.codereview;

import javax.swing.plaf.synth.Region;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class App
{
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "Team_4");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
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
     * 7. All the cities in the world organised by largest population to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<Cities> getAllCities()
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
            ArrayList<Cities> cities = new ArrayList<Cities>();
            while (rset.next())
            {
                Cities cit = new Cities();
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
    public void printCities(ArrayList<Cities> cities)
    {
        if (cities == null){
            System.out.println("There is no city in the world!");
        }
        // Print header
        System.out.println(String.format("%-40s %-40s %-30s %-30s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (Cities cit : cities)
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
    public ArrayList<Cities> getAllCitiesContinent(String input_continent)
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
            ArrayList<Cities> continent = new ArrayList<Cities>();
            while (rset.next())
            {
                Cities conti = new Cities();
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
    public void printContinents(ArrayList<Cities> continent)
    {
        if (continent == null){
            System.out.println("There is no city in a continent!");
        }
        // Print header
        System.out.println(String.format("%-20s %-40s %-20s %-20s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (Cities c : continent)
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
    public ArrayList<Cities> getAllCitiesRegions(String input_regions)
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
            ArrayList<Cities> regions = new ArrayList<Cities>();
            while (rset.next())
            {
                Cities reg          = new Cities();
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
    public void printRegions(ArrayList<Cities> regions)
    {
        if (regions == null){
            System.out.println("There is no city in a region!");
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-50s %-30s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (Cities r : regions)
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
    public ArrayList<Cities> getAllCitiesCountries(String input_countries)
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
            ArrayList<Cities> countries = new ArrayList<Cities>();
            while (rset.next())
            {
                Cities c1           = new Cities();
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
    public void printCountries(ArrayList<Cities> countries)
    {
        if (countries == null){
            System.out.println("There is no city in a country!");
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-50s %-30s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (Cities cou : countries)
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
    public ArrayList<Cities> getAllCitiesDistrict(String input_district)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 11: All the cities in a district organised by largest population to smallest.
            String strQueryEight =
                    "SELECT city.Name as 'CityName', country.Name as 'CountryName', city.District as 'District', city.Population FROM country INNER JOIN city WHERE city.District='"+input_district+"' ORDER BY city.Population DESC;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryEight);
            // Extract district information
            ArrayList<Cities> district = new ArrayList<Cities>();
            while (rset.next())
            {
                Cities dist         = new Cities();
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
    public void printDistrict(ArrayList<Cities> district)
    {
        if (district == null){
            System.out.println("There is no cities in a district!");
        }
        // Print header
        System.out.println(String.format("%-30s %-50s %-50s %-30s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (Cities di : district)
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
    public ArrayList<Cities> getTopNPopulatedCities(int input_world)
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
            ArrayList<Cities> worlds = new ArrayList<Cities>();
            while (rset.next())
            {
                Cities world = new Cities();
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
    public void printTopNWorlds(ArrayList<Cities> wld)
    {
        if (wld == null){
            System.out.println("There is no top N populated cities in the world!");
        }
        // Print header
        System.out.println(String.format("%-30s %-50s %-50s %-30s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (Cities w : wld)
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
    public ArrayList<Cities>getTopNPopulatedContinent(String input_Continent, int input_limited)
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
            ArrayList<Cities> continent = new ArrayList<Cities>();
            while (rset.next())
            {
                Cities conti            = new Cities();
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
    public void printTopNContinent(ArrayList<Cities> cnt)
    {
        if (cnt == null){
            System.out.println("No cities in a continent");
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s %-30s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (Cities cont : cnt)
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
    public ArrayList<Cities> getTopNPopulatedRegion(String input_Region, int input_limited)
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
            ArrayList<Cities> region = new ArrayList<Cities>();
            while (rset.next())
            {
                Cities reg          = new Cities();
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
    public void printTopNRegion(ArrayList<Cities> regn)
    {
        if (regn == null){
            System.out.println("No cities in a region");
        }
        // Print header
        System.out.println(String.format("%-40s %-40s %-40s %-30s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (Cities cont : regn)
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
    public ArrayList<Cities> getTopNPopulatedCountries(String input_Country, int input_limited)
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
            ArrayList<Cities> country = new ArrayList<Cities>();
            while (rset.next())
            {
                Cities cty = new Cities();
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
    public void printTopNCountries(ArrayList<Cities> count)
    {
        if (count == null){
            System.out.println("No cities in a country");
        }
        // Print header
        System.out.println(String.format("%-20s %-35s %-35s %-30s","Country","City Name","District","Population"));
        // Loop over all cities in the list
        for (Cities cont : count)
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
    public ArrayList<Cities> getTopNPopulatedDistrict(String input_District, int input_limited)
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
            ArrayList<Cities> dist = new ArrayList<Cities>();
            while (rset.next())
            {
                Cities dis = new Cities();
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
    public void printTopNDistrict(ArrayList<Cities> dists)
    {
        if (dists == null){
            System.out.println("No cities in a region");
        }
        // Print header
        System.out.println(String.format("%-30s %-20s %-20s %-30s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (Cities cont : dists)
        {
            if (cont == null)
                continue;
            String cont_string =
                    String.format("%-30s %-20s %-20s %-30s",
                            cont.cit_name,cont.country_name,cont.cit_district,cont.cit_population);
            System.out.println(cont_string);
        }

    }
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();
        // Connect to database
        a.connect();
        System.out.println("\n");

        // Display all the countries in the world organised by largest population to smallest.
        // System.out.println("1: All the countries in the world organised by largest population to smallest.\n");
        // ArrayList<World> countries = a.getAllCountries();
        // a.printCountries(countries);
        // System.out.println("\n");

        // Display all the countries in a continent organised by largest population to smallest.
        // System.out.println("2: All the countries in a continent organised by largest population to smallest.\n");
        // ArrayList<Countries> continent = a.getAllContinents();
        // a.printContinent(continent);
        // System.out.println("\n");

        // Display all the countries in a region organised by largest population to smallest.
        // System.out.println("3: All the countries in a region organised by largest population to smallest.\n");
        // ArrayList<Countries> continent = a.getAllRegion();
        // a.printRegion(continent);
        // System.out.println("\n");

        // Display the top N populated countries in the world where N is provided by the user.
        // System.out.println("4: The top N populated countries in the world where N is provided by the user.\n");
        // ArrayList<World> tnw = a.getAllNPopulatedCountries("Africa",10);
        // a.printNPopulatedCountries(tnw);
        // System.out.println("\n");

        // Display all the countries in a continent organised by largest population to smallest.
        // System.out.println("5. The top N populated countries in a continent where N is provided by the user.\n");
        // ArrayList<Countries> continent = a.getAllContinents();
        // a.printContinent(continent);
        // System.out.println("\n");

        // Display the top N populated countries in a region where N is provided by the user.
        // System.out.println("6: the top N populated countries in a region where N is provided by the user.\n");
        // ArrayList<Countries> continent = a.getAllNPopulatedRegion(10);
        // a.printNPopulatedRegion(continent);
        // System.out.println("\n");

        // Display all the cities in the world organised by largest population to smallest.
         System.out.println("7: All the cities in the world organised by largest population to smallest.\n");
         ArrayList<Cities> cou = a.getAllCities();
         a.printCities(cou);
         System.out.println("\n");

        // Display all the cities in a continent organised by largest population to smallest.
         System.out.println("8. All the cities in a continent organised by largest population to smallest.\n");
         ArrayList<Cities> continent = a.getAllCitiesContinent("Asia");
         a.printContinents(continent);
         System.out.println("\n");

        // Display all the cities in a region organised by largest population to smallest.
         System.out.println("9: All the cities in a region organised by largest population to smallest.\n");
         ArrayList<Cities> regions = a.getAllCitiesRegions("Caribbean");
         a.printRegions(regions);
         System.out.println("\n");

        // Display all the cities in a country organised by largest population to smallest.
         System.out.println("10: All the cities in a country organised by largest population to smallest.\n");
         ArrayList<Cities> countries = a.getAllCitiesCountries("Myanmar");
         a.printCountries(countries);
         System.out.println("\n");

        // Diaplay all the cities in a country organised by largest population to smallest.
         System.out.println("11: All the cities in a district organised by largest population to smallest.\n");
         ArrayList<Cities> dist = a.getAllCitiesDistrict("Queensland");
         a.printDistrict(dist);
         System.out.println("\n");

        // Display the top N populated cities in the world where N is provided by the user.
         System.out.println("12: the top N populated cities in the world where N is provided by the user.\n");
         ArrayList<Cities> city = a.getTopNPopulatedCities(10);
         a.printTopNWorlds(city);
         System.out.println("\n");

        // Display the top N populated cities in a continent where N is provided by the user.
         System.out.println("13. The top N populated cities in a continent where N is provided by the user.\n");
         ArrayList<Cities> topcnt = a.getTopNPopulatedContinent("Europe",10);
         a.printTopNContinent(topcnt);
         System.out.println("\n");

        // Display the top N populated cities in a region where N is provided by the user.
         System.out.println("14: The top N populated cities in a region where N is provided by the user.\n");
         ArrayList<Cities> regs = a.getTopNPopulatedRegion("Caribbean",10);
         a.printTopNRegion(regs);
         System.out.println("\n");

        // Display the top N populated cities in a country where N is provided by the user.
         System.out.println("15: The top N populated cities in a country where N is provided by the user.\n");
         ArrayList<Cities> ctys = a.getTopNPopulatedCountries("Argentina",10);
         a.printTopNCountries(ctys);
         System.out.println("\n");

        // Display the top N populated cities in a district where N is provided by the user.
         System.out.println("16: The top N populated cities in a district where N is provided by the user.\n");
         ArrayList<Cities> district = a.getTopNPopulatedDistrict("Zuid-Holland",10);
         a.printTopNDistrict(district);
         System.out.println("\n");

        // Disconnect from database
        a.disconnect();
    }
}