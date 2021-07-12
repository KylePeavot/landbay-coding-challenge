# Landbay coding challenge

## How to run the program
- Pull this repo
- Run the main method
- Expected output: Each funder and the mortgage(s) they have been allocated

## Assumptions
- The products are finite. In other words, the 6 products need to be distributed fairly across the 4 funders.
- It's better to give the funders the highest value mortgages and have the distribution be unfair than provide the funders with similar, lower value mortgages.
- Because funders care more about deploying large amounts of money than the interest earned on that money, I have made the assumption that rates are not something that need to be considered when distributing mortgages.

## How I calculate fairness
- Sort the total money deployed by each funder from lowest to highest
- divide the lowest total with the highest total for each pair of totals moving inwards (e.g. total[0]/total[length] + total[1]/total[length - 1] + etc) 
- get the mean of that value