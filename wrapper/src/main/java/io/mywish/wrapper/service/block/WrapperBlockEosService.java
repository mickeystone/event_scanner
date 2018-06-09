package io.mywish.wrapper.service.block;

import io.mywish.eoscli4j.model.Block;
import io.mywish.eoscli4j.model.Transaction;
import io.mywish.wrapper.WrapperBlock;
import io.mywish.wrapper.WrapperTransaction;
import io.mywish.wrapper.service.WrapperBlockService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WrapperBlockEosService implements WrapperBlockService<Block> {
    @Override
    public WrapperBlock build(Block block) {
        String hash = block.getId();
        Long number = block.getBlock_num();
        List<WrapperTransaction> transactions = block.getTransactions().stream().map(x -> (WrapperTransaction)null).collect(Collectors.toList());
        return new WrapperBlock(hash, number, null, transactions);
    }
}
