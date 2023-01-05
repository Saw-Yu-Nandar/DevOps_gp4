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
     * 7. All the cities in the world organised by largest population to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
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
                cit.setCitName(rset.getString("CityName"));
                cit.setCountryName(rset.getString("CountryName"));
                cit.setCitDistrict(rset.getString("District"));
                cit.setCitPopulation(rset.getString("Population"));
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
            String allcitString =
                    String.format("%-35s %-40s %-25s %-25s",
                            cit.getCitName(),cit.getCountryName(),cit.getCitDistrict(),cit.getCitPopulation());
            System.out.println(allcitString);
        }
    }
    /**
     * 8. All the cities in a continent organised by largest population to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<City> getAllCitiesContinent(String inputCitContinent)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 8: All the cities in a continent organised by largest population to smallest.
            String strQueryEight =
                    "SELECT city.Name as 'CityName', country.Name as 'CountryName', city.District, country.Continent, city.Population FROM city INNER JOIN country on city.CountryCode = country.Code WHERE country.Continent = '"+ inputCitContinent +"' ORDER BY city.Population DESC;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryEight);
            // Extract continent information
            ArrayList<City> continent = new ArrayList<City>();
            while (rset.next())
            {
                City conti = new City();
                conti.setCitName(rset.getString("CityName"));
                conti.setCountryName(rset.getString("CountryName"));
                conti.setCitDistrict(rset.getString("District"));
                conti.setCitCont(rset.getString("Continent"));
                conti.setCitPopulation(rset.getString("Population"));
                continent.add(conti);
            }
            return continent;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get all the cities in Asia organised by largest population to smallest.");
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
            System.out.println("There is no city in Asia.");
        }
        //System.out.println("8. All the cities in a continent organised by largest population to smallest.");
        // Print header
        System.out.println(String.format("%-35s %-40s %-25s %-25s %-25s","City Name","Country Name","District","Continent","Population"));
        // Loop over all cities in the list
        for (City c : continent)
        {
            if (c == null)
                continue;
            String allcontiString =
                    String.format("%-35s %-40s %-25s %-25s %-25s",
                            c.getCitName(),c.getCountryName(),c.getCitDistrict(),c.getCitCont(),c.getCitPopulation());
            System.out.println(allcontiString);
        }
    }
    /**
     * 9. All the cities in a region organised by largest population to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<City> getAllCitiesRegions(String inputCitRegions)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 9: All the cities in a region organised by largest population to smallest.
            String strQueryEight =
                    "SELECT city.Name as 'CityName',country.Name as 'CountryName', city.District, country.Region, city.Population FROM city INNER JOIN country on city.CountryCode = country.Code WHERE country.Region = '"+inputCitRegions+"' ORDER BY city.Population DESC;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryEight);
            // Extract regions information
            ArrayList<City> regions = new ArrayList<City>();
            while (rset.next())
            {
                City reg          = new City();
                reg.setCitName(rset.getString("CityName"));
                reg.setCountryName(rset.getString("CountryName"));
                reg.setCitDistrict(rset.getString("District"));
                reg.setCitReg(rset.getString("Region"));
                reg.setCitPopulation(rset.getString("Population"));
                regions.add(reg);
            }
            return regions;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get all the cities in Caribbean organised by largest population to smallest.");
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
            System.out.println("There is no city in Caribbean");
        }
        //System.out.println("9. All the cities in a region organised by largest population to smallest.");
        // Print header
        System.out.println(String.format("%-35s %-40s %-25s %-25s %-25s","City Name","Country Name","District","Region","Population"));
        // Loop over all cities in the list
        for (City r : regions)
        {
            if (r == null)
                continue;
            String allregString =
                    String.format("%-35s %-40s %-25s %-25s %-25s",
                            r.getCitName(),r.getCountryName(),r.getCitDistrict(),r.getCitReg(),r.getCitPopulation());
            System.out.println(allregString);
        }
    }
    /**
     * 10. All the cities in a country organised by largest population to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<City> getAllCitiesCountries(String inputCitCountries)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 10: All the cities in a country organised by largest population to smallest.
            String strQueryEight =
                    "SELECT city.Name as 'CityName',country.Name as 'CountryName',city.District, city.Population FROM country INNER JOIN city WHERE country.Code = city.CountryCode AND country.Name='"+inputCitCountries+"' ORDER BY city.Population DESC;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryEight);
            // Extract country information
            ArrayList<City> countries = new ArrayList<City>();
            while (rset.next())
            {
                City c1           = new City();
                c1.setCitName(rset.getString("CityName"));
                c1.setCountryName(rset.getString("CountryName"));
                c1.setCitDistrict(rset.getString("District"));
                c1.setCitPopulation(rset.getString("Population"));
                countries.add(c1);
            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get all the cities in Myanmar organised by largest population to smallest.");
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
            System.out.println("There is no city in Myanmar.");
        }
        //System.out.println("10. All the cities in a country organised by largest population to smallest.");
        // Print header
        System.out.println(String.format("%-35s %-40s %-25s %-25s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (City cou : countries)
        {
            if (cou == null)
                continue;
            String allcouString =
                    String.format("%-35s %-40s %-25s %-25s",
                            cou.getCitName(),cou.getCountryName(),cou.getCitDistrict(),cou.getCitPopulation());
            System.out.println(allcouString);
        }
    }
    /**
     * 11. All the cities in a district organised by largest population to smallest.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<City> getAllCitiesDistrict(String inputCitDistrict)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 11: All the cities in a district organised by largest population to smallest.
            String strQueryEight =
                    "SELECT city.Name as 'CityName', country.Name as 'CountryName', city.District as 'District', city.Population FROM country INNER JOIN city WHERE city.District='"+inputCitDistrict+"' AND country.Code=city.CountryCode ORDER BY city.Population DESC;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryEight);
            // Extract city information
            ArrayList<City> district = new ArrayList<City>();
            while (rset.next())
            {
                City dist         = new City();
                dist.setCitName(rset.getString("CityName"));
                dist.setCountryName(rset.getString("CountryName"));
                dist.setCitDistrict(rset.getString("District"));
                dist.setCitPopulation(rset.getString("Population"));
                district.add(dist);
            }
            return district;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get all the cities in Queensland organised by largest population to smallest.");
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
            System.out.println("There is no city in Queensland");
        }
        //System.out.println("11. All the cities in a district organised by largest population to smallest.");
        // Print header
        System.out.println(String.format("%-35s %-40s %-25s %-25s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (City di : district)
        {
            if (di == null)
                continue;
            String alldistString =
                    String.format("%-35s %-40s %-25s %-25s",
                            di.getCitName(),di.getCountryName(),di.getCitDistrict(),di.getCitPopulation());
            System.out.println(alldistString);
        }
    }
    /**
     * 12. The top N populated cities in the world where N is provided by the user.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<City> getTopNPopulatedCities(int inputTopWorld)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 12: The top N populated cities in the world where N is provided by the user.
            String strQueryEight =
                    "SELECT city.Name as 'Cityname', country.Name as 'Countryname', city.District, city.Population FROM city INNER JOIN country WHERE city.CountryCode=country.Code ORDER BY city.Population DESC LIMIT "+inputTopWorld+";";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryEight);
            // Extract cities information
            ArrayList<City> worlds = new ArrayList<City>();
            while (rset.next())
            {
                City world = new City();
                world.setCitName(rset.getString("Cityname"));
                world.setCountryName(rset.getString("Countryname"));
                world.setCitDistrict(rset.getString("District"));
                world.setCitPopulation(rset.getString("Population"));
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
            System.out.println("There is no top 10 populated cities in the world!");
        }
        //System.out.println("12. The top N populated cities in the world where N is provided by the user.");
        // Print header
        System.out.println(String.format("%-35s %-40s %-25s %-25s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (City w : wld)
        {
            if (w == null)
                continue;
            String topwString =
                    String.format("%-35s %-40s %-25s %-25s",
                            w.getCitName(),w.getCountryName(),w.getCitDistrict(),w.getCitPopulation());
            System.out.println(topwString);
        }
    }
    /**
     * 13. The top N populated cities in a continent where N is provided by the user.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<City>getTopNPopulatedContinent(String inputTopContinent, int inputLimited)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 13: The top N populated cities in the continent where N is provided by the user.
            String strQueryEight =
                    "SELECT city.Name as 'CityName', country.Name as 'CountryName', city.District, country.Continent, city.Population FROM city INNER JOIN country on city.CountryCode = country.Code WHERE country.Continent = '"+inputTopContinent+"' ORDER BY city.Population DESC LIMIT "+ inputLimited +";";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryEight);
            // Extract city information
            ArrayList<City> continent = new ArrayList<City>();
            while (rset.next())
            {
                City conti            = new City();
                conti.setCitName(rset.getString("Cityname"));
                conti.setCountryName(rset.getString("Countryname"));
                conti.setCitDistrict(rset.getString("District"));
                conti.setCitCont(rset.getString("Continent"));
                conti.setCitPopulation(rset.getString("Population"));
                continent.add(conti);
            }
            return continent;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top 10 populated cities in Europe.");
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
            System.out.println("There is no top 10 populated city in Europe.");
        }
        //System.out.println("13. The top N populated cities in a continent where N is provided by the user.");
        // Print header
        System.out.println(String.format("%-35s %-40s %-25s %-25s %-25s","City Name","Country Name","District","Continent","Population"));
        // Loop over all cities in the list
        for (City cont : cnt)
        {
            if (cont == null)
                continue;
            String topcontString =
                    String.format("%-35s %-40s %-25s %-25s %-25s",
                            cont.getCitName(),cont.getCountryName(),cont.getCitDistrict(),cont.getCitCont(),cont.getCitPopulation());
            System.out.println(topcontString);
        }
    }
    /**
     * 14. The top N populated cities in a region where N is provided by the user.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<City> getTopNPopulatedRegion(String inputTopRegion, int inputLimited)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 14: The top N populated cities in a region where N is provided by the user.
            String strQueryEight =
                    "SELECT country.Region, city.Name as 'CityName', country.Name as 'CountryName', city.District, country.Region, city.Population FROM city INNER JOIN country on city.CountryCode = country.Code WHERE country.Region = '"+inputTopRegion+"' ORDER BY city.Population DESC LIMIT "+ inputLimited +";";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryEight);
            // Extract city information
            ArrayList<City> region = new ArrayList<City>();
            while (rset.next())
            {
                City reg          = new City();
                reg.setCitName(rset.getString("Cityname"));
                reg.setCountryName(rset.getString("Countryname"));
                reg.setCitDistrict(rset.getString("District"));
                reg.setCitReg(rset.getString("Region"));
                reg.setCitPopulation(rset.getString("Population"));
                region.add(reg);
            }
            return region;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top 10 populated cities in Caribbean.");
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
            System.out.println("There is no top 10 populated city in Caribbean");
        }
        //System.out.println("14. The top N populated cities in a region where N is provided by the user.");
        // Print header
        System.out.println(String.format("%-35s %-40s %-25s %-25s %-25s","City Name","Country Name","District","Region","Population"));
        // Loop over all cities in the list
        for (City cont : regn)
        {
            if (cont == null)
                continue;
            String topregString =
                    String.format("%-35s %-40s %-25s %-25s %-25s",
                            cont.getCitName(),cont.getCountryName(),cont.getCitDistrict(),cont.getCitReg(),cont.getCitPopulation());
            System.out.println(topregString);
        }
    }

    /**
     * 15. The top N populated cities in a country where N is provided by the user.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<City> getTopNPopulatedCountries(String inputTopCountry, int inputLimited)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 15: The top N populated cities in a country where N is provided by the user.
            String strQueryEight =
                    "SELECT city.Name as 'CityName',country.Name as 'CountryName',city.District, city.Population FROM city INNER JOIN country on city.CountryCode = country.Code WHERE country.Name = '"+inputTopCountry+"' ORDER BY city.Population DESC LIMIT "+ inputLimited +";";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryEight);
            // Extract city information
            ArrayList<City> country = new ArrayList<City>();
            while (rset.next())
            {
                City cty = new City();
                cty.setCitName(rset.getString("CityName"));
                cty.setCountryName(rset.getString("CountryName"));
                cty.setCitDistrict(rset.getString("District"));
                cty.setCitPopulation(rset.getString("Population"));
                country.add(cty);
            }
            return country;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top 10 populated cities in Argentina.");
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
            System.out.println("There is no top 10 populated city in Argentina.");
        }
        //System.out.println("15. The top N populated cities in a country where N is provided by the user.");
        // Print header
        System.out.println(String.format("%-35s %-40s %-25s %-30s","Country","City Name","District","Population"));
        // Loop over all cities in the list
        for (City cont : count)
        {
            if (cont == null)
                continue;
            String topcouString =
                    String.format("%-35s %-40s %-25s %-30s",
                            cont.getCitName(),cont.getCountryName(),cont.getCitDistrict(),cont.getCitPopulation());
            System.out.println(topcouString);
        }
    }

    /**
     * 16. The top N populated cities in a district where N is provided by the user.
     * Query execution and pass the array list to format the return value.
     * Function is called in main.
     **/
    public ArrayList<City> getTopNPopulatedDistrict(String inputTopDistrict, int inputLimited)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //Query 13: The top N populated cities in a district  where N is provided by the user.
            String strQueryEight =
                    "SELECT city.Name as 'CityName', country.name as 'CountryName', city.District, city.Population FROM city INNER JOIN country on city.CountryCode = country.Code WHERE city.District = '"+inputTopDistrict+"' ORDER BY city.Population DESC LIMIT "+ inputLimited +";";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strQueryEight);
            // Extract city information
            ArrayList<City> dist = new ArrayList<City>();
            while (rset.next())
            {
                City dis = new City();
                dis.setCitName(rset.getString("Cityname"));
                dis.setCountryName(rset.getString("Countryname"));
                dis.setCitDistrict(rset.getString("District"));
                dis.setCitPopulation(rset.getString("Population"));
                dist.add(dis);
            }
            return dist;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top 10 populated cities in Zuid-Holland.");
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
            System.out.println("There is no top 10 populated city in Zuid-Holland");
        }
        //System.out.println("16. The top N populated cities in a district where N is provided by the user.");
        // Print header
        System.out.println(String.format("%-30s %-40s %-25s %-30s","City Name","Country Name","District","Population"));
        // Loop over all cities in the list
        for (City cont : dists)
        {
            if (cont == null)
                continue;
            String topdistString =
                    String.format("%-30s %-40s %-25s %-30s",
                            cont.getCitName(),cont.getCountryName(),cont.getCitDistrict(),cont.getCitPopulation());
            System.out.println(topdistString);
        }

    }


    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();
        // Connect to database
        if(args.length < 1){
            a.connect("localhost:33060", 0);
        }else{
            a.connect("db:3306", 30000);
        }
        // Display all the cities in the world organised by largest population to smallest.
        System.out.println("7: All the cities in the world organised by largest population to smallest.\n");
        ArrayList<City> cou = a.getAllCities();
        a.printCities(cou);
        System.out.println("\n");

        // Display all the cities in a continent organised by largest population to smallest.
        System.out.println("8. All the cities in Asia organised by largest population to smallest.\n");
        ArrayList<City> continent = a.getAllCitiesContinent("Asia");
        a.printContinents(continent);
        System.out.println("\n");

        // Display all the cities in a region organised by largest population to smallest.
        System.out.println("9: All the cities in Caribbean organised by largest population to smallest.\n");
        ArrayList<City> regi = a.getAllCitiesRegions("Caribbean");
        a.printRegions(regi);
        System.out.println("\n");

        // Display all the cities in a country organised by largest population to smallest.
        System.out.println("10: All the cities in Myanmar organised by largest population to smallest.\n");
        ArrayList<City> countries = a.getAllCitiesCountries("Myanmar");
        a.printCountries(countries);
        System.out.println("\n");

        // Diaplay all the cities in a country organised by largest population to smallest.
        System.out.println("11: All the cities in Queensland organised by largest population to smallest.\n");
        ArrayList<City> dist = a.getAllCitiesDistrict("Queensland");
        a.printDistrict(dist);
        System.out.println("\n");

        // Display the top N populated cities in the world where N is provided by the user.
        System.out.println("12: The top 10 populated cities in the world.\n");
        ArrayList<City> city = a.getTopNPopulatedCities(10);
        a.printTopNWorlds(city);
        System.out.println("\n");

        // Display the top N populated cities in a continent where N is provided by the user.
        System.out.println("13. The top 10 populated cities in Europe.\n");
        ArrayList<City> topcnt = a.getTopNPopulatedContinent("Europe",10);
        a.printTopNContinent(topcnt);
        System.out.println("\n");

        // Display the top N populated cities in a region where N is provided by the user.
        System.out.println("14: The top 10 populated cities in Caribbean.\n");
        ArrayList<City> regs = a.getTopNPopulatedRegion("Caribbean",10);
        a.printTopNRegion(regs);
        System.out.println("\n");

        // Display the top N populated cities in a country where N is provided by the user.
        System.out.println("15: The top 10 populated cities in Argentina.\n");
        ArrayList<City> ctys = a.getTopNPopulatedCountries("Argentina",10);
        a.printTopNCountries(ctys);
        System.out.println("\n");

        // Display the top N populated cities in a district where N is provided by the user.
        System.out.println("16: The top 10 populated cities in Zuid-Holland.\n");
        ArrayList<City> district = a.getTopNPopulatedDistrict("Zuid-Holland",10);
        a.printTopNDistrict(district);
        System.out.println("\n");
        // Disconnect from database
        a.disconnect();
    }
}