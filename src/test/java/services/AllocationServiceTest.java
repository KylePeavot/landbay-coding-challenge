package test.java.services;

import main.java.entities.Allocation;
import main.java.entities.Funder;
import main.java.entities.Mortgage;
import main.java.services.AllocationService;
import main.java.services.MortgageService;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class AllocationServiceTest {

    private AllocationService allocationService;

    @Before
    public void setUp() {
        allocationService = new AllocationService();
    }

    @Test
    public void testDistributionWithEqualValueMortgages() {
        var mortgageA = new Mortgage(1, 100, "P1", "testPostcode");
        var mortgageB = new Mortgage(2, 100, "P2", "testPostcode");
        var mortgageC = new Mortgage(3, 100, "P3", "testPostcode");
        var mortgageD = new Mortgage(4, 100, "P4", "testPostcode");

        var mortgages = new ArrayList<>(List.of(mortgageA, mortgageB, mortgageC, mortgageD));

        var funderA = new Funder("Funder A", List.of("P1", "P3"));
        var funderB = new Funder("Funder B", List.of("P2", "P4"));

        var funders = new ArrayList<>(List.of(funderA, funderB));

        var allocations = allocationService.distributeMortgagesAcrossFunders(mortgages, funders);

        var funderAAllocation = allocations.stream().filter(allocation -> allocation.getFunder() == funderA).findFirst().get();
        var funderBAllocation = allocations.stream().filter(allocation -> allocation.getFunder() == funderB).findFirst().get();

        assertTrue(funderAAllocation.totalMoneyDeployed() == 200);
        assertTrue(funderBAllocation.totalMoneyDeployed() == 200);

    }

    @Test
    public void testDistributionWithUnequalMortgages() {
        var mortgageA = new Mortgage(1, 25, "P1", "testPostcode");
        var mortgageB = new Mortgage(2, 75, "P2", "testPostcode");
        var mortgageC = new Mortgage(3, 25, "P3", "testPostcode");
        var mortgageD = new Mortgage(4, 75, "P4", "testPostcode");

        var mortgages = new ArrayList<>(List.of(mortgageA, mortgageB, mortgageC, mortgageD));

        var funderA = new Funder("Funder A", List.of("P1", "P2", "P3", "P4"));
        var funderB = new Funder("Funder B", List.of("P1", "P2", "P3", "P4"));

        var funders = new ArrayList<>(List.of(funderA, funderB));

        var allocations = allocationService.distributeMortgagesAcrossFunders(mortgages, funders);

        var funderAAllocation = allocations.stream().filter(allocation -> allocation.getFunder() == funderA).findFirst().get();
        var funderBAllocation = allocations.stream().filter(allocation -> allocation.getFunder() == funderB).findFirst().get();

        assertTrue(funderAAllocation.totalMoneyDeployed() == 100);
        assertTrue(funderBAllocation.totalMoneyDeployed() == 100);
    }
}