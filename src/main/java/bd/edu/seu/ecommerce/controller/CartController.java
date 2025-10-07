package bd.edu.seu.ecommerce.controller;

import bd.edu.seu.ecommerce.global.GlobalData;
import bd.edu.seu.ecommerce.model.Product;
import bd.edu.seu.ecommerce.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.thymeleaf.model.IModel;

@Controller
public class CartController {
    private final ProductService productService;

    public CartController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id, Model model) {


        GlobalData.cart.add(productService.getProductById(id).get());

        double shippingCostPerProduct = 100.0;

        double productTotal = GlobalData.cart.stream().mapToDouble(Product::getPrice).sum();
        int totalProducts = GlobalData.cart.size();
        double totalShippingCost = totalProducts * shippingCostPerProduct;

        double grandTotal = productTotal + totalShippingCost;

        model.addAttribute("cartCount", totalProducts);
        model.addAttribute("total", grandTotal);
        model.addAttribute("shipping", totalShippingCost);
        model.addAttribute("cart", GlobalData.cart);


        return "cart";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        double shippingCostPerProduct = 100.0;

        double productTotal = GlobalData.cart.stream().mapToDouble(Product::getPrice).sum();
        int totalProducts = GlobalData.cart.size();
        double totalShippingCost = totalProducts * shippingCostPerProduct;

        double grandTotal = productTotal + totalShippingCost;

        model.addAttribute("cartCount", totalProducts);
        model.addAttribute("total", grandTotal);
        model.addAttribute("shipping", totalShippingCost);
        model.addAttribute("cart", GlobalData.cart);

        return "cart";
    }



    @GetMapping("/cart/removeItem/{index}")
    public String removeCartItem(@PathVariable int index) {
        GlobalData.cart.remove(index);
        return "redirect:/cart";
    }



}
