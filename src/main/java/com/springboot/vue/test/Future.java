package com.springboot.vue.test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class Future<I extends Number> {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //CompiledFunction compiledFunction = new CompiledFunction();

        /*
        * 获得单个任务的执行结果。通过supplyAsync接收一个任务，
        * 有点类似new Runnable时写的run()实现。supplyAsync还支持第二个参数，
        * 该参数表示线程池。如果不指定，则使用默认的ForkJoinPool.commonPool()。
        * */
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            return 300;
        });

        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(new Supplier<String>(){
            @Override
            public String get() {
                return "SUCCESS";
            }
        });
        System.out.println("计算结果:"+completableFuture.get());
        System.out.println("计算结果:"+completableFuture1.get());


        /*
        * 两个任务，第一个任务的结果作为第二个任务的入参数，第二个任务要等待第一个任务的执行。通过thenCompose
        * */
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            return 100;
        });

        CompletableFuture<String> completableFuture3 = completableFuture2.thenCompose(result -> CompletableFuture.supplyAsync(() -> {
            return  result + ":" + "200";
        }));
        System.out.println(completableFuture3.get());

        /*
        * 两个任务，将两个任务的结果合并起来，这两个任务不分先后顺序执行。通过thenCombine
        * */

        CompletableFuture<Integer> completableFutureA = CompletableFuture.supplyAsync(() -> {
            return 100;
        });

        CompletableFuture<Integer> completableFutureB = completableFutureA.thenCombine(
                CompletableFuture.supplyAsync(() -> {
                    return 2000;
                }),
                (result1, result2) -> result1 + result2);

        System.out.println(completableFutureB.get());

        /*
        * 任务完成后，添加监听函数。通过thenAccept
        * */

        CompletableFuture<Integer> completableFutureC = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        //注册完成事件
        completableFutureC.thenAccept(result -> System.out.println("task1 done, result = " + result));
        System.out.println(completableFutureC.get());


    }
}
