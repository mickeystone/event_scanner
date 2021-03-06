package io.mywish.wrapper.transaction;

import io.mywish.neocli4j.Transaction;
import io.mywish.neocli4j.TransactionInput;
import io.mywish.wrapper.WrapperOutput;
import io.mywish.wrapper.WrapperTransaction;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class WrapperTransactionNeo extends WrapperTransaction {
    private final Transaction.Type type;
    private final List<String> contracts;

    public WrapperTransactionNeo(String hash, List<String> inputs, List<WrapperOutput> outputs, boolean contractCreation, Transaction.Type type, List<String> contracts) {
        super(hash, inputs, outputs, contractCreation);
        this.type = type;
        this.contracts = contracts;
    }
}
