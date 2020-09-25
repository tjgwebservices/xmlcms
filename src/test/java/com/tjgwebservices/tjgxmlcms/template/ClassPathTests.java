package com.tjgwebservices.tjgxmlcms.template;

import com.tjgwebservices.tjgxmlcms.SpringWebConfig;
import freemarker.template.Configuration;
import java.util.Properties;
import javax.servlet.ServletContext;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.support.StaticWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.theme.FixedThemeResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SpringExtension.class})
@Import(SpringWebConfig.class)
public class ClassPathTests {
    private static final Logger logger = LoggerFactory.getLogger(SpringWebConfig.class);
    private static final String TEMPLATE_PATH = "/views/ftl/";
    private StaticWebApplicationContext wac;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private FreeMarkerConfigurer fc;

    @BeforeEach
    public void setUp() throws Exception {
        ServletContext sc = new MockServletContext();
        wac = new StaticWebApplicationContext();
        wac.setServletContext(sc);
        // final Template expectedTemplate = new Template();
        fc = new FreeMarkerConfigurer();
        fc.setTemplateLoaderPaths(TEMPLATE_PATH);
        fc.setServletContext(sc);
        fc.afterPropertiesSet(); 
        wac.getDefaultListableBeanFactory().registerSingleton("freeMarkerConfigurer", fc);
        wac.refresh(); 
        request = new MockHttpServletRequest();
        request.setAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE, wac);
        request.setAttribute(DispatcherServlet.LOCALE_RESOLVER_ATTRIBUTE, new AcceptHeaderLocaleResolver());
        request.setAttribute(DispatcherServlet.THEME_RESOLVER_ATTRIBUTE, new FixedThemeResolver());
        response = new MockHttpServletResponse();
    }
 

    @Test
    void testFreeMarkerConfiguration(){
        Properties freemarkerSettings = new Properties();
        freemarkerSettings.setProperty(Configuration.LOCALIZED_LOOKUP_KEY, Boolean.FALSE.toString());
        freemarkerSettings.setProperty(Configuration.NUMBER_FORMAT_KEY, "computer");
        fc.setFreemarkerSettings(freemarkerSettings);
        assertFalse(fc.getTaglibFactory().isEmpty());
    }

    
}
