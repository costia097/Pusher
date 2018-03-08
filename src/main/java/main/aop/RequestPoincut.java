package main.aop;

import main.annotations.CheckGood;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class RequestPoincut extends DynamicMethodMatcherPointcut {

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        return matches(method, targetClass);
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return targetClass.getAnnotation(CheckGood.class) != null;
    }
}
