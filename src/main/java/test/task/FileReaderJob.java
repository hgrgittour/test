package test.task;

import test.constant.Currency;
import test.util.CollectionDb;
import io.netty.util.internal.StringUtil;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;

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
        while (true) {
            WatchKey watchKey = watchService.take();

            for (WatchEvent event : watchKey.pollEvents()) {
                System.out.println(event.context() + "发生了" + event.kind() + "事件！");
                 watchKey.pollEvents() ;
                 event.context();
                 event.kind();


            }

            watchKey.reset();
        }
        }catch(Exception e){
            e.printStackTrace();

        }
        //        BufferedReader buffReader = null;
//        try {
//            FileInputStream fin = new FileInputStream(filePath);
//            InputStreamReader reader = new InputStreamReader(fin);
//            buffReader = new BufferedReader(reader);
//            String strTmp = "";
//            while (true) {
//                //check
//                strTmp = buffReader.readLine();
//                if (strTmp != null && strTmp.equals("quit")) {
//                    System.exit(0);
//                }
//                if (!StringUtil.isNullOrEmpty(strTmp)) {
//                    String[] s = strTmp.split(" ");
//                    CollectionDb.set(s[0], Integer.valueOf(s[1]));
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            System.exit(0);
//        }
    }

    //    @Override
    public void run2() {
        BufferedReader buffReader = null;
        try {
            FileInputStream fin = new FileInputStream(filePath);
            InputStreamReader reader = new InputStreamReader(fin);
            buffReader = new BufferedReader(reader);
            String strTmp = "";
            while (true) {
                //check
                strTmp = buffReader.readLine();
                if (strTmp != null && strTmp.equals("quit")) {
                    System.exit(0);
                }
                if (!StringUtil.isNullOrEmpty(strTmp)) {
                    String[] s = strTmp.split(" ");
                    CollectionDb.set(s[0], Integer.valueOf(s[1]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
}
