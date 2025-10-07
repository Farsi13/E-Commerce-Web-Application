package bd.edu.seu.ecommerce.controller;

import bd.edu.seu.ecommerce.dto.LoginDto;
import bd.edu.seu.ecommerce.service.AccountService;
import bd.edu.seu.ecommerce.model.User;
import bd.edu.seu.ecommerce.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final AccountService accountService;

    private final UserService userService;

    public LoginController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    /*
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("dto", new LoginDto());
        return "login";
    }
    */


    @GetMapping("/login")
    public String loginPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        if (authentication != null
                && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken)) {

            // Check user roles
            if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
                return "redirect:/admin";
            } else {
                return "redirect:/shop";
            }
        } else {
            model.addAttribute("dto", new LoginDto());
            return "login";
        }
    }




    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute LoginDto dto, Model model) {

        accountService.setEmail(dto.getEmail());

        model.addAttribute("dto", new LoginDto());

        User user = userService.getUserByEmail(dto.getEmail());

        if (user != null && user.getEmail().contains("admin") && dto.getPassword().equals(user.getPassword())) {
            return "redirect:/admin";
        }
        else if (user != null && dto.getPassword().equals(user.getPassword())) {
            model.addAttribute("user", user);
            return "redirect:/shop";
        }
        else {
            return "login";
        }

    }
}
