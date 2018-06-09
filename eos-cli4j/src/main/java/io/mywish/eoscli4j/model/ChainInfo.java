package io.mywish.eoscli4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ChainInfo {
    @JsonProperty("head_block_num")
    private Long headBlockNum;
}
