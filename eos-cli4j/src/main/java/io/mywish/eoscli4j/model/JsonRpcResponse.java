package io.mywish.eoscli4j.model;

import lombok.Getter;

@Getter
public class JsonRpcResponse<T> {
    Error error;
    T result;
}
