package devops.codereview;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
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

            pcon.setLivingPopContinent(strlivingconper);
            pcon.setNotLivingPopContinent(strnotlivingconper);

            String pconString =
                    String.format("%-20s %-28s %-25s %-25s %-25s",
                            pcon.getContinentName(),pcon.getContinentPopulation(), pcon.getCityPopulation(), strnotlivingconper, strlivingconper);
            System.out.println(pconString);
        }
    }

    /**
     * Outputs to Markdown
     * 23. The population of people, people living in cities, and people not living in cities in each continent.
     * @param populationContinent
     */
    public void outputPopulationContinent(ArrayList<PeoplePopulation> populationContinent, String AllCountries) {
        // Check employees is not null
        if (populationContinent == null) {
            System.out.println("No population");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| Continent Name | Continent Total Population | City Total Population | People Not Living (%) | People Living (%) |\r\n");
        sb.append("| --- | --- | --- | --- | --- |\r\n");
        // Loop over all employees in the list
        for (PeoplePopulation popcontinent : populationContinent) {
            if (popcontinent == null) continue;
            sb.append("| " + popcontinent.getContinentName() + " | " +
                    popcontinent.getContinentPopulation() + " | " + popcontinent.getCityPopulation() + " | " +
                    popcontinent.getLivingPopContinent() + " | " + popcontinent.getNotLivingPopContinent() + " | "
                    + "|\r\n");
        }
        try {
            new File("./reports/").mkdir();
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./reports/" + populationContinent)));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
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

        // The population of people, people living in cities, and people not living in cities in each continent.
        System.out.println("23.The population of people, people living in cities, and people not living in cities in each continent. \n");
        ArrayList<PeoplePopulation> popCont = a.getPopulatedPeopleContinent();
        a.printPopulatedPeopleConitnent(popCont);
        a.outputPopulationContinent(popCont, "PeoplePopulationContinent.md");
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
