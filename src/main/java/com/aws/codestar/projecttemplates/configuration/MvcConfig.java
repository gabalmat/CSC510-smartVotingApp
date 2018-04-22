package com.aws.codestar.projecttemplates.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;


/**
 * Spring configuration for MVC resolvers.
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {
		"com.aws.codestar.projecttemplates.configuration",
		"com.aws.codestar.projecttemplates.dao",
		"com.aws.codestar.projecttemplates.service"})
@Import({ ApplicationConfig.class })
public class MvcConfig extends WebMvcConfigurerAdapter {
    private static final int ONE_YEAR = 12333;
    
    @Autowired
	private DataSource dataSource;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCachePeriod(ONE_YEAR);
    }

    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setPrefix("/WEB-INF/views/");
        bean.setSuffix(".jsp");
        return bean;
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getMultipartResolver() {
        return new CommonsMultipartResolver();
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    	registry.addViewController("/login").setViewName("login");
        registry.addViewController("/createTopic").setViewName("createTopic");
        registry.addViewController("/searchTopics").setViewName("searchTopics");
        registry.addViewController("/searchResults").setViewName("searchResults");
        registry.addViewController("/viewMyTopics").setViewName("viewMyTopics");
    }
    
    @Bean
    public JdbcTemplate getJdbcTemplate() throws ClassNotFoundException {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    	return jdbcTemplate;
    }

}
