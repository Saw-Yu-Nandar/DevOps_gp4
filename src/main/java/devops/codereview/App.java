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
     * 23.The population of people, people living in cities, and people not living in cities in each continent.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<PeoplePopulation> getPopulatedPeopleContinent(String intput_pop_conti, int input_limited)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 23.The population of people, people living in cities, and people not living in cities in each continent.
            String strQueryTwentyThree =
                    "SELECT city.Name as 'CityName', country.Name as 'CountryName', country.Population FROM city INNER JOIN country WHERE city.ID = country.Capital AND country.Code=city.CountryCode AND country.Continent='"+intput_pop_conti+"' ORDER BY country.Population DESC LIMIT "+input_limited+";";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryTwentyThree);
            // Extract continent information
            ArrayList<PeoplePopulation> population_cont = new ArrayList<PeoplePopulation>();
            while (rset.next())
            {
                PeoplePopulation pop_cont   = new PeoplePopulation();
                pop_cont.cap_cit_name       = rset.getString("CityName");
                pop_cont.cap_cit_country    = rset.getString("CountryName");
                pop_cont.cap_cit_population = rset.getString("Population");
                population_cont.add(pop_cont);
            }
            return population_cont;
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
    public void printPopulatedPeopleConitnent(ArrayList<PeoplePopulation> Popu_Conti)
    {
        // Check continent is not null
        if (Popu_Conti == null)
        {
            System.out.println("There is no population of people, people living in cities, and people not living in cities in each continent.");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s", "Capital City Name","Country Name", "Population"));
        // Loop over all capital cities in a continent
        for (PeoplePopulation pcon : Popu_Conti)
        {
            //print the list to check if capital cities in a continent is null
            if (pcon == null)
                continue;
            String pcon_string =
                    String.format("%-30s %-30s %-30s",
                            pcon.cap_cit_name,pcon.cap_cit_country, pcon.cap_cit_population);
            System.out.println(pcon_string);
        }
    }

    /**
     * 24.The population of people, people living in cities, and people not living in cities in each region.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<PeoplePopulation> getPopulatedPeopleRegions(String intput_pop_reg, int input_limited)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 24.The population of people, people living in cities, and people not living in cities in each region.
            String strQueryTwentyFour =
                    "SELECT city.Name as 'CityName', country.Name as 'CountryName', country.Population FROM city INNER JOIN country WHERE city.ID = country.Capital AND country.Code=city.CountryCode AND country.Region='"+intput_pop_reg+"' ORDER BY country.Population DESC LIMIT "+input_limited+";";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryTwentyFour);
            // Extract region information
            ArrayList<PeoplePopulation> population_reg = new ArrayList<PeoplePopulation>();
            while (rset.next())
            {
                PeoplePopulation pop_reg    = new PeoplePopulation();
                pop_reg.cap_cit_name        = rset.getString("CityName");
                pop_reg.cap_cit_country     = rset.getString("CountryName");
                pop_reg.cap_cit_population  = rset.getString("Population");
                population_reg.add(pop_reg);
            }
            return population_reg;
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
    public void printPopulatedPeopleRegions(ArrayList<PeoplePopulation> Popu_Regs)
    {
        // Check region is not null
        if (Popu_Regs == null)
        {
            System.out.println("There is no population of people, people living in cities, and people not living in cities in each region.");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s", "Capital City Name","Country Name", "Population"));
        // Loop over all capital cities in a region
        for (PeoplePopulation preg : Popu_Regs)
        {
            //print the list to check if capital cities in a region is null
            if (preg == null)
                continue;
            String preg_string =
                    String.format("%-30s %-30s %-30s",
                            preg.cap_cit_name,preg.cap_cit_country, preg.cap_cit_population);
            System.out.println(preg_string);
        }
    }
    /**
     * 25.The population of people, people living in cities, and people not living in cities in each country.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<PeoplePopulation> getPopulatedPeopleCountry(String intput_pop_cou, int input_limited)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 25.The population of people, people living in cities, and people not living in cities in each country.
            String strQueryTwentyFive =
                    "SELECT city.Name as 'CityName', country.Name as 'CountryName', country.Population FROM city INNER JOIN country WHERE city.ID = country.Capital AND country.Code=city.CountryCode AND country.Name='"+intput_pop_cou+"' ORDER BY country.Population DESC LIMIT "+input_limited+";";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryTwentyFive);
            // Extract country information
            ArrayList<PeoplePopulation> population_cou = new ArrayList<PeoplePopulation>();
            while (rset.next())
            {
                PeoplePopulation pop_cou    = new PeoplePopulation();
                pop_cou.cap_cit_name        = rset.getString("CityName");
                pop_cou.cap_cit_country     = rset.getString("CountryName");
                pop_cou.cap_cit_population  = rset.getString("Population");
                population_cou.add(pop_cou);
            }
            return population_cou;
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
    public void printPopulatedPeopleCountry(ArrayList<PeoplePopulation> Popu_Coun)
    {
        // Check country is not null
        if (Popu_Coun == null)
        {
            System.out.println("There is no population of people, people living in cities, and people not living in cities in each country.");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s", "Capital City Name","Country Name", "Population"));
        // Loop over all capital cities in a country
        for (PeoplePopulation pcou : Popu_Coun)
        {
            //print the list to check if capital cities in a country is null
            if (pcou == null)
                continue;
            String pcou_string =
                    String.format("%-30s %-30s %-30s",
                            pcou.cap_cit_name,pcou.cap_cit_country, pcou.cap_cit_population);
            System.out.println(pcou_string);
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

        // The population of people, people living in cities, and people not living in cities in each continent.
        System.out.println("23.The population of people, people living in cities, and people not living in cities in each continent. \n");
        ArrayList<PeoplePopulation> PopCont = a.getPopulatedPeopleContinent("Europe",10);
        a.printPopulatedPeopleConitnent(PopCont);
        System.out.println("\n");

        // The population of people, people living in cities, and people not living in cities in each region.
        System.out.println("24.The population of people, people living in cities, and people not living in cities in each region. \n");
        ArrayList<PeoplePopulation> PopReg = a.getPopulatedPeopleRegions("Southern Europe",10);
        a.printPopulatedPeopleRegions(PopReg);
        System.out.println("\n");

        // The population of people, people living in cities, and people not living in cities in each country.
        System.out.println("25.The population of people, people living in cities, and people not living in cities in each country. \n");
        ArrayList<PeoplePopulation> PopCoun = a.getPopulatedPeopleCountry("Myanmar",10);
        a.printPopulatedPeopleCountry(PopCoun);
        System.out.println("\n");

        // Disconnect from database
        a.disconnect();
    }
}