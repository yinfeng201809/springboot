package com.example.demo.spring.mvc;

import com.example.demo.spring.validation.RootConfig;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { RootConfig.class };
    }

    /**
     * Create a {@link DispatcherServlet} (or other kind of {@link FrameworkServlet}-derived
     * dispatcher) with the specified {@link WebApplicationContext}.
     * <p>Note: This allows for any {@link FrameworkServlet} subclass as of 4.2.3.
     * Previously, it insisted on returning a {@link DispatcherServlet} or subclass thereof.
     *
     * @param servletAppContext
     */
    @Override
    protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
        return super.createDispatcherServlet(servletAppContext);
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[] {
                new HiddenHttpMethodFilter(), new CharacterEncodingFilter() };
    }
}