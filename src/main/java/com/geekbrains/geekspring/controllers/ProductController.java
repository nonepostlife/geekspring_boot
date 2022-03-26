package com.geekbrains.geekspring.controllers;

import com.geekbrains.geekspring.entities.Category;
import com.geekbrains.geekspring.entities.Product;
import com.geekbrains.geekspring.services.CategoryService;
import com.geekbrains.geekspring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String showProductList(Model model,
                                  @RequestParam(defaultValue = "0") Integer page,
                                  @RequestParam(defaultValue = "5") Integer pageSize,
                                  @RequestParam(defaultValue = "id") String sortBy) {
        Page<Product> productPage = productService.getAllProductList(page, pageSize, sortBy);
        model.addAttribute("productPage", productPage);

        int totalPages = productPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "/products-list";
    }

    @GetMapping(path = "/{id}")
    public String showProductById(@PathVariable(value = "id") Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "/product";
    }

    @DeleteMapping(path = "/delete/{id}")
    public String removeProductById(@PathVariable(value = "id") Long id) {
        productService.removeById(id);
        return "redirect:/products/list";
    }

    @GetMapping(path = "/add")
    public String showAddForm(Model model) {
        Product product = new Product();
        Category category = new Category();
        List<Category> categoryList = categoryService.getAllCategoryList();
        model.addAttribute("product", product);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("cat", category);
        return "products-add-form";
    }

    @PostMapping(path = "/add")
    public String showAddForm(Category cat, Product product, Model model) {
        Category category = categoryService.getCategoryById(cat.getId());
        product.setCategory_id(category);
        product.setId(0L);
        productService.addProduct(product);
        return "redirect:/products/list";
    }
}
