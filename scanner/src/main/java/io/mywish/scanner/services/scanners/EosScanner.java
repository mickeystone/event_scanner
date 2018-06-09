package io.mywish.scanner.services.scanners;

import io.mywish.scanner.services.LastBlockPersister;
import io.mywish.scanner.services.Scanner;
import io.mywish.wrapper.WrapperBlock;
import io.mywish.wrapper.networks.EosNetwork;

public class EosScanner extends Scanner {
    public EosScanner(EosNetwork network, LastBlockPersister lastBlockPersister, Long pollingInterval, Integer commitmentChainLength) {
        super(network, lastBlockPersister, pollingInterval, commitmentChainLength);
    }

    @Override
    protected void processBlock(WrapperBlock block) {
        System.out.println(block.getNumber() + ": " + block.getTransactions().size());
    }
}
