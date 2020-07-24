package guru.springframework;

public class Sum implements Expression {

    //the first value of (3 + 7) - >3
    final Expression augmend;

    //the second value of (3 + 7) - >7
    final Expression addmend;

    public Sum(Expression augmend, Expression addmend) {
        this.augmend = augmend;
        this.addmend = addmend;
    }


    @Override
    public Money reduce(Bank bank, String currency) {
        int amount = augmend.reduce(bank, currency).amount + addmend.reduce(bank, currency).amount;
        return new Money(amount, currency);
    }

    @Override
    public Expression plus(Expression addend) {
        return new Sum(this, addmend);
    }

    @Override
    public Expression times(int multiplier) {
        return new Sum(augmend.times(multiplier), addmend.times(multiplier));
    }
}
