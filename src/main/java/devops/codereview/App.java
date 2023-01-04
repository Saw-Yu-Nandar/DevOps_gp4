package devops.codereview;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;

public class App {
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
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
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
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

    /** 1. All the countries in the world organised by largest population to smallest.
     * * Query execution and pass the array list to format the return value.
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
            System.out.println("No Countries");
            return;
        }
        // Print header
        System.out.println(String.format("%-10s %-40s %-30s %-30s %-30s %-20s", "Code","Name","Continent","Region","Population","Capital"));
        // Loop over all countries in the list
        for (Country c : countries)
        {
            if (c == null)
                continue;
            String allctrString =
                    String.format("%-10s %-40s %-30s %-30s %-30s %-20s",
                            c.getCountryCode(),c.getCountryName(),c.getCountryCont(),c.getCountryReg(),c.getCountryPopulation(),c.getCountryCap());
            System.out.println(allctrString);
        }
    }

    /**
     * Outputs to Markdown
     * 1. All the countries in the world organised by largest population to smallest.
     * @param countries
     */
    public void outputCountries(ArrayList<Country> countries, String AllCountries) {
        // Check employees is not null
        if (countries == null) {
            System.out.println("No country");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| Country Code | Country Name | Continent | Region | Population | Capital |\r\n");
        sb.append("| --- | --- | --- | --- | --- | --- |\r\n");
        // Loop over all employees in the list
        for (Country cou : countries) {
            if (cou == null) continue;
            sb.append("| " + cou.getCountryCode() + " | " +
                    cou.getCountryName() + " | " + cou.getCountryCont() + " | " +
                    cou.getCountryReg() + " | " + cou.getCountryPopulation() + " | "
                    + cou.getCountryCap() + "|\r\n");
        }
        try {
            new File("./reports/").mkdir();
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./reports/" + AllCountries)));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
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
     * Formatting the output data from the list.
     **/
    public void printContinent(ArrayList<Country> continent)
    {
        // Check continent is not null
        if (continent == null)
        {
            System.out.println("No continent");
            return;
        }
        // Print header
        System.out.println(String.format("%-10s %-40s %-30s %-30s %-30s %-30s","Code","Country Name","Continent","Region","Population","Capital"));
        // Loop over all continent in the list
        for (Country cont : continent)
        {
            if (cont == null)
                continue;
            String contString =
                    String.format("%-10s %-40s %-30s %-30s %-30s %-30s",
                            cont.getCountryCode(),cont.getCountryName(),cont.getCountryCont(),cont.getCountryReg(),cont.getCountryPopulation(),cont.getCountryCap());
            System.out.println(contString);
        }
    }
    /**
     * Outputs to Markdown
     * 2. All the countries in a continent organised by largest population to smallest.
     * @param continents
     */
    public void outputContinent(ArrayList<Country> continents, String AllContinents) {
        // Check employees is not null
        if (continents == null) {
            System.out.println("No continents");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| Country Code | Country Name | Continent | Region | Population | Capital |\r\n");
        sb.append("| --- | --- | --- | --- | --- | --- |\r\n");
        // Loop over all employees in the list
        for (Country cont : continents) {
            if (cont == null) continue;
            sb.append("| " + cont.getCountryCode() + " | " +
                    cont.getCountryName() + " | " + cont.getCountryCont() + " | " +
                    cont.getCountryReg() + " | " + cont.getCountryPopulation() + " | "
                    + cont.getCountryCap() + "|\r\n");
        }
        try {
            new File("./reports/").mkdir();
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./reports/" + AllContinents)));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
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
                    String.format("%-10s %-40s %-30s %-30s %-30s %-30s",
                            r.getCountryCode(),r.getCountryName(),r.getCountryCont(),r.getCountryReg(),r.getCountryPopulation(),r.getCountryCap());
            System.out.println(regString);
        }
    }

    /**
     * Outputs to Markdown
     * 3. All the countries in a region organised by largest population to smallest.
     * @param allregion
     */
    public void outputRegion(ArrayList<Country> allregion, String AllRegions) {
        // Check region is not null
        if (allregion == null)
        {
            System.out.println("No regions");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| Country Code | Country Name | Continent | Region | Population | Capital |\r\n");
        sb.append("| --- | --- | --- | --- | --- | --- |\r\n");
        // Loop over all employees in the list
        for (Country regs : allregion)
        {
            if (regs == null) continue;
            sb.append("| " + regs.getCountryCode() + " | " +
                    regs.getCountryName() + " | " + regs.getCountryCont() + " | " +
                    regs.getCountryReg() + " | " + regs.getCountryPopulation() + " | "
                    + regs.getCountryCap() + "|\r\n");
        }
        try
        {
            new File("./reports/").mkdir();
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./reports/" + AllRegions)));
            writer.write(sb.toString());
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
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
        System.out.println(String.format("%-10s %-40s %-30s %-30s %-30s %-30s", "Code", "Country Name", "Continent", "Region", "Population", "Capital"));
        // Loop over all countries in the list
        for (Country npopc : nPopulatedCountry)
        {
            //printcontinent to check if an Continent is null
            if (npopc == null)
                continue;
            String npopctrString =
                    String.format("%-10s %-40s %-30s %-30s %-30s %-30s",
                            npopc.getCountryCode(), npopc.getCountryName(), npopc.getCountryCont(), npopc.getCountryReg(), npopc.getCountryPopulation(), npopc.getCountryCap());
            System.out.println(npopctrString);
        }
    }

    /**
     * Outputs to Markdown
     * 4. The top N populated countries in the world where N is provided by the user.
     * @param ncountries
     */
    public void outputNCountries(ArrayList<Country> ncountries, String TopNCountries) {
        // Check ncountries is not null
        if (ncountries == null) {
            System.out.println("No N Countries");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| Country Code | Country Name | Continent | Region | Population | Capital |\r\n");
        sb.append("| --- | --- | --- | --- | --- | --- |\r\n");
        // Loop over all employees in the list
        for (Country ncont : ncountries) {
            if (ncont == null) continue;
            sb.append("| " + ncont.getCountryCode() + " | " +
                    ncont.getCountryName() + " | " + ncont.getCountryCont() + " | " +
                    ncont.getCountryReg() + " | " + ncont.getCountryPopulation() + " | "
                    + ncont.getCountryCap() + "|\r\n");
        }
        try {
            new File("./reports/").mkdir();
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./reports/" + TopNCountries)));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
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
        System.out.println(String.format("%-10s %-40s %-30s %-30s %-30s %-30s","Code","Country Name","Continent","Region","Population","Capital"));
        // Loop over all continent in the list
        for (Country npopconti : nPopulatedContinents)
        {
            //printRegion to check if an Continent is null
            if (npopconti == null)
                continue;
            String npopcontString =
                    String.format("%-10s %-40s %-30s %-30s %-30s %-30s",
                            npopconti.getCountryCode(),npopconti.getCountryName(),npopconti.getCountryCont(),npopconti.getCountryReg(),npopconti.getCountryPopulation(),npopconti.getCountryCap());
            System.out.println(npopcontString);
        }
    }

    /**
     * Outputs to Markdown
     * 5. The top N populated countries in a continent where N is provided by the user.
     * @param ncontinents
     */
    public void outputNContinents(ArrayList<Country> ncontinents, String TopNContinents) {
        // Check ncontinents is not null
        if (ncontinents == null) {
            System.out.println("No N Countries");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| Country Code | Country Name | Continent | Region | Population | Capital |\r\n");
        sb.append("| --- | --- | --- | --- | --- | --- |\r\n");
        // Loop over all employees in the list
        for (Country ncont : ncontinents) {
            if (ncont == null) continue;
            sb.append("| " + ncont.getCountryCode() + " | " +
                    ncont.getCountryName() + " | " + ncont.getCountryCont() + " | " +
                    ncont.getCountryReg() + " | " + ncont.getCountryPopulation() + " | "
                    + ncont.getCountryCap() + "|\r\n");
        }
        try {
            new File("./reports/").mkdir();
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./reports/" + TopNContinents)));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
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
        System.out.println(String.format("%-10s %-40s %-30s %-30s %-30s %-30s","Code","Country Name","Continent","Region","Population","Capital"));
        // Loop over all region in the list
        for (Country npopreg : nPopulatedRegion)
        {
            //printNpopulatedRegion to check if an region is null
            if (npopreg == null)
                continue;
            String npopregString =
                    String.format("%-10s %-40s %-30s %-30s %-30s %-30s",
                            npopreg.getCountryCode(),npopreg.getCountryName(),npopreg.getCountryCont(),npopreg.getCountryReg(),npopreg.getCountryPopulation(),npopreg.getCountryCap());
            System.out.println(npopregString);
        }
    }
    /**
     * Outputs to Markdown
     * 6. The top N populated countries in a region where N is provided by the user.
     * @param nregions
     */
    public void outputNRegions(ArrayList<Country> nregions, String TopNRegions) {
        // Check ncontinents is not null
        if (nregions == null) {
            System.out.println("No N Countries");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| Country Code | Country Name | Continent | Region | Population | Capital |\r\n");
        sb.append("| --- | --- | --- | --- | --- | --- |\r\n");
        // Loop over all employees in the list
        for (Country nreg : nregions) {
            if (nreg == null) continue;
            sb.append("| " + nreg.getCountryCode() + " | " +
                    nreg.getCountryName() + " | " + nreg.getCountryCont() + " | " +
                    nreg.getCountryReg() + " | " + nreg.getCountryPopulation() + " | "
                    + nreg.getCountryCap() + "|\r\n");
        }
        try {
            new File("./reports/").mkdir();
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./reports/" + TopNRegions)));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 7. All the cities in the world organised by largest population to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.**/

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
                cit.setCit_name(rset.getString("CityName"));
                cit.setCountry_name(rset.getString("CountryName"));
                cit.setCit_district(rset.getString("District"));
                cit.setCit_population(rset.getString("Population"));
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
        //System.out.println("7. All the cities in the world organised by largest population to smallest.");
        // Print header
        System.out.println(String.format("%-35s %-40s %-25s %-25s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (City cit : cities)
        {
            if (cit == null)
                continue;
            String cit_string =
                    String.format("%-35s %-40s %-25s %-25s",
                            cit.getCit_name(),cit.getCountry_name(),cit.getCit_district(),cit.getCit_population());
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
                conti.setCit_name(rset.getString("CityName"));
                conti.setCountry_name(rset.getString("CountryName"));
                conti.setCit_district(rset.getString("District"));
                conti.setCit_population(rset.getString("Population"));
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
        //System.out.println("8. All the cities in a continent organised by largest population to smallest.");
        // Print header
        System.out.println(String.format("%-35s %-40s %-25s %-25s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (City c : continent)
        {
            if (c == null)
                continue;
            String conti_string =
                    String.format("%-35s %-40s %-25s %-25s",
                            c.getCit_name(),c.getCountry_name(),c.getCit_district(),c.getCit_population());
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
                reg.setCit_name(rset.getString("CityName"));
                reg.setCountry_name(rset.getString("CountryName"));
                reg.setCit_district(rset.getString("District"));
                reg.setCit_population(rset.getString("Population"));
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
        //System.out.println("9. All the cities in a region organised by largest population to smallest.");
        // Print header
        System.out.println(String.format("%-35s %-40s %-25s %-25s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (City r : regions)
        {
            //printRegion to check if an region is null
            if (r == null)
                continue;
            String reg_string =
                    String.format("%-35s %-40s %-25s %-25s",
                            r.getCit_name(),r.getCountry_name(),r.getCit_district(),r.getCit_population());
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
                c1.setCit_name(rset.getString("CityName"));
                c1.setCountry_name(rset.getString("CountryName"));
                c1.setCit_district(rset.getString("District"));
                c1.setCit_population(rset.getString("Population"));
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
        //System.out.println("10. All the cities in a country organised by largest population to smallest.");
        // Print header
        System.out.println(String.format("%-35s %-40s %-25s %-25s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (City cou : countries)
        {
            if (cou == null)
                continue;
            String c_string =
                    String.format("%-35s %-40s %-25s %-25s",
                            cou.getCit_name(),cou.getCountry_name(),cou.getCit_district(),cou.getCit_population());
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
                dist.setCit_name(rset.getString("CityName"));
                dist.setCountry_name(rset.getString("CountryName"));
                dist.setCit_district(rset.getString("District"));
                dist.setCit_population(rset.getString("Population"));
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
        //System.out.println("11. All the cities in a district organised by largest population to smallest.");
        // Print header
        System.out.println(String.format("%-35s %-40s %-25s %-25s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (City di : district)
        {
            if (di == null)
                continue;
            String d_string =
                    String.format("%-35s %-40s %-25s %-25s",
                            di.getCit_name(),di.getCountry_name(),di.getCit_district(),di.getCit_population());
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
                world.setCit_name(rset.getString("Cityname"));
                world.setCountry_name(rset.getString("Countryname"));
                world.setCit_district(rset.getString("District"));
                world.setCit_population(rset.getString("Population"));
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
        //System.out.println("12. The top N populated cities in the world where N is provided by the user.");
        // Print header
        System.out.println(String.format("%-35s %-40s %-25s %-25s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (City w : wld)
        {
            if (w == null)
                continue;
            String w_string =
                    String.format("%-35s %-40s %-25s %-25s",
                            w.getCit_name(),w.getCountry_name(),w.getCit_district(),w.getCit_population());
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
                conti.setCit_name(rset.getString("Cityname"));
                conti.setCountry_name(rset.getString("Countryname"));
                conti.setCit_district(rset.getString("District"));
                conti.setCit_population(rset.getString("Population"));
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
        //System.out.println("13. The top N populated cities in a continent where N is provided by the user.");
        // Print header
        System.out.println(String.format("%-35s %-40s %-25s %-25s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (City cont : cnt)
        {
            if (cont == null)
                continue;
            String cont_string =
                    String.format("%-35s %-40s %-25s %-25s",
                            cont.getCit_name(),cont.getCountry_name(),cont.getCit_district(),cont.getCit_population());
            System.out.println(cont_string);
        }
    }

    /**
     * 14. The top N populated cities in a region where N is provided by the user.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<City> getTopNPopulatedRegion(String inputRegion, int inputLimit)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 14: The top N populated cities in a region where N is provided by the user.
            String strQueryEight =
                    "SELECT country.Region, city.Name as 'CityName', country.Name as 'CountryName', city.District, city.Population FROM city INNER JOIN country on city.CountryCode = country.Code WHERE country.Region = '"+ inputRegion +"' ORDER BY city.Population DESC LIMIT "+ inputLimit +";";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryEight);
            // Extract region information
            ArrayList<City> region = new ArrayList<City>();
            while (rset.next())
            {
                City reg          = new City();
                reg.setCit_name(rset.getString("Cityname"));
                reg.setCountry_name(rset.getString("Countryname"));
                reg.setCit_district(rset.getString("District"));
                reg.setCit_population(rset.getString("Population"));
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
        //System.out.println("14. The top N populated cities in a region where N is provided by the user.");
        // Print header
        System.out.println(String.format("%-35s %-40s %-25s %-25s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (City cont : regn)
        {
            if (cont == null)
                continue;
            String cont_string =
                    String.format("%-35s %-40s %-25s %-25s",
                            cont.getCit_name(),cont.getCountry_name(),cont.getCit_district(),cont.getCit_population());
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
                cty.setCit_name(rset.getString("Cityname"));
                cty.setCountry_name(rset.getString("Countryname"));
                cty.setCit_district(rset.getString("District"));
                cty.setCit_population(rset.getString("Population"));
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
        //System.out.println("15. The top N populated cities in a country where N is provided by the user.");
        // Print header
        System.out.println(String.format("%-35s %-40s %-25s %-30s","Country","City Name","District","Population"));
        // Loop over all cities in the list
        for (City cont : count)
        {
            if (cont == null)
                continue;
            String cont_string =
                    String.format("%-35s %-40s %-25s %-30s",
                            cont.getCit_name(),cont.getCountry_name(),cont.getCit_district(),cont.getCit_population());
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
                dis.setCit_name(rset.getString("Cityname"));
                dis.setCountry_name(rset.getString("Countryname"));
                dis.setCit_district(rset.getString("District"));
                dis.setCit_population(rset.getString("Population"));
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
        //System.out.println("16. The top N populated cities in a district where N is provided by the user.");
        // Print header
        System.out.println(String.format("%-30s %-25s %-25s %-30s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (City cont : dists)
        {
            if (cont == null)
                continue;
            String cont_string =
                    String.format("%-30s %-40s %-25s %-30s",
                            cont.getCit_name(),cont.getCountry_name(),cont.getCit_district(),cont.getCit_population());
            System.out.println(cont_string);
        }

    }

    /**
     * 17.All the capital cities in the world organised by largest population to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<CapitalCity> getAllCapitalCities()
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
            ArrayList<CapitalCity> allCapitalCity = new ArrayList<CapitalCity>();
            while (rset.next())
            {
                CapitalCity capcit = new CapitalCity();
                capcit.setCapCityName(rset.getString("CapitalCity"));
                capcit.setCapCityCountry(rset.getString("CountryName"));
                capcit.setCapCityPopulation(rset.getString("Population"));
                allCapitalCity.add(capcit);
            }
            return allCapitalCity;
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
    public void printAllCapitalCities(ArrayList<CapitalCity> capitalCities)
    {
        // Check countries is not null
        if (capitalCities == null)
        {
            System.out.println("No Capital Cities");
            return;
        }
        // Print header
        System.out.println(String.format("%-40s %-40s %-25s", "CapitalCity","Country","Population"));
        // Loop over all countries in the list
        for (CapitalCity cc : capitalCities)
        {
            if (cc == null)
                continue;

            String capctrString =
                    String.format("%-40s %-40s %-25s",
                            cc.getCapCityName(),cc.getCapCityCountry(),cc.getCapCityPopulation());
            System.out.println(capctrString);
        }
    }

    /**
     * 18.All the capital cities in a continent organised by largest population to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<CapitalCity> getAllCapitalCitiesContinents(String inputCont)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 18.All the capital cities in a continent organised by largest population to smallest.
            String strQueryEighteen =
                    "SELECT city.Name as 'CapitalCity', country.Name as 'CountryName', country.Continent, country.Population FROM city INNER JOIN country WHERE city.ID = country.Capital AND country.Code=city.CountryCode AND country.Continent = '"+ inputCont +"' ORDER BY country.Population DESC;";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryEighteen);
            // Extract continent information
            ArrayList<CapitalCity> capCitContinent = new ArrayList<CapitalCity>();
            while (rset.next())
            {
                CapitalCity capcitCont = new CapitalCity();
                capcitCont.setCapCityName(rset.getString("CapitalCity"));
                capcitCont.setCapCityCountry(rset.getString("CountryName"));
                capcitCont.setCapCityContinent(rset.getString("Continent"));
                capcitCont.setCapCityPopulation(rset.getString("Population"));
                capCitContinent.add(capcitCont);
            }
            return capCitContinent;
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
    public void printAllCapitalCityContinent(ArrayList<CapitalCity> capcitContinent)
    {
        // Check continent is not null
        if (capcitContinent == null)
        {
            System.out.println("No Capital City Continent");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-40s %-30s %-25s", "CapitalCity", "Country", "Continent", "Population"));
        // Loop over all continent in the list
        for (CapitalCity cccon : capcitContinent)
        {
            if (cccon == null)
                continue;
            String capconString =
                    String.format("%-30s %-40s %-30s %-25s",
                            cccon.getCapCityName(), cccon.getCapCityCountry(), cccon.getCapCityContinent(), cccon.getCapCityPopulation());
            System.out.println(capconString);
        }
    }

    /**
     * 19.All the capital cities in a region organised by largest to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<CapitalCity> getAllCapitalCitiesRegions(String inputRegi)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 19.All the capital cities in a region organised by largest to smallest.
            String strQueryNineteen =
                    "SELECT city.Name as 'CapitalCity', country.Name as 'CountryName', country.Region, country.Population FROM city INNER JOIN country WHERE city.ID = country.Capital AND country.Code=city.CountryCode AND country.Region = '"+ inputRegi +"' ORDER BY country.Population DESC;";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryNineteen);
            // Extract region information
            ArrayList<CapitalCity> capCitRegion = new ArrayList<CapitalCity>();
            while (rset.next())
            {
                CapitalCity capcitreg          = new CapitalCity();
                capcitreg.setCapCityName(rset.getString("CapitalCity"));
                capcitreg.setCapCityCountry(rset.getString("CountryName"));
                capcitreg.setCapCityRegion(rset.getString("Region"));
                capcitreg.setCapCityPopulation(rset.getString("Population"));
                capCitRegion.add(capcitreg);
            }
            return capCitRegion;
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
    public void printAllCapitalCityRegion(ArrayList<CapitalCity> capcitRegion)
    {
        // Check region is not null
        if (capcitRegion == null)
        {
            System.out.println("No CapitalCity region");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-40s %-30s %-25s", "CapitalCity","Country","Region", "Population"));
        // Loop over all region in the list
        for (CapitalCity ccr : capcitRegion)
        {
            //printRegion to check if an region is null
            if (ccr == null)
                continue;
            String regString =
                    String.format("%-30s %-40s %-30s %-25s",
                            ccr.getCapCityName(),ccr.getCapCityCountry(),ccr.getCapCityRegion(), ccr.getCapCityPopulation());
            System.out.println(regString);
        }
    }

    /**
     * 20.The top N populated capital cities in the world where N is provided by the user.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<CapitalCity> getTopNCapCitiesWorld(int inputLimit)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 20.The top N populated capital cities in the world where N is provided by the user.
            String strQueryTwenty =
                    "SELECT city.Name as 'CityName', country.Name as 'CountryName', country.Population FROM city INNER JOIN country WHERE city.ID = country.Capital AND country.Code=city.CountryCode ORDER BY country.Population DESC LIMIT "+ inputLimit +";";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryTwenty);
            // Extract region information
            ArrayList<CapitalCity> capWorld = new ArrayList<CapitalCity>();
            while (rset.next())
            {
                CapitalCity capWld = new CapitalCity();
                capWld.setCapCityName(rset.getString("CityName"));
                capWld.setCapCityCountry(rset.getString("CountryName"));
                capWld.setCapCityPopulation(rset.getString("Population"));
                capWorld.add(capWld);
            }
            return capWorld;
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
    public void printTopNCapCitiesWorld(ArrayList<CapitalCity> capWorld)
    {
        // Check region is not null
        if (capWorld == null)
        {
            System.out.println("There is no Capital City in the world");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-40s %-30s", "Capital City Name","Country Name", "Population"));
        // Loop over all capital cities in the world
        for (CapitalCity ccr : capWorld)
        {
            //print the list to check if capital cities in the world is null
            if (ccr == null)
                continue;
            String wString =
                    String.format("%-30s %-40s %-30s",
                            ccr.getCapCityName(),ccr.getCapCityCountry(), ccr.getCapCityPopulation());
            System.out.println(wString);
        }
    }

    /**
     * 21.The top N populated capital cities in a continent where N is provided by the user.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<CapitalCity> getTopNCapCitiesCont(String inputContin, int inputLimit)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 20.The top N populated capital cities in a continent where N is provided by the user.
            String strQueryTwentyOne =
                    "SELECT city.Name as 'CityName', country.Name as 'CountryName', country.Population FROM city INNER JOIN country WHERE city.ID = country.Capital AND country.Code=city.CountryCode AND country.Continent = '"+ inputContin +"' ORDER BY country.Population DESC LIMIT "+ inputLimit +";";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryTwentyOne);
            // Extract region information
            ArrayList<CapitalCity> capCont = new ArrayList<CapitalCity>();
            while (rset.next())
            {
                CapitalCity capCnt = new CapitalCity();
                capCnt.setCapCityName(rset.getString("CityName"));
                capCnt.setCapCityCountry(rset.getString("CountryName"));
                capCnt.setCapCityPopulation(rset.getString("Population"));
                capCont.add(capCnt);
            }
            return capCont;
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
    public void printTopNCapCitiesCont(ArrayList<CapitalCity> ncontCap)
    {
        // Check region is not null
        if (ncontCap == null)
        {
            System.out.println("There is no Capital City in the world");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-40s %-30s", "Capital City Name","Country Name", "Population"));
        // Loop over all capital cities in a continent
        for (CapitalCity ccr : ncontCap)
        {
            //print the list to check if capital cities in a continent is null
            if (ccr == null)
                continue;
            String coString =
                    String.format("%-30s %-40s %-30s",
                            ccr.getCapCityName(),ccr.getCapCityCountry(), ccr.getCapCityPopulation());
            System.out.println(coString);
        }
    }

    /**
     * 22.The top N populated capital cities in a region where N is provided by the user.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<CapitalCity> getTopNCapCitiesReg(String inputTopRegion, int inputLimit)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 20.The top N populated capital cities in a region where N is provided by the user.
            String strQueryTwentyTwo =
                    "SELECT city.Name as 'CityName', country.Name as 'CountryName', country.Population FROM city INNER JOIN country WHERE city.ID = country.Capital AND country.Code=city.CountryCode AND country.Region='"+ inputTopRegion +"' ORDER BY country.Population DESC LIMIT "+ inputLimit +";";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryTwentyTwo);
            // Extract region information
            ArrayList<CapitalCity> capRegion = new ArrayList<CapitalCity>();
            while (rset.next())
            {
                CapitalCity capRegi = new CapitalCity();
                capRegi.setCapCityName(rset.getString("CityName"));
                capRegi.setCapCityCountry(rset.getString("CountryName"));
                capRegi.setCapCityPopulation(rset.getString("Population"));
                capRegion.add(capRegi);
            }
            return capRegion;
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
    public void printTopNCapCitiesReg(ArrayList<CapitalCity> regCap)
    {
        // Check region is not null
        if (regCap == null)
        {
            System.out.println("There is no Capital City in the world");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-40s %-30s", "Capital City Name","Country Name", "Population"));
        // Loop over all capital cities in a continent
        for (CapitalCity ccr : regCap)
        {
            //print the list to check if capital cities in a region is null
            if (ccr == null)
                continue;
            String reString =
                    String.format("%-30s %-40s %-30s",
                            ccr.getCapCityName(),ccr.getCapCityCountry(), ccr.getCapCityPopulation());
            System.out.println(reString);
        }
    }

    /**
     * 23.The population of people, people living in cities, and people not living in cities in each continent.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<PeoplePopulation> getPopulatedPeopleContinent()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 23.The population of people, people living in cities, and people not living in cities in each continent.
            String strQueryTwentyThree =
                    "SELECT country.Continent, SUM(country.Population) as 'ContinentPopulation', SUM((SELECT SUM(city.Population) FROM city WHERE city.CountryCode=country.Code)) as 'CityPopulation' FROM country GROUP BY country.Continent;";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryTwentyThree);
            // Extract continent information
            ArrayList<PeoplePopulation> populationCont = new ArrayList<PeoplePopulation>();
            while (rset.next())
            {
                PeoplePopulation popCont = new PeoplePopulation();
                popCont.setContinentName(rset.getString("Continent"));
                popCont.setContinentPopulation(rset.getString("ContinentPopulation"));
                popCont.setCityPopulation(rset.getString("CityPopulation"));
                populationCont.add(popCont);
            }
            return populationCont;
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
    public void printPopulatedPeopleConitnent(ArrayList<PeoplePopulation> popuConti)
    {
        // Check continent is not null
        if (popuConti == null)
        {
            System.out.println("There is no population of people, people living in cities, and people not living in cities in each continent.");
            return;
        }
        // Print header
        System.out.println(String.format("%-20s %-28s %-25s %-25s %-25s", "Continent","Continent Total Population","City Total Population","People Not Living (%)","People Living (%)"));
        // Loop over all capital cities in a continent

        for (PeoplePopulation pcon : popuConti)
        {
            //print the list to check if capital cities in a continent is null
            if (pcon == null)
                continue;
            if (pcon.getCityPopulation() == null){
                pcon.setCityPopulation("0");
            }
            float conpopulation = Float.parseFloat(pcon.getContinentPopulation());
            float concitypopulation = Float.parseFloat(pcon.getCityPopulation());
            float livingconper = 100 * (concitypopulation/conpopulation);
            float notlivingcon = 100 - livingconper;
            String nan = "NaN";
            if(String.valueOf(livingconper) == nan){
                livingconper = 0.0F;
            }
            if(String.valueOf(notlivingcon) == nan){
                notlivingcon = 0.0F;
            }
            String strnotlivingconper = notlivingcon+"%";
            String strlivingconper = livingconper+"%";
            String pconString =
                    String.format("%-20s %-28s %-25s %-25s %-25s",
                            pcon.getContinentName(),pcon.getContinentPopulation(), pcon.getCityPopulation(), strnotlivingconper, strlivingconper);
            System.out.println(pconString);
        }
    }
    /**
     * 24.The population of people, people living in cities, and people not living in cities in each region.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<PeoplePopulation> getPopulatedPeopleRegions()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 24.The population of people, people living in cities, and people not living in cities in each region.
            String strQueryTwentyFour =
                    "SELECT country.Region, SUM(country.Population) as 'RegionPopulation', SUM((SELECT SUM(city.Population) FROM city WHERE city.CountryCode=country.Code)) as 'CityPopulation' FROM country GROUP BY country.Region;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryTwentyFour);
            // Extract region information
            ArrayList<PeoplePopulation> populationReg = new ArrayList<PeoplePopulation>();
            while (rset.next())
            {
                PeoplePopulation popReg = new PeoplePopulation();
                popReg.setRegionsName(rset.getString("Region"));
                popReg.setRegionsPopulation(rset.getString("RegionPopulation"));
                popReg.setCityPopulation(rset.getString("CityPopulation"));
                populationReg.add(popReg);
            }
            return populationReg;
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
    public void printPopulatedPeopleRegions(ArrayList<PeoplePopulation> popuRegs)
    {
        // Check region is not null
        if (popuRegs == null)
        {
            System.out.println("There is no population of people, people living in cities, and people not living in cities in each region.");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-25s %-25s %-25s %-25s", "Region", "Region Total Population","City Total Population", "People Not Living (%)", "People Living (%)"));
        // Loop over all capital cities in a region
        for (PeoplePopulation preg : popuRegs)
        {
            //print the list to check if capital cities in a region is null
            if (preg == null)
                continue;
            if (preg.getCityPopulation() == null){
                preg.setCityPopulation("0");
            }
            float regpopulation = Float.parseFloat(preg.getRegionsPopulation());
            float regcitypopulation = Float.parseFloat(preg.getCityPopulation());
            float livingregper = 100 * (regcitypopulation/regpopulation);
            float notlivingregper = 100 - livingregper;
            String nan = "NaN";
            if(String.valueOf(livingregper) == nan){
                livingregper = 0.0F;
            }
            if(String.valueOf(notlivingregper) == nan){
                notlivingregper = 0.0F;
            }
            String strnotlivingregper = notlivingregper+"%";
            String strlivingregper = livingregper+"%";
            String pregString =
                    String.format("%-30s %-25s %-25s %-25s %-25s",
                            preg.getRegionsName(),preg.getRegionsPopulation(),preg.getCityPopulation(),strnotlivingregper,strlivingregper);
            System.out.println(pregString);
        }
    }

    /**
     * 25.The population of people, people living in cities, and people not living in cities in each country.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<PeoplePopulation> getPopulatedPeopleCountry()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 25.The population of people, people living in cities, and people not living in cities in each country.
            String strQueryTwentyFive =
                    "SELECT country.Name, SUM(country.Population) as 'CountryPopulation', SUM((SELECT SUM(city.Population) FROM city WHERE city.CountryCode=country.Code)) as 'CityPopulation' FROM country GROUP BY country.Name;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryTwentyFive);
            // Extract country information
            ArrayList<PeoplePopulation> populationCou = new ArrayList<PeoplePopulation>();
            while (rset.next())
            {
                PeoplePopulation popCou = new PeoplePopulation();
                popCou.setCountriesName(rset.getString("Name"));
                popCou.setCountriesPopulation(rset.getString("CountryPopulation"));
                popCou.setCityPopulation(rset.getString("CityPopulation"));
                populationCou.add(popCou);
            }
            return populationCou;
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
    public void printPopulatedPeopleCountry(ArrayList<PeoplePopulation> popuCoun)
    {
        // Check country is not null
        if (popuCoun == null)
        {
            System.out.println("There is no population of people, people living in cities, and people not living in cities in each country.");
            return;
        }
        // Print header
        System.out.println(String.format("%-45s %-28s %-25s %-25s %-25s", "Country Name" ,"Country Total Population" ,"City Total Population", "People Not Living(%)", "People Living(%)"));
        for (PeoplePopulation pcou : popuCoun)
        {
            //print the list to check if capital cities in a country is null
            if (pcou == null)
                continue;
            if (pcou.getCityPopulation() == null){
                pcou.setCityPopulation("0");
            }
            float coupopulation = Float.parseFloat(pcou.getCountriesPopulation());
            float coucitypopulation = Float.parseFloat(pcou.getCityPopulation());
            float livingcouper = 100 * (coucitypopulation/coupopulation);
            float notlivingcouper = 100 - livingcouper;
            String nan = "NaN";
            if(String.valueOf(livingcouper) == nan){
                livingcouper = 0.0F;
            }
            if(String.valueOf(notlivingcouper) == nan){
                notlivingcouper = 0.0F;
            }
            String strnotlivingcouper = notlivingcouper+"%";
            String strlivingcouper = livingcouper+"%";
            String pcouString =
                    String.format("%-45s %-28s %-25s %-25s %-25s",
                            pcou.getCountriesName(), pcou.getCountriesPopulation(), pcou.getCityPopulation(), strnotlivingcouper, strlivingcouper);
            System.out.println(pcouString);
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
            ArrayList<PeoplePopulation> populationWorld = new ArrayList<PeoplePopulation>();
            while (rset.next())
            {
                PeoplePopulation popWorld = new PeoplePopulation();
                popWorld.setWorldPopulation(rset.getString("Population"));
                populationWorld.add(popWorld);
            }
            return populationWorld;
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
    public void printWorldPopulation(ArrayList<PeoplePopulation> popWorld)
    {

        // Check country is not null
        if (popWorld == null) {
            System.out.println("There is no population of the world.");
            return;
        }
        // Print header
        for (PeoplePopulation wpop : popWorld) {
            //print the list to check if capital cities in a country is null
            if (wpop == null)
                continue;
            String pworldString =
                    String.format("%-40s",
                            wpop.getWorldPopulation());
            System.out.println("Total World Population: "+pworldString);
        }
    }


    /**
     * 27.The population of the continent.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<PeoplePopulation> getContinentPopulation(String inputTotalCont)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 27.The population of the continent.
            String strQueryTwentySeven =
                    "SELECT country.Continent, SUM(country.Population) AS 'Population' FROM country WHERE country.Continent = '"+inputTotalCont+"';";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryTwentySeven);
            // Extract continent information
            ArrayList<PeoplePopulation> populationConti = new ArrayList<PeoplePopulation>();
            while (rset.next())
            {
                PeoplePopulation popContinent = new PeoplePopulation();
                popContinent.setContinentName(rset.getString("Continent"));
                popContinent.setContinentPopulation(rset.getString("Population"));
                populationConti.add(popContinent);
            }
            return populationConti;
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
    public void printContinentPopulation(ArrayList<PeoplePopulation> popConti)
    {

        // Check country is not null
        if (popConti == null) {
            System.out.println("There is no population of the continent.");
            return;
        }
        // Print header
        System.out.println(String.format("%-40s %-40s", "Continent Name", "Population"));
        // Loop over all capital cities in a country
        for (PeoplePopulation continentPopulation : popConti)
        {
            //print the list to check if capital cities in a country is null
            if (continentPopulation == null)
                continue;
            String pcontString =
                    String.format("%-40s %-40s",
                            continentPopulation.getContinentName(), continentPopulation.getContinentPopulation());
            System.out.println(pcontString);
        }
    }

    /**
     * 28.The population of the regions.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<PeoplePopulation> getRegionsPopulation(String inputTotalRegion)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 27.The population of the continent.
            String strQueryTwentyEight =
                    "SELECT country.Region, SUM(country.Population) AS 'Population' FROM country WHERE country.Region='"+inputTotalRegion+"';";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryTwentyEight);
            // Extract regions information
            ArrayList<PeoplePopulation> populationRegi = new ArrayList<PeoplePopulation>();
            while (rset.next())
            {
                PeoplePopulation popRegi = new PeoplePopulation();
                popRegi.setRegionsName(rset.getString("Region"));
                popRegi.setRegionsPopulation(rset.getString("Population"));
                populationRegi.add(popRegi);
            }
            return populationRegi;
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
    public void printRegionsPopulation(ArrayList<PeoplePopulation> popRegs)
    {

        // Check country is not null
        if (popRegs == null) {
            System.out.println("There is no population of the regions.");
            return;
        }
        // Print header
        System.out.println(String.format("%-40s %-40s", "Region Name", "Population"));
        // Loop over population from region
        for (PeoplePopulation regionsPopulation : popRegs)
        {
            //print the list to check if capital cities in a country is null
            if (regionsPopulation == null)
                continue;
            String pregiString =
                    String.format("%-40s %-40s",
                            regionsPopulation.getRegionsName(), regionsPopulation.getRegionsPopulation());
            System.out.println(pregiString);
        }
    }

    /**
     * 29.The population of the country.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<PeoplePopulation> getCountriesPopulation(String inputTotalCountry)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 29.The population of the country.
            String strQueryTwentyNine =
                    "SELECT country.Name, country.Population FROM country WHERE country.Name='"+inputTotalCountry+"';";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryTwentyNine);
            // Extract countries information
            ArrayList<PeoplePopulation> populCountry = new ArrayList<PeoplePopulation>();
            while (rset.next())
            {
                PeoplePopulation pCountry = new PeoplePopulation();
                pCountry.setCountriesName(rset.getString("Name"));
                pCountry.setCountriesPopulation(rset.getString("Population"));
                populCountry.add(pCountry);
            }
            return populCountry;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the population of the country.");
            return null;
        }
    }
    /**
     * 29. The population of the countries.
     * Formatting the output data from the list.
     **/
    public void printCountriesPopulation(ArrayList<PeoplePopulation> popContr)
    {

        // Check country is not null
        if (popContr == null) {
            System.out.println("There is no population of the countries.");
            return;
        }
        // Print header
        System.out.println(String.format("%-50s %-40s", "Country Name", "Population"));
        // Loop over population from region
        for (PeoplePopulation countrPopulation : popContr)
        {
            //print the list to check if capital cities in a country is null
            if (countrPopulation == null)
                continue;
            String pcoString =
                    String.format("%-50s %-40s",
                            countrPopulation.getCountriesName(), countrPopulation.getCountriesPopulation());
            System.out.println(pcoString);
        }
    }

    /**
     * 30.The population of the district.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<PeoplePopulation> getDistrictPopulation(String inputTotalDistrict)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 29.The population of the country.
            String strQueryThirty =
                    "SELECT city.District, city.Population FROM city WHERE city.District='"+inputTotalDistrict+"';";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryThirty);
            // Extract countries information
            ArrayList<PeoplePopulation> populationDistr = new ArrayList<PeoplePopulation>();
            while (rset.next())
            {
                PeoplePopulation pDistrict = new PeoplePopulation();
                pDistrict.setDistrictName(rset.getString("District"));
                pDistrict.setDistrictPopulation(rset.getString("Population"));
                populationDistr.add(pDistrict);
            }
            return populationDistr;
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
    public void printDistrictsPopulation(ArrayList<PeoplePopulation> popDist)
    {

        // Check country is not null
        if (popDist == null) {
            System.out.println("There is no population of the districts.");
            return;
        }
        // Print header
        System.out.println(String.format("%-40s %-40s", "District Name", "Population"));
        // Loop over population from region
        for (PeoplePopulation dstPopulation : popDist)
        {
            //print the list to check if capital cities in a country is null
            if (dstPopulation == null)
                continue;
            String pdiString =
                    String.format("%-40s %-40s",
                            dstPopulation.getDistrictName(), dstPopulation.getDistrictPopulation());
            System.out.println(pdiString);
        }
    }

    /**
     * 31.The population of the city.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<PeoplePopulation> getCityPopulation(String inputTotalCity)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 31.The population of the city.
            String strQueryThirtyOne =
                    "SELECT city.Name, city.Population FROM city WHERE city.Name='"+inputTotalCity+"';";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryThirtyOne);
            // Extract city information
            ArrayList<PeoplePopulation> populatCities = new ArrayList<PeoplePopulation>();
            while (rset.next())
            {
                PeoplePopulation poCity   = new PeoplePopulation();
                poCity.setCityName(rset.getString("Name"));
                poCity.setCityPopulation(rset.getString("Population"));
                populatCities.add(poCity);
            }
            return populatCities;
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
    public void printCityPopulation(ArrayList<PeoplePopulation> popCity)
    {

        // Check country is not null
        if (popCity == null) {
            System.out.println("There is no population of the cities.");
            return;
        }
        // Print header
        System.out.println(String.format("%-40s %-40s", "City Name", "Population"));
        // Loop over population from region
        for (PeoplePopulation cityPopul : popCity)
        {
            //print the list to check if capital cities in a country is null
            if (cityPopul == null)
                continue;
            String pcitString =
                    String.format("%-40s %-40s",
                            cityPopul.getCityName(), cityPopul.getCityPopulation());
            System.out.println(pcitString);
        }
    }
    /**
     * 32. List the population of people who speak different languages in descending order
     * Query execution by user input and pass the array list to format the return value.
     * Function is called in ma
     **/
    public ArrayList<CountryLanguage> getCountryLanguage(String inputCh,String inputEn,String inputHin,String inputSp, String inputAr)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 33. List the population of people who speak different language in descending order
            String strQueryLanguage4 =
                    "SELECT countrylanguage.Language, SUM(country.Population) as 'countrypop' FROM country, countrylanguage WHERE country.Code=countrylanguage.CountryCode AND countrylanguage.Language='"+ inputCh +"' UNION SELECT countrylanguage.Language, SUM(country.Population) AS countrypop FROM country, countrylanguage WHERE country.Code=countrylanguage.CountryCode AND countrylanguage.Language='"+ inputEn +"' UNION SELECT countrylanguage.Language, SUM(country.Population) AS countrypop FROM country, countrylanguage WHERE country.Code=countrylanguage.CountryCode AND countrylanguage.Language='"+ inputHin +"' UNION SELECT countrylanguage.Language, SUM(country.Population) AS countrypop FROM country, countrylanguage WHERE country.Code=countrylanguage.CountryCode AND countrylanguage.Language='"+ inputSp +"' UNION SELECT countrylanguage.Language, SUM(country.Population) AS countrypop FROM country, countrylanguage WHERE country.Code=countrylanguage.CountryCode AND countrylanguage.Language='"+ inputAr +"' ORDER BY countrypop DESC;";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryLanguage4);
            // Extract countries information
            ArrayList<CountryLanguage> countryLanguage4 = new ArrayList<CountryLanguage>();
            while (rset.next())
            {
                CountryLanguage language4 = new CountryLanguage();
                language4.setCountryLanguage(rset.getString("Language"));
                language4.setCountryPopulation(rset.getString("countrypop"));
                countryLanguage4.add(language4);
            }
            return countryLanguage4;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get list the population of people who speak different language in descending order.");
            return null;
        }
    }

    /**
     * 32. List the population of people who speak different languages in descending order
     * Formatting the output data from the list.
     **/
    public void printCountryLanguage(ArrayList<CountryLanguage> countryLang)
    {
        // Check country language is not null
        if (countryLang == null)
        {
            System.out.println("No languages");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s", "Language", "Population", "Percent"));
        // Loop over all languages in the list
        BigInteger totalpoplan = BigInteger.valueOf(0);
        for (CountryLanguage lp : countryLang)
        {
            if (lp == null)
                continue;
            BigInteger counint = new BigInteger(String.format(lp.getCountryPopulation()));
            totalpoplan = totalpoplan.add(counint);
        }
        for (CountryLanguage cl4 : countryLang)
        {
            //print language to check if a language is null
            if (cl4 == null)
                continue;
            float totalpoplanfloat = Float.parseFloat(String.valueOf(totalpoplan));
            float countrpoplan = Float.parseFloat(cl4.getCountryPopulation());
            float res = 100 * (countrpoplan/totalpoplanfloat);
            String resStr = res+"%";
            String langString =
                    String.format("%-30s %-30s %-30s",
                            cl4.getCountryLanguage(),cl4.getCountryPopulation(), resStr);
            System.out.println(langString);
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
        ArrayList<Country> country = a.getAllCountries();
        a.printAllCountries(country);
        a.outputCountries(country, "AllCountries.md");
        System.out.println("\n");

        // Display all the countries in a continent organised by largest population to smallest.
        System.out.println("2: All the countries in the Oceania organised by largest population to smallest.\n");
        ArrayList<Country> continents = a.getAllContinents("Oceania");
        a.printAllCountries(continents);
        a.outputContinent(continents, "AllContinents.md");
        System.out.println("\n");

        // Display all the countries in a region organised by largest population to smallest.
        System.out.println("3: All the countries in the Caribbean organised by largest population to smallest.\n");
        ArrayList<Country> regions = a.getAllRegion("Caribbean");
        a.printAllCountries(regions);
        a.outputRegion(regions, "AllRegions.md");
        System.out.println("\n");

        // Display the top N populated countries in the world where N is provided by the user.
        System.out.println("4: The top 10 populated countries in the world.\n");
        ArrayList<Country> nPopulatedCountries = a.getAllNPopulatedCountries(10);
        a.printNPopulatedCountries(nPopulatedCountries);
        a.outputRegion(nPopulatedCountries, "TopNCountries.md");
        System.out.println("\n");

        // Display all the countries in a continent organised by largest population to smallest.
        System.out.println("5. The top 10 populated countries in the Europe.\n");
        ArrayList<Country> nPopulatedContinents = a.getAllNPopulatedContinents("Europe", 10);
        a.printContinent(nPopulatedContinents);
        a.outputRegion(nPopulatedContinents, "TopNContinents.md");
        System.out.println("\n");

        // Display the top N populated countries in a region where N is provided by the user.
        System.out.println("6: The top 10 populated countries in the Caribbean.\n");
        ArrayList<Country> nPopulatedRegion = a.getAllNPopulatedRegion("Caribbean", 10);
        a.printNPopulatedRegion(nPopulatedRegion);
        a.outputRegion(nPopulatedRegion, "TopNRegions.md");
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
        ArrayList<City> regi = a.getAllCitiesRegions("Caribbean");
        a.printRegions(regi);
        System.out.println("\n");

        // Display all the cities in a country organised by largest population to smallest.
        System.out.println("10: All the cities in the Myanmar organised by largest population to smallest.\n");
        ArrayList<City> countries = a.getAllCitiesCountries("Myanmar");
        a.printCountries(countries);
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
        ArrayList<CapitalCity> capitalCities = a.getAllCapitalCities();
        a.printAllCapitalCities(capitalCities);
        System.out.println("\n");

        // All the capital cities in a continent organised by largest population to smallest.
        System.out.println("18: All the capital cities in a Oceania continent organised by largest population to smallest.\n");
        ArrayList<CapitalCity> capCitContinent = a.getAllCapitalCitiesContinents("Asia");
        a.printAllCapitalCityContinent(capCitContinent);
        System.out.println("\n");

        // All the capital cities in a region organised by largest to smallest.
        System.out.println("19.All the capital cities in a Caribbean region organised by largest to smallest.\n");
        ArrayList<CapitalCity> capCitRegion = a.getAllCapitalCitiesRegions("Caribbean");
        a.printAllCapitalCityRegion(capCitRegion);
        System.out.println("\n");

        // The top N populated capital cities in the world where N is provided by the user.
        System.out.println("20.The top 10 populated capital cities in the world \n");
        ArrayList<CapitalCity> capWld = a.getTopNCapCitiesWorld(10);
        a.printTopNCapCitiesWorld(capWld);
        System.out.println("\n");

        // The top N populated capital cities in a continent where N is provided by the user.
        System.out.println("21.The top 10 populated capital cities in North America \n");
        ArrayList<CapitalCity> contCapital = a.getTopNCapCitiesCont("North America",10);
        a.printTopNCapCitiesCont(contCapital);
        System.out.println("\n");

        // The top N populated capital cities in a region where N is provided by the user.
        System.out.println("22.The top 10 populated capital cities in Middle East \n");
        ArrayList<CapitalCity> regWld = a.getTopNCapCitiesReg("Middle East",10);
        a.printTopNCapCitiesReg(regWld);
        System.out.println("\n");

        // The population of people, people living in cities, and people not living in cities in each continent.
        System.out.println("23.The population of people, people living in cities, and people not living in cities in each continent. \n");
        ArrayList<PeoplePopulation> popCont = a.getPopulatedPeopleContinent();
        a.printPopulatedPeopleConitnent(popCont);
        System.out.println("\n");

        // The population of people, people living in cities, and people not living in cities in each region.
        System.out.println("24.The population of people, people living in cities, and people not living in cities in each region. \n");
        ArrayList<PeoplePopulation> popReg = a.getPopulatedPeopleRegions();
        a.printPopulatedPeopleRegions(popReg);
        System.out.println("\n");

        // The population of people, people living in cities, and people not living in cities in each country.
        System.out.println("25.The population of people, people living in cities, and people not living in cities in each country. \n");
        ArrayList<PeoplePopulation> popCoun = a.getPopulatedPeopleCountry();
        a.printPopulatedPeopleCountry(popCoun);
        System.out.println("\n");

        // The population of the world.
        System.out.println("26. The population of the world.");
        ArrayList<PeoplePopulation> popWorld = a.getWorldPopulation();
        a.printWorldPopulation(popWorld);
        System.out.println("\n");

        // The population of the continent.
        System.out.println("27. The population of the continent.");
        ArrayList<PeoplePopulation> popContin = a.getContinentPopulation("Asia");
        a.printContinentPopulation(popContin);
        System.out.println("\n");

        // The population of the regions.
        System.out.println("28. The population of the regions.");
        ArrayList<PeoplePopulation> popRegions = a.getRegionsPopulation("Caribbean");
        a.printRegionsPopulation(popRegions);
        System.out.println("\n");

        // The population of the countries.
        System.out.println("29. The population of the countries.");
        ArrayList<PeoplePopulation> popCountries = a.getCountriesPopulation("Austria");
        a.printCountriesPopulation(popCountries);
        System.out.println("\n");

        // The population of the districts.
        System.out.println("30. The population of the districts.");
        ArrayList<PeoplePopulation> popDisct = a.getDistrictPopulation("Kabol");
        a.printDistrictsPopulation(popDisct);
        System.out.println("\n");

        // The population of the cities.
        System.out.println("31. The population of the cities.");
        ArrayList<PeoplePopulation> popCities = a.getCityPopulation("Haag");
        a.printCityPopulation(popCities);
        System.out.println("\n");

        // List the population of people who speak language in descending order.
        System.out.println("32: List the population of people who speak language in descending order.\n");
        ArrayList<CountryLanguage> countLanguage = a.getCountryLanguage("Chinese", "English","Hindi", "Spanish", "Arabic");
        a.printCountryLanguage(countLanguage);


        // Disconnect from database
        a.disconnect();
    }
}
