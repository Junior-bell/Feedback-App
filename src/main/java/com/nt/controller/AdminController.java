package com.nt.controller;

import com.nt.entity.Feedback;
import com.nt.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("feedbackList", feedbackService.getAllFeedback());
        model.addAttribute("feedback", new Feedback());
        return "admin-dashboard";
    }

    @PostMapping("/save")
    public String saveFeedback(@ModelAttribute("feedback") Feedback feedback) {
        // Admin can create new without user, or assign if needed
        feedbackService.saveFeedback(feedback);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/edit/{id}")
    public String editFeedback(@PathVariable Long id, Model model) {
        Feedback feedback = feedbackService.getFeedbackById(id);
        if (feedback == null) {
            return "redirect:/admin/dashboard?error";
        }
        model.addAttribute("feedback", feedback);
        model.addAttribute("feedbackList", feedbackService.getAllFeedback());
        return "admin-dashboard";
    }

    @GetMapping("/delete/{id}")
    public String deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return "redirect:/admin/dashboard";
    }
}