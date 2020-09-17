package com.tjgwebservices.tjgxmlcms;

import freemarker.template.Configuration;
import freemarker.template.Version;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.web.context.support.StaticWebApplicationContext;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@SpringBootTest
@ExtendWith({SpringExtension.class})
@Import(SpringWebConfig.class)
public class FreeMarkerTests {
    Map<String,String> data = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(SpringWebConfig.class);
    private static final String TEMPLATE_PATH = "/views/ftl/";
    private StaticWebApplicationContext wac;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private FreeMarkerConfigurer fc;


    //@Autowired
    @Qualifier("freemarkerConfig")
    private FreeMarkerConfigurationFactoryBean freemarkerConfig;

    @BeforeEach
        void setup() throws Exception {
        ServletContext sc = new MockServletContext();
        wac = new StaticWebApplicationContext();
        wac.setServletContext(sc);
        fc = new FreeMarkerConfigurer();
        fc.setTemplateLoaderPaths(TEMPLATE_PATH);
        fc.setServletContext(sc);
        fc.afterPropertiesSet(); 
        Configuration cfg = new Configuration(new Version("2.3.23"));            
        cfg.setClassForTemplateLoading(TJGXMLCMSApplication.class, "/views/ftl/");
        cfg.setDefaultEncoding("UTF-8");
        fc.setConfiguration(cfg);    
    }
    
    @Test
    void testIndexTemplate() throws Exception{
        Assertions.assertEquals(fc.getConfiguration().getTemplateNameFormat().toString(),"TemplateNameFormat.DEFAULT_2_3_0");
    }   

    
}
