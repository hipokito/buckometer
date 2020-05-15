import java.util.ArrayList;
import java.util.List;

public class MoneyMachine {

    public static List<Loan> loans;

    public static Integer totalPeriod;

    public static Integer totalValue;

    public static Integer initialLoanInterest;
    public static Integer initialLoanPeriod;
    public static Integer initialLoanValue;

    public static void main(String[] args) {
        loans = new ArrayList<>();

        totalPeriod = 60;

        initialLoanInterest = 20;
        initialLoanPeriod = 6;
        initialLoanValue = 20;

        addLoanToList(initialLoanInterest, initialLoanPeriod, initialLoanValue);

        for (int i = 0; i < totalPeriod; i++ ) {
            processLoans();
        }

        System.out.println(totalValue);
    }

    private static void processLoans() {
        for (Loan loan : loans) {
            if (totalPeriod > 0) {



                if ((totalValue >= 10) && (totalValue <= 20)) {
                    addLoanToList(initialLoanInterest, initialLoanPeriod, initialLoanValue);
                }
            }
            totalPeriod--;
        }
    }

    private static void addLoanToList(double interest, double period, double value) {
        Loan loan = new Loan(interest, period, value);
        loans.add(loan);
    }

}
