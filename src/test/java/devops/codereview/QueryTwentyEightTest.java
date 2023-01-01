package devops.codereview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryTwentyEightTest {
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printPopRegionsTestEmpty()
    {
        ArrayList<PeoplePopulation> popRegEmp = new ArrayList<PeoplePopulation>();
        app.printRegionsPopulation(popRegEmp);
    }

    //print a list with a null value
    @Test
    void printPopRegionsNull()
    {
        ArrayList<PeoplePopulation> popRegNull = new ArrayList<PeoplePopulation>();
        popRegNull.add(null);
        app.printRegionsPopulation(popRegNull);
    }

    @Test
    void printPopRegions()
    {
        ArrayList<PeoplePopulation> popRegPrint = new ArrayList<PeoplePopulation>();
        PeoplePopulation popReg   = new PeoplePopulation();
        popReg.setRegionsName("Yerevan");
        popReg.setRegionsPopulation("3520000");
        popRegPrint.add(popReg);
        app.printRegionsPopulation(popRegPrint);
    }
}
