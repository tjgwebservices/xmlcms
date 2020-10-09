package com.tjgwebservices.tjgxmlcms;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TJGXMLCMSApplication.class);
	}
        
        private String TMP_FOLDER = "/temp";
        private int MAX_UPLOAD_SIZE = 5 * 1024 * 1024;
        
        @Override
        public void onStartup(ServletContext sc) throws ServletException {
            AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
            ctx.register(WebMvcConfigurer.class);
            ctx.setServletContext(sc);
            ServletRegistration.Dynamic servlet = sc.addServlet(
                    "mvc", new DispatcherServlet(ctx)
            );
            servlet.setLoadOnStartup(1);
            MultipartConfigElement multipartConfigElement = new MultipartConfigElement(
            TMP_FOLDER, MAX_UPLOAD_SIZE, MAX_UPLOAD_SIZE *2, MAX_UPLOAD_SIZE /2);
            servlet.setMultipartConfig(multipartConfigElement);
        }

}
