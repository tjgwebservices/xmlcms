package com.tjgwebservices.tjgxmlcms.template;

import com.tjgwebservices.tjgxmlcms.SpringWebConfig;
import com.tjgwebservices.tjgxmlcms.form.article.ArticleForm;
import com.tjgwebservices.tjgxmlcms.model.article.Article;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.support.AutowireCandidateQualifier;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SpringExtension.class})
@Import(SpringWebConfig.class)
public class ContextTests {
    private AnnotationConfigApplicationContext context;
    
    @BeforeEach
    public void newContext() {
        context = new AnnotationConfigApplicationContext();
    }

    @Test
    public void beanWithQualifier() {

        final GenericBeanDefinition articleBeanDefinition = new GenericBeanDefinition();
        articleBeanDefinition.addQualifier(new AutowireCandidateQualifier(Article.class));

        final GenericBeanDefinition articleFormBeanDefinition = new GenericBeanDefinition();
        articleFormBeanDefinition.addQualifier(new AutowireCandidateQualifier(ArticleForm.class));

        final DefaultListableBeanFactory factory = context.getDefaultListableBeanFactory();

        factory.registerBeanDefinition("articleBean", articleBeanDefinition);
        factory.registerSingleton("articleBean", "article");

        factory.registerBeanDefinition("articleFormBean", articleFormBeanDefinition);
        factory.registerSingleton("articleFormBean", "articleForm");

        context.register(Article.class);

        context.refresh();
        
        final Article article = context.getBean(Article.class);

        assertThat(factory.getBeanDefinitionNames().length).isEqualTo(8);
    }


}
