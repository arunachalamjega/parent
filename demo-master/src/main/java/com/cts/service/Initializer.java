package com.cts.service;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import ingswii.quizpro.presentacion.comun.impl.*;
import org.directwebremoting.servlet.*;
import org.springframework.boot.context.embedded.*;
import org.springframework.boot.web.support.*;
import org.springframework.context.annotation.*;
import org.springframework.boot.builder.*;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.directwebremoting.servlet.DwrServlet;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import org.springframework.web.WebApplicationInitializer;
import org.apache.struts2.dispatcher.ActionContextCleanUp;
import org.displaytag.filter.ResponseOverrideFilter;
import com.opensymphony.module.sitemesh.filter.PageFilter;
import org.apache.struts2.dispatcher.FilterDispatcher;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletContext;
import org.springframework.web.context.ContextLoaderListener;

@Configuration
public class Initializer  extends SpringBootServletInitializer  implements WebApplicationInitializer  {

    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Initializer.class);
    }




    @Bean
    public DwrServlet dwr() {
        return new DwrServlet();
    }


    @Bean
    public ServletRegistrationBean servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(dwr());
        Map<String,String> params = new HashMap<String,String>();
        params.put("debug","true");
        registration.setInitParameters(params);
        return registration;
    }


    @Bean
    public FilterRegistrationBean  actionContextCleanUp() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        ActionContextCleanUp actionContext = new ActionContextCleanUp();
        registration.setFilter(actionContext);
        registration.setName("struts-cleanup");
        List<String> list = new ArrayList<String>();
        list.add("*.jsp");
        registration.setUrlPatterns(list);
        registration.setMatchAfter(true);
        return registration;
    }


    @Bean
    public FilterRegistrationBean responeOverride() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        ResponseOverrideFilter responseOverride = new ResponseOverrideFilter();
        registration.setFilter(responseOverride);
        registration.setName("ResponseOverrideFilter");
        List<String> list = new ArrayList<String>();
        list.add("*.jsp");
        registration.setUrlPatterns(list);
        registration.setMatchAfter(true);
        return registration;
    }


    @Bean
    public FilterRegistrationBean filteroAuthetication() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        FiltroAutenticacion filterAuth = new FiltroAutenticacion();
        registration.setFilter(filterAuth);
        registration.setName("filtroAutenticacion");
        List<String> list = new ArrayList<String>();
        list.add("*.jsp");
        list.add("*.action");
        registration.setUrlPatterns(list);
        registration.setMatchAfter(true);
        return registration;
    }


    @Bean
    public FilterRegistrationBean pageFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        PageFilter sitemesh = new PageFilter();
        registration.setFilter(sitemesh);
        registration.setName("sitemesh");
        List<String> list = new ArrayList<String>();
        list.add("*.jsp");
        registration.setUrlPatterns(list);
        registration.setMatchAfter(true);

        return registration;
    }


    @Bean
    public FilterRegistrationBean struts() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        FilterDispatcher dispatcher = new FilterDispatcher();
        registration.setFilter(dispatcher);
        registration.setName("struts");
        List<String> list = new ArrayList<String>();
        list.add("*.jsp");
        registration.setUrlPatterns(list);
        registration.setMatchAfter(true);

        return registration;
    }

    /*
    @Bean
    public ServletListenerRegistrationBean<ServletContextListener> myServletListener () {
        ServletListenerRegistrationBean<ServletContextListener> srb =
                new ServletListenerRegistrationBean<>();
        srb.setListener(new ContextLoaderListener());
        return srb;
    }
*/
}




