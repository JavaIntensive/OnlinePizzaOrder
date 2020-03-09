package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    //    @Autowired
//    CloudinaryConfig cloudc;
    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result,
                                          Model model) {
        model.addAttribute("user", user);
        if (result.hasErrors())
        {
            return "register";
        }
        else
        {
            userService.saveUser(user);
            model.addAttribute("message", "User Account Created");
        }
        return "redirect:/";
    }

    @RequestMapping("/")
    public String listOrders(Principal principal, Model model) {
        if(userService.getUser() != null) {
//            model.addAttribute("myuser", userService.getUser());
//            String username = principal.getName();
//            model.addAttribute("user", userRepository.findByUsername(username));
            model.addAttribute("user_id", userService.getUser().getId());
        }
        model.addAttribute("orders", orderRepository.findAll());
//        model.addAttribute("users", userRepository.findAll());
        return "index";
    }


    @GetMapping("/add")
    public String orderForm(Model model) {
//        model.addAttribute("myuser", userService.getUser());
//        model.addAttribute("user", userService.getUser());
        model.addAttribute("order", new Order());
        return "orderform";
    }

    @PostMapping("/process")
    public String processForm(@ModelAttribute Order order, Model model) {

        order.setUser(userService.getUser());
        orderRepository.save(order);
        return "redirect:/";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

//    @RequestMapping("/logout")
//    public String logout() {
//        return "redirect:/";
//    }

    @RequestMapping("/secure")
    public String secure(Principal principal, Model model) {
        String username = principal.getName();
        model.addAttribute("user", userRepository.findByUsername(username));
        return "secure";
    }

    @RequestMapping("/userorders")
    public String myMessage(Principal principal, Model model) {
        User user = userService.getUser();
        ArrayList<Order> orders = (ArrayList<Order>) orderRepository.findByUser(user);
        model.addAttribute("orders", orders);

//        if(userService.getUser() != null) {
//            String username = principal.getName();
//            model.addAttribute("user", userRepository.findByUsername(username));
//            model.addAttribute("user_id", userService.getUser().getId());
//        }
        return "userorders";
    }

    @RequestMapping("/allorders")
    public String allMessages(Model model) {
        if (userService.getUser() != null) {
            model.addAttribute("user_id", userService.getUser().getId());
        }
        model.addAttribute("orders", orderRepository.findAll());
//        model.addAttribute("users", userRepository.findAll());
        return "allorders";
    }

    @RequestMapping("/detail/{id}")
    public String showOrder(@PathVariable("id") long id, Model model) {
        model.addAttribute("order", orderRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateOrder(@PathVariable("id") long id, Model model) {
        model.addAttribute("order", orderRepository.findById(id).get());
        return "orderform";
    }

    @RequestMapping("/delete/{id}")
    public String delOrder(@PathVariable("id") long id, Authentication auth) {
        orderRepository.deleteById(id);
//        System.out.println(auth.getAuthorities().toString());
        if (auth.getAuthorities().toString().equals("[ADMIN]")) {
//            return "redirect:/allmessages";
            return "redirect:/allorders";
        }
        else {
            return "redirect:/userorders";
        }
//        return "redirect:/";
    }
}

