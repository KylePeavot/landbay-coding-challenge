package test.java.services;

import main.java.entities.Funder;
import main.java.entities.Mortgage;
import main.java.services.FunderService;
import main.java.services.MortgageService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MortgageServiceTest {

    private MortgageService mortgageService;

    @Before
    public void setUp(){
        mortgageService = new MortgageService();
    }

    @Test()
    public void testGetFromCsvReturnsListOfFunders() {
        List<Mortgage> mortgages = mortgageService.getFromCsv("src/test/testResources/test_mortgages.csv");

        assertTrue(mortgages.size() > 0);
    }

}