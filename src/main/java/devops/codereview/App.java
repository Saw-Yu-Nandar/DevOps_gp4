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
                    "SELECT country.Name, country.Population FROM country ORDER BY country.Population DESC;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryOne);
            // Extract countries information
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
        // Loop over all countries in the list
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
            // Extract continent information
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
        // Loop over all continent in the list
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
            // Extract region information
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
        // Loop over all region in the list
        for (Regions r : region)
        {
            String reg_string =
                    String.format("%-70s %-70s",
                            r.region, r.population);
            System.out.println(reg_string);
        }
    }



    /**
     * 4. The top N populated countries in the world where N is provided by the user.
     * Query execution by user input and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<NPopulatedCountries> getAllNPopulatedCountries(int input_num)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 4: The top N populated countries in the world where N is provided by the user.
            String strQueryFour =
                    "SELECT country.Name, country.Population FROM country ORDER BY country.Population DESC LIMIT "+input_num;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryFour);
            // Extract countries information
            ArrayList<NPopulatedCountries> NPopulatedCountries = new ArrayList<NPopulatedCountries>();
            while (rset.next())
            {
                NPopulatedCountries npopctr = new NPopulatedCountries();
                npopctr.name = rset.getString("Name");
                npopctr.population = rset.getString("Population");
                NPopulatedCountries.add(npopctr);
            }
            return NPopulatedCountries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get salary details");
            return null;
        }
    }

    /**
     * 4. The top N populated countries in the world where N is provided by the user.
     * Formatting the output data from the list.
     **/
    public void printNPopulatedCountries(ArrayList<NPopulatedCountries> NPopulatedCountries)
    {
        // Print header
        System.out.println(String.format("%-70s %-70s", "Countries Name", "Population"));
        // Loop over all countries in the list
        for (NPopulatedCountries npopctr : NPopulatedCountries)
        {
            String npopctr_string =
                    String.format("%-70s %-70s",
                            npopctr.name, npopctr.population);
            System.out.println(npopctr_string);
        }
    }



    /**
     * 5. The top N populated countries in a continent where N is provided by the user.
     * Query execution by user input and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<NPopulatedContinents> getAllNPopulatedContinents(int input_num)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 5: The top N populated countries in a continent where N is provided by the user.
            String strQueryFive =
                    "SELECT country.Continent, country.Population FROM country ORDER BY country.Population DESC LIMIT "+input_num;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryFive);
            // Extract continent information
            ArrayList<NPopulatedContinents> NPopulated_Continents = new ArrayList<NPopulatedContinents>();
            while (rset.next())
            {
                NPopulatedContinents npopcont = new NPopulatedContinents();
                npopcont.cont_name = rset.getString("Continent");
                npopcont.cont_population = rset.getString("Population");
                NPopulated_Continents.add(npopcont);
            }
            return NPopulated_Continents;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get salary details");
            return null;
        }
    }
    /**
     * 5. The top N populated countries in a continent where N is provided by the user.
     * Query execution by user input and pass the array list to format the return value.
     * Function is called in main.
     **/
    public void printNPopulatedContinents(ArrayList<NPopulatedContinents> NPopulatedContinents)
    {
        // Print header
        System.out.println(String.format("%-70s %-70s", "Continent Name", "Population"));
        // Loop over all continent in the list
        for (NPopulatedContinents npopcont : NPopulatedContinents)
        {
            String npopcont_string =
                    String.format("%-70s %-70s",
                            npopcont.cont_name, npopcont.cont_population);
            System.out.println(npopcont_string);
        }
    }

    /**
     * 6. The top N populated countries in a region where N is provided by the user.
     * Query execution by user input and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<NPopulatedRegion> getAllNPopulatedRegion(int input_num)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 6: The top N populated countries in a region where N is provided by the user.
            String strQuerySix =
                    "SELECT country.Region, country.Population FROM country ORDER BY country.Population DESC LIMIT "+input_num;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQuerySix);
            // Extract region information
            ArrayList<NPopulatedRegion> NPopulated_Region = new ArrayList<NPopulatedRegion>();
            while (rset.next())
            {
                NPopulatedRegion npopreg = new NPopulatedRegion();
                npopreg.reg_name = rset.getString("Region");
                npopreg.reg_population = rset.getString("Population");
                NPopulated_Region.add(npopreg);
            }
            return NPopulated_Region;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get salary details");
            return null;
        }
    }
    /**
     * 6. The top N populated countries in a region where N is provided by the user
     * Query execution by user input and pass the array list to format the return value.
     * Function is called in main.
     **/
    public void printNPopulatedRegion(ArrayList<NPopulatedRegion> NPopulatedRegion)
    {
        // Print header
        System.out.println(String.format("%-70s %-70s", "Region Name", "Population"));
        // Loop over all region in the list
        for (NPopulatedRegion npopreg : NPopulatedRegion)
        {
            String npopreg_string =
                    String.format("%-70s %-70s",
                            npopreg.reg_name, npopreg.reg_population);
            System.out.println(npopreg_string);
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
    public ArrayList<Continent> getAllContinent()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 8: All the cities in a continent organised by largest population to smallest.
            String strQueryEight =
                    "SELECT city.Name, country.Continent, country.Population FROM city INNER JOIN country on city.Population = country.Population ORDER BY city.Population DESC;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryEight);
            // Extract continent information
            ArrayList<Continent> continent = new ArrayList<Continent>();
            while (rset.next())
            {
                Continent conti = new Continent();
                conti.continent = rset.getString("Continent");
                conti.population = rset.getString("Population");
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
        System.out.println(String.format("%-70s %-70s", "Name", "Population"));
        // Loop over all countries in the list
        for (Continent conti : continent)
        {
            String conti_string =
                    String.format("%-70s %-70s",
                            conti.continent, conti.population);
            System.out.println(conti_string);
        }
    }
    /**
     * 9. All the cities in a region organised by largest population to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<Regions> getALLRegions()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 9: All the cities in a region organised by largest population to smallest.
            String strQueryEight =
                    "SELECT city.Name, country.Region, country.Population FROM city INNER JOIN country on city.Population = country.Population ORDER BY city.Population DESC;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryEight);
            // Extract continent information
            ArrayList<Regions> reg = new ArrayList<Regions>();
            while (rset.next())
            {
                Regions regs = new Regions();
                regs.region = rset.getString("Region");
                regs.region = rset.getString("Population");
                reg.add(regs);
            }
            return reg;
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
        System.out.println(String.format("%-70s %-70s", "Name", "Population"));
        // Loop over all countries in the list
        for (Regions regs : regions)
        {
            String reg_string =
                    String.format("%-70s %-70s",
                            regs.region,regs.population);
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
        //System.out.println("1: All the countries in the world organised by largest population to smallest.\n");
        //ArrayList<Countries> countries = a.getAllCountries();
        //a.printCountries(countries);
        //System.out.println("\n");

        // Display continent
        //System.out.println("2: All the countries in a continent organised by largest population to smallest.\n");
        //ArrayList<Continent> continent = a.getAllContinents();
        //a.printContinent(continent);
        //System.out.println("\n");

        // Display region
        //System.out.println("3: All the countries in a region organised by largest population to smallest.\n");
        //ArrayList<Regions> region = a.getAllRegion();
        //a.printRegion(region);
        //System.out.println("\n");

        // Top N countries by user input
        //System.out.println("4: The top N populated countries in the world where N is provided by the user.\n");
        //Request input from user
        //Scanner sc = new Scanner(System.in);
        //System.out.println("Enter number: ");
        //int usr_input = sc.nextInt();
        //ArrayList<NPopulatedCountries> npopctr = a.getAllNPopulatedCountries(10);
        //a.printNPopulatedCountries(npopctr);
        //System.out.println("\n");

        // Top N countries in continent by user input
//        System.out.println("5.The top N populated countries in a continent where N is provided by the user..\n");
//        ArrayList<NPopulatedContinents> npopcont = a.getAllNPopulatedContinents(10);
//        a.printNPopulatedContinents(npopcont);
//        System.out.println("\n");

        // Top N countries in continent by user input
//        System.out.println("6.The top N populated countries in a region where N is provided by the user..\n");
//        ArrayList<NPopulatedRegion> npopreg = a.getAllNPopulatedRegion(10);
//        a.printNPopulatedRegion(npopreg);
//        System.out.println("\n");

//        System.out.println("7: All the cities in the world organised by largest population to smallest.\n");
//        ArrayList<Cities> cities = a.getAllCities();
//        a.printCities(cities);
//        System.out.println("\n");

       // System.out.println("8: All the cities in a continent organised by largest population to smallest.\n");
       // ArrayList<Continent> continent = a.getAllContinent();
       // a.printContinent(continent);
       // System.out.println("\n");


        System.out.println("9: All the cities in a region organised by largest population to smallest.\n");
        ArrayList<Regions> regions = a.getAllRegion();
        a.printRegions(regions);
        System.out.println("\n");


        // Disconnect from database
        a.disconnect();
    }
}