package com.oplentia.oplentia;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Oplentia - Connect Entrepreneurs with Stakeholders");
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About Oplentia");
        return "about";
    }

    @GetMapping("/discover")
    public String discover(Model model) {
        model.addAttribute("title", "Discover Startups");
        return "discover";
    }

    @GetMapping("/blog")
    public String blog(Model model) {
        model.addAttribute("title", "Blog");
        return "blog";
    }

    @GetMapping("/startup/{id}")
    public String startup(@PathVariable String id, Model model) {
        model.addAttribute("title", "Startup Idea");
        model.addAttribute("startupId", id);
        return "startup";
    }

    @GetMapping("/blog/{slug}")
    public String blogPost(@PathVariable String slug, Model model) {
        model.addAttribute("title", "Blog Post");
        model.addAttribute("slug", slug);
        return "blog-post";
    }
}