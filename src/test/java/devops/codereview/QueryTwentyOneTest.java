package devops.codereview;

//Unit testing for the top N populated capital cities in a continent where N is provided by the user.
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryTwentyOneTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printTopNCapCities_contTestEmpty()
    {
        ArrayList<CapitalCities> cap_cont = new ArrayList<CapitalCities>();
        app.printTopNCapCities_cont(cap_cont);
    }

    //print a list with a null value
    @Test
    void printTopNCapCities_contNull()
    {
        ArrayList<CapitalCities> cap_cont = new ArrayList<CapitalCities>();
        cap_cont.add(null);
        app.printTopNCapCities_cont(cap_cont);
    }

    @Test
    void printTopNCapCities_cont()
    {
        ArrayList<CapitalCities> cap_cont = new ArrayList<CapitalCities>();
        CapitalCities capCont            = new CapitalCities();
        capCont.setCap_cit_name("Washington");
        capCont.setCap_cit_country("United States");
        capCont.setCap_cit_population("278357000");
        cap_cont.add(capCont);
        app.printTopNCapCities_cont(cap_cont);
    }
}
