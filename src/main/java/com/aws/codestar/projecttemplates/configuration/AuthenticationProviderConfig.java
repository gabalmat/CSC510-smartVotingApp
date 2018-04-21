package com.aws.codestar.projecttemplates.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

@Configuration
@PropertySource("classpath:application.properties")
public class AuthenticationProviderConfig {
	
	/**
     * Retrieved from properties file.
     */
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.datasource.driver-class-name}")
	private String driver;
	
	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName(driver);
	    driverManagerDataSource.setUrl(url);
	    driverManagerDataSource.setUsername(username);
	    driverManagerDataSource.setPassword(password);
	    return driverManagerDataSource;
	}
    
//    @Bean(name="userDetailsService")
//    public UserDetailsService userDetailsService(){
//    	JdbcDaoImpl jdbcImpl = new JdbcDaoImpl();
//    	jdbcImpl.setDataSource(dataSource());
//    	jdbcImpl.setUsersByUsernameQuery("select username,password, enabled from users where username=?");
//    	jdbcImpl.setAuthoritiesByUsernameQuery("select users.username, user_roles.role from user_roles , users  where users.username=? and a.userid=b.userid");
//    	return jdbcImpl;
//    }
}