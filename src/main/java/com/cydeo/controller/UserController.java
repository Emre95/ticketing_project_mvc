package com.cydeo.controller;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final RoleService roleService;
    private final UserService userService;

    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }


    @GetMapping("/create")
    public String createUser1(Model model) {

        model.addAttribute("user", new UserDTO());

        model.addAttribute("roles", roleService.findAll());

        model.addAttribute("users", userService.findAll());


        return "/user/create";
    }

    @PostMapping("/create")
    public String createUser2(@ModelAttribute UserDTO user) {

        userService.save(user);

        return "redirect:/user/create";
    }

    @GetMapping("/update")
    public String updateUser1(@RequestParam String email, Model model){

        model.addAttribute("user",userService.findById(email));
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("users", userService.findAll());

        return "/user/update";
    }

    @PostMapping("/update")
    public String updateUser2(UserDTO user){

        userService.update(user);

        return "redirect:/user/create";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam String email){

        userService.deleteById(email);

        return "redirect:/user/create";
    }


}
