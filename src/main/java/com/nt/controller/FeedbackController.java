package com.nt.controller;

import com.nt.entity.Feedback;
import com.nt.entity.User;
import com.nt.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    public String showFeedbackForm(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("feedbackList", feedbackService.getFeedbackByUser(user));
        model.addAttribute("feedback", new Feedback());
        return "feedback";
    }

    @PostMapping
    public String saveFeedback(@ModelAttribute("feedback") Feedback feedback, 
                               @AuthenticationPrincipal User user) {
        feedback.setUser(user);
        feedbackService.saveFeedback(feedback);
        return "redirect:/feedback";
    }

    @GetMapping("/edit/{id}")
    public String editFeedback(@PathVariable Long id, 
                               Model model, 
                               @AuthenticationPrincipal User user) {
        Feedback feedback = feedbackService.getFeedbackById(id);
        if (feedback == null || !feedback.getUser().getId().equals(user.getId())) {
            return "redirect:/feedback?error=unauthorized";
        }
        model.addAttribute("feedback", feedback);
        model.addAttribute("feedbackList", feedbackService.getFeedbackByUser(user));
        return "feedback";
    }

    @PostMapping("/delete/{id}")
    public String deleteFeedback(@PathVariable Long id, 
                                 @AuthenticationPrincipal User user,
                                 RedirectAttributes redirectAttributes) {
        
        Feedback feedback = feedbackService.getFeedbackById(id);
        
        if (feedback == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Feedback not found!");
            return "redirect:/feedback";
        }
        
        if (!feedback.getUser().getId().equals(user.getId())) {
            redirectAttributes.addFlashAttribute("errorMessage", "You are not authorized to delete this feedback!");
            return "redirect:/feedback";
        }
        
        try {
            feedbackService.deleteFeedback(id);
            redirectAttributes.addFlashAttribute("successMessage", "Feedback deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting feedback: " + e.getMessage());
        }
        
        return "redirect:/feedback";
    }
}