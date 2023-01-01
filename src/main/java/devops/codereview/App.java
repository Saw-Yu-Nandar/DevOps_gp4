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
                ctr.setCountryCode(rset.getString("Code"));
                ctr.setCountryName(rset.getString("CountryName"));
                ctr.setCountryCont(rset.getString("Continent"));
                ctr.setCountryReg(rset.getString("Region"));
                ctr.setCountryPopulation(rset.getString("Population"));
                ctr.setCountryCap(rset.getString("CityName"));
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
            String allctrString =
                    String.format("%-10s %-30s %-30s %-30s %-30s %-20s",
                            c.getCountryCode(),c.getCountryName(),c.getCountryCont(),c.getCountryReg(),c.getCountryPopulation(),c.getCountryCap());
            System.out.println(allctrString);
        }
    }

    /**
     * 2. All the countries in a continent organised by largest population to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<Country> getAllContinents(String inputContinent)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 2: All the countries in a continent organised by largest population to smallest.
            String strQueryTwo =
                    "SELECT country.Code, country.Name as 'CountryName', country.Continent, country.Region, city.Name as 'CityName', country.Population FROM country INNER JOIN city WHERE country.Code = city.CountryCode AND country.Capital=city.ID AND country.Continent = '"+inputContinent+"' ORDER BY country.Population DESC;";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryTwo);
            // Extract continent information
            ArrayList<Country> continent = new ArrayList<Country>();
            while (rset.next())
            {
                Country contnt = new Country();
                contnt.setCountryCode(rset.getString("Code"));
                contnt.setCountryName(rset.getString("CountryName"));
                contnt.setCountryCont(rset.getString("Continent"));
                contnt.setCountryReg(rset.getString("Region"));
                contnt.setCountryPopulation(rset.getString("Population"));
                contnt.setCountryCap(rset.getString("CityName"));
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
            String contString =
                    String.format("%-10s %-30s %-30s %-30s %-30s %-20s",
                            cont.getCountryCode(),cont.getCountryName(),cont.getCountryCont(),cont.getCountryReg(),cont.getCountryPopulation(),cont.getCountryCap());
            System.out.println(contString);
        }
    }

    /**
     * 3. All the countries in a region organised by largest population to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<Country> getAllRegion(String inputRegion)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 3: All the countries in a region organised by largest population to smallest.
            String strQueryThree =
                    "SELECT country.Code, country.Name as 'CountryName', country.Continent, country.Region, city.Name as 'CityName', country.Population FROM country INNER JOIN city WHERE country.Code = city.CountryCode AND country.Capital=city.ID AND country.Region = '"+inputRegion+"' ORDER BY country.Population DESC;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryThree);
            // Extract region information
            ArrayList<Country> regions = new ArrayList<Country>();
            while (rset.next())
            {
                Country reg           = new Country();
                reg.setCountryCode(rset.getString("Code"));
                reg.setCountryName(rset.getString("CountryName"));
                reg.setCountryCont(rset.getString("Continent"));
                reg.setCountryReg(rset.getString("Region"));
                reg.setCountryPopulation(rset.getString("Population"));
                reg.setCountryCap(rset.getString("CityName"));
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
            String regString =
                    String.format("%-10s %-30s %-30s %-30s %-30s %-20s",
                            r.getCountryCode(),r.getCountryName(),r.getCountryCont(),r.getCountryReg(),r.getCountryPopulation(),r.getCountryCap());
            System.out.println(regString);
        }
    }

    /**
     * 4. The top N populated countries in the world where N is provided by the user.
     * Query execution by user input and pass the array list to format the return value.
     * Function is called in ma
     **/
    public ArrayList<Country> getAllNPopulatedCountries(int inputLimit)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 4: The top N populated countries in the world where N is provided by the user.
            String strQueryFour =
                    "SELECT country.Code, country.Name as 'CountryName', country.Continent, country.Region, city.Name as 'CityName',country.Population FROM country INNER JOIN city WHERE country.Code = city.CountryCode AND country.Capital=city.ID ORDER BY country.Population DESC LIMIT "+inputLimit+";";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryFour);
            // Extract countries information
            ArrayList<Country> nPopulatedCountry = new ArrayList<Country>();
            while (rset.next())
            {
                Country npopctr = new Country();
                npopctr.setCountryCode(rset.getString("Code"));
                npopctr.setCountryName(rset.getString("CountryName"));
                npopctr.setCountryCont(rset.getString("Continent"));
                npopctr.setCountryReg(rset.getString("Region"));
                npopctr.setCountryPopulation(rset.getString("Population"));
                npopctr.setCountryCap(rset.getString("CityName"));
                nPopulatedCountry.add(npopctr);
            }
            return nPopulatedCountry;
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
    public void printNPopulatedCountries(ArrayList<Country> nPopulatedCountry)
    {
        // Check npopulatedcountries is not null
        if (nPopulatedCountry == null)
        {
            System.out.println("No region");
            return;
        }
        // Print header
        System.out.println(String.format("%-10s %-30s %-30s %-30s %-30s %-20s", "Code", "Country Name", "Continent", "Region", "Population", "Capital"));
        // Loop over all countries in the list
        for (Country npopc : nPopulatedCountry)
        {
            //printcontinent to check if an Continent is null
            if (npopc == null)
                continue;
            String npopctrString =
                    String.format("%-10s %-30s %-30s %-30s %-30s %-20s",
                            npopc.getCountryCode(), npopc.getCountryName(), npopc.getCountryCont(), npopc.getCountryReg(), npopc.getCountryPopulation(), npopc.getCountryCap());
            System.out.println(npopctrString);
        }
    }

    /**
     * 5. The top N populated countries in a continent where N is provided by the user.
     * Query execution by user input and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<Country> getAllNPopulatedContinents(String inputTopCon, int inputLimit)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 5: The top N populated countries in a continent where N is provided by the user.
            String strQueryFive =
                    "SELECT country.Code, country.Name as 'CountryName', country.Continent, country.Region, city.Name as 'CityName', country.Population FROM country INNER JOIN city WHERE country.Code = city.CountryCode AND country.Capital=city.ID AND country.Continent = '"+ inputTopCon +"' ORDER BY country.Population DESC LIMIT "+ inputLimit +";";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryFive);
            // Extract continent information
            ArrayList<Country> nPopulatedContinents = new ArrayList<Country>();
            while (rset.next())
            {
                Country npopcont = new Country();
                npopcont.setCountryCode(rset.getString("Code"));
                npopcont.setCountryName(rset.getString("CountryName"));
                npopcont.setCountryCont(rset.getString("Continent"));
                npopcont.setCountryReg(rset.getString("Region"));
                npopcont.setCountryPopulation(rset.getString("Population"));
                npopcont.setCountryCap(rset.getString("CityName"));
                nPopulatedContinents.add(npopcont);
            }
            return nPopulatedContinents;
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
    public void printNPopulatedContinents(ArrayList<Country> nPopulatedContinents)
    {
        // Check npopulatedcontinent is not null
        if (nPopulatedContinents == null)
        {
            System.out.println("No region");
            return;
        }
        // Print header
        System.out.println(String.format("%-10s %-30s %-30s %-30s %-30s %-20s","Code","Country Name","Continent","Region","Population","Capital"));
        // Loop over all continent in the list
        for (Country npopconti : nPopulatedContinents)
        {
            //printRegion to check if an Continent is null
            if (npopconti == null)
                continue;
            String npopcontString =
                    String.format("%-10s %-30s %-30s %-30s %-30s %-20s",
                            npopconti.getCountryCode(),npopconti.getCountryName(),npopconti.getCountryCont(),npopconti.getCountryReg(),npopconti.getCountryPopulation(),npopconti.getCountryCap());
            System.out.println(npopcontString);
        }
    }

    /**
     * 6. The top N populated countries in a region where N is provided by the user.
     * Query execution by user input and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<Country> getAllNPopulatedRegion(String inputTopReg, int inputLimit)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 6: The top N populated countries in a region where N is provided by the user.
            String strQuerySix =
                    "SELECT country.Code, country.Name as 'CountryName', country.Continent, country.Region, city.Name as 'CityName', country.Population FROM country INNER JOIN city WHERE country.Code = city.CountryCode AND country.Capital=city.ID AND country.Region = '"+ inputTopReg +"' ORDER BY country.Population DESC LIMIT "+ inputLimit +";";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQuerySix);
            // Extract region information
            ArrayList<Country> nPopulatedRegion = new ArrayList<Country>();
            while (rset.next())
            {
                Country npopreg           = new Country();
                npopreg.setCountryCode(rset.getString("Code"));
                npopreg.setCountryName(rset.getString("CountryName"));
                npopreg.setCountryCont(rset.getString("Continent"));
                npopreg.setCountryReg(rset.getString("Region"));
                npopreg.setCountryPopulation(rset.getString("Population"));
                npopreg.setCountryCap(rset.getString("CityName"));
                nPopulatedRegion.add(npopreg);
            }
            return nPopulatedRegion;
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
    public void printNPopulatedRegion(ArrayList<Country> nPopulatedRegion)
    {
        // Check npopulatedregion is not null
        if (nPopulatedRegion == null)
        {
            System.out.println("No region");
            return;
        }
        // Print header
        System.out.println(String.format("%-10s %-30s %-30s %-30s %-30s %-20s","Code","Country Name","Continent","Region","Population","Capital"));
        // Loop over all region in the list
        for (Country npopreg : nPopulatedRegion)
        {
            //printNpopulatedRegion to check if an region is null
            if (npopreg == null)
                continue;
            String npopregString =
                    String.format("%-10s %-30s %-30s %-30s %-30s %-20s",
                            npopreg.getCountryCode(),npopreg.getCountryName(),npopreg.getCountryCont(),npopreg.getCountryReg(),npopreg.getCountryPopulation(),npopreg.getCountryCap());
            System.out.println(npopregString);
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
        ArrayList<Country> nPopulatedCountries = a.getAllNPopulatedCountries(10);
        a.printNPopulatedCountries(nPopulatedCountries);
        System.out.println("\n");

        // Display all the countries in a continent organised by largest population to smallest.
        System.out.println("5. The top 10 populated countries in the Europe.\n");
        ArrayList<Country> nPopulatedContinents = a.getAllNPopulatedContinents("Europe", 10);
        a.printContinent(nPopulatedContinents);
        System.out.println("\n");

        // Display the top N populated countries in a region where N is provided by the user.
        System.out.println("6: The top 10 populated countries in the Caribbean.\n");
        ArrayList<Country> nPopulatedRegion = a.getAllNPopulatedRegion("Caribbean", 10);
        a.printNPopulatedRegion(nPopulatedRegion);
        System.out.println("\n");

        // Disconnect from database
        a.disconnect();
    }
}