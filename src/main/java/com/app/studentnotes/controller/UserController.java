package com.app.studentnotes.controller;

import com.app.studentnotes.model.User;
import com.app.studentnotes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * The type User controller.
 */
@Controller
public class UserController {
    /**
     * The User service.
     */
    UserService userService;

    /**
     * Instantiates a new User controller.
     *
     * @param userService the user service
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Save user string.
     *
     * @param user the user
     * @return the string
     */
    @PostMapping("/save-user")
    public String saveUser(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/";
    }

    /**
     * Add user string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/adduser")
    public String addUser(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "new_user";
    }

    @PostMapping("/post-user")
    public void postUser(User user){
        user.setRole("ROLE_"+user.getRole());
        userService.saveUser(user);
    }

}
