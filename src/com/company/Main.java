package com.company;

import java.util.concurrent.*;

public class Main {
    static int factorialResult = 0;
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Factorial factorial = new Factorial(5);
        Future<Integer> future = executorService.submit(factorial);
        try {
            System.out.println(future.isDone());
            System.out.println("We want to get a result");
            factorialResult = future.get();
            System.out.println(future.isDone());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println(e.getCause());
        }
        finally {
            executorService.shutdown();
        }
        System.out.println(factorialResult);
    }
}
class Factorial implements Callable<Integer> {
    int val;
    public Factorial(int val) {
        this.val = val;
    }
    @Override
    public Integer call() throws Exception {
        if (val <= 0) {
            throw new Exception("You've typed an incorrect message");
        }
        int res = 1;
        for (int i = 1; i <= val ; i++) {
            res *= i;
        }
        return res;
    }
}
