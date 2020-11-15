package findMax;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FindMax {

    private static int MAX_VALUE = 0;
    private static int count = 4;
    private static CountDownLatch countDownLatch;
    private static ExecutorService executorService;

    FindMax(int count) {
        FindMax.count = count;
        executorService = Executors.newFixedThreadPool(count);
        countDownLatch = new CountDownLatch(count);
    }

    /*generate array of random numbers*/
    static void generateRandomArray(int[] arr) {

        for (int i = 0; i < 4000000; i++)
            arr[i] = (int) (Math.random() * 100000000);
    }

    /*get sum of an array using 4 threads*/
    static long findMax(int[] arr) {
        int size = arr.length;
        int batch = size / count + 1;
        for (int i = 0; i < count; i++) {
            int end;
            int start;
            start = i * batch;
            end = Math.min(start + batch, size);
            executorService.execute(new Runner(arr, start, end));
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        return MAX_VALUE;
    }

    public static void main(String[] args) {

        int[] arr = new int[4000000];
        generateRandomArray(arr);
        FindMax countDownLatchTest = new FindMax(4);
        long max = findMax(arr);
        System.out.println("Max: " + max);

    }

    static class Runner implements Runnable {

        private final int[] arr;
        private final int startIdx;
        private final int end;

        Runner(int[] arr, int startIdx, int end) {
            this.arr = arr;
            this.startIdx = startIdx;
            this.end = end;
        }

        @Override
        public void run() {
            int sum = 0;
            for (int i = startIdx; i < end; i++) {
                if (MAX_VALUE<arr[i]){
                    MAX_VALUE=arr[i];
                };
            }
            countDownLatch.countDown();

        }
    }
}