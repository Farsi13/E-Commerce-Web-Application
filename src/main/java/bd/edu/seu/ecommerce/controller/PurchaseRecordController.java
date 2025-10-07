package bd.edu.seu.ecommerce.controller;

import bd.edu.seu.ecommerce.service.CheckoutService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PurchaseRecordController {
    private final CheckoutService checkoutService;

    public PurchaseRecordController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @GetMapping("/admin/purchase-record")
    public String purchaseRecord(Model model) {
        model.addAttribute("records", checkoutService.getAllCheckouts());
        return "purchase-record";
    }
}
