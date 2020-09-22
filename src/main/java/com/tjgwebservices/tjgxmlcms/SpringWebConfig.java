package com.tjgwebservices.tjgxmlcms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
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
        registry.addResourceHandler("/js/**")
        .addResourceLocations("/js/")
        .addResourceLocations("classpath:/js/"); 
    }
}
