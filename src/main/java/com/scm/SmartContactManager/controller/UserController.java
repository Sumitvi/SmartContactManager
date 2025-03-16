package com.scm.SmartContactManager.controller;

import com.scm.SmartContactManager.entities.User;
import com.scm.SmartContactManager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

//    user dashboard

    @GetMapping("/dashboard")
    public String userDashboard(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login"; // Redirect if not authenticated
        }

        System.out.println("Principal Name: " + principal.getName()); // Debugging output

        // Fetch the user
        User loggedInUser = userService.getUserByEmail(principal.getName());

        if (loggedInUser == null) {
            System.out.println("User not found for email: " + principal.getName());
            return "error-page"; // Handle null user properly
        }

        model.addAttribute("loggedInUser", loggedInUser);
        return "user/dashboard";
    }


    //    user profile
   @RequestMapping("/profile")
   public String getProfile(Model model, Principal principal) {
    if (principal == null) {
        return "redirect:/login"; // Redirect to login if user is not authenticated
    }

    User loggedInUser = userService.getUserByEmail(principal.getName());
    if (loggedInUser == null) {
        return "error-page"; // Handle null case properly
    }

    model.addAttribute("loggedInUser", loggedInUser);
    return "user/profile";
}

//    user contact
//    user edit
//    user delete page
//    user view page
}
