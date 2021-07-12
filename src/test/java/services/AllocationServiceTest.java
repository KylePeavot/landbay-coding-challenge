package test.java.services;

import main.java.entities.Allocation;
import main.java.entities.Funder;
import main.java.entities.Mortgage;
import main.java.services.AllocationService;
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
    public void testSimpleDistribution() {
        //ensure that 4 sets of 100 moneys are distributed evenly across 2 funders

    }

    @Test
    public void testComplexDistribution() {
        //test similar functionality to use case for challenge
    }


    @Test
    public void testFairnessReturnsOneIfAllFundersDeploySameMoney() {
        var allocationA = new Allocation(new Funder("testFunderA", Collections.emptyList()));
        var allocationB = new Allocation(new Funder("testFunderB", Collections.emptyList()));
        var allocationC = new Allocation(new Funder("testFunderC", Collections.emptyList()));
        var allocationD = new Allocation(new Funder("testFunderD", Collections.emptyList()));

        allocationA.addToFundedMortgages(new Mortgage(1, 100, "testProduct", "testPostcode"));
        allocationB.addToFundedMortgages(new Mortgage(2, 100, "testProduct", "testPostcode"));
        allocationC.addToFundedMortgages(new Mortgage(3, 100, "testProduct", "testPostcode"));
        allocationD.addToFundedMortgages(new Mortgage(4, 100, "testProduct", "testPostcode"));

        var allocations = new ArrayList<>(List.of(allocationA, allocationB, allocationC, allocationD));

        double fairness = allocationService.fairness(allocations);

        assertEquals(1.0, fairness, 0.0);
    }

    @Test
    public void testFairnessIsWorseForPoorlyDistributedDeployedMoney() {
        var fairAllocationA = new Allocation(new Funder("testFunderFairA", Collections.emptyList()));
        var fairAllocationB = new Allocation(new Funder("testFunderFairB", Collections.emptyList()));
        var fairAllocationC = new Allocation(new Funder("testFunderFairC", Collections.emptyList()));
        var fairAllocationD = new Allocation(new Funder("testFunderFairD", Collections.emptyList()));

        fairAllocationA.addToFundedMortgages(new Mortgage(1, 60, "testProduct", "testPostcode"));
        fairAllocationB.addToFundedMortgages(new Mortgage(2, 33, "testProduct", "testPostcode"));
        fairAllocationC.addToFundedMortgages(new Mortgage(3, 27, "testProduct", "testPostcode"));
        fairAllocationD.addToFundedMortgages(new Mortgage(4, 67, "testProduct", "testPostcode"));

        //An good distribution of deployed money
        var fairAllocations = new ArrayList<>(List.of(fairAllocationA, fairAllocationB, fairAllocationC, fairAllocationD));

        double fairFairness = allocationService.fairness(fairAllocations);

        var unfairAllocationA = new Allocation(new Funder("testFunderFairA", Collections.emptyList()));
        var unfairAllocationB = new Allocation(new Funder("testFunderFairB", Collections.emptyList()));
        var unfairAllocationC = new Allocation(new Funder("testFunderFairC", Collections.emptyList()));
        var unfairAllocationD = new Allocation(new Funder("testFunderFairD", Collections.emptyList()));

        unfairAllocationA.addToFundedMortgages(new Mortgage(1, 20, "testProduct", "testPostcode"));
        unfairAllocationB.addToFundedMortgages(new Mortgage(2, 40, "testProduct", "testPostcode"));
        unfairAllocationC.addToFundedMortgages(new Mortgage(3, 60, "testProduct", "testPostcode"));
        unfairAllocationD.addToFundedMortgages(new Mortgage(4, 80, "testProduct", "testPostcode"));

        //An bad distribution of deployed money
        var unfairAllocations = new ArrayList<>(List.of(unfairAllocationA, unfairAllocationB, unfairAllocationC, unfairAllocationD));

        double unfairFairness = allocationService.fairness(unfairAllocations);

        assertTrue(fairFairness > unfairFairness);
    }

}