package main.java.services;

import main.java.entities.Allocation;
import main.java.entities.Funder;
import main.java.entities.Mortgage;

import java.util.*;
import java.util.stream.Collectors;

/**
 * For allocating mortgages to funders and ensuring a fair distribution of those mortgages
 */
public class AllocationService {

    /**
     * attempts a simple allocation of mortgages by taking the highest value mortgage and giving it to the funder with the lowest amount deployed
     * @param mortgages
     * @param funders
     * @return
     */
    public List<Allocation> distributeMortgagesAcrossFunders(List<Mortgage> mortgages, List<Funder> funders) {
        //Sort the mortgages by loan amount highest to lowest
        mortgages.sort(Mortgage::compareTo);

        //A list of all products that have been allocated
        List<String> allocatedProducts = new ArrayList<>();
        List<Allocation> allocations = funders.stream().map(Allocation::new).collect(Collectors.toList());

        //for each mortgage
        for (var mortgage : mortgages) {
            //if the product for a mortgage hasn't been allocated yet
            if (!allocatedProducts.contains(mortgage.getProductId())) {
                //find the funders willing to buy it
                List<Allocation> potentialAllocationsForMortgage = allocations.stream()
                        .filter(allocation -> allocation.getFunder().getDesiredProducts().contains(mortgage.getProductId()))
                        .collect(Collectors.toList());

                //if there exists a funder willing to buy the product on the current mortgage
                if (potentialAllocationsForMortgage.size() > 0) {
                    //Give the mortgage to the funder with the least money deployed
                    Allocation leastDeployedMoney = potentialAllocationsForMortgage.stream()
                        .min(Allocation::compareTo)
                        .get();

                    leastDeployedMoney.addToFundedMortgages(mortgage);

                    allocatedProducts.add(mortgage.getProductId());
                }
            }
        }

        allocations = distributeMortgagesFairly(allocations, 0.6, 20);

        return allocations;
    }

    /**
     * Using the fairness calculator, repeatedly take a mortgage from the funder with the most money deployed and
     * give it to the funder with the least deployed until either the limit or fairness value has been reached
     * @param allocations the current allocations
     * @param acceptableFairness a value of fairness that allocations needs to reach in order for it to be fair
     * @param maxCycles the maximum number of times to do the while loop
     * @return
     */
    public List<Allocation> distributeMortgagesFairly(List<Allocation> allocations, double acceptableFairness, int maxCycles) {
        allocations.sort(Allocation::compareTo);

        Allocation mostDeployedAllocation;
        Allocation leastDeployedAllocation;

        int cyclesCompleted = 0;
        double fairness = fairness(allocations);

        while (fairness < acceptableFairness && cyclesCompleted < maxCycles) {
            boolean mortgageTransferred = false;

            //attempt to move a mortgage from the funder with the most deployed to the funder with the least deployed
            //if no swaps performed, check the funder with the second most deployed etc until all funders checked
            //if no swaps performed, attempt to give a mortgage to the funder with the second least deployed and so on until all funders checked for swaps
            for (int leastDeployedIndex = 0; leastDeployedIndex < allocations.size() && !mortgageTransferred; leastDeployedIndex++) {
                leastDeployedAllocation = allocations.get(leastDeployedIndex);
                for (int mostDeployedIndex = allocations.size() - 1; mostDeployedIndex > leastDeployedIndex && !mortgageTransferred; mostDeployedIndex--) {
                    mostDeployedAllocation = allocations.get(mostDeployedIndex);

                    //for all mortgages funded by the funder with the most deployed
                    for (int i = 0; i < mostDeployedAllocation.getFundedMortgages().size() - 1 && !mortgageTransferred; i++) {
                        Mortgage mortgageToGive = mostDeployedAllocation.getFundedMortgages().get(i);
                        //if one of their mortgages can be given to the funder with the least deployed, then do so and set the flag to trues
                        if (leastDeployedAllocation.getFunder().getDesiredProducts().contains(mortgageToGive.getProductId())) {
                            leastDeployedAllocation.addToFundedMortgages(mortgageToGive);
                            mostDeployedAllocation.removeFromFundedMortgages(mortgageToGive);
                            mortgageTransferred = true;
                        }
                    }
                }
            }
            fairness = fairness(allocations);
            cyclesCompleted++;
        }

        return allocations;
    }

    /**
     * Finds how fair the loan distribution is across funders by averaging division of the least money deployed and
     * most money deployed and then averaging those values.
     *
     * 1 represents perfectly fair, the more unfair the distribution, the closer to 0 the result is.
     *
     * @param allocations the list of allocations
     * @return the fairness of the loan distribution
     */
    private double fairness(List<Allocation> allocations) {
        List<Allocation> allocationsCopy = new ArrayList<>(allocations);
        allocationsCopy.sort(Allocation::compareTo);
        double fairness = 0.0;
        int timesCalculated = 0;

        while (allocationsCopy.size() > 0) {
            fairness += (double) allocationsCopy.get(0).totalMoneyDeployed() / (double) allocationsCopy.get(allocationsCopy.size() - 1).totalMoneyDeployed();
            allocationsCopy.remove(0);
            allocationsCopy.remove(allocationsCopy.size() - 1);
            timesCalculated++;
        }

        return fairness / (double) timesCalculated;
    }
}
