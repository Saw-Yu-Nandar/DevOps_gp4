package devops.codereview;

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
        ArrayList<PeoplePopulation> popDstEmp = new ArrayList<PeoplePopulation>();
        app.printDistrictsPopulation(popDstEmp);
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
        PeoplePopulation popDst   = new PeoplePopulation();
        popDst.setDistrictName("Apeldoorn");
        popDst.setDistrictPopulation("153491");
        popDstPrint.add(popDst);
        app.printDistrictsPopulation(popDstPrint);
    }
}
