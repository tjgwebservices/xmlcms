package com.tjgwebservices.tjgxmlcms;


import com.tjgwebservices.tjgxmlcms.services.filters.CustomFilter;
import com.tjgwebservices.tjgxmlcms.controller.conference.RefererRedirectionAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${administrator.password}")
    private String adminPassword;

    @Value("${guest.password}")
    private String guestPassword;

    final private String[] ignorelist = {"/","/home","/index","/error","/login",
        "/success","/socket","/socket/**","/sockets","/socketDisplay",
        "/display","/css/**","/js/**","/courses/**","/images/**",
        "/subscriptions/publish","/articles/articleList",
        "/articles/addArticle","/articles/editArticle","/articles/editArticle/*",
        "/subscriptions/subscriptionList","/subscriptions/addSubscription",
        "/subscriptions/subscribe","/subscriptions/subscribers","/subscriptions/editSubscriber",
        "/uploads","/topics","/topics/**","/topic/*","/topics/messages/",
        "/topics/messages/**","/topics/messages/info/**",
        "/stream/media","/stream/video",
        "/stream/media/**","/stream/video/**",
        "/conferences/workshop","/conferences/conference","/conferences/test",
        "/conferences/forum","/conferences/project","/conferences/room",
        "/videos/addArtist","/videos/addVideo","/videos/videoList",
        "/reviews/reviewList","/reviews/addReview","/reviews/editReview",
        "/reviews/editReview/*","/events/eventList",
        "/events/eventAdvertisementList","/events/editEvent","/events/addEvent",
        "/events/addEventAdvertisement","/events/editEventAdvertisement",
        "/events/eventAdministratorList","/events/addEventAdministrator",
        "/events/editEventAdministrator","/events/editEventAdministrator/*",
        "/events/editEventAdvertisement/*","/events/editEvent/*",
        "/reviews/editReview/*","/aiml/mlAlgorithms","/aiml/aiAlgorithms",
        "/aiml/addAiAlgorithm","/aiml/editAiAlgorithm","/aiml/addMlAlgorithm",
        "/aiml/editMlAlgorithm","/aiml/editMlAlgorithm/*","/aiml/editAiAlgorithm/*",
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
        "/chat/addChat","/chat/addChat/*","/chat/checkServer","/chat/chatList",
        "/chat/checkConversation","/chat/newConversation","/chat/continueConversation"};
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(ignorelist).permitAll()
                .antMatchers("/authenticated/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/users/**").access("hasRole('ROLE_USER')")
                .anyRequest()
                .authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .successHandler(new RefererRedirectionAuthenticationSuccessHandler())
                    .permitAll();
        http.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(ignorelist);
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("guest1").password("{noop}"+guestPassword).roles("USER");
        auth.inMemoryAuthentication().withUser("admin1").password("{noop}"+adminPassword).roles("ADMIN");                
    }

    
}
