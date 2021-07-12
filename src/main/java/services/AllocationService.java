package main.java.services;

import main.java.entities.Allocation;
import main.java.entities.Funder;
import main.java.entities.Mortgage;

import java.util.*;
import java.util.stream.Collectors;

public class AllocationService {

    public List<Allocation> distributeMortgagesAcrossFunders(List<Mortgage> mortgages, List<Funder> funders) {
        mortgages.sort(Mortgage::compareTo);

        List<String> boughtProducts = new ArrayList<>();
        List<Allocation> allocations = funders.stream().map(Allocation::new).collect(Collectors.toList());

        //for each mortgage
        for (var mortgage : mortgages) {
            //if the product for a mortgage hasn't been allocated yet
            if (!boughtProducts.contains(mortgage.getProductId())) {
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

                    boughtProducts.add(mortgage.getProductId());
                }
            }
        }

        allocations = distributeMortgagesFairly(allocations, 0.8, 20);

        return allocations;
    }

    public List<Allocation> distributeMortgagesFairly(List<Allocation> allocations, double acceptableFairness, int maxCycles) {
        allocations.sort(Allocation::compareTo);

        Allocation mostDeployedAllocation;
        Allocation leastDeployedAllocation;

        int cyclesCompleted = 0;
        double fairness = fairness(allocations);

        while (fairness > acceptableFairness && cyclesCompleted < maxCycles) {
            boolean mortgageTransferred = false;

            for (int leastDeployedIndex = 0; leastDeployedIndex < allocations.size() && !mortgageTransferred; leastDeployedIndex++) {
                leastDeployedAllocation = allocations.get(leastDeployedIndex);
                for (int mostDeployedIndex = allocations.size() - 1; mostDeployedIndex > leastDeployedIndex && !mortgageTransferred; mostDeployedIndex--) {
                    //can mostDeployedIndex give anything to leastDeployedIndex
                    mostDeployedAllocation = allocations.get(mostDeployedIndex);

                    for (int i = 0; i < mostDeployedAllocation.getFundedMortgages().size() - 1 && !mortgageTransferred; i++) {
                        Mortgage mortgageToGive = mostDeployedAllocation.getFundedMortgages().get(i);
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
     * Finds how fair the loan distribution is across funders by averaging the distance? between the funders with the most and least money deployed
     *
     * @param allocations the list of allocations
     * @return the fairness of the loan distribution
     */
    public double fairness(List<Allocation> allocations) {
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
