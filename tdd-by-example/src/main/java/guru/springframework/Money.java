package guru.springframework;

public class Money implements Expression {

    protected final int amount;
    protected final String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }


    public String currency() {
        return currency;
    }


    public Money times(int multiplier) {
        return new Money(amount * multiplier, this.currency);
    }

    @Override
    public Money reduce(Bank bank, String currency) {
        //return this;
        return new Money(amount / bank.rate(this.currency, currency), currency);
    }

    public static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    public static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;
        Money money = (Money) o;
        return amount == money.amount && this.currency.equals(money.currency);
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }

    @Override
    public Expression plus(Expression addend) {
        return new Sum(this, addend);
    }
}
