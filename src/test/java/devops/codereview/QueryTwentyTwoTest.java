package devops.codereview;

//Unit testing for the top N populated capital cities in a region where N is provided by the user.
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryTwentyTwoTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printTopNCapCities_RegTestEmpty()
    {
        ArrayList<CapitalCities> cap_region = new ArrayList<CapitalCities>();
        app.printTopNCapCities_Reg(cap_region);
    }

    //print a list with a null value
    @Test
    void printTopNCapCities_contNull()
    {
        ArrayList<CapitalCities> cap_region = new ArrayList<CapitalCities>();
        cap_region.add(null);
        app.printTopNCapCities_Reg(cap_region);
    }

    @Test
    void printTopNCapCities_cont()
    {
        ArrayList<CapitalCities> cap_region = new ArrayList<CapitalCities>();
        CapitalCities capReg            = new CapitalCities();
        capReg.cap_cit_name             = "Ankara";
        capReg.cap_cit_country          = "Turkey";
        capReg.cap_cit_population       = "66591000";
        cap_region.add(capReg);
        app.printTopNCapCities_Reg(cap_region);
    }
}
