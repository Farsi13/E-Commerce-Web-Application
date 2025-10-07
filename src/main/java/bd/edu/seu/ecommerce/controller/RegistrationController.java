package bd.edu.seu.ecommerce.controller;

import bd.edu.seu.ecommerce.dto.LoginDto;
import bd.edu.seu.ecommerce.dto.RegistrationDto;
import bd.edu.seu.ecommerce.model.User;
import bd.edu.seu.ecommerce.model.UserType;
import bd.edu.seu.ecommerce.service.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    /*
    @GetMapping("/registration")
    public String registrationPage( Model model) {
        model.addAttribute("dto", new RegistrationDto());
        return "registration";
    }
    */


    @GetMapping("/registration")
    public String registrationPage(Model model) {
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
            model.addAttribute("dto", new RegistrationDto());
            return "registration";
        }
    }




    @PostMapping("/registration")
    public String registrationSubmit(@ModelAttribute RegistrationDto dto, Model model) {

        User user = userService.getUserByEmail(dto.getEmail());

        if(user == null) {

            User newUser = new User();
            newUser.setName(dto.getName());
            newUser.setEmail(dto.getEmail());
            newUser.setPassword(dto.getPassword());

            if (dto.getEmail().contains("admin")) {
                newUser.setUserType(UserType.ADMIN);
                newUser.getRoleList().add("ROLE_ADMIN");
            } else {
                newUser.setUserType(UserType.USER);
                newUser.getRoleList().add("ROLE_USER");
            }

            userService.saveUser(newUser);

            LoginDto loginDto = new LoginDto();
            loginDto.setRegistrationSuccess(true);
            model.addAttribute("dto", loginDto);
            return "login";
        }
        else {
            RegistrationDto registrationDto = new RegistrationDto();
            registrationDto.setEmailExist(true);
            model.addAttribute("dto", registrationDto);
            //return "registration";
            return "redirect:/login?register=true";
        }
    }
}
