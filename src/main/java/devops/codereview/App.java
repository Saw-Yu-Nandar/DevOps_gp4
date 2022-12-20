package devops.codereview;

import javax.swing.plaf.synth.Region;
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
     * 1. All the countries in the world organised by largest population to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<Countries> getAllCountries()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 1: All the countries in the world organised by largest population to smallest.
            String strQueryOne =
                    "SELECT country.Name, country.Population FROM country ORDER BY country.Population DESC;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryOne);
            // Extract employee information
            ArrayList<Countries> countries = new ArrayList<Countries>();
            while (rset.next())
            {
                Countries ctr = new Countries();
                ctr.name = rset.getString("Name");
                ctr.population = rset.getString("Population");
                countries.add(ctr);
            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get salary details");
            return null;
        }
    }

    /**
     * 1. All the countries in the world organised by largest population to smallest.
     * Formatting the output data from the list.
     **/
    public void printCountries(ArrayList<Countries> countries)
    {
        // Print header
        System.out.println(String.format("%-70s %-70s", "Name", "Population"));
        // Loop over all employees in the list
        for (Countries ctr : countries)
        {
            String ctr_string =
                    String.format("%-70s %-70s",
                            ctr.name, ctr.population);
            System.out.println(ctr_string);
        }
    }

    /**
     * 2. All the countries in a continent organised by largest population to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<Continent> getAllContinents()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 2: All the countries in a continent organised by largest population to smallest.
            String strQueryTwo =
                    "SELECT country.Continent, country.Population FROM country ORDER BY country.Population DESC;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryTwo);
            // Extract employee information
            ArrayList<Continent> continent = new ArrayList<Continent>();
            while (rset.next())
            {
                Continent contnt = new Continent();
                contnt.continent = rset.getString("Continent");
                contnt.population = rset.getString("Population");
                continent.add(contnt);
            }
            return continent;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get salary details");
            return null;
        }
    }

    /**
     * 2. All the countries in a continent organised by largest population to smallest.
     * Formatting the output data from the list.
     **/
    public void printContinent(ArrayList<Continent> continent)
    {
        // Print header
        System.out.println(String.format("%-70s %-70s", "Continent", "Population"));
        // Loop over all employees in the list
        for (Continent cont : continent)
        {
            String ctr_string =
                    String.format("%-70s %-70s",
                            cont.continent, cont.population);
            System.out.println(ctr_string);
        }
    }

    /**
     * 3. All the countries in a region organised by largest population to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<Regions> getAllRegion()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 3: All the countries in a region organised by largest population to smallest.
            String strQueryThree =
                    "SELECT country.Region, country.Population FROM country ORDER BY country.Population DESC;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryThree);
            // Extract employee information
            ArrayList<Regions> regions = new ArrayList<Regions>();
            while (rset.next())
            {
                Regions reg = new Regions();
                reg.region = rset.getString("Region");
                reg.population = rset.getString("Population");
                regions.add(reg);
            }
            return regions;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get salary details");
            return null;
        }
    }

    /**
     * 3. All the countries in a region organised by largest population to smallest.
     * Formatting the output data from the list.
     **/
    public void printRegion(ArrayList<Regions> region)
    {
        // Print header
        System.out.println(String.format("%-70s %-70s", "Region", "Population"));
        // Loop over all employees in the list
        for (Regions r : region)
        {
            String reg_string =
                    String.format("%-70s %-70s",
                            r.region, r.population);
            System.out.println(reg_string);
        }
    }

    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();
        // Connect to database
        a.connect();
        System.out.println("\n");

        // Display countries
        System.out.println("1: All the countries in the world organised by largest population to smallest.\n");
        ArrayList<Countries> countries = a.getAllCountries();
        a.printCountries(countries);
        System.out.println("\n");

        // Display continent
        System.out.println("2: All the countries in a continent organised by largest population to smallest.\n");
        ArrayList<Continent> continent = a.getAllContinents();
        a.printContinent(continent);
        System.out.println("\n");

        // Display region
        System.out.println("3: All the countries in a region organised by largest population to smallest.\n");
        ArrayList<Regions> region = a.getAllRegion();
        a.printRegion(region);
        System.out.println("\n");

        // Disconnect from database
        a.disconnect();
    }
}