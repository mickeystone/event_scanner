package io.mywish.eoscli4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.mywish.eoscli4j.model.Block;
import io.mywish.eoscli4j.model.ChainInfo;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;

public class EosClientImpl implements EosClient {
    private final HttpClient client;
    private final URI rpc;
    private final ObjectMapper objectMapper;
    private Charset UTF8;

    public EosClientImpl(HttpClient client, URI rpc, ObjectMapper objectMapper) {
        this.client = client;
        this.rpc = rpc;
        this.objectMapper = objectMapper;
        this.UTF8 = Charset.forName("UTF-8");
    }

    private <T> T doRequest(final Class<T> clazz, final String endpoint, final String json) throws IOException {
        HttpPost httpPost = new HttpPost(rpc + endpoint);
        httpPost.setEntity(new StringEntity(json));
        HttpResponse httpResponse = this.client.execute(httpPost);
        HttpEntity entity = httpResponse.getEntity();
        String responseBody = EntityUtils.toString(entity, UTF8);
        T response = objectMapper.readValue(responseBody, clazz);
/*        if (response.getError() != null) {
            throw new IOException("Exception in endpoint '" + endpoint + "'"
                    + " with arguments "
                    + objectMapper.writeValueAsString(params)
                    + ". Error code: "
                    + response.getError().getCode()
            );
        }*/
        return response;
    }

    private <T> T doRequest(final Class<T> clazz, final String endpoint) throws IOException {
        return doRequest(clazz, endpoint, "");
    }

    @Override
    public ChainInfo getChainInfo() throws IOException {
        return doRequest(ChainInfo.class, "/v1/chain/get_info");
    }

    @Override
    public Block getBlock(String hash) throws IOException {
        return doRequest(Block.class, "/v1/chain/get_block", "{\"block_num_or_id\":\"" + hash + "\"}");
    }

    @Override
    public Block getBlock(Long number) throws IOException {
        return doRequest(Block.class, "/v1/chain/get_block", "{\"block_num_or_id\":\"" + number + "\"}");
    }
}
