package main.reflection;

import java.lang.reflect.Method;

public class Startet {
    public static void main(String[] args) {
        TargetClass targetClass = new TargetClass();
        final Class<? extends TargetClass> aClass = targetClass.getClass();
        final Method[] declaredMethods = aClass.getDeclaredMethods();
        final Method[] methods = aClass.getMethods();
//        aClass.
        System.out.println("Fishish");
    }
}
