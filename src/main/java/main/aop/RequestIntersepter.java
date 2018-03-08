package main.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Component
public class RequestIntersepter implements MethodInterceptor{

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestIntersepter.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        LOGGER.info("Method is: " + method);
        Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
        LOGGER.info("DeclaredAnnotations is: " + declaredAnnotations);
        boolean accessible = method.isAccessible();
        LOGGER.info("IsAccessible: " + accessible);
        return invocation.proceed();
    }
}
