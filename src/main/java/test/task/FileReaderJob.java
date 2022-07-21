package test.task;

import test.constant.Currency;
import test.util.CollectionDb;
import io.netty.util.internal.StringUtil;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;

public class FileReaderJob implements Runnable {
    private String filePath;

    public FileReaderJob(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
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
