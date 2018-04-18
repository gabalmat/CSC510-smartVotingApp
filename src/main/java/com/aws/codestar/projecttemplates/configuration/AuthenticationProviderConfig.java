package com.aws.codestar.projecttemplates.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

@Configuration
public class AuthenticationProviderConfig {
	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
	    driverManagerDataSource.setUrl("jdbc:postgresql://aa1mhv3msby6vii.cs7westvbs2e.us-west-2.rds.amazonaws.com:5432/ebdb");
	    driverManagerDataSource.setUsername("DBAdmin");
	    driverManagerDataSource.setPassword("star1234");
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