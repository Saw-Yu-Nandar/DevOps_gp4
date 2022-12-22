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
                    "SELECT city.Name, city.Population FROM city ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQuerySeven);
            // Extract cities information
            ArrayList<Cities> cities = new ArrayList<Cities>();
            while (rset.next())
            {
                Cities cit = new Cities();
                cit.cit_name = rset.getString("Name");
                cit.cit_population = rset.getString("Population");
                cities.add(cit);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get salary details");
            return null;
        }
    }

    /**
     * 7. All the cities in the world organised by largest population to smallest.
     * Formatting the output data from the list.
     **/
    public void printCities(ArrayList<Cities> cities)
    {
        // Print header
        System.out.println(String.format("%-70s %-70s", "Name", "Population"));
        // Loop over all cities in the list
        for (Cities cit : cities)
        {
            String cit_string =
                    String.format("%-70s %-70s",
                            cit.cit_name, cit.cit_population);
            System.out.println(cit_string);
        }
    }

    /**
     * 8. All the cities in a continent organised by largest population to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<Continent> getAllcitiesContinent(String input_continent)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 8: All the cities in a continent organised by largest population to smallest.
            String strQueryEight =
                    "SELECT country.Continent, city.Name as 'cityname', country.Name as 'countryname', city.District, city.Population, country.Code FROM city INNER JOIN country on city.CountryCode = country.Code WHERE country.Continent = '"+input_continent+"' ORDER BY city.Population DESC;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryEight);
            // Extract continent information
            ArrayList<Continent> continent = new ArrayList<Continent>();
            while (rset.next())
            {
                Continent conti = new Continent();
                conti.continent = rset.getString("Continent");
                conti.cityname = rset.getString("cityname");
                conti.countryname = rset.getString("countryname");
                conti.district = rset.getString("District");
                conti.population = rset.getString("Population");
                conti.countrycode = rset.getString("Code");
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
    public void printContinents(ArrayList<Continent> continent)
    {
        // Print header
        System.out.println(String.format("%-20s %-20s %-20s %-50s %-20s %-20s","Continent","City Name","Country Name","District","Population","Code"));
        // Loop over all countries in the list
        for (Continent c : continent)
        {
            String conti_string =
                    String.format("%-20s %-20s %-20s %-50s %-20s %-20s",
                            c.continent,c.cityname,c.countryname,c.district,c.population,c.countrycode);
            System.out.println(conti_string);
        }
    }
    /**
     * 9. All the cities in a region organised by largest population to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<Regions> getAllcitiesRegions(String input_regions)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 9: All the cities in a region organised by largest population to smallest.
            String strQueryEight =
                    "SELECT country.Region, city.Name as 'cityname', country.Name as 'countryname', city.District, city.Population, country.Code FROM city INNER JOIN country on city.CountryCode = country.Code WHERE country.Region = '"+input_regions+"' ORDER BY city.Population DESC;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryEight);
            // Extract continent information
            ArrayList<Regions> regions = new ArrayList<Regions>();
            while (rset.next())
            {
                Regions reg = new Regions();
                reg.region = rset.getString("Region");
                reg.cityname = rset.getString("cityname");
                reg.countryname = rset.getString("countryname");
                reg.district = rset.getString("District");
                reg.population = rset.getString("Population");
                reg.countrycode = rset.getString("Code");
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
    public void printRegions(ArrayList<Regions> regions)
    {
        // Print header
        System.out.println(String.format("%-20s %-30s %-30s %-50s %-30s %-20s","Region","City Name","Country Name","District","Population","Code"));
        // Loop over all countries in the list
        for (Regions r : regions)
        {
            String reg_string =
                    String.format("%-20s %-30s %-30s %-50s %-30s %-20s",
                            r.region,r.cityname,r.countryname,r.district,r.population,r.countrycode);
            System.out.println(reg_string);
        }
    }
    /**
     * 10. All the cities in a country organised by largest population to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<Countries> getAllcitiesCountries(String input_countries)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 10: All the cities in a country organised by largest population to smallest.
            String strQueryEight =
                    "SELECT country.Name as 'Countries', city.Name as 'Cityname', city.District, city.Population FROM country INNER JOIN city WHERE country.Code = city.CountryCode AND country.Name='"+input_countries+"' ORDER BY city.Population DESC;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryEight);
            // Extract continent information
            ArrayList<Countries> countries = new ArrayList<Countries>();
            while (rset.next())
            {
                Countries c1 = new Countries();
                c1.name = rset.getString("Countries");
                c1.cityname = rset.getString("Cityname");
                c1.district = rset.getString("District");
                c1.population = rset.getString("Population");
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
    public void printCountries(ArrayList<Countries> countries)
    {
        // Print header
        System.out.println(String.format("%-30s %-30s %-50s %-30s","Country Name","City Name","District","Population"));
        // Loop over all countries in the list
        for (Countries cou : countries)
        {
            String c_string =
                    String.format("%-30s %-30s %-50s %-30s",
                            cou.name,cou.cityname,cou.district,cou.population);
            System.out.println(c_string);
        }
    }
    /**
     * 11. All the cities in a district organised by largest population to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<Cities> getAllcitiesDistrict(String input_district)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 11: All the cities in a district organised by largest population to smallest.
            String strQueryEight =
                    "SELECT city.Name as 'Cityname', country.Name as 'Countryname', city.District as 'District', city.Population FROM country INNER JOIN city WHERE city.District='"+input_district+"' ORDER BY city.Population DESC;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryEight);
            // Extract continent information
            ArrayList<Cities> district = new ArrayList<Cities>();
            while (rset.next())
            {
                Cities dist = new Cities();
                dist.cit_name = rset.getString("Cityname");
                dist.countryname = rset.getString("Countryname");
                dist.district = rset.getString("District");
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
        // Print header
        System.out.println(String.format("%-30s %-50s %-50s %-30s","City Name","Country Name","District","Population"));
        // Loop over all countries in the list
        for (Cities di : district)
        {
            String d_string =
                    String.format("%-30s %-50s %-50s %-30s",
                            di.cit_name,di.countryname,di.district,di.cit_population);
            System.out.println(d_string);
        }
    }
    /**
     * 12. The top N populated cities in the world where N is provided by the user.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<Cities> getTopNPopulatedcities(int input_world)
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
            // Extract continent information
            ArrayList<Cities> worlds = new ArrayList<Cities>();
            while (rset.next())
            {
                Cities world = new Cities();
                world.cit_name = rset.getString("Cityname");
                world.countryname = rset.getString("Countryname");
                world.district = rset.getString("District");
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

    /**
     * 12. The top N populated cities in the world where N is provided by the user.
     * Formatting the output data from the list.
     **/
    public void printWorlds(ArrayList<Cities> wld)
    {
        // Print header
        System.out.println(String.format("%-30s %-50s %-50s %-30s","City Name","Country Name","District","Population"));
        // Loop over all countries in the list
        for (Cities w : wld)
        {
            String w_string =
                    String.format("%-30s %-50s %-50s %-30s",
                            w.cit_name,w.countryname,w.district,w.cit_population);
            System.out.println(w_string);
        }
    }
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();
        // Connect to database
        a.connect();
        System.out.println("\n");

//        System.out.println("7: All the cities in the world organised by largest population to smallest.\n");
//        ArrayList<Cities> cities = a.getAllCities();
//        a.printCities(cities);
//        System.out.println("\n");

        // System.out.println("8: All the cities in a continent organised by largest population to smallest.\n");
        // ArrayList<Continent> continent = a.getAllcitiesContinent("Asia");
        // a.printContinents(continent);
        // System.out.println("\n");

       // System.out.println("9: All the cities in a region organised by largest population to smallest.\n");
       // ArrayList<Regions> regions = a.getAllcitiesRegions("Caribbean");
       // a.printRegions(regions);
       // System.out.println("\n");

       // System.out.println("10: All the cities in a country organised by largest population to smallest.\n");
       // ArrayList<Countries> countries = a.getAllcitiesCountries("Myanmar");
       // a.printCountries(countries);
       // System.out.println("\n");

       // System.out.println("11: All the cities in a district organised by largest population to smallest.\n");
       // ArrayList<Cities> dist = a.getAllcitiesDistrict("Queensland");
       // a.printDistrict(dist);
       // System.out.println("\n");


        System.out.println("12: The top N populated cities in the world where N is provided by the user.\n");
        ArrayList<Cities> wor = a.getTopNPopulatedcities(10);
        a.printWorlds(wor);
        System.out.println("\n");
            // Disconnect from database
        a.disconnect();
    }
}