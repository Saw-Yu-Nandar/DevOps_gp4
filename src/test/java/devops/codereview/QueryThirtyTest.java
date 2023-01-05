package devops.codereview;
//Unit testing for the population of a district.
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryThirtyTest {
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printPopDistrictTestEmpty()
    {
        ArrayList<PeoplePopulation> popDstEmpt = new ArrayList<PeoplePopulation>();
        app.printDistrictsPopulation(popDstEmpt);
    }

    //print a list with a null value
    @Test
    void printPopDistrictNull()
    {
        ArrayList<PeoplePopulation> popDstNull = new ArrayList<PeoplePopulation>();
        popDstNull.add(null);
        app.printDistrictsPopulation(popDstNull);
    }

    @Test
    void printPopDistrict()
    {
        ArrayList<PeoplePopulation> popDstPrint = new ArrayList<PeoplePopulation>();
        PeoplePopulation popDstp   = new PeoplePopulation();
        popDstp.setDistrictName("Dubai");
        popDstp.setDistrictPopulation("669181");
        popDstPrint.add(popDstp);
        app.printDistrictsPopulation(popDstPrint);
    }
}
