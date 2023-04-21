package com.cgs.task1.bank.register.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.cgs.task1.bank.register")
public class WebConfig implements WebMvcConfigurer {

	
	 @Bean
	    public InternalResourceViewResolver getInternalResourceViewResolver(){
	        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	    

	        resolver.setPrefix("/WEB-INF/jsp/");
	        resolver.setSuffix(".jsp");

	        return resolver;
	    }

	    @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    	System.err.println("working");

	        registry.addResourceHandler("/css/**")
	                .addResourceLocations("/WEB-INF/css/");
	        
	        
	        registry.addResourceHandler("/images/**")
            .addResourceLocations("/WEB-INF/images/");
	    }
	    
}