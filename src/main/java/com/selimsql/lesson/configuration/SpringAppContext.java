package com.selimsql.lesson.configuration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.selimsql.lesson.service.UserService;

@Component
public class SpringAppContext implements ApplicationContextAware {

    private static ApplicationContext appContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
       appContext = applicationContext;
    }

    private static Object getBean(String beanName) {
      Assert.notNull(appContext, "ApplicationContext must be not null!");

      try {
         Object obj = appContext.getBean(beanName);
         return obj;
      }
      catch(NoSuchBeanDefinitionException nsbex) {
         throw nsbex;
      }
      catch(BeanCreationException bcex) {
         //throw bcex;
         return null;
      }
      catch(Exception ex) {
         return null;
      }
    }

    public static UserService getUserServiceBean() {
       return (UserService)getBean("userService");
    }
}
