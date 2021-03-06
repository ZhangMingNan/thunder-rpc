package org.ming.thunder.spring;



import org.ming.thunder.config.RefererConfig;
import org.ming.thunder.config.RegistryConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 * 作者：张明楠
 * 时间：2018/6/27
 */
public class RefererConfigBean<T> extends RefererConfig<T> implements FactoryBean<T>, BeanFactoryAware, InitializingBean, DisposableBean {


    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public T getObject() throws Exception {
        return getReferer();
    }

    @Override
    public Class<T> getObjectType() {

        return null;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        checkAndConfigRegistry();

    }

    private void checkAndConfigRegistry() {
        if (registry==null){

            RegistryConfig rc = beanFactory.getBean(RegistryConfig.class);

            setRegistry(rc);
        }
    }
}
