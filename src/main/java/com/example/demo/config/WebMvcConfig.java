package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.OptionalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.MappedInterceptor;
import org.springframework.web.util.UrlPathHelper;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * Helps with configuring HandlerMappings path matching options such as trailing slash match,
     * suffix registration, path matcher and path helper.
     * Configured path matcher and path helper instances are shared for:
     * <ul>
     * <li>RequestMappings</li>
     * <li>ViewControllerMappings</li>
     * <li>ResourcesMappings</li>
     * </ul>
     *
     * @param configurer
     * @since 4.0.3
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }

    /**
     * Configure asynchronous request handling options.
     *
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(mvcTaskExecutor());
        configurer.setDefaultTimeout(TimeUnit.SECONDS.toMillis(60));
    }

    @Bean
    public ThreadPoolTaskExecutor mvcTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        return executor;
    }

    /**
     * Configure cross origin requests processing.
     *
     * @param registry
     * @since 4.2
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("")
                .allowedOrigins("https://domain2.com")
                .allowedMethods("PUT", "DELETE")
                .allowedHeaders("header1", "header2", "header3")
                .exposedHeaders("header1", "header2")
                .allowCredentials(true).maxAge(3600);
    }

    /**
     * Add Spring MVC lifecycle interceptors for pre- and post-processing of
     * controller method invocations. Interceptors can be registered to apply
     * to all requests or be limited to a subset of URL patterns.
     * <p><strong>Note</strong> that interceptors registered here only apply to
     * controllers and not to resource handler requests. To intercept requests for
     * static resources either declare a
     * {@link MappedInterceptor MappedInterceptor}
     * bean or switch to advanced configuration mode by extending
     * {@link WebMvcConfigurationSupport
     * WebMvcConfigurationSupport} and then override {@code resourceHandlerMapping}.
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }

    /**
     * Provide a custom {@link Validator} instead of the one created by default.
     * The default implementation, assuming JSR-303 is on the classpath, is:
     * {@link OptionalValidatorFactoryBean}.
     * Leave the return value as {@code null} to keep the default.
     */
    @Override
    public Validator getValidator() {
        return null;
    }

    /**
     * Add {@link Converter Converters} and {@link Formatter Formatters} in addition to the ones
     * registered by default.
     *
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new Formatter<Date>() {
            /**
             * Print the object of type T for display.
             *
             * @param object the instance to print
             * @param locale the current user locale
             * @return the printed text string
             */
            @Override
            public String print(Date object, Locale locale) {
                return null;
            }

            /**
             * Parse a text String to produce a T.
             *
             * @param text   the text string
             * @param locale the current user locale
             * @return an instance of T
             * @throws ParseException           when a parse exception occurs in a java.text parsing library
             * @throws IllegalArgumentException when a parse exception occurs
             */
            @Override
            public Date parse(String text, Locale locale) throws ParseException {
                System.out.println(text);
                return null;
            }
        });
    }
}
