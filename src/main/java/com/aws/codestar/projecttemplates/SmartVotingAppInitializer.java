package com.aws.codestar.projecttemplates;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.aws.codestar.projecttemplates.configuration.AuthenticationProviderConfig;
import com.aws.codestar.projecttemplates.configuration.MvcConfig;
import com.aws.codestar.projecttemplates.configuration.WebSecurityConfig;

/**
 * Utility to initialize the Spring MVC SmartVoting application.
 */
public class SmartVotingAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {
        		WebSecurityConfig.class, MvcConfig.class, AuthenticationProviderConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
    	return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {
                "/"
        };
    }

}
