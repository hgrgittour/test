package test;

import test.handler.GetNumHandler;
import test.handler.PublishHandler;
import test.task.FileReaderJob;
import test.task.OutputTimer;
import io.muserver.Method;
import io.muserver.MuServer;
import io.netty.util.internal.StringUtil;

import java.util.concurrent.*;

import static io.muserver.MuServerBuilder.httpServer;

public class main {
    private static ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1);
    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        startTimer();
            String filePath = args[0];
//        String filePath = "D:\\testmodifyfile";
        if (!StringUtil.isNullOrEmpty(filePath)) {
            startReader(filePath);
        }
        MuServer server = httpServer()
                .withHttpPort(8080)
                .addHandler(Method.GET, "/getNum/{code}", new GetNumHandler())
                .addHandler(Method.GET, "/showUpdate", new PublishHandler())
                .start();
    }

    private static void startReader(String filePath) {
        executorService.execute(new FileReaderJob(filePath));
    }

    private static void startTimer() {
        scheduledExecutor.scheduleWithFixedDelay(new OutputTimer(), 0, 30, TimeUnit.SECONDS);
    }
}
