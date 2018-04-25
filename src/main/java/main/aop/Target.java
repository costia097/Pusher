package main.aop;

import org.springframework.stereotype.Service;

@Service
@TargetAnot
public class Target {
    public void mehtod1() {
        System.out.println("method1");
    }

    public void mehtod2() {
        System.out.println("method2");
    }
}
