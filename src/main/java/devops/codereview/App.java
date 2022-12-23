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
                    "SELECT country.Code, country.Name as 'CountryName', country.Continent, country.Region, city.Name as 'CityName', country.Population  FROM country INNER JOIN city WHERE country.Code = city.CountryCode ORDER BY country.Population DESC;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryOne);
            // Extract countries information
            ArrayList<Countries> countries = new ArrayList<Countries>();
            while (rset.next())
            {
                Countries ctr = new Countries();
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
    public void printAllCountries(ArrayList<Countries> countries)
    {
        // Print header
        System.out.println(String.format("%-20s %-50s %-50s %-50s %-50s %-30s", "Code","Name","Continent","Region","Population","Capital"));
        // Loop over all countries in the list
        for (Countries c : countries)
        {
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
    public ArrayList<Countries> getAllContinents(String input_continent)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 2: All the countries in a continent organised by largest population to smallest.
            String strQueryTwo =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name FROM country INNER JOIN city WHERE country.Code = city.CountryCode AND country.Continent = '"+input_continent+"' ORDER BY country.Population DESC;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryTwo);
            // Extract continent information
            ArrayList<Countries> continent = new ArrayList<Countries>();
            while (rset.next())
            {
                Countries contnt = new Countries();
                contnt.country_code         = rset.getString("Code");
                contnt.country_name         = rset.getString("Name");
                contnt.country_cont         = rset.getString("Continent");
                contnt.country_reg          = rset.getString("Region");
                contnt.country_population   = rset.getString("Population");
                contnt.country_cap          = rset.getString("Capital");
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
     * Formatting the output data from the list.
     **/
    public void printContinent(ArrayList<Countries> continent)
    {
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s %-30s %-30s %-30s","Code","Country Name","Continent","Region","Population","Capital"));
        // Loop over all continent in the list
        for (Countries cont : continent)
        {
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
    public ArrayList<Countries> getAllRegion(String input_region)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 3: All the countries in a region organised by largest population to smallest.
            String strQueryThree =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name FROM country INNER JOIN city WHERE country.Code = city.CountryCode AND country.Region = '"+input_region+"' ORDER BY country.Population DESC;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryThree);
            // Extract region information
            ArrayList<Countries> regions = new ArrayList<Countries>();
            while (rset.next())
            {
                Countries reg           = new Countries();
                reg.country_code        = rset.getString("Code");
                reg.country_name        = rset.getString("Name");
                reg.country_cont        = rset.getString("Continent");
                reg.country_reg         = rset.getString("Region");
                reg.country_population  = rset.getString("Population");
                reg.country_cap         = rset.getString("Capital");
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
    public void printRegion(ArrayList<Countries> region)
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
        for (Countries r : region)
        {
            //printRegion to check if an Continent is null
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
     * Function is called in main.
     **/
    public ArrayList<Countries> getAllNPopulatedCountries(int input_limit)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 4: The top N populated countries in the world where N is provided by the user.
            String strQueryFour =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name FROM country INNER JOIN city WHERE country.Code = city.CountryCode ORDER BY country.Population DESC LIMIT "+input_limit+";";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryFour);
            // Extract countries information
            ArrayList<Countries> NPopulatedCountries = new ArrayList<Countries>();
            while (rset.next())
            {
                Countries npopctr = new Countries();
                npopctr.country_code        = rset.getString("Code");
                npopctr.country_name        = rset.getString("Name");
                npopctr.country_cont        = rset.getString("Continent");
                npopctr.country_reg         = rset.getString("Region");
                npopctr.country_population  = rset.getString("Population");
                npopctr.country_cap         = rset.getString("Capital");
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
    public void printNPopulatedCountries(ArrayList<Countries> NPopulatedC) {
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s %-30s %-30s %-30s", "Code", "Country Name", "Continent", "Region", "Population", "Capital"));
        // Loop over all countries in the list
        for (Countries npopc : NPopulatedC) {
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
    public ArrayList<Countries> getAllNPopulatedContinents(String input_topCon,int input_numb)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 5: The top N populated countries in a continent where N is provided by the user.
            String strQueryFive =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name FROM country INNER JOIN city WHERE country.Code = city.CountryCode AND country.Continent = '"+ input_topCon+"' ORDER BY country.Population DESC LIMIT "+input_numb+";";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryFive);
            // Extract continent information
            ArrayList<Countries> NPopulated_Continents = new ArrayList<Countries>();
            while (rset.next())
            {
                Countries npopcont = new Countries();
                npopcont.country_code       = rset.getString("Code");
                npopcont.country_name       = rset.getString("Name");
                npopcont.country_cont       = rset.getString("Continent");
                npopcont.country_reg        = rset.getString("Region");
                npopcont.country_population = rset.getString("Population");
                npopcont.country_cap        = rset.getString("Capital");
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
    public void printNPopulatedContinents(ArrayList<Countries> NPopulatedContinents)
    {
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s %-30s %-30s %-30s","Code","Country Name","Continent","Region","Population","Capital"));
        // Loop over all continent in the list
        for (Countries npopcont : NPopulatedContinents)
        {
            String npopcont_string =
                    String.format("%-30s %-30s %-30s %-30s %-30s %-30s",
                            npopcont.country_code,npopcont.country_name,npopcont.country_cont,npopcont.country_reg,npopcont.country_population,npopcont.country_cap);
            System.out.println(npopcont_string);
        }
    }

    /**
     * 6. The top N populated countries in a region where N is provided by the user.
     * Query execution by user input and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<Countries> getAllNPopulatedRegion(String input_topReg, int input_num)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 6: The top N populated countries in a region where N is provided by the user.
            String strQuerySix =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name FROM country INNER JOIN city WHERE country.Code = city.CountryCode AND country.Region = '"+ input_topReg+"' ORDER BY country.Population DESC LIMIT "+input_num+";";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQuerySix);
            // Extract region information
            ArrayList<Countries> NPopulated_Region = new ArrayList<Countries>();
            while (rset.next())
            {
                Countries npopreg           = new Countries();
                npopreg.country_code        = rset.getString("Code");
                npopreg.country_name        = rset.getString("Name");
                npopreg.country_cont        = rset.getString("Continent");
                npopreg.country_reg         = rset.getString("Region");
                npopreg.country_population  = rset.getString("Population");
                npopreg.country_cap         = rset.getString("Capital");
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
     * 6. The top N populated countries in a region where N is provided by the user
     * Query execution by user input and pass the array list to format the return value.
     * Function is called in main.
     **/
    public void printNPopulatedRegion(ArrayList<Countries> NPopulatedRegion)
    {
        // Check NPopulatedContinents is not null
        if (NPopulatedRegion == null)
        {
            System.out.println("No populated region");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s %-30s %-30s %-30s","Code","Country Name","Continent","Region","Population","Capital"));
        // Loop over all region in the list
        for (Countries npopreg : NPopulatedRegion)
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
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();
        // Connect to database
        a.connect();
        System.out.println("\n");

        // Display all the countries in the world organised by largest population to smallest.
         System.out.println("1: All the countries in the world organised by largest population to smallest.\n");
         ArrayList<Countries> countries = a.getAllCountries();
         a.printAllCountries(countries);
         System.out.println("\n");

        // Display all the countries in a continent organised by largest population to smallest.
         System.out.println("2: All the countries in a continent organised by largest population to smallest.\n");
         ArrayList<Countries> continent = a.getAllContinents("Antarctica");
         a.printContinent(continent);
         System.out.println("\n");

        // Display all the countries in a region organised by largest population to smallest.
         System.out.println("3: All the countries in a region organised by largest population to smallest.\n");
         ArrayList<Countries> regions = a.getAllRegion("North Africa");
         a.printRegion(regions);
         System.out.println("\n");

        // Display the top N populated countries in the world where N is provided by the user.
         System.out.println("4: The top N populated countries in the world where N is provided by the user.\n");
         ArrayList<Countries> NPopulatedCountries = a.getAllNPopulatedCountries(10);
         a.printNPopulatedCountries(NPopulatedCountries);
         System.out.println("\n");

        // Display all the countries in a continent organised by largest population to smallest.
         System.out.println("5. The top N populated countries in a continent where N is provided by the user.\n");
         ArrayList<Countries> NPopulated_Continents = a.getAllNPopulatedContinents("Asia", 10);
         a.printContinent(NPopulated_Continents);
         System.out.println("\n");

        // Display the top N populated countries in a region where N is provided by the user.
         System.out.println("6: the top N populated countries in a region where N is provided by the user.\n");
         ArrayList<Countries> NPopulated_Region = a.getAllNPopulatedRegion("Caribbean", 10);
         a.printNPopulatedRegion(NPopulated_Region);
         System.out.println("\n");

        // Disconnect from database
        a.disconnect();
    }
}