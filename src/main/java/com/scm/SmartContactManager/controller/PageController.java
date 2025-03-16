package com.scm.SmartContactManager.controller;

import com.scm.SmartContactManager.Forms.UserForm;
import com.scm.SmartContactManager.entities.User;
import com.scm.SmartContactManager.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {


    private UserService userService;

    @Autowired
    public PageController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/")
    public String index(){
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home(Model model){
        System.out.println("Home Controller");
        model.addAttribute("name" , "Sumit Vishwakarma");
        return "home";
    }


//    about

    @RequestMapping("/about")
    public String aboutPage(){
        return "about";
    }


//    services
     @RequestMapping("/services")
     public String servicesPage(){
          return "services";
     }

//     conatact page

    @GetMapping("/contact")
    public String contactPage(){
        return "contact";
    }
//    login page

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

//    register page

    @GetMapping("/register")
    public String registerPage(Model model){

        UserForm userForm = new UserForm();
        model.addAttribute("userForm",userForm);

        return "register";
    }


//    processing register


    @RequestMapping(value = "/do-register" , method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm , BindingResult rBindingResult){
        System.out.println("Processing Request");

        System.out.println(userForm);
//        fetch data
//        validate data
        if(rBindingResult.hasErrors()){
            return "register";
        }
//        save to db


        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
//        user.setEnabled(false);
        user.setProfilePic("https://upload.wikimedia.org/wikipedia/commons/thumb/9/9a/Mahesh_Babu_in_Spyder_%28cropped%29.jpg/640px-Mahesh_Babu_in_Spyder_%28cropped%29.jpg");
        userService.saveUser(user);
//        message
//        session.setAttribute("message", "Registration Sucessful");


 //        redirect
        return "redirect:/register";
    }
}
