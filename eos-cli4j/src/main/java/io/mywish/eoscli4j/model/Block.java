package io.mywish.eoscli4j.model;

import lombok.Getter;

import java.util.List;

@Getter
public class Block {
    private String id;
    private Long block_num;
    private List<Transaction> transactions;
}
