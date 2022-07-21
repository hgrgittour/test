package test.handler;

import test.util.CollectionDb;
import io.muserver.MuRequest;
import io.muserver.MuResponse;
import io.muserver.RouteHandler;

import java.util.Map;

public class GetNumHandler implements RouteHandler {
    @Override
    public void handle(MuRequest muRequest, MuResponse muResponse, Map<String, String> map) throws Exception {
        String code = map.get("code");
        Integer response = CollectionDb.getVal(code);
        if (response != null) {
            muResponse.write(CollectionDb.getVal(code).toString());
        } else {
            muResponse.write("dont contain this currency");
        }
    }
}
