package devops.codereview;

import java.sql.*;
import java.util.ArrayList;
import java.text.NumberFormat;

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
        System.out.println(String.format("%-30s %-30s %-30s %30s %30s", "Capital City Name","Country Name", "Population", "People Not Living (%)", "People Living (%)"));
        ArrayList<PeoplePopulation> world_pop_arr = getWorldPopulation();
        PeoplePopulation wpop = world_pop_arr.get(0);
        float worldpop = Float.parseFloat(wpop.world_population);
        // Loop over all capital cities in a continent
        for (PeoplePopulation pcon : Popu_Conti)
        {
            //print the list to check if capital cities in a continent is null
            if (pcon == null)
                continue;
            float ccp = Integer.parseInt(pcon.cap_cit_population);
            float finalnotres = 100 * (ccp / worldpop);
            float finalres = 100 - finalnotres;
            String resString = finalres+"%";
            String resNotString = finalnotres+"%";

            String pcon_string =
                    String.format("%-30s %-30s %-30s %30s %30s",
                            pcon.cap_cit_name, pcon.cap_cit_country, pcon.cap_cit_population, resNotString, resString);
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
        System.out.println(String.format("%-30s %-30s %-30s %-30s %-30s", "Capital City Name","Country Name", "Population", "People Not Living (%)", "People Living (%)"));
        ArrayList<PeoplePopulation> contient_pop_arr = getRegionsPopulation();
        PeoplePopulation conpop = contient_pop_arr.get(3);
        float continentpop = Float.parseFloat(conpop.regions_population);
        // Loop over all capital cities in a region
        for (PeoplePopulation preg : Popu_Regs)
        {
            //print the list to check if capital cities in a region is null
            if (preg == null)
                continue;
            float rp = Integer.parseInt(preg.cap_cit_population);
            float finalresregion = 100 * (rp / continentpop);
            float finalnotresregions = 100 - finalresregion;
            String resNotStringRegion = finalresregion+"%";
            String resStringRegion = finalnotresregions+"%";
            String preg_string =
                    String.format("%-30s %-30s %-30s %-30s %-30s",
                            preg.cap_cit_name,preg.cap_cit_country, preg.cap_cit_population, resNotStringRegion, resStringRegion);
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
        System.out.println(String.format("%-30s %-30s %-30s %-30s %-30s", "Capital City Name","Country Name", "Population", "People Not Living(%)", "People Living(%)"));
        ArrayList<PeoplePopulation> country_pop_arr = getCountriesPopulation();
        PeoplePopulation counpop = country_pop_arr.get(140);
        float countrypop = Float.parseFloat(counpop.countries_population);
        // Loop over all capital cities in a country
        for (PeoplePopulation pcou : Popu_Coun)
        {
            //print the list to check if capital cities in a country is null
            if (pcou == null)
                continue;
            float conunp = Integer.parseInt(pcou.cap_cit_population);
            float finalrescountry = 100 * (conunp / countrypop);
            float finalnotrescountry = 100 - finalrescountry;
            String resStringCountry = finalrescountry+"%";
            String resNotStringCountry = finalnotrescountry+"%";
            String pcou_string =
                    String.format("%-30s %-30s %-30s %-30s %-30s",
                            pcou.cap_cit_name,pcou.cap_cit_country, pcou.cap_cit_population, resNotStringCountry, resStringCountry);
            System.out.println(pcou_string);
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
            ArrayList<PeoplePopulation> population_world = new ArrayList<PeoplePopulation>();
            while (rset.next())
            {
                PeoplePopulation pop_world    = new PeoplePopulation();
                //pop_world.world_name      = rset.getString("Name");
                pop_world.world_population      = rset.getString("Population");
                population_world.add(pop_world);
            }
            return population_world;
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
    public void printWorldPopulation(ArrayList<PeoplePopulation> pop_world)
    {

        // Check country is not null
        if (pop_world == null) {
            System.out.println("There is no population of the world.");
            return;
        }
        // Print header
        //System.out.println(String.format("%-40s", "Population"));
        // Loop over all capital cities in a country
        // int total_word_pop = 0;
        //NumberFormat world_res_format = NumberFormat.getInstance();
        for (PeoplePopulation wpop : pop_world) {
            //print the list to check if capital cities in a country is null
            if (wpop == null)
                continue;
            String pworld_string =
                    String.format("%-40s",
                            wpop.world_population);
            System.out.println("Total World Population: "+pworld_string);
            //System.out.println("Total World Population: "+world_res_format.format(pworld_string));
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
            ArrayList<PeoplePopulation> population_cont = new ArrayList<PeoplePopulation>();
            while (rset.next())
            {
                PeoplePopulation pop_continent   = new PeoplePopulation();
                pop_continent.continent_name = rset.getString("Continent");
                pop_continent.continent_population      = rset.getString("Population");
                population_cont.add(pop_continent);
            }
            return population_cont;
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
    public void printContinentPopulation(ArrayList<PeoplePopulation> pop_conti)
    {

        // Check country is not null
        if (pop_conti == null) {
            System.out.println("There is no population of the continent.");
            return;
        }
        // Print header
        System.out.println(String.format("%-40s %-40s", "Continent Name", "Population"));
        // Loop over all capital cities in a country
        //int total_cont_pop = 0;
        for (PeoplePopulation continent_population : pop_conti)
        {
            //print the list to check if capital cities in a country is null
            if (continent_population == null)
                continue;
            String pworld_string =
                    String.format("%-40s %-40s",
                            continent_population.continent_name, continent_population.continent_population);
            System.out.println(pworld_string);
            //total_cont_pop = Integer.parseInt(continent_population.continent_population) + total_cont_pop;
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
            ArrayList<PeoplePopulation> population_regions = new ArrayList<PeoplePopulation>();
            while (rset.next())
            {
                PeoplePopulation pop_regions   = new PeoplePopulation();
                pop_regions.regions_name      = rset.getString("Region");
                pop_regions.regions_population     = rset.getString("Population");
                population_regions.add(pop_regions);
            }
            return population_regions;
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
    public void printRegionsPopulation(ArrayList<PeoplePopulation> pop_regs)
    {

        // Check country is not null
        if (pop_regs == null) {
            System.out.println("There is no population of the regions.");
            return;
        }
        // Print header
        System.out.println(String.format("%-40s %-40s", "Region Name", "Population"));
        // Loop over population from region
        //int total_reg_pop = 0;
        for (PeoplePopulation regions_population : pop_regs)
        {
            //print the list to check if capital cities in a country is null
            if (regions_population == null)
                continue;
            String pworld_string =
                    String.format("%-40s %-40s",
                            regions_population.regions_name, regions_population.regions_population);
            System.out.println(pworld_string);
            //total_reg_pop = Integer.parseInt(regions_population.regions_population) + total_reg_pop;
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
            ArrayList<PeoplePopulation> population_countries = new ArrayList<PeoplePopulation>();
            while (rset.next())
            {
                PeoplePopulation pop_country   = new PeoplePopulation();
                pop_country.countries_name      = rset.getString("Name");
                pop_country.countries_population     = rset.getString("Population");
                population_countries.add(pop_country);
            }
            return population_countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the population of the continent.");
            return null;
        }
    }
    /**
     * 29. The population of the countries.
     * Formatting the output data from the list.
     **/
    public void printCountriesPopulation(ArrayList<PeoplePopulation> pop_contr)
    {

        // Check country is not null
        if (pop_contr == null) {
            System.out.println("There is no population of the countries.");
            return;
        }
        // Print header
        System.out.println(String.format("%-40s %-40s", "Name", "Population"));
        // Loop over population from region
        //int total_con_pop = 0;
        for (PeoplePopulation countr_population : pop_contr)
        {
            //print the list to check if capital cities in a country is null
            if (countr_population == null)
                continue;
            String pworld_string =
                    String.format("%-40s %-40s",
                            countr_population.countries_name, countr_population.countries_population);
            System.out.println(pworld_string);
            //total_con_pop = Integer.parseInt(countr_population.countries_population) + total_con_pop;
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
            ArrayList<PeoplePopulation> population_districts = new ArrayList<PeoplePopulation>();
            while (rset.next())
            {
                PeoplePopulation population_district   = new PeoplePopulation();
                population_district.district_name      = rset.getString("District");
                population_district.district_population     = rset.getString("Population");
                population_districts.add(population_district);
            }
            return population_districts;
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
    public void printDistrictsPopulation(ArrayList<PeoplePopulation> pop_dist)
    {

        // Check country is not null
        if (pop_dist == null) {
            System.out.println("There is no population of the districts.");
            return;
        }
        // Print header
        System.out.println(String.format("%-40s %-40s", "District Name", "Population"));
        // Loop over population from region
        //int total_dist_pop = 0;
        for (PeoplePopulation dst_population : pop_dist)
        {
            //print the list to check if capital cities in a country is null
            if (dst_population == null)
                continue;
            String pworld_string =
                    String.format("%-40s %-40s",
                            dst_population.district_name, dst_population.district_population);
            System.out.println(pworld_string);
            //total_dist_pop = Integer.parseInt(dst_population.district_population) + total_dist_pop;
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
            ArrayList<PeoplePopulation> population_cities = new ArrayList<PeoplePopulation>();
            while (rset.next())
            {
                PeoplePopulation population_city   = new PeoplePopulation();
                population_city.city_name      = rset.getString("Name");
                population_city.city_population     = rset.getString("Population");
                population_cities.add(population_city);
            }
            return population_cities;
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
    public void printCityPopulation(ArrayList<PeoplePopulation> pop_city)
    {

        // Check country is not null
        if (pop_city == null) {
            System.out.println("There is no population of the cities.");
            return;
        }
        // Print header
        System.out.println(String.format("%-40s %-40s", "City Name", "Population"));
        // Loop over population from region
        //int total_city_pop = 0;
        for (PeoplePopulation city_population : pop_city)
        {
            //print the list to check if capital cities in a country is null
            if (city_population == null)
                continue;
            String pworld_string =
                    String.format("%-40s %-40s",
                            city_population.city_name, city_population.city_population);
            System.out.println(pworld_string);
            //total_city_pop = Integer.parseInt(city_population.city_population) + total_city_pop;
        }
        //NumberFormat city_res_format = NumberFormat.getInstance();
        //city_res_format.setGroupingUsed(true);
        //String result_city = String.format("The total number of city population: "+city_res_format.format(total_city_pop));
        //System.out.println(result_city);
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

        // The population of the world.
        System.out.println("26. The population of the world.");
        ArrayList<PeoplePopulation> pop_world = a.getWorldPopulation();
        a.printWorldPopulation(pop_world);
        System.out.println("\n");

        // The population of the continent.
        System.out.println("27. The population of the continent.");
        ArrayList<PeoplePopulation> pop_contin = a.getContinentPopulation();
        a.printContinentPopulation(pop_contin);
        System.out.println("\n");

        // The population of the regions.
        System.out.println("28. The population of the regions.");
        ArrayList<PeoplePopulation> pop_regions = a.getRegionsPopulation();
        a.printRegionsPopulation(pop_regions);
        System.out.println("\n");

        // The population of the countries.
        System.out.println("29. The population of the countries.");
        ArrayList<PeoplePopulation> pop_countries = a.getCountriesPopulation();
        a.printCountriesPopulation(pop_countries);
        System.out.println("\n");

        // The population of the districts.
        System.out.println("30. The population of the districts.");
        ArrayList<PeoplePopulation> pop_disct = a.getDistrictPopulation();
        a.printDistrictsPopulation(pop_disct);
        System.out.println("\n");

        // The population of the cities.
        System.out.println("31. The population of the cities.");
        ArrayList<PeoplePopulation> pop_cities = a.getCityPopulation();
        a.printCityPopulation(pop_cities);
        System.out.println("\n");

        // Disconnect from database
        a.disconnect();
    }
}
