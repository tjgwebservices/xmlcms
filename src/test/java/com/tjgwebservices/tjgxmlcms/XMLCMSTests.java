package com.tjgwebservices.tjgxmlcms;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateExceptionHandler;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.StaticWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.theme.FixedThemeResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@SpringBootTest
@ExtendWith({SpringExtension.class})
@Import(SpringWebConfig.class)
class XMLCMSTests {
    private static final Logger logger = LoggerFactory.getLogger(SpringWebConfig.class);
    private static final String TEMPLATE_FILE = "index.ftl";
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
        fc.setTemplateLoaderPaths("/views/ftl/");
        fc.setServletContext(sc);
        fc.afterPropertiesSet(); 
        wac.getDefaultListableBeanFactory().registerSingleton("Article", fc);
        wac.refresh(); 
        request = new MockHttpServletRequest();
        request.setAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE, wac);
        request.setAttribute(DispatcherServlet.LOCALE_RESOLVER_ATTRIBUTE, new AcceptHeaderLocaleResolver());
        request.setAttribute(DispatcherServlet.THEME_RESOLVER_ATTRIBUTE, new FixedThemeResolver());
        response = new MockHttpServletResponse();
    }
 


    @Test
    void requestTests() throws Exception {
        Properties settings = new Properties();
        settings.setProperty("template_update_delay", "0");
        settings.setProperty("default_encoding", "UTF-8");
        settings.setProperty("number_format", "0.####");
        settings.setProperty("datetime_format", "yyyy-MM-dd HH:mm:ss");
        settings.setProperty("classic_compatible", "true");
        settings.setProperty("template_exception_handler", "ignore");
        fc.setFreemarkerSettings(settings);
        fc.afterPropertiesSet();
        HttpServletRequest request = 
        ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
        .getRequest();
        Assertions.assertEquals(request.getHeaderNames().hasMoreElements(),false);                
    }
        
    @Test
    void testExplicitConfigurations() throws Exception {
        Properties settings = new Properties();
        settings.setProperty("template_update_delay", "0");
        settings.setProperty("default_encoding", "UTF-8");
        settings.setProperty("number_format", "0.####");
        settings.setProperty("datetime_format", "yyyy-MM-dd HH:mm:ss");
        settings.setProperty("classic_compatible", "true");
        settings.setProperty("template_exception_handler", "ignore");
        fc.setFreemarkerSettings(settings);
        fc.afterPropertiesSet();

        Configuration conf = fc.getConfiguration();
        Assertions.assertFalse(conf.isLocaleExplicitlySet());
        Assertions.assertFalse(conf.isDefaultEncodingExplicitlySet());
        Assertions.assertTrue(conf.isNumberFormatSet());
        Assertions.assertTrue(conf.isTemplateExceptionHandlerSet());
        Assertions.assertFalse(conf.isTemplateExceptionHandlerExplicitlySet());
    }

        @Test
        void testProperties() throws Exception{

            Properties freemarkerSettings = new Properties();
            freemarkerSettings.setProperty(Configuration.LOCALIZED_LOOKUP_KEY, Boolean.FALSE.toString());
            freemarkerSettings.setProperty(Configuration.NUMBER_FORMAT_KEY, "computer");
            fc.setFreemarkerSettings(freemarkerSettings);
            Assertions.assertEquals(fc.getConfiguration().getCustomAttributeNames().length,0);
        }
        
        @Test
        void testConfigurationandEncoding() throws Exception{
            Configuration myCfg = new Configuration(Configuration.VERSION_2_3_27);
            myCfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            myCfg.setDefaultEncoding("UTF-8");
            DefaultObjectWrapperBuilder owb = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_27);
            owb.setForceLegacyNonListCollections(false);
            owb.setDefaultDateType(TemplateDateModel.DATETIME);
            myCfg.setObjectWrapper(owb.build());            
            fc.setConfiguration(myCfg);
            Assertions.assertEquals(fc.getConfiguration().getCustomAttributeNames().length,0);
        }
        

        
}
