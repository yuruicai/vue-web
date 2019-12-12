package com.springboot.vue.test;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.Future;

public class CompletionServiceTest {

    public static void main(String[] args) throws Exception {

        ExecutorService service = Executors.newFixedThreadPool(3);
        CompletionService<Object> completionService = new ExecutorCompletionService<>(service);
        List<Future<Object>> futures = new ArrayList<>();
        List<Future<Object>> futures2 = new ArrayList<>();
        for (int i1 = 0; i1 < 10; i1++) {
            System.out.println("B提交任务 "+ i1);
            futures2.add(completionService.submit(new Task(i1 , "B")));
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("A提交任务 "+ i);
            futures.add(completionService.submit(new Task(i , "A")));
        }


        for (int i4 = 0; i4 < 10; i4++) {
            System.out.println("B获取任务集"+futures2.get(i4).get().toString());
        }
        System.out.println("111111111111111111111111111111");
        for (int i3 = 0; i3 < 10; i3++) {
            System.out.println("A获取任务集"+futures.get(i3).get().toString());
        }
        for (int i = 0; i < 20; i++) {
            System.out.println("completionService.take().get()"+completionService.poll().get());
        }
        for (int i = 0; i < 20; i++) {
            System.out.println("completionService.take().get()"+completionService.poll().get());
        }
        service.shutdown();
    }
static class  Task implements Callable<Object>{
        int i;
        String name;
    public Task(int i ,String name) {
        this.i= i ;
        this.name= name;
    }

    @Override
    public Object call() throws Exception {
        if ("A".endsWith(name)) {
            Thread.sleep((long)Math.random()* 1000);
        }
        System.out.println("执行任务中 = "+name+i);
        return name+i;
    }
}
}
