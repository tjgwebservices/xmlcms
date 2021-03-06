package com.tjgwebservices.tjgxmlcms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan({"com.tjgwebservices.tjgxmlcms"})
public class SpringWebConfig  extends WebMvcConfigurerAdapter {

    @Bean 
    public FreeMarkerViewResolver freemarkerViewResolver() { 
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver(); 
        resolver.setCache(true); 
        resolver.setPrefix(""); 
        resolver.setSuffix(".ftl"); 
        return resolver; 
    }
    
    @Bean 
    public FreeMarkerConfigurer freemarkerConfig() { 
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer(); 
        freeMarkerConfigurer.setTemplateLoaderPath("/views/ftl/");
        return freeMarkerConfigurer; 
    }
    
    @Bean(name="multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(100000);
        return multipartResolver;
    }
    
    @Bean
    public StandardServletMultipartResolver standardMultipartResolver() {
        return new StandardServletMultipartResolver();
    }

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.ignoreUnknownPathExtensions(false).defaultContentType(MediaType.TEXT_HTML);
	}
        
  @Override
  public void configureViewResolvers (ViewResolverRegistry registry) {
      registry.freeMarker();
  }
  
      @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/resources/**")
          .addResourceLocations("/resources/");
        registry.addResourceHandler("/css/**")
        .addResourceLocations("/css/")
        .addResourceLocations("classpath:/css/"); 
        registry.addResourceHandler("/csv/**")
        .addResourceLocations("/csv/")
        .addResourceLocations("classpath:/csv/"); 
        registry.addResourceHandler("/js/**")
        .addResourceLocations("/js/")
        .addResourceLocations("classpath:/js/"); 
        registry.addResourceHandler("/images/**")
        .addResourceLocations("/images/")
        .addResourceLocations("classpath:/images/"); 
    }
}
