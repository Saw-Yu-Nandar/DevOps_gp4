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
                ctr.setCountry_code(rset.getString("Code"));
                ctr.setCountry_name(rset.getString("CountryName"));
                ctr.setCountry_cont(rset.getString("Continent"));
                ctr.setCountry_reg(rset.getString("Region"));
                ctr.setCountry_population(rset.getString("Population"));
                ctr.setCountry_cap(rset.getString("CityName"));
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
                            c.getCountry_code(),c.getCountry_name(),c.getCountry_cont(),c.getCountry_reg(),c.getCountry_population(),c.getCountry_cap());
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
                contnt.setCountry_code(rset.getString("Code"));
                contnt.setCountry_name(rset.getString("CountryName"));
                contnt.setCountry_cont(rset.getString("Continent"));
                contnt.setCountry_reg(rset.getString("Region"));
                contnt.setCountry_population(rset.getString("Population"));
                contnt.setCountry_cap(rset.getString("CityName"));
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
                            cont.getCountry_code(),cont.getCountry_name(),cont.getCountry_cont(),cont.getCountry_reg(),cont.getCountry_population(),cont.getCountry_cap());
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
                reg.setCountry_code(rset.getString("Code"));
                reg.setCountry_name(rset.getString("CountryName"));
                reg.setCountry_cont(rset.getString("Continent"));
                reg.setCountry_reg(rset.getString("Region"));
                reg.setCountry_population(rset.getString("Population"));
                reg.setCountry_cap(rset.getString("CityName"));
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
                            r.getCountry_code(),r.getCountry_name(),r.getCountry_cont(),r.getCountry_reg(),r.getCountry_population(),r.getCountry_cap());
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
                npopctr.setCountry_code(rset.getString("Code"));
                npopctr.setCountry_name(rset.getString("CountryName"));
                npopctr.setCountry_cont(rset.getString("Continent"));
                npopctr.setCountry_reg(rset.getString("Region"));
                npopctr.setCountry_population(rset.getString("Population"));
                npopctr.setCountry_cap(rset.getString("CityName"));
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
                            npopc.getCountry_code(), npopc.getCountry_name(), npopc.getCountry_cont(), npopc.getCountry_reg(), npopc.getCountry_population(), npopc.getCountry_cap());
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
                npopcont.setCountry_code(rset.getString("Code"));
                npopcont.setCountry_name(rset.getString("CountryName"));
                npopcont.setCountry_cont(rset.getString("Continent"));
                npopcont.setCountry_reg(rset.getString("Region"));
                npopcont.setCountry_population(rset.getString("Population"));
                npopcont.setCountry_cap(rset.getString("CityName"));
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
                            npopconti.getCountry_code(),npopconti.getCountry_name(),npopconti.getCountry_cont(),npopconti.getCountry_reg(),npopconti.getCountry_population(),npopconti.getCountry_cap());
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
                npopreg.setCountry_code(rset.getString("Code"));
                npopreg.setCountry_name(rset.getString("CountryName"));
                npopreg.setCountry_cont(rset.getString("Continent"));
                npopreg.setCountry_reg(rset.getString("Region"));
                npopreg.setCountry_population(rset.getString("Population"));
                npopreg.setCountry_cap(rset.getString("CityName"));
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
                            npopreg.getCountry_code(),npopreg.getCountry_name(),npopreg.getCountry_cont(),npopreg.getCountry_reg(),npopreg.getCountry_population(),npopreg.getCountry_cap());
            System.out.println(npopreg_string);
        }
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

        // Disconnect from database
        a.disconnect();
    }
}