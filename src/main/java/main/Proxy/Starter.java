package main.Proxy;

import java.lang.reflect.Proxy;

public class Starter {
    public static void main(String[] args) {
        TargetInterface targetInterface = new Target("Name");
        final TargetInterface target = (TargetInterface) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{TargetInterface.class}, new InvocationHandlerImpl(targetInterface));
        System.out.println(target.doSome());
    }
}
