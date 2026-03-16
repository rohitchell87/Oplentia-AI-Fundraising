package com.oplentia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private IdeaRepository ideaRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Oplentia - Connect Entrepreneurs with Stakeholders");
        model.addAttribute("ideasCount", ideaRepository.count());
        model.addAttribute("investorsCount", userRepository.findAll().stream().filter(u -> "investor".equals(u.getRole())).count());
        model.addAttribute("fundedCount", 50); // hardcoded for now
        model.addAttribute("fundsAmount", 2.0); // hardcoded for now
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
        model.addAttribute("ideas", ideaRepository.findAll());
        return "discover";
    }

    @GetMapping("/submit")
    public String submitIdeaForm(Model model) {
        model.addAttribute("title", "Submit Your Idea");
        return "submit";
    }

    @PostMapping("/submit")
    public String submitIdea(@RequestParam String title, @RequestParam String description,
                             @RequestParam String founderName, @RequestParam String email, RedirectAttributes redirectAttributes, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            redirectAttributes.addFlashAttribute("message", "You must be logged in to submit an idea!");
            return "redirect:/login";
        }
        Idea idea = new Idea(title, description, founderName, email, user);
        ideaRepository.save(idea);
        redirectAttributes.addFlashAttribute("message", "Your idea has been submitted successfully!");
        return "redirect:/discover";
    }

    @GetMapping("/blog")
    public String blog(Model model) {
        model.addAttribute("title", "Blog");
        return "blog";
    }

    @GetMapping("/startup/{id}")
    public String startup(@PathVariable Long id, Model model) {
        Idea idea = ideaRepository.findById(id).orElse(null);
        if (idea == null) {
            return "redirect:/discover";
        }
        model.addAttribute("title", "Startup Idea");
        model.addAttribute("idea", idea);
        return "startup";
    }

    @GetMapping("/signup/founder")
    public String signupFounder(Model model) {
        model.addAttribute("title", "Sign Up as Founder");
        model.addAttribute("userType", "founder");
        return "signup";
    }

    @GetMapping("/signup/investor")
    public String signupInvestor(Model model) {
        model.addAttribute("title", "Sign Up as Investor");
        model.addAttribute("userType", "investor");
        return "signup";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Login");
        return "login";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String userType, @RequestParam String name,
                         @RequestParam String email, @RequestParam String password, RedirectAttributes redirectAttributes, HttpSession session) {
        User user = new User(name, email, password, userType);
        userRepository.save(user);
        session.setAttribute("user", user);
        redirectAttributes.addFlashAttribute("message", "Thank you for signing up as a " + userType + "!");
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, RedirectAttributes redirectAttributes, HttpSession session) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("user", user);
            redirectAttributes.addFlashAttribute("message", "Login successful!");
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("message", "Invalid email or password!");
            return "redirect:/login";
        }
    }

    @GetMapping("/leaderboard")
    public String leaderboard(Model model) {
        model.addAttribute("title", "AI Leaderboard");
        model.addAttribute("ideas", ideaRepository.findAllByOrderByAiScoreDesc());
        return "leaderboard";
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("title", "My Profile");
        model.addAttribute("user", user);
        model.addAttribute("totalIdeas", ideaRepository.count());

        if (user != null) {
            model.addAttribute("myIdeas", ideaRepository.findByUser(user));
            model.addAttribute("ideasCount", ideaRepository.findByUser(user).size());
        }

        return "profile";
    }

    @GetMapping("/my-startup")
    public String myStartup(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("title", "My Startup");
        if (user != null) {
            model.addAttribute("ideas", ideaRepository.findByUser(user));
        }
        return "my-startup";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("message", "Logged out successfully!");
        return "redirect:/";
    }

    @PostMapping("/newsletter")
    public String subscribeNewsletter(@RequestParam String email, RedirectAttributes redirectAttributes) {
        // In a real app, save to database or send email
        redirectAttributes.addFlashAttribute("message", "Thank you for subscribing! We'll keep you updated.");
        return "redirect:/";
    }
}