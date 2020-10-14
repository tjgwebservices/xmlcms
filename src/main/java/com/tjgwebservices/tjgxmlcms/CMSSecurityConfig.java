package com.tjgwebservices.tjgxmlcms;


import com.tjgwebservices.tjgxmlcms.services.filters.CustomFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
@EnableWebSecurity(debug = true)
public class CMSSecurityConfig extends WebSecurityConfigurerAdapter {
    final private String[] ignorelist = {"/","/home","/index","/error","/login",
        "/success","/sockets","/display","/publish","/uploads","/css/**",
        "/js/**","/courses/**","/workshop","/conference","/forum","/project",
        "/learn","/report","/schools/addAdministrator",
        "/schools/addAdministratorGroup","/schools/addLecture",
        "/schools/addLecturer","/schools/addLectureNote","/schools/addSchool",
        "/schools/addStudent","/schools/adminList","/schools/addCourse",
        "/hr/addEmployer","/hr/addClient","/hr/addHrGroup","/hr/clients",
        "/hr/employers","/research/researchers","/research/topics",
        "/research/projects","/research/addResearcher","research/addTopic",
        "research/addProject","/articleList","/addArticle","/editArticle",
            "/editArticle/*","/subscriptionList","/addSubscription"};
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(ignorelist).permitAll()
                .antMatchers("/authenticated/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/users/**").access("hasRole('ROLE_USER')")
                .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll();
        http.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(ignorelist);
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("guest1").password("{noop}password").roles("USER");
        auth.inMemoryAuthentication().withUser("admin1").password("{noop}administrator").roles("ADMIN");                
    }

    
}
