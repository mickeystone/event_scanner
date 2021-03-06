package io.lastwill.eventscan.messages;

import io.lastwill.eventscan.model.CryptoCurrency;
import lombok.Getter;
import lombok.ToString;

import java.math.BigInteger;

@ToString(callSuper = true)
@Getter
public class FgwPaymentIncomeNotify extends PaymentNotify {
    private final BigInteger balance;

    public FgwPaymentIncomeNotify(BigInteger amount, PaymentStatus status, String txHash, boolean isSuccess, BigInteger balance) {
        super(0, amount, status, txHash, CryptoCurrency.RSK, isSuccess);
        this.balance = balance;
    }

    @Override
    public String getType() {
        return "fgwIncome";
    }
}
