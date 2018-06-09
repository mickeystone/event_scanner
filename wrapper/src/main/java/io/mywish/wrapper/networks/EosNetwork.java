package io.mywish.wrapper.networks;

import io.lastwill.eventscan.model.NetworkType;
import io.mywish.eoscli4j.EosClient;
import io.mywish.wrapper.WrapperBlock;
import io.mywish.wrapper.WrapperNetwork;
import io.mywish.wrapper.WrapperTransaction;
import io.mywish.wrapper.WrapperTransactionReceipt;
import io.mywish.wrapper.service.block.WrapperBlockEosService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;

public class EosNetwork extends WrapperNetwork {
    @Autowired
    private WrapperBlockEosService blockBuilder;

    private final EosClient eosClient;

    public EosNetwork(NetworkType type, EosClient eosClient) {
        super(type);
        this.eosClient = eosClient;
    }

    @Override
    public Long getLastBlock() throws Exception {
        return eosClient.getChainInfo().getHeadBlockNum();
    }

    @Override
    public BigInteger getBalance(String address, Long blockNo) throws Exception {
        throw new Exception("Method not supported");
    }

    @Override
    public WrapperBlock getBlock(String hash) throws Exception {
        return blockBuilder.build(eosClient.getBlock(hash));
    }

    @Override
    public WrapperBlock getBlock(Long number) throws Exception {
        return blockBuilder.build(eosClient.getBlock(number));
    }

    @Override
    public WrapperTransaction getTransaction(String hash) throws Exception {
        throw new Exception("Method not supported");
    }

    @Override
    public WrapperTransactionReceipt getTxReceipt(WrapperTransaction transaction) throws Exception {
        throw new Exception("Method not supported");
    }
}
