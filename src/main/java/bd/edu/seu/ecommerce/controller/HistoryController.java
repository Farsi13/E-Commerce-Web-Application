package bd.edu.seu.ecommerce.controller;

import bd.edu.seu.ecommerce.service.AccountService;
import bd.edu.seu.ecommerce.service.CheckoutService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HistoryController {
    private final AccountService accountService;
    private final CheckoutService checkoutService;

    public HistoryController(AccountService accountService, CheckoutService checkoutService) {
        this.accountService = accountService;
        this.checkoutService = checkoutService;
    }

    @GetMapping("/history")
    public String history(Model model) {
//        String email = accountService.getEmail();
//        model.addAttribute("records", checkoutService.getAllCheckoutsByUserEmail(email));
        return "history";
    }
}
