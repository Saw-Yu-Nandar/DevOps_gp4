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
     * 32. List the population of people who speak Chinese in descending order
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
            //Query 32. List the population of people who speak Chinese in descending order
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
     * 32. List the population of people who speak Chinese in descending order
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
     * 33. List the population of people who speak English in descending order
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
            //Query 33. List the population of people who speak Chinese in descending order
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
     * 33. List the population of people who speak English in descending order
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
     * 34. List the population of people who speak Hindi in descending order
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
            //Query 34. List the population of people who speak Hindi in descending order
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
     * 34. List the population of people who speak Hindi in descending order
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
     * 35. List the population of people who speak Spanish in descending order
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
            //Query 35. List the population of people who speak Spanish in descending order
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
     * 35. List the population of people who speak Spanish in descending order
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
     * 36. List the population of people who speak Arabic in descending order.
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
            //Query 36. List the population of people who speak Arabic in descending order
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
     * 36. List the population of people who speak Arabic in descending order
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