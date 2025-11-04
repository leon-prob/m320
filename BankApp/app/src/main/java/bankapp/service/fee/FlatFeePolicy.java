package bankapp.service.fee;

import bankapp.domain.Money;
import java.math.BigDecimal;
import java.util.Currency;

public class FlatFeePolicy implements FeePolicy {

    private final Money flatFee;

    public FlatFeePolicy(Money flatFee) {
        if (flatFee == null) {
            throw new IllegalArgumentException("Flat fee cannot be null");
        }
        this.flatFee = flatFee;
    }

    public FlatFeePolicy(BigDecimal feeAmount, Currency currency) {
        this(new Money(feeAmount, currency));
    }

    public FlatFeePolicy(Currency currency) {
        this(new Money(new BigDecimal("1.00"), currency));
    }

    @Override
    public Money calculateFee(Money amount) {
        if (amount == null) {
            throw new IllegalArgumentException("Transaction amount cannot be null");
        }

        return flatFee;
    }

    @Override
    public String getDescription() {
        return String.format("Flat fee of %s per transaction", flatFee.toString());
    }

    public Money getFlatFee() {
        return flatFee;
    }
}