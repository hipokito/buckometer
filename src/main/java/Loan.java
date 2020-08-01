public class Loan {
    
    Double interest;
    Double value;
    Integer period;
    Double monthlyInterest;
    Double monthlyPrincipal;

    public Loan(double interest, int period, double value) {
        setInterest(interest);
        setPeriod(period);
        setValue(value);

        monthlyInterest = (value * (interest / 100)) / 12;
        monthlyPrincipal = value / period;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Double getMonthlyInterest() {
        return monthlyInterest;
    }

    public void setMonthlyInterest(Double monthlyInterest) {
        this.monthlyInterest = monthlyInterest;
    }

    public Double getMonthlyPrincipal() {
        return monthlyPrincipal;
    }

    public void setMonthlyPrincipal(Double monthlyPrincipal) {
        this.monthlyPrincipal = monthlyPrincipal;
    }
}
