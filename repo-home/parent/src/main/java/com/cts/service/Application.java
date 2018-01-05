package com.cts.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ingswii.quizpro.presentacion.comun.impl.*;
import org.springframework.context.annotation.*;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.directwebremoting.servlet.DwrServlet;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import org.apache.struts2.dispatcher.ActionContextCleanUp;
import org.displaytag.filter.ResponseOverrideFilter;
import com.opensymphony.module.sitemesh.filter.PageFilter;
import static java.util.Collections.singletonList;
import org.apache.struts2.dispatcher.FilterDispatcher;


@ImportResource({
      "classpath:META-INF/spring/accesoDatos/daos.xml",
      "classpath:META-INF/spring/biz/controladores.xml",
      "classpath:META-INF/spring/presentacion/actions.xml",
      "classpath:META-INF/spring/presentacion/comun.xml"
})
@SpringBootApplication
public class Application {

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
      registration.addUrlMappings("/dwr/*");
      return registration;
   }

   @Bean
   public FilterRegistrationBean  actionContextCleanUp() {
      FilterRegistrationBean registration = new FilterRegistrationBean();
      ActionContextCleanUp actionContext = new ActionContextCleanUp();
      registration.setFilter(actionContext);
      registration.setName("struts-cleanup");
      List<String> list = new ArrayList<String>();
      list.add("/*");
      registration.setUrlPatterns(list);
      registration.setOrder(1);
      return registration;
   }


   @Bean
   public FilterRegistrationBean responeOverride() {
      FilterRegistrationBean registration = new FilterRegistrationBean();
      ResponseOverrideFilter responseOverride = new ResponseOverrideFilter();
      registration.setFilter(responseOverride);
      registration.setName("ResponseOverrideFilter");
      List<String> list = new ArrayList<String>();
      list.add("/*");
      registration.setUrlPatterns(list);
      registration.setOrder(2);
      return registration;
   }


   @Bean
   public FilterRegistrationBean pageFilter() {
      FilterRegistrationBean registration = new FilterRegistrationBean();
      PageFilter sitemesh = new PageFilter();
      registration.setFilter(sitemesh);
      registration.setName("sitemesh");
      List<String> list = new ArrayList<String>();
      list.add("/*");
      registration.setUrlPatterns(list);
      registration.setOrder(3);
      return registration;
   }




   @Bean
   public FilterRegistrationBean struts() {
      FilterRegistrationBean registration = new FilterRegistrationBean();
      FilterDispatcher dispatcher = new FilterDispatcher();
      registration.setFilter(dispatcher);
      registration.setName("struts");
      registration.setUrlPatterns(singletonList("/*"));
      registration.setOrder(4);
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
      registration.setOrder(5);
      return registration;
   }

   public static void main(String[] args) {
      SpringApplication.run(Application.class, args);
   }



}
 
