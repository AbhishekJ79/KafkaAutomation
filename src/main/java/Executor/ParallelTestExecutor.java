package Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelTestExecutor {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3); // Create thread pool with 3 threads

        Runnable test1 = () -> System.out.println("Executing Test 1 - " + Thread.currentThread().getName());
        Runnable test2 = () -> System.out.println("Executing Test 2 - " + Thread.currentThread().getName());
        Runnable test3 = () -> System.out.println("Executing Test 3 - " + Thread.currentThread().getName());

        executor.submit(test1);
        executor.submit(test2);
        executor.submit(test3);

        executor.shutdown(); // Stop accepting new tasks
    }
}

