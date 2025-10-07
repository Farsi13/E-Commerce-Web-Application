package bd.edu.seu.ecommerce.controller;

import bd.edu.seu.ecommerce.service.AccountService;
import bd.edu.seu.ecommerce.model.Checkout;
import bd.edu.seu.ecommerce.model.Product;
import bd.edu.seu.ecommerce.service.CheckoutService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.stream.Collectors;

import static bd.edu.seu.ecommerce.global.GlobalData.cart;

@Controller
public class CheckoutController {
    private final AccountService accountService;

    private final CheckoutService checkoutService;

    public CheckoutController(AccountService accountService, CheckoutService checkoutService) {
        this.accountService = accountService;
        this.checkoutService = checkoutService;
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {

        //model.addAttribute("selectedProduct", product);

        model.addAttribute("checkout", new Checkout());

        double shippingCostPerProduct = 100.0;

        double productTotal = cart.stream().mapToDouble(Product::getPrice).sum();
        int totalProducts = cart.size();
        double totalShippingCost = totalProducts * shippingCostPerProduct;

        double grandTotal = productTotal + totalShippingCost;

        model.addAttribute("cartCount", totalProducts);
        model.addAttribute("total", grandTotal);
        model.addAttribute("shipping", totalShippingCost);

        return "checkout";
    }

    @PostMapping("/payNow")
    public String checkoutProduct(@ModelAttribute("checkout") Checkout checkout,
                                  @RequestParam("finalPrice") double price) {

        String productIds = cart.stream().map(product -> String.valueOf(product.getId())).collect(Collectors.joining(", "));

        String productNames = cart.stream().map(Product::getName).collect(Collectors.joining(", "));

        Checkout newCheckout = new Checkout();
        newCheckout.setName(checkout.getName());
        newCheckout.setAddress(checkout.getAddress());
        newCheckout.setPostalCode(checkout.getPostalCode());
        newCheckout.setCity(checkout.getCity());
        newCheckout.setPhone(checkout.getPhone());
        //newCheckout.setEmail(accountService.getEmail());
        newCheckout.setEmail("test@gmail.com");
        newCheckout.setPrice(price);
        newCheckout.setPurchasingProductId(productIds);
        newCheckout.setPurchasingProductName(productNames);

        checkoutService.insertCheckout(newCheckout);
        cart.clear();

        return "redirect:/shop";
    }

}
