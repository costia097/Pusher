package main.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.AopUtils;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static main.aop.AopUtils.addProxyWithSeveralAdvicers;

@Component
public class SecondBeanPostProssesor implements BeanPostProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecondBeanPostProssesor.class);

    private final RequestPoincut requestPoincut;

    private final RequestIntersepter requestIntersepter;

    private final MetricIntercepter metricIntercepter;

    @Autowired
    public SecondBeanPostProssesor(RequestPoincut requestPoincut, RequestIntersepter requestIntersepter, MetricIntercepter metricIntercepter) {
        this.requestPoincut = requestPoincut;
        this.requestIntersepter = requestIntersepter;
        this.metricIntercepter = metricIntercepter;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClass = AopUtils.getTargetClass(bean);
        LOGGER.info("TargetClass is: " + targetClass);
        if (AopUtils.canApply(requestPoincut, targetClass)) {
            Advisor advisorFirst = new DefaultPointcutAdvisor(requestPoincut, requestIntersepter);
            Advisor advisorSecond = new DefaultPointcutAdvisor(requestPoincut, metricIntercepter);
            return addProxyWithSeveralAdvicers(bean, Arrays.asList(advisorFirst, advisorSecond));
        }
        return bean;
    }
}
