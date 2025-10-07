package bd.edu.seu.ecommerce.controller;

import bd.edu.seu.ecommerce.dto.ProductDto;
import bd.edu.seu.ecommerce.model.Product;
import bd.edu.seu.ecommerce.service.CategoryService;
import bd.edu.seu.ecommerce.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class ProductController {

    public String uploadDirectory = "src/main/resources/static/productImages";

    private final CategoryService categoryService;
    private final ProductService productService;

    public ProductController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/admin/products")
    public String products(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @GetMapping("/admin/product/add")
    public String getProduct(Model model) {
        model.addAttribute("dto", new ProductDto());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "product-add";
    }

    @PostMapping("/admin/product/add")
    public String addProduct(@ModelAttribute("dto") ProductDto dto,
                             @RequestParam("productImage") MultipartFile file,
                             @RequestParam("imgName") String imgName) throws IOException {

        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setCategory(categoryService.getCategoryById(dto.getCategoryId()).get());
        product.setPrice(dto.getPrice());
        product.setWeight(dto.getWeight());
        product.setDescription(dto.getDescription());
        String image;

        if(!file.isEmpty()) {
            image = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDirectory, imgName);
            //Files.write(fileNameAndPath, file.getBytes());
        }
        else {
            image = imgName;
        }
        product.setImageName(image);
        productService.addProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteProductById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable int id, Model model) {
        Optional<Product> product = productService.getProductById(id);

        if (product.isPresent()) {
            ProductDto productDto = new ProductDto();
            productDto.setId(product.get().getId());
            productDto.setName(product.get().getName());
            productDto.setCategoryId(product.get().getCategory().getId());
            productDto.setPrice(product.get().getPrice());
            productDto.setWeight(product.get().getWeight());
            productDto.setDescription(product.get().getDescription());
            productDto.setImageName(product.get().getImageName());

            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("dto", productDto);

            return "product-add";
        }
        else {
            return "404";
        }
    }
}
