package test.util;


import test.constant.Currency;

import javax.management.monitor.Monitor;
import java.util.concurrent.*;

public class CollectionDb {
    private final static ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
    private final static BlockingQueue<Payment> queue = new LinkedBlockingQueue<>();

    public static Integer getVal(String key) {
        return map.get(key);
    }

    public static void set(String key, Integer value) {
        Currency currency = Currency.valueOf(key);
        synchronized (currency) {
            Integer s = map.get(key);
            if (s != null) {
                map.put(key, s + value);
            } else {
                map.put(key, value);
            }
        }
        Payment payment = new Payment();
        payment.setCurrency(key);
        payment.setAmount(value);
        queue.add(payment);
    }

    public static void forEachMap() {
        map.forEach((key, value) -> {
            if (value != 0) {
                System.out.println(key + " " + value);
            }
        });
    }

    public static Payment pollQueue() {
        return queue.poll();
    }
}
