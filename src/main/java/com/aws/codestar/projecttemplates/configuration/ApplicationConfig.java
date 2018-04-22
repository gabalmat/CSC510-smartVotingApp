package com.aws.codestar.projecttemplates.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.aws.codestar.projecttemplates.controller.PollController;
import com.aws.codestar.projecttemplates.controller.SmartVotingController;
import com.aws.codestar.projecttemplates.controller.SearchController;


/**
 * Spring configuration for sample application.
 */
@Configuration
@ComponentScan({ "com.aws.codestar.projecttemplates.configuration" })
@PropertySource("classpath:application.properties")
public class ApplicationConfig {

    /**
     * Retrieved from properties file.
     */
    @Value("${SmartVoting.SiteName}")
    private String siteName;

    @Bean
    public SmartVotingController smartVoting() {
        return new SmartVotingController(this.siteName);
    }
    
    @Bean
    public PollController showForm() {
    	return new PollController();
    }

    @Bean
    public SearchController searchTopics() {
        return new SearchController();
    }

    /**
     * Required to inject properties using the 'Value' annotation.
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
