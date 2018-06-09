package io.mywish.eoscli4j;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;

import java.net.URI;

public class Application {
    public static void main(String[] args) {
        try {
            HttpClient httpClient = HttpClients.createDefault();
            EosClient client = new EosClientImpl(httpClient, new URI("http://193.93.219.219:8888"), new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false));
            System.out.println(client.getChainInfo().getHeadBlockNum());
            System.out.println(client.getBlock(213193L).getTransactions().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
