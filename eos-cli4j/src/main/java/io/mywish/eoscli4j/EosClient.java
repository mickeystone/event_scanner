package io.mywish.eoscli4j;

import io.mywish.eoscli4j.model.Block;
import io.mywish.eoscli4j.model.ChainInfo;

public interface EosClient {
    ChainInfo getChainInfo() throws Exception;
    Block getBlock(String hash) throws Exception;
    Block getBlock(Long number) throws Exception;
}
