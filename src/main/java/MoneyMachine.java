import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MoneyMachine {

    public static List<Loan> loans;
    public static Double totalValue;
    public static Double initialLoanInterest;
    public static Integer initialLoanPeriod;

    public static final Double MIN_LOAN_PRICE_BOUND = 10D;
    public static Double maxLoanPriceBound;

    public static Double totalOverall = 0D;


    public static void main(String[] args) {

        for (int i = 0; i < 100000; i++) {
            runSimulation(60D, 20D, 6, 100D);
        }


        System.out.println(totalOverall / 100000);
    }

    public static void runSimulation(Double totalPeriod, Double initialLoanInterest,
                                     Integer initialLoanPeriod, Double maxLoanPriceBound) {

        MoneyMachine.loans = new ArrayList<>();
        MoneyMachine.totalValue = 0D;
        MoneyMachine.initialLoanInterest = initialLoanInterest;
        MoneyMachine.initialLoanPeriod = initialLoanPeriod;
        MoneyMachine.maxLoanPriceBound = maxLoanPriceBound;

        addLoanToList(ThreadLocalRandom.current().nextDouble(MIN_LOAN_PRICE_BOUND, MoneyMachine.maxLoanPriceBound));

        for (int i = 0; i < totalPeriod; i++ ) {
            processLoans();
        }

        addRemainingLoanValueToTotalValue();

        totalOverall += totalValue;
    }

    private static void processLoans() {

        for (Loan loan : loans) {

            if (loan.getPeriod() > 0) {
                totalValue += (loan.monthlyPrincipal + loan.monthlyInterest);
                loan.setValue(loan.getValue() - loan.monthlyPrincipal);
                loan.setPeriod(loan.getPeriod() - 1);
            }
        }

        Random random = new Random();

        while (totalValue >= maxLoanPriceBound) {

            if (random.nextInt(2) == 1) {
                addLoanToList(maxLoanPriceBound);
                totalValue -= maxLoanPriceBound;
            } else {
                double loanValue = ThreadLocalRandom.current().nextDouble(MIN_LOAN_PRICE_BOUND, maxLoanPriceBound);
                addLoanToList(loanValue);
                totalValue -= loanValue;
            }
        }

        if ((totalValue >= MIN_LOAN_PRICE_BOUND) && (totalValue < maxLoanPriceBound)) {
            double loanValue = ThreadLocalRandom.current().nextDouble(MIN_LOAN_PRICE_BOUND, totalValue);
            addLoanToList(loanValue);
            totalValue -= loanValue;
        }

    }

    private static void addLoanToList(double value) {

        int initialPeriod = 0;
        while (initialPeriod == 0) {
            initialPeriod = new Random().nextInt(initialLoanPeriod + 1);
        }

        Loan loan = new Loan(initialLoanInterest, initialPeriod, value);
        loans.add(loan);
    }

    private static void addRemainingLoanValueToTotalValue() {

        for (Loan loan : loans) {
            if (loan.getPeriod() > 0) {
                totalValue += loan.getValue();
            }
        }
    }

}
