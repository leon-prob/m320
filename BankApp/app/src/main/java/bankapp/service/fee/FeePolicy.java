package bankapp.service.fee;

import bankapp.domain.Money;

public interface FeePolicy {
    Money calculateFee(Money amount);

    String getDescription();
}