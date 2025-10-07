package bd.edu.seu.ecommerce.controller;

import bd.edu.seu.ecommerce.service.AccountService;
import bd.edu.seu.ecommerce.global.GlobalData;
import bd.edu.seu.ecommerce.service.CategoryService;
import bd.edu.seu.ecommerce.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ShopController {
    private final CategoryService categoryService;
    private final ProductService productService;

    public ShopController(CategoryService categoryService, ProductService productService, AccountService accountService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

//    @GetMapping("/home")
//    public String homePage(Model model) {
//        model.addAttribute("cartCount", GlobalData.cart.size());
//        return "index";
//    }

    @GetMapping("/shop")
    public String shopPage(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "shop";
    }

    @GetMapping("/shop/category/{id}")
    public String shopPageByCategory(@PathVariable int id, Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("products", productService.getAllProductsByCategoryId(id));
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "shop";
    }

    @GetMapping("/shop/viewProduct/{id}")
    public String viewProductPage(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.getProductById(id).get());
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "view-product";
    }

}
