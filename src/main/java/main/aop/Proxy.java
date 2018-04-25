package main.aop;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

@Component
public class Proxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return new Object();
    }

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinTask<Integer> taskOne = ForkJoinTask.adapt(() -> {
            Thread one = Thread.currentThread();
            System.out.println("Name one thread: " + one.getName());
            lock.lock();
            for (int i = 0; i < 10; i++) {
                System.out.println("wait " + one);
                lock.newCondition().await(1, TimeUnit.SECONDS);
            }
            lock.unlock();
            System.out.println("Done " + one);
            return 1;
        });
        ForkJoinTask<Thread> taskTwo = ForkJoinTask.adapt(() -> {
            Thread two = Thread.currentThread();
            System.out.println("Name one thread: " + two.getName());
            lock.lock();
            for (int i = 0; i < 5; i++) {
                System.out.println("wait " + two);
                lock.newCondition().await(1, TimeUnit.SECONDS);
            }
            lock.unlock();
            System.out.println("Done " + two);
            System.out.println(two.getState());
            return two;
        });
        final ForkJoinTask<Integer> taskOneForked = taskOne.fork();
        final ForkJoinTask<Thread> taskTwoForked = taskTwo.fork();
        taskOneForked.join();
        taskTwoForked.join();
        final Thread x = taskTwoForked.get();
        System.out.println(x.getState());
        System.out.println(x);
        System.out.println(taskOneForked.get());
//        Stream.of("A","B").parallel()
    }
}
