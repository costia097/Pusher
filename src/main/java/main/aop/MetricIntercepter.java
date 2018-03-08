package main.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class MetricIntercepter implements MethodInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MetricIntercepter.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        Class<?> returnType = method.getReturnType();
        LOGGER.info("ReturnTypes is: " + returnType);
        return invocation.proceed();
    }
}
