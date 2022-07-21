package test.handler;

import test.util.Payment;
import test.util.CollectionDb;
import io.muserver.MuRequest;
import io.muserver.MuResponse;
import io.muserver.RouteHandler;
import io.muserver.SsePublisher;

import java.io.IOException;
import java.util.Map;

public class PublishHandler implements RouteHandler {
    @Override
    public void handle(MuRequest muRequest, MuResponse muResponse, Map<String, String> map) throws Exception {
        SsePublisher publisher = SsePublisher.start(muRequest, muResponse);
        new Thread(() -> sendUpdate(publisher)).start();
    }
    private void sendUpdate(SsePublisher publisher) {
        while (true) {
            try {
                Payment poll = CollectionDb.pollQueue();
                if (poll != null) {
                    publisher.send(poll.getCurrency() + ":" +poll.getAmount());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
