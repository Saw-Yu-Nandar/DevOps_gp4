package devops.codereview;

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
//    public void connect()
//    {
//        try
//        {
//            // Load Database driver
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        }
//        catch (ClassNotFoundException e)
//        {
//            System.out.println("Could not load SQL driver");
//            System.exit(-1);
//        }
//
//        int retries = 10;
//        for (int i = 0; i < retries; ++i)
//        {
//            System.out.println("Connecting to database...");
//            try
//            {
//                // Wait a bit for db to start
//                Thread.sleep(30000);
//                // Connect to database
//                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "Team_4");
//                System.out.println("Successfully connected");
//                break;
//            }
//            catch (SQLException sqle)
//            {
//                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
//                System.out.println(sqle.getMessage());
//            }
//            catch (InterruptedException ie)
//            {
//                System.out.println("Thread interrupted? Should not happen.");
//            }
//        }
//    }

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
    public ArrayList<CapitalCities> getAllCapitalCities()
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
            ArrayList<CapitalCities> capital_cities = new ArrayList<CapitalCities>();
            while (rset.next())
            {
                CapitalCities capcit = new CapitalCities();
                capcit.cap_cit_name        = rset.getString("CapitalCity");
                capcit.cap_cit_country        = rset.getString("CountryName");
                capcit.cap_cit_population        = rset.getString("Population");
                capital_cities.add(capcit);
            }
            return capital_cities;
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
    public void printAllCapitalCities(ArrayList<CapitalCities> capital_cities)
    {
        // Check countries is not null
        if (capital_cities == null)
        {
            System.out.println("No Capital Cities");
            return;
        }
        // Print header
        System.out.println(String.format("%-25s %-25s %-25s", "CapitalCity","Country","Population"));
        // Loop over all countries in the list
        for (CapitalCities cc : capital_cities)
        {
            if (cc == null)
                continue;

            String ctr_string =
                    String.format("%-25s %-25s %-25s",
                            cc.cap_cit_name,cc.cap_cit_country,cc.cap_cit_population);
            System.out.println(ctr_string);
        }
    }

    /**
     * 18.All the capital cities in a continent organised by largest population to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<CapitalCities> getAllCapitalCitiesContinents(String input_continent)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 18.All the capital cities in a continent organised by largest population to smallest.
            String strQueryEighteen =
                    "SELECT city.Name as 'CapitalCity', country.Name as 'CountryName', country.Continent, country.Population FROM city INNER JOIN country WHERE city.ID = country.Capital AND country.Code=city.CountryCode AND country.Continent = '"+input_continent+"' ORDER BY country.Population DESC;";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryEighteen);
            // Extract continent information
            ArrayList<CapitalCities> capcit_continent = new ArrayList<CapitalCities>();
            while (rset.next())
            {
                CapitalCities capcitCont = new CapitalCities();
                capcitCont.cap_cit_name         = rset.getString("CapitalCity");
                capcitCont.cap_cit_country         = rset.getString("CountryName");
                capcitCont.cap_cit_continent       = rset.getString("Continent");
                capcitCont.cap_cit_population         = rset.getString("Population");
                capcit_continent.add(capcitCont);
            }
            return capcit_continent;
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
    public void printAllCapitalCityContinent(ArrayList<CapitalCities> capcit_continent)
    {
        // Check continent is not null
        if (capcit_continent == null)
        {
            System.out.println("No Capital City Continent");
            return;
        }
        // Print header
        System.out.println(String.format("%-25s %-25s %-25s %-25s", "CapitalCity", "Country", "Continent", "Population"));
        // Loop over all continent in the list
        for (CapitalCities cccon : capcit_continent)
        {
            if (cccon == null)
                continue;
            String ctr_string =
                    String.format("%-25s %-25s %-25s %-25s",
                            cccon.cap_cit_name, cccon.cap_cit_country, cccon.cap_cit_continent, cccon.cap_cit_population);
            System.out.println(ctr_string);
        }
    }

    /**
     * 19.All the capital cities in a region organised by largest to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<CapitalCities> getAllCapitalCitiesRegions(String input_region)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 19.All the capital cities in a region organised by largest to smallest.
            String strQueryNineteen =
                    "SELECT city.Name as 'CapitalCity', country.Name as 'CountryName', country.Region, country.Population FROM city INNER JOIN country WHERE city.ID = country.Capital AND country.Code=city.CountryCode AND country.Region = '"+input_region+"' ORDER BY country.Population DESC;";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryNineteen);
            // Extract region information
            ArrayList<CapitalCities> capcit_region = new ArrayList<CapitalCities>();
            while (rset.next())
            {
                CapitalCities capcitreg          = new CapitalCities();
                capcitreg.cap_cit_name           = rset.getString("CapitalCity");
                capcitreg.cap_cit_country        = rset.getString("CountryName");
                capcitreg.cap_cit_region         = rset.getString("Region");
                capcitreg.cap_cit_population     = rset.getString("Population");
                capcit_region.add(capcitreg);
            }
            return capcit_region;
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
    public void printAllCapitalCityRegion(ArrayList<CapitalCities> capcit_region)
    {
        // Check region is not null
        if (capcit_region == null)
        {
            System.out.println("No CapitalCity region");
            return;
        }
        // Print header
        System.out.println(String.format("%-25s %-25s %-25s %-25s", "CapitalCity","Country","Region", "Population"));
        // Loop over all region in the list
        for (CapitalCities ccr : capcit_region)
        {
            //printRegion to check if an region is null
            if (ccr == null)
                continue;
            String reg_string =
                    String.format("%-25s %-25s %-25s %-25s",
                            ccr.cap_cit_name,ccr.cap_cit_country,ccr.cap_cit_region, ccr.cap_cit_population);
            System.out.println(reg_string);
        }
    }

    /**
     * 20.The top N populated capital cities in the world where N is provided by the user.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<CapitalCities> getTopNCapCities_World(int input_limited)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 20.The top N populated capital cities in the world where N is provided by the user.
            String strQueryNineteen =
                    "SELECT city.Name as 'CityName', country.Name as 'CountryName', country.Population FROM city INNER JOIN country WHERE city.ID = country.Capital AND country.Code=city.CountryCode ORDER BY country.Population DESC LIMIT "+input_limited+";";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryNineteen);
            // Extract region information
            ArrayList<CapitalCities> cap_world = new ArrayList<CapitalCities>();
            while (rset.next())
            {
                CapitalCities cap_wld          = new CapitalCities();
                cap_wld.cap_cit_name           = rset.getString("CityName");
                cap_wld.cap_cit_country        = rset.getString("CountryName");
                cap_wld.cap_cit_population     = rset.getString("Population");
                cap_world.add(cap_wld);
            }
            return cap_world;
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
    public void printTopNCapCities_World(ArrayList<CapitalCities> cap_world)
    {
        // Check region is not null
        if (cap_world == null)
        {
            System.out.println("There is no Capital City in the world");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s", "Capital City Name","Country Name", "Population"));
        // Loop over all capital cities in the list
        for (CapitalCities ccr : cap_world)
        {
            //print the list to check if capital cities in the world is null
            if (ccr == null)
                continue;
            String reg_string =
                    String.format("%-30s %-30s %-30s",
                            ccr.cap_cit_name,ccr.cap_cit_country, ccr.cap_cit_population);
            System.out.println(reg_string);
        }
    }
    //start
    /**
     * 21.The top N populated capital cities in a continent where N is provided by the user.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<CapitalCities> getTopNCapCities_cont(int input_limited)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 20.The top N populated capital cities in a continent where N is provided by the user.
            String strQueryNineteen =
                    "SELECT city.Name as 'CityName', country.Name as 'CountryName', country.Population FROM city INNER JOIN country WHERE city.ID = country.Capital AND country.Code=city.CountryCode ORDER BY country.Population DESC LIMIT "+input_limited+";";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryNineteen);
            // Extract region information
            ArrayList<CapitalCities> cap_cont = new ArrayList<CapitalCities>();
            while (rset.next())
            {
                CapitalCities cap_cnt          = new CapitalCities();
                cap_cnt.cap_cit_name           = rset.getString("CityName");
                cap_cnt.cap_cit_country        = rset.getString("CountryName");
                cap_cnt.cap_cit_population     = rset.getString("Population");
                cap_cont.add(cap_cnt);
            }
            return cap_cont;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top N populated capital cities in a continent where N is provided by the user.");
            return null;
        }
    }
    /**
     * 20. The top N populated capital cities in a continent where N is provided by the user.
     * Formatting the output data from the list.
     **/
    public void printTopNCapCities_cont(ArrayList<CapitalCities> cap_cont)
    {
        // Check region is not null
        if (cap_cont == null)
        {
            System.out.println("There is no Capital City in the world");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s", "Capital City Name","Country Name", "Population"));
        // Loop over all capital cities in a continent
        for (CapitalCities ccr : cap_cont)
        {
            //print the list to check if capital cities in a continent is null
            if (ccr == null)
                continue;
            String reg_string =
                    String.format("%-30s %-30s %-30s",
                            ccr.cap_cit_name,ccr.cap_cit_country, ccr.cap_cit_population);
            System.out.println(reg_string);
        }
    }

    //end

    /**
     * 31. List the population of people who speak Chinese in descending order
     * Query execution by user input and pass the array list to format the return value.
     * Function is called in ma
     **/
    public ArrayList<CountryLanguage> getCountryLanguage1(String input_language)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 31. List the population of people who speak Chinese in descending order
            String strQuerylanguage1 =
                    "SELECT countrylanguage.Language, countrylanguage.Percentage, country.Population FROM countrylanguage INNER JOIN country WHERE countrylanguage.CountryCode = country.Code AND countrylanguage.Language='"+input_language+"' ORDER BY countrylanguage.Percentage DESC;";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQuerylanguage1);
            // Extract countries information
            ArrayList<CountryLanguage> countrylanguage1 = new ArrayList<CountryLanguage>();
            while (rset.next())
            {
                CountryLanguage language1 = new CountryLanguage();
                language1.language       = rset.getString("Language");
                language1.percentage        = rset.getString("Percentage");
                language1.population        = rset.getString("Population");
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
    public void printCountryLanguage1(ArrayList<CountryLanguage> countrylanguage1)
    {
        // Check country language is not null
        if (countrylanguage1 == null)
        {
            System.out.println("No Chinese");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s", "Language", "Percentage", "Population"));
        // Loop over all countries in the list
        for (CountryLanguage cl1 : countrylanguage1)
        {
            //print language to check if an language is null
            if (cl1 == null)
                continue;
            String language_string =
                    String.format("%-30s %-30s %-30s",
                            cl1.language, cl1.percentage, cl1.population);
            System.out.println(language_string);
        }
    }

    /**
     * 31. List the population of people who speak English in descending order
     * Query execution by user input and pass the array list to format the return value.
     * Function is called in ma
     **/
    public ArrayList<CountryLanguage> getCountryLanguage2(String input_language)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 31. List the population of people who speak Chinese in descending order
            String strQueryFour =
                    "SELECT countrylanguage.Language, countrylanguage.Percentage, country.Population FROM countrylanguage INNER JOIN country WHERE countrylanguage.CountryCode = country.Code AND countrylanguage.Language='"+input_language+"' ORDER BY countrylanguage.Percentage DESC;";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryFour);
            // Extract countries information
            ArrayList<CountryLanguage> countrylanguage2 = new ArrayList<CountryLanguage>();
            while (rset.next())
            {
                CountryLanguage language2 = new CountryLanguage();
                language2.language       = rset.getString("Language");
                language2.percentage        = rset.getString("Percentage");
                language2.population        = rset.getString("Population");
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
     * 31. List the population of people who speak Chinese in descending order
     * Formatting the output data from the list.
     **/
    public void printCountryLanguage2(ArrayList<CountryLanguage> countrylanguage2)
    {
        // Check country language is not null
        if (countrylanguage2 == null)
        {
            System.out.println("No English");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s", "Language", "Percentage", "Population"));
        // Loop over all countries in the list
        for (CountryLanguage cl2 : countrylanguage2)
        {
            //print language to check if an language is null
            if (cl2 == null)
                continue;
            String language_string =
                    String.format("%-30s %-30s %-30s",
                            cl2.language, cl2.percentage, cl2.population);
            System.out.println(language_string);
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
        ArrayList<CapitalCities> capital_cities = a.getAllCapitalCities();
        a.printAllCapitalCities(capital_cities);
        System.out.println("\n");

        // All the capital cities in a continent organised by largest population to smallest.
        System.out.println("18: All the capital cities in a Oceania continent organised by largest population to smallest.\n");
        ArrayList<CapitalCities> capcit_continent = a.getAllCapitalCitiesContinents("Asia");
        a.printAllCapitalCityContinent(capcit_continent);
        System.out.println("\n");

        // All the capital cities in a region organised by largest to smallest.
        System.out.println("19.All the capital cities in a Caribbean region organised by largest to smallest.\n");
        ArrayList<CapitalCities> capcit_region = a.getAllCapitalCitiesRegions("Caribbean");
        a.printAllCapitalCityRegion(capcit_region);
        System.out.println("\n");

        // The top N populated capital cities in the world where N is provided by the user.
        System.out.println("20.The top 10 populated capital cities in the world \n");
        ArrayList<CapitalCities> Capwld = a.getTopNCapCities_World(10);
        a.printTopNCapCities_World(Capwld);
        System.out.println("\n");

        // List the population of people who speak Chinese in descending order.
        System.out.println("31: List the population of people who speak Chinese in descending order.\n");
        ArrayList<CountryLanguage> countrylanguage1 = a.getCountryLanguage1("Chinese");
        a.printCountryLanguage1(countrylanguage1);
        System.out.println("\n");

        // List the population of people who speak English in descending order.
        System.out.println("31: List the population of people who speak English in descending order.\n");
        ArrayList<CountryLanguage> countrylanguage2 = a.getCountryLanguage2("English");
        a.printCountryLanguage2(countrylanguage2);
        System.out.println("\n");


        // Disconnect from database
        a.disconnect();
    }
}