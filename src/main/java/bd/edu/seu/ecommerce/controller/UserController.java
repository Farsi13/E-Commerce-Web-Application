package bd.edu.seu.ecommerce.controller;

import bd.edu.seu.ecommerce.model.User;
import bd.edu.seu.ecommerce.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/users")
    public String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }


    @GetMapping("/admin/user/delete/{email}")
    public String deleteUser(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        if (user != null && user.getEmail().contains("admin")) {
            return "redirect:/admin/users";
        }
        else {
            userService.deleteUser(email);
            return "redirect:/admin/users";
        }
    }

    @GetMapping("/admin/user/update/{email}")
    public String updateUser(@PathVariable String email, Model model) {
        User user = userService.getUserByEmail(email);

        if (user.getEmail().contains("admin")) {
            return "redirect:/admin/users";
        }

        model.addAttribute("user", user);
        return "user-update";

    }

    @PostMapping("/admin/user/update")
    public String updateUserWithNewValue(@ModelAttribute("user") User user) {

        User existingUser = userService.getUserByEmail(user.getEmail());

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        userService.saveUser(existingUser);

        return "redirect:/admin/users";
    }
}
