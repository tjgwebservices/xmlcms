package com.tjgwebservices.tjgxmlcms.controller.review;

import com.tjgwebservices.tjgxmlcms.dbo.review.ReviewDBO;
import com.tjgwebservices.tjgxmlcms.form.SocketDisplay;
import com.tjgwebservices.tjgxmlcms.model.review.Review;
import com.tjgwebservices.tjgxmlcms.form.review.ReviewForm;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class ReviewController {
    private static List<Review> reviews = new ArrayList<Review>();
 
    static {
    }

    @RequestMapping(value = { "/reviewList" }, method = RequestMethod.GET)
    public String reviewList(Model model) {
        reviews = ReviewDBO.loadReviews();
        model.addAttribute("reviews", reviews);
 
        return "reviewList";
    }
 
    @RequestMapping(value = { "/addReview" }, method = RequestMethod.GET)
    public String addReviewForm(Model model) {
 
        ReviewForm reviewForm = new ReviewForm();
        SocketDisplay socketDisplay = new SocketDisplay();
        model.addAttribute("reviewForm", reviewForm);
        model.addAttribute("socketDisplay", socketDisplay);
 
        return "addReview";
    }
 
    @RequestMapping(value = { "/addReview" }, method = RequestMethod.POST)
    public String addReviewSave(Model model, //
        @ModelAttribute("reviewForm") ReviewForm reviewForm) {
        String author = reviewForm.getAuthor();
        String authorDate = reviewForm.getAuthorDate();
        String title = reviewForm.getTitle();
        String description = reviewForm.getDescription();
        String content = reviewForm.getContent();
 
        if (author != null && author.length() > 0 
                && authorDate != null && authorDate.length() > 0 
                && title != null && title.length() > 0 
                && description != null && description.length() > 0
                && content != null && content.length() > 0) {
            Review newReview = new Review(author, authorDate, 
                    title, description, content);
            reviews.add(newReview);
            ReviewDBO.saveSQLReview(newReview);
            return "redirect:/reviewList";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "addReview";
    }

    
    @RequestMapping(value = { "/editReview/{id}" }, method = RequestMethod.GET)
    public String editReviewForm(Model model,
            @PathVariable("id") Integer id) {
 
        ReviewForm reviewForm = new ReviewForm();
        ReviewForm reviewEditForm = new ReviewForm();
        Review editReview = reviews.stream()
            .filter((review) -> review.getId() == id)
            .collect(Collectors.toList()).get(0);
        reviewEditForm.setAuthor(editReview.getAuthor());
        reviewEditForm.setAuthorDate(editReview.getAuthorDate());
        reviewEditForm.setTitle(editReview.getTitle());
        reviewEditForm.setDescription(editReview.getDescription());
        reviewEditForm.setContent(editReview.getContent());
        model.addAttribute("reviewForm", reviewForm);
        model.addAttribute("reviewEditForm", reviewEditForm);
        return "editReview/{id}";
    }
 
    @RequestMapping(value = { "/editReview/{id}" }, method = RequestMethod.POST)
    public String editReviewSave(Model model, //
        @ModelAttribute("reviewForm") ReviewForm reviewForm) {
        String author = reviewForm.getAuthor();
        String authorDate = reviewForm.getAuthorDate();
        String title = reviewForm.getTitle();
        String description = reviewForm.getDescription();
        String content = reviewForm.getContent();
 
        if (author != null && author.length() > 0 
                && authorDate != null && authorDate.length() > 0 
                && title != null && title.length() > 0 
                && description != null && description.length() > 0
                && content != null && content.length() > 0) {
            Review newReview = new Review(author, authorDate, 
                    title, description, content);
            reviews.add(newReview);
            ReviewDBO.saveSQLReview(newReview);
            return "redirect:/reviewList";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "editReview/{id}";
    }
    
}
