package main.java.entities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A POJO used to represent the solution to this problem
 */

public class Allocation implements Comparable<Allocation> {

    private final Funder funder;
    private final List<Mortgage> fundedMortgages;

    public Allocation(Funder funder) {
        this.funder = funder;
        this.fundedMortgages = new ArrayList<>();
    }

    public Funder getFunder() {
        return funder;
    }

    public List<Mortgage> getFundedMortgages() {
        return fundedMortgages;
    }

    public void addToFundedMortgages(Mortgage mortgage) {
        fundedMortgages.add(mortgage);
        fundedMortgages.sort(Comparator.comparingInt(Mortgage::getLoanAmount));
    }

    public void removeFromFundedMortgages(Mortgage mortgage) {
        fundedMortgages.remove(mortgage);
    }

    public int totalMoneyDeployed() {
        int total = 0;

        for (Mortgage m : this.fundedMortgages) {
            total += m.getLoanAmount();
        }

        return total;
    }

    @Override
    public int compareTo(Allocation allocation) {
        return Integer.compare(this.totalMoneyDeployed(), allocation.totalMoneyDeployed());
    }

    @Override
    public String toString() {
        String output = this.getFunder().getCodeName() + " has funded: \n";

        for (Mortgage mortgage : getFundedMortgages()) {
            output += mortgage.toString() + "\n";
        }

        return output;
    }
}
