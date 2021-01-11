package com.tjgwebservices.tjgxmlcms.controller.article;

import com.tjgwebservices.tjgxmlcms.dbo.article.ConfigurationDBO;
import com.tjgwebservices.tjgxmlcms.dbo.article.SocialDBO;
import com.tjgwebservices.tjgxmlcms.form.article.ConfigurationForm;
import com.tjgwebservices.tjgxmlcms.form.article.SocialForm;
import com.tjgwebservices.tjgxmlcms.model.article.Configuration;
import com.tjgwebservices.tjgxmlcms.model.article.Social;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SocialController {

    private static List<Social> socials = new ArrayList<Social>();

    static {
    }
 
    @Value("${welcome.message}")
    private String message;
 
    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = { "/articles/socialList" }, method = RequestMethod.GET)
    public String socialList(Model model) {
        socials = SocialDBO.loadSocials();
        model.addAttribute("socials", socials);
 
        return "/articles/socialList";
    }
 
    @RequestMapping(value = { "/articles/addSocial" }, method = RequestMethod.GET)
    public String addSocialForm(Model model) {
 
        SocialForm socialForm = new SocialForm();
        model.addAttribute("socialForm", socialForm);
 
        return "articles/addSocial";
    }

    @RequestMapping(value = { "/articles/addSocial" }, method = RequestMethod.POST)
    public String addSocialSave(Model model, //
        @ModelAttribute("socialForm") SocialForm socialForm) {
        socials = SocialDBO.loadSocials();
        String postname = socialForm.getPostname();
        String content = socialForm.getContent();
        Integer reviewed = socialForm.getReviewed();
        String published = socialForm.getPublished();
 
        if (postname != null && postname.length() > 0 
                && content != null && content.length() > 0 
                && reviewed != null && reviewed > -1 
                && published != null && published.length() > 0) {
            Social newSocial = new Social(postname, content, 
                    reviewed, published);
            socials.add(newSocial);
            SocialDBO.saveSQLSocial(newSocial);
            return "redirect:/articles/socialList";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "articles/addSocial";
    }

    
    @RequestMapping(value = { "/articles/editSocial/{id}" }, method = RequestMethod.GET)
    public String editSocialForm(Model model,
            @PathVariable("id") Integer id) {
        socials = SocialDBO.loadSocials();
 
        SocialForm socialForm = new SocialForm();
        SocialForm socialEditForm = new SocialForm();
        Social editSocial = socials.stream()
            .filter((social) -> social.getId() == id)
            .collect(Collectors.toList()).get(0);
        socialEditForm.setPostname(editSocial.getPostname());
        socialEditForm.setContent(editSocial.getContent());
        socialEditForm.setReviewed(editSocial.getReviewed());
        socialEditForm.setPublished(editSocial.getPublished());
        model.addAttribute("social", socialForm);
        model.addAttribute("socialEditForm", socialEditForm);
        return "/articles/editSocial/{id}";
    }
 
    @RequestMapping(value = { "/articles/editSocial" }, method = RequestMethod.POST)
    public String editSocialSave(Model model, //
        @ModelAttribute("socialForm") SocialForm socialForm) {
        socials = SocialDBO.loadSocials();
        Integer id = socialForm.getId();
        String postname = socialForm.getPostname();
        String content = socialForm.getContent();
        Integer reviewed = socialForm.getReviewed();
        String published = socialForm.getPublished();
 
        if (postname != null && postname.length() > 0 
                && content != null && content.length() > 0 
                && reviewed != null && reviewed > -1 
                && published != null && published.length() > 0) {
            Social newSocial = new Social(postname, content, 
                    reviewed, published);
            newSocial.setId(id);
            SocialDBO.updateSocial(newSocial);
            return "redirect:/articles/socialList";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "articles/editSocial/{id}";
    }
    
}
