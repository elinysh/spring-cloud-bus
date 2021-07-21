package com.example.fiber;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2020/09/06
 */
public class FiberTest {

    @Test
    public void test_fiber_non_virtual() {
        int callDelay = 5;
        int stackTraceOfCallAfter = 3;
        int stackTraceOfSleepAfter = 2;

        var latch = new CountDownLatch(1);
        Thread callThread = Thread.builder().name("callThread")
                .task(() -> {
                    sendGet(callDelay);
                    latch.countDown();
                }).start();

        Thread stackOfCallThread = Thread.builder().name("stackOfCallThread")
                .task(() -> {
                    try {
                        Thread.sleep(stackTraceOfCallAfter * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    StackTraceElement[] stackTrace = callThread.getStackTrace();
                    System.out.println("============== stack trace =============");
                    System.out.println("Thread Name : %s, virtual : %s"
                            .formatteㅠd(callThread.getName(), callThread.isVirtual()));
                    for (StackTraceElement e : stackTrace) {
                        System.out.println(e);
                    }

                }).start();

        Thread.builder().name("stackTraceOfSleepThread")
                .task(() -> {
                    try {
                        Thread.sleep(stackTraceOfSleepAfter * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    StackTraceElement[] stackTrace = stackOfCallThread.getStackTrace();
                    System.out.println("============== stack trace =============");
                    System.out.println("Thread Name : %s, virtual : %s"
                            .formatted(stackOfCallThread.getName(), stackOfCallThread.isVirtual()));
                    for (StackTraceElement e : stackTrace) {
                        System.out.println(e);
                    }

                }).start();

        try {
            latch.await();
            System.out.println("All Threads done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_fiber_virtual() {
        System.out.println("#### pid=" + ManagementFactory.getRuntimeMXBean().getName());
        int callDelay = 10;
        int stackTraceOfCallAfter = 3;
        int stackTraceOfSleepAfter = 2;

        var latch = new CountDownLatch(1);
        Thread callThread = Thread.builder().name("callThread").virtual()
                .task(() -> {
                    sendGet(callDelay);
                    latch.countDown();
                }).start();

        Thread stackOfCallThread = Thread.builder().name("stackOfCallThread").virtual()
                .task(() -> {
                    try {
                        Thread.sleep(stackTraceOfCallAfter * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    StackTraceElement[] stackTrace = callThread.getStackTrace();
                    System.out.println("============== stack trace =============");
                    System.out.println("Thread Name : %s, virtual : %s"
                            .formatted(callThread.getName(), callThread.isVirtual()));
                    for (StackTraceElement e : stackTrace) {
                        System.out.println(e);
                    }

                }).start();

        Thread.builder().name("stackTraceOfSleepThread").virtual()
                .task(() -> {
                    try {
                        Thread.sleep(stackTraceOfSleepAfter * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    StackTraceElement[] stackTrace = stackOfCallThread.getStackTrace();
                    System.out.println("============== stack trace =============");
                    System.out.println("Thread Name : %s, virtual : %s"
                            .formatted(stackOfCallThread.getName(), stackOfCallThread.isVirtual()));
                    for (StackTraceElement e : stackTrace) {
                        System.out.println(e);
                    }
                    System.out.println("==========================================");
                    System.out.println("========== thread dump ==============");
                    threadDump();

                }).start();

        try {
            latch.await();
            System.out.println("All Threads done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void threadDump() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] infos = bean.dumpAllThreads(true, true);
        for (ThreadInfo info : infos) {
            System.out.println(info);
        }
    }
    private static void sendGet(int sleep) {
        try {
            String url = "http://httpbin.org/delay/" + sleep;

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            //전송방식
            con.setRequestMethod("GET");
            con.setConnectTimeout(3000);       //컨텍션타임아웃 10초
            con.setReadTimeout(15000);           //컨텐츠조회 타임아웃 5총

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            Charset charset = Charset.forName("UTF-8");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println("조회결과 : " + response.toString());

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
