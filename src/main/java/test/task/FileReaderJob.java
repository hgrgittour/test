package test.task;

import test.util.CollectionDb;
import io.netty.util.internal.StringUtil;

import java.nio.file.*;
import java.util.List;

public class FileReaderJob implements Runnable {
    private String filePath;

    public FileReaderJob(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path path = Paths.get(filePath);
            path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
            Integer line = 0;
            while (true) {
                WatchKey watchKey = watchService.take();
                for (WatchEvent event : watchKey.pollEvents()) {
                    watchKey.pollEvents();
                    List<String> strings = Files.readAllLines(Paths.get(filePath + "\\" + event.context()));
                    for (int i = line; i < strings.size(); i++) {
                        String strTmp = strings.get(i);
                        if (strTmp != null && strTmp.equals("quit")) {
                            System.exit(0);
                        }
                        if (!StringUtil.isNullOrEmpty(strTmp)) {
                            String[] s = strTmp.split(" ");
                            CollectionDb.set(s[0], Integer.valueOf(s[1]));
                            line += 1;
                        }
                    }
                }
                watchKey.reset();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
