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
        "/js/**","/courses/**","/conferences/workshop","/conferences/conference",
        "/conferences/forum","/conferences/project",
        "/conferences/learn","/conferences/report","/schools/addAdministrator",
        "/schools/addAdministratorGroup","/schools/addLecture",
        "/schools/addLecturer","/schools/addLectureNote","/schools/addSchool",
        "/schools/addStudent","/schools/adminList","/schools/addCourse",
        "/hr/addEmployer","/hr/addClient","/hr/addHrGroup","/hr/editClient",
        "/hr/editClient/*","/hr/editEmployer","/hr/editEmployer/*",
        "/hr/editHrGroup","/hr/editHrGroup/*","/hr/clients",
        "/hr/employers","/hr/hrgroups","/research/researchers","/research/topics",
        "/research/projects","/research/addResearcher","/research/addTopic",
        "/research/addProject","/research/editProject",
        "/research/editProject*","/research/editProject/*",
        "/research/editTopic","/research/editTopic/*","/research/editTopic*",
        "/research/editResearcher",
        "/research/editResearcher/*","/research/editResearcher*",
        "/articleList","/addArticle","/editArticle",
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
