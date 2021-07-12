package main.java.services;

import main.java.entities.Allocation;
import main.java.entities.Funder;
import main.java.entities.Mortgage;

import java.util.*;
import java.util.stream.Collectors;

public class AllocationService {


    /**
     * Finds how fair the loan distribution is across funders by averaging the distance? between the funders with the most and least money deployed
     *
     * @param allocations the list of allocations
     * @return the fairness of the loan distribution
     */
    public static double fairness(List<Allocation> allocations) {
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


    public static List<Allocation> distributeMortgagesFairly(List<Allocation> allocations) {
        return distributeMortgagesFairly(allocations, 0.8, 10);
    }

    public static List<Allocation> distributeMortgagesFairly(List<Allocation> allocations, double acceptableFairness, int maxCycles) {
        allocations.sort(Allocation::compareTo);

        Allocation mostDeployedAllocation;
        Allocation leastDeployedAllocation;

        int cyclesCompleted = 0;
        double fairness = fairness(allocations);

        while (fairness > acceptableFairness || cyclesCompleted < maxCycles) {
            boolean mortgageTransferred = false;

            for (int leastDeployedIndex = 0; leastDeployedIndex < allocations.size() && !mortgageTransferred; leastDeployedIndex++) {
                leastDeployedAllocation = allocations.get(leastDeployedIndex);
                for (int mostDeployedIndex = allocations.size() - 1; mostDeployedIndex > leastDeployedIndex && !mortgageTransferred; mostDeployedIndex--) {
                    //can mostDeployedIndex give anything to leastDeployedIndex
                    mostDeployedAllocation = allocations.get(mostDeployedIndex);


                    for (int i = 0; i < mostDeployedAllocation.getFundedMortgages().size() - 1 && !mortgageTransferred; i++) {
                        Mortgage mortgageToGive = mostDeployedAllocation.getFundedMortgages().get(i);
                        if (leastDeployedAllocation.getFunder().getDesiredProducts().contains(mortgageToGive.getProduct())) {
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

        //can the most funded funder give any resources to the least funded funder without bankrupting themselves?
        //  get most funded funder
        //  get least funded funder
        //  if no mortgage can be given, get next most funded funder
        //  if no funders can give least funded any mortgage, get next least funded funder and reset most funded funder
        //  do process X times or until f(x = funders) > 0.8
        //find a way to define the end i.e. if f(x) > 0.8 or certain number of attempts reached?

        return allocations;

    }

}
