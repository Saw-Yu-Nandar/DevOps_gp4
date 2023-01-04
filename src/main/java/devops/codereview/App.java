package devops.codereview;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class App {
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
            String contString =
                    String.format("%-20s %-25s %-25s %-25s",
                            cont.getCityName(),cont.getCountryName(),cont.getCityDistrict(),cont.getCityPopulation());
            System.out.println(contString);
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
                reg.setCityName(rset.getString("Cityname"));
                reg.setCountryName(rset.getString("Countryname"));
                reg.setCityDistrict(rset.getString("District"));
                reg.setCityPopulation(rset.getString("Population"));
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
            String rString =
                    String.format("%-20s %-25s %-25s %-25s",
                            cont.getCityName(),cont.getCountryName(),cont.getCityDistrict(),cont.getCityPopulation());
            System.out.println(rString);
        }
    }

    /**
     * 15. The top N populated cities in a country where N is provided by the user.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<City> getTopNPopulatedCountries(String inputCountry, int inputLimit)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 13: The top N populated cities in a country where N is provided by the user.
            String strQueryEight =
                    "SELECT city.Name as 'CityName',country.Name as 'CountryName',city.District, city.Population FROM city INNER JOIN country on city.CountryCode = country.Code WHERE country.Name = '"+ inputCountry +"' ORDER BY city.Population DESC LIMIT "+ inputLimit +";";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryEight);
            // Extract country information
            ArrayList<City> country = new ArrayList<City>();
            while (rset.next())
            {
                City cty = new City();
                cty.setCityName(rset.getString("Cityname"));
                cty.setCountryName(rset.getString("Countryname"));
                cty.setCityDistrict(rset.getString("District"));
                cty.setCityPopulation(rset.getString("Population"));
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
            String ciString =
                    String.format("%-20s %-25s %-25s %-30s",
                            cont.getCityName(),cont.getCountryName(),cont.getCityDistrict(),cont.getCityPopulation());
            System.out.println(ciString);
        }
    }

    /**
     * 16. The top N populated cities in a district where N is provided by the user.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<City> getTopNPopulatedDistrict(String inputDistrict, int inputLimit)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 13: The top N populated cities in a district  where N is provided by the user.
            String strQueryEight =
                    "SELECT city.Name as 'CityName', country.name as 'CountryName', city.District, city.Population FROM city INNER JOIN country on city.CountryCode = country.Code WHERE city.District = '"+ inputDistrict +"' ORDER BY city.Population DESC LIMIT "+ inputLimit +";";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryEight);
            // Extract district information
            ArrayList<City> dist = new ArrayList<City>();
            while (rset.next())
            {
                City dis = new City();
                dis.setCityName(rset.getString("Cityname"));
                dis.setCountryName(rset.getString("Countryname"));
                dis.setCityDistrict(rset.getString("District"));
                dis.setCityPopulation(rset.getString("Population"));
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
            String disString =
                    String.format("%-30s %-25s %-25s %-30s",
                            cont.getCityName(),cont.getCountryName(),cont.getCityDistrict(),cont.getCityPopulation());
            System.out.println(disString);
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
        System.out.println(String.format("%-25s %-25s %-25s", "CapitalCity","Country","Population"));
        // Loop over all countries in the list
        for (CapitalCity cc : capitalCities)
        {
            if (cc == null)
                continue;

            String capctrString =
                    String.format("%-25s %-25s %-25s",
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
        System.out.println(String.format("%-25s %-25s %-25s %-25s", "CapitalCity", "Country", "Continent", "Population"));
        // Loop over all continent in the list
        for (CapitalCity cccon : capcitContinent)
        {
            if (cccon == null)
                continue;
            String capconString =
                    String.format("%-25s %-25s %-25s %-25s",
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
        System.out.println(String.format("%-25s %-25s %-25s %-25s", "CapitalCity","Country","Region", "Population"));
        // Loop over all region in the list
        for (CapitalCity ccr : capcitRegion)
        {
            //printRegion to check if an region is null
            if (ccr == null)
                continue;
            String regString =
                    String.format("%-25s %-25s %-25s %-25s",
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
        System.out.println(String.format("%-30s %-30s %-30s", "Capital City Name","Country Name", "Population"));
        // Loop over all capital cities in the world
        for (CapitalCity ccr : capWorld)
        {
            //print the list to check if capital cities in the world is null
            if (ccr == null)
                continue;
            String wString =
                    String.format("%-30s %-30s %-30s",
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
        System.out.println(String.format("%-30s %-30s %-30s", "Capital City Name","Country Name", "Population"));
        // Loop over all capital cities in a continent
        for (CapitalCity ccr : ncontCap)
        {
            //print the list to check if capital cities in a continent is null
            if (ccr == null)
                continue;
            String coString =
                    String.format("%-30s %-30s %-30s",
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
        System.out.println(String.format("%-30s %-30s %-30s", "Capital City Name","Country Name", "Population"));
        // Loop over all capital cities in a continent
        for (CapitalCity ccr : regCap)
        {
            //print the list to check if capital cities in a region is null
            if (ccr == null)
                continue;
            String reString =
                    String.format("%-30s %-30s %-30s",
                            ccr.getCapCityName(),ccr.getCapCityCountry(), ccr.getCapCityPopulation());
            System.out.println(reString);
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
                PeoplePopulation popWld = new PeoplePopulation();
                //popWld.world_name      = rset.getString("Name");
                popWld.setWorldPopulation(rset.getString("Population"));
                //popWld.world_population      = rset.getString("Population");
                populationWorld.add(popWld);
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
        //System.out.println(String.format("%-40s", "Population"));
        // Loop over all capital cities in a country
        // int total_word_pop = 0;
        //NumberFormat world_res_format = NumberFormat.getInstance();
        for (PeoplePopulation wpop : popWorld) {
            //print the list to check if capital cities in a country is null
            if (wpop == null)
                continue;
            String pworldString =
                    String.format("%-40s",
                            wpop.getWorldPopulation());
            System.out.println("Total World Population: "+ pworldString);
            //System.out.println("Total World Population: "+world_res_format.format(pworldString));
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
            ArrayList<PeoplePopulation> populationCont = new ArrayList<PeoplePopulation>();
            while (rset.next())
            {
                PeoplePopulation popConti = new PeoplePopulation();
                popConti.setContinentName(rset.getString("Continent"));
                popConti.setContinentPopulation(rset.getString("Population"));
                populationCont.add(popConti);
            }
            return populationCont;
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
        //int total_cont_pop = 0;
        for (PeoplePopulation continentPopulation : popConti)
        {
            //print the list to check if capital cities in a country is null
            if (continentPopulation == null)
                continue;
            String pwldconString =
                    String.format("%-40s %-40s",
                            continentPopulation.getContinentName(), continentPopulation.getContinentPopulation());
            System.out.println(pwldconString);
            //total_cont_pop = Integer.parseInt(continentPopulation.continentPopulation) + total_cont_pop;
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
            ArrayList<PeoplePopulation> populationRegions = new ArrayList<PeoplePopulation>();
            while (rset.next())
            {
                PeoplePopulation popRegi = new PeoplePopulation();
                popRegi.setRegionsName(rset.getString("Region"));
                popRegi.setRegionsPopulation(rset.getString("Population"));
                populationRegions.add(popRegi);
            }
            return populationRegions;
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
        //int total_reg_pop = 0;
        for (PeoplePopulation regionPopulation : popRegs)
        {
            //print the list to check if capital cities in a country is null
            if (regionPopulation == null)
                continue;
            String pwldregString =
                    String.format("%-40s %-40s",
                            regionPopulation.getRegionsName(), regionPopulation.getRegionsPopulation());
            System.out.println(pwldregString);
            //total_reg_pop = Integer.parseInt(regionPopulation.regionPopulation) + total_reg_pop;
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
            ArrayList<PeoplePopulation> populationCountry = new ArrayList<PeoplePopulation>();
            while (rset.next())
            {
                PeoplePopulation popCoun = new PeoplePopulation();
                popCoun.setCountriesName(rset.getString("Name"));
                popCoun.setCountriesPopulation(rset.getString("Population"));
                populationCountry.add(popCoun);
            }
            return populationCountry;
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
        System.out.println(String.format("%-40s %-40s", "Country Name", "Population"));
        // Loop over population from region
        //int total_con_pop = 0;
        for (PeoplePopulation countPopulation : popContr)
        {
            //print the list to check if capital cities in a country is null
            if (countPopulation == null)
                continue;
            String pwldcapString =
                    String.format("%-40s %-40s",
                            countPopulation.getCountriesName(), countPopulation.getCountriesPopulation());
            System.out.println(pwldcapString);
            //total_con_pop = Integer.parseInt(countPopulation.countries_population) + total_con_pop;
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
            ArrayList<PeoplePopulation> populationDistrict = new ArrayList<PeoplePopulation>();
            while (rset.next())
            {
                PeoplePopulation populationDist = new PeoplePopulation();
                populationDist.setDistrictName(rset.getString("District"));
                populationDist.setDistrictPopulation(rset.getString("Population"));
                populationDistrict.add(populationDist);
            }
            return populationDistrict;
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
        //int total_dist_pop = 0;
        for (PeoplePopulation dstPopulation : popDist)
        {
            //print the list to check if capital cities in a country is null
            if (dstPopulation == null)
                continue;
            String pwlddisString =
                    String.format("%-40s %-40s",
                            dstPopulation.getDistrictName(), dstPopulation.getDistrictPopulation());
            System.out.println(pwlddisString);
            //total_dist_pop = Integer.parseInt(dstPopulation.district_population) + total_dist_pop;
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
            ArrayList<PeoplePopulation> populationCit = new ArrayList<PeoplePopulation>();
            while (rset.next())
            {
                PeoplePopulation popuCity = new PeoplePopulation();
                popuCity.setCityName(rset.getString("Name"));
                popuCity.setCityPopulation(rset.getString("Population"));
                populationCit.add(popuCity);
            }
            return populationCit;
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
        //int total_city_pop = 0;
        for (PeoplePopulation cityPopulation : popCity)
        {
            //print the list to check if capital cities in a country is null
            if (cityPopulation == null)
                continue;
            String popcitString =
                    String.format("%-40s %-40s",
                            cityPopulation.getCityName(), cityPopulation.getCityPopulation());
            System.out.println(popcitString);
            //total_city_pop = Integer.parseInt(cityPopulation.cityPopulation) + total_city_pop;
        }
        //NumberFormat city_res_format = NumberFormat.getInstance();
        //city_res_format.setGroupingUsed(true);
        //String result_city = String.format("The total number of city population: "+city_res_format.format(total_city_pop));
        //System.out.println(result_city);
    }

    /**
     * 31. List the population of people who speak Chinese in descending order
     * Query execution by user input and pass the array list to format the return value.
     * Function is called in ma
     **/
    public ArrayList<CountryLanguage> getCountryLanguage1(String inputLanguage)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 31. List the population of people who speak Chinese in descending order
            String strQuerylanguage1 =
                    "SELECT countrylanguage.Language, countrylanguage.Percentage, country.Population FROM countrylanguage INNER JOIN country WHERE countrylanguage.CountryCode = country.Code AND countrylanguage.Language='"+ inputLanguage +"' ORDER BY countrylanguage.Percentage DESC;";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQuerylanguage1);
            // Extract countries information
            ArrayList<CountryLanguage> countrylanguage1 = new ArrayList<CountryLanguage>();
            while (rset.next())
            {
                CountryLanguage language1 = new CountryLanguage();
                language1.setLanguage(rset.getString("Language"));
                language1.setPercentage(rset.getString("Percentage"));
                language1.setPopulation(rset.getString("Population"));
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
    public void printCountryLanguage1(ArrayList<CountryLanguage> countryLanguage1)
    {
        // Check country language is not null
        if (countryLanguage1 == null)
        {
            System.out.println("No Chinese");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s", "Language", "Percentage", "Population"));
        // Loop over all countries in the list
        for (CountryLanguage cl1 : countryLanguage1)
        {
            //print language to check if an language is null
            if (cl1 == null)
                continue;
            String chilangString =
                    String.format("%-30s %-30s %-30s",
                            cl1.getLanguage(), cl1.getPercentage(), cl1.getPopulation());
            System.out.println(chilangString);
        }
    }

    /**
     * 32. List the population of people who speak English in descending order
     * Query execution by user input and pass the array list to format the return value.
     * Function is called in ma
     **/
    public ArrayList<CountryLanguage> getCountryLanguage2(String inputLanguage)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 31. List the population of people who speak Chinese in descending order
            String strQueryFour =
                    "SELECT countrylanguage.Language, countrylanguage.Percentage, country.Population FROM countrylanguage INNER JOIN country WHERE countrylanguage.CountryCode = country.Code AND countrylanguage.Language='"+ inputLanguage +"' ORDER BY countrylanguage.Percentage DESC;";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryFour);
            // Extract countries information
            ArrayList<CountryLanguage> countrylanguage2 = new ArrayList<CountryLanguage>();
            while (rset.next())
            {
                CountryLanguage language2 = new CountryLanguage();
                language2.setLanguage(rset.getString("Language"));
                language2.setPercentage(rset.getString("Percentage"));
                language2.setPopulation(rset.getString("Population"));
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
     * 32. List the population of people who speak English in descending order
     * Formatting the output data from the list.
     **/
    public void printCountryLanguage2(ArrayList<CountryLanguage> countryLanguage2)
    {
        // Check country language is not null
        if (countryLanguage2 == null)
        {
            System.out.println("No English");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s", "Language", "Percentage", "Population"));
        // Loop over all countries in the list
        for (CountryLanguage cl2 : countryLanguage2)
        {
            //print language to check if an language is null
            if (cl2 == null)
                continue;
            String englangString =
                    String.format("%-30s %-30s %-30s",
                            cl2.getLanguage(), cl2.getPercentage(), cl2.getPopulation());
            System.out.println(englangString);
        }
    }

    /**
     * 33. List the population of people who speak Hindi in descending order
     * Query execution by user input and pass the array list to format the return value.
     * Function is called in ma
     **/
    public ArrayList<CountryLanguage> getCountryLanguage3(String inputLanguage)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 33. List the population of people who speak Hindi in descending order
            String strQueryLanguage3 =
                    "SELECT countrylanguage.Language, countrylanguage.Percentage, country.Population FROM countrylanguage INNER JOIN country WHERE countrylanguage.CountryCode = country.Code AND countrylanguage.Language='"+ inputLanguage +"' ORDER BY countrylanguage.Percentage DESC;";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryLanguage3);
            // Extract countries information
            ArrayList<CountryLanguage> countryLanguage3 = new ArrayList<CountryLanguage>();
            while (rset.next())
            {
                CountryLanguage language3 = new CountryLanguage();
                language3.setLanguage(rset.getString("Language"));
                language3.setPercentage(rset.getString("Percentage"));
                language3.setPopulation(rset.getString("Population"));
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
    public void printCountryLanguage3(ArrayList<CountryLanguage> countryLanguage3)
    {
        // Check country language is not null
        if (countryLanguage3 == null)
        {
            System.out.println("No Hindi");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s", "Language", "Percentage", "Population"));
        // Loop over all countries in the list
        for (CountryLanguage cl3 : countryLanguage3)
        {
            //print language to check if a language is null
            if (cl3 == null)
                continue;
            String hinlangString =
                    String.format("%-30s %-30s %-30s",
                            cl3.getLanguage(), cl3.getPercentage(), cl3.getPopulation());
            System.out.println(hinlangString);
        }
    }

    //start
    /**
     * 34. List the population of people who speak Spanish in descending order
     * Query execution by user input and pass the array list to format the return value.
     * Function is called in ma
     **/
    public ArrayList<CountryLanguage> getCountryLanguage4(String inputLanguage)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 33. List the population of people who speak Spanish in descending order
            String strQueryLanguage4 =
                    "SELECT countrylanguage.Language, countrylanguage.Percentage, country.Population FROM countrylanguage INNER JOIN country WHERE countrylanguage.CountryCode = country.Code AND countrylanguage.Language='"+ inputLanguage +"' ORDER BY countrylanguage.Percentage DESC;";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryLanguage4);
            // Extract countries information
            ArrayList<CountryLanguage> countryLanguage4 = new ArrayList<CountryLanguage>();
            while (rset.next())
            {
                CountryLanguage language4 = new CountryLanguage();
                language4.setLanguage(rset.getString("Language"));
                language4.setPercentage(rset.getString("Percentage"));
                language4.setPopulation(rset.getString("Population"));
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
    public void printCountryLanguage4(ArrayList<CountryLanguage> countryLanguage4)
    {
        // Check country language is not null
        if (countryLanguage4 == null)
        {
            System.out.println("No Spanish");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s", "Language", "Percentage", "Population"));
        // Loop over all countries in the list
        for (CountryLanguage cl4 : countryLanguage4)
        {
            //print language to check if a language is null
            if (cl4 == null)
                continue;
            String spanlangString =
                    String.format("%-30s %-30s %-30s",
                            cl4.getLanguage(), cl4.getPercentage(), cl4.getPopulation());
            System.out.println(spanlangString);
        }
    }
    //end
    /** start
     * 35. List the population of people who speak Arabic in descending order.
     * Query execution by user input and pass the array list to format the return value.
     * Function is called in ma
     **/
    public ArrayList<CountryLanguage> getArabicLanguage(String inputLanguage)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 35. List the population of people who speak Arabic in descending order
            String strQueryLanguage5 =
                    "SELECT countrylanguage.Language, countrylanguage.Percentage, country.Population FROM countrylanguage INNER JOIN country WHERE countrylanguage.CountryCode = country.Code AND countrylanguage.Language='"+inputLanguage+"' ORDER BY countrylanguage.Percentage DESC;";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryLanguage5);
            // Extract countries information
            ArrayList<CountryLanguage> arabLang = new ArrayList<CountryLanguage>();
            while (rset.next())
            {
                CountryLanguage language5 = new CountryLanguage();
                language5.setLanguage(rset.getString("Language"));
                language5.setPercentage(rset.getString("Percentage"));
                language5.setPopulation(rset.getString("Population"));
                arabLang.add(language5);
            }
            return arabLang;
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
    public void printArabicLanguage(ArrayList<CountryLanguage> countryLanguage5)
    {
        // Check country language is not null
        if (countryLanguage5 == null)
        {
            System.out.println("There is no one who speak Abrabic.");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s", "Language", "Percentage", "Population"));
        // Loop over all countries in the list
        for (CountryLanguage cl5 : countryLanguage5)
        {
            //print language to check if an language is null
            if (cl5 == null)
                continue;
            String arablangString =
                    String.format("%-30s %-30s %-30s",
                            cl5.getLanguage(), cl5.getPercentage(), cl5.getPercentage());
            System.out.println(arablangString);
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
        ArrayList<City> reg = a.getAllCitiesRegions("Caribbean");
        a.printRegions(reg);
        System.out.println("\n");

        // Display all the cities in a country organised by largest population to smallest.
        System.out.println("10: All the cities in the Myanmar organised by largest population to smallest.\n");
        ArrayList<City> ctr = a.getAllCitiesCountries("Myanmar");
        a.printCountries(ctr);
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

        // The population of the world.
        System.out.println("26. The population of the world.");
        ArrayList<PeoplePopulation> populWld = a.getWorldPopulation();
        a.printWorldPopulation(populWld);
        System.out.println("\n");

        // The population of the continent.
        System.out.println("27. The population of the continent.");
        ArrayList<PeoplePopulation> popContin = a.getContinentPopulation();
        a.printContinentPopulation(popContin);
        System.out.println("\n");

        // The population of the regions.
        System.out.println("28. The population of the regions.");
        ArrayList<PeoplePopulation> popRegi = a.getRegionsPopulation();
        a.printRegionsPopulation(popRegi);
        System.out.println("\n");

        // The population of the countries.
        System.out.println("29. The population of the countries.");
        ArrayList<PeoplePopulation> popCoun = a.getCountriesPopulation();
        a.printCountriesPopulation(popCoun);
        System.out.println("\n");

        // The population of the districts.
        System.out.println("30. The population of the districts.");
        ArrayList<PeoplePopulation> popDistr = a.getDistrictPopulation();
        a.printDistrictsPopulation(popDistr);
        System.out.println("\n");

        // The population of the cities.
        System.out.println("31. The population of the cities.");
        ArrayList<PeoplePopulation> populCities = a.getCityPopulation();
        a.printCityPopulation(populCities);
        System.out.println("\n");

        // List the population of people who speak Chinese in descending order.
        System.out.println("32: List the population of people who speak Chinese in descending order.\n");
        ArrayList<CountryLanguage> countrylanguage1 = a.getCountryLanguage1("Chinese");
        a.printCountryLanguage1(countrylanguage1);
        System.out.println("\n");

        // List the population of people who speak English in descending order.
        System.out.println("33: List the population of people who speak English in descending order.\n");
        ArrayList<CountryLanguage> countrylanguage2 = a.getCountryLanguage2("English");
        a.printCountryLanguage2(countrylanguage2);
        System.out.println("\n");

        // List the population of people who speak Hindi in descending order.
        System.out.println("34: List the population of people who speak Hindi in descending order.\n");
        ArrayList<CountryLanguage> countLanguage3 = a.getCountryLanguage3("Hindi");
        a.printCountryLanguage3(countLanguage3);
        System.out.println("\n");

        // List the population of people who speak Spanish in descending order.
        System.out.println("35: List the population of people who speak Spanish in descending order.\n");
        ArrayList<CountryLanguage> countLanguage4 = a.getCountryLanguage4("Spanish");
        a.printCountryLanguage4(countLanguage4);
        System.out.println("\n");

        // List the population of people who speak Arabic in descending order.
        System.out.println("36: List the population of people who speak Arabic in descending order.\n");
        ArrayList<CountryLanguage> countLanguage5 = a.getArabicLanguage("Arabic");
        a.printArabicLanguage(countLanguage5);
        System.out.println("\n");

        // Disconnect from database
        a.disconnect();
    }
}
