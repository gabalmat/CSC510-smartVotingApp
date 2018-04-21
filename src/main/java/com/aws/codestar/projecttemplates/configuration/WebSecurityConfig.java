package com.aws.codestar.projecttemplates.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
	   		auth.jdbcAuthentication().dataSource(dataSource)
		        .usersByUsernameQuery(
		        		"select username, password, enabled from users where username=?")
		        .authoritiesByUsernameQuery("select users.username, user_roles.role"
		        		+ " from users join user_roles on users.id = user_roles.user_id"
		        		+ " where users.username=?");
	   }

	   @Override
	   protected void configure(HttpSecurity http) throws Exception {
	      http
	      	.authorizeRequests().antMatchers("/resources/**").permitAll()
	      	.and()
	      	.authorizeRequests().anyRequest().hasAnyRole("USER","ADMIN")
	      	.and()
	      	.authorizeRequests()
	      	.antMatchers("/login**").permitAll()
	      	.and()
	      	.formLogin()
	      	.loginPage("/login") // Specifies the login page URL
	      	.loginProcessingUrl("/signin") // Specifies the URL,which is used 
	                                     //in action attribute on the <from> tag
	      	.usernameParameter("userid")  // Username parameter, used in name attribute
	                                    // of the <input> tag. Default is 'username'.
	      	.passwordParameter("passwd")  // Password parameter, used in name attribute
	                                    // of the <input> tag. Default is 'password'.
	      	.successHandler((req,res,auth)->{    //Success handler invoked after successful authentication
	      		for (GrantedAuthority authority : auth.getAuthorities()) {
	      			System.out.println(authority.getAuthority());
	      		}
	      		System.out.println(auth.getName());
	      		res.sendRedirect("/"); // Redirect user to index/home page
	      })
//	    .defaultSuccessUrl("/")   // URL, where user will go after authenticating successfully.
	                                // Skipped if successHandler() is used.
	      .failureHandler((req,res,exp)->{  // Failure handler invoked after authentication failure
	         String errMsg="";
	         if(exp.getClass().isAssignableFrom(BadCredentialsException.class)){
	            errMsg="Invalid username or password.";
	         }else{
	            errMsg="Unknown error - "+exp.getMessage();
	         }
	         req.getSession().setAttribute("message", errMsg);
	         res.sendRedirect("/SmartVoting/login"); // Redirect user to login page with error message.
	      })
//	    .failureUrl("/login?error")   // URL, where user will go after authentication failure.
	                                    //  Skipped if failureHandler() is used.
	      .permitAll() // Allow access to any URL associate to formLogin()
	      .and()
	      .logout()
	      .logoutUrl("/signout")   // Specifies the logout URL, default URL is '/logout'
	      .logoutSuccessHandler((req,res,auth)->{   // Logout handler called after successful logout 
	         req.getSession().setAttribute("message", "You are logged out successfully.");
	         res.sendRedirect("/SmartVoting/login"); // Redirect user to login page with message.
	      })
//	    .logoutSuccessUrl("/login") // URL, where user will be redirect after successful
	                                  //  logout. Ignored if logoutSuccessHandler() is used.
	      .permitAll() // Allow access to any URL associate to logout()
	      .and()
	      .csrf().disable(); // Disable CSRF support
	   }
	}