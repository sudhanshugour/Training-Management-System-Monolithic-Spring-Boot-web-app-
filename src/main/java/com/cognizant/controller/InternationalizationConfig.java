package com.cognizant.controller;


import java.util.Locale;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
@Service
public class InternationalizationConfig extends WebMvcConfigurerAdapter{

	@Bean(name = "messageSource")
	public MessageSource messageSource() {
	   ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	   System.out.println("msg source");
	   messageSource.setBasename("classpath:messages");
	  // messageSource.setCacheSeconds(5);
	   return messageSource;
	}

	@Bean
	@Primary
	public LocalValidatorFactoryBean getValidator() {
	    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	    bean.setValidationMessageSource(messageSource());
	    return bean;
	}
	@Bean
	public LocaleResolver localeResolver(){
	SessionLocaleResolver  sessionLocaleResolver = new SessionLocaleResolver ();
	sessionLocaleResolver.setDefaultLocale(new Locale("en"));
	   return sessionLocaleResolver;
	} 
	
	 @Bean
	   public LocaleChangeInterceptor localeChangeInterceptor() {
	      LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
	      System.out.println("chage inter");
	      localeChangeInterceptor.setParamName("language");
	      return localeChangeInterceptor;
	   }
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
//	LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
//	localeChangeInterceptor.setParamName("language");
//	registry.addInterceptor(localeChangeInterceptor);
		registry.addInterceptor(localeChangeInterceptor());
    }
}
