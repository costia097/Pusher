package main.aop;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyConfig;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.util.List;

@Component
public class AopUtils {
    private static final ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    public static Object addProxy(final Object bean, final Advisor advisor) {
        final ProxyFactory proxyFactory = new ProxyFactory(bean);
        proxyFactory.copyFrom(buildProxyConfig());
        proxyFactory.addAdvisor(advisor);
        return proxyFactory.getProxy(beanClassLoader);
    }

    public static Object addProxyWithSeveralAdvicers(final Object bean, final List<Advisor> advisors) {
        final ProxyFactory proxyFactory = new ProxyFactory(bean);
        proxyFactory.copyFrom(buildProxyConfig());
        advisors.forEach(proxyFactory::addAdvisor);
        return proxyFactory.getProxy(beanClassLoader);
    }


    private static ProxyConfig buildProxyConfig() {
        ProxyConfig proxyConfig = new ProxyConfig();
        proxyConfig.setExposeProxy(true);
        proxyConfig.setProxyTargetClass(true);
        return proxyConfig;
    }
}
