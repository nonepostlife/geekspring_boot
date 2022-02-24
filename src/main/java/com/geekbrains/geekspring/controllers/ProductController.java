package com.geekbrains.geekspring.controllers;

import com.geekbrains.geekspring.entities.Category;
import com.geekbrains.geekspring.entities.Product;
import com.geekbrains.geekspring.services.CategoryService;
import com.geekbrains.geekspring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

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

    @RequestMapping("/list")
    public String showProductList(Model model) {
        List<Product> allProduct = productService.getAllProductList();
        model.addAttribute("productsList", allProduct);
        return "/products-list";
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String showProductById(@PathVariable(value = "id") Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "/product";
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
    public String removeProductById(@PathVariable(value = "id") Long id) {
        productService.removeById(id);
        return "redirect:/products/list";
    }

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String showAddForm(Model model) {
        Product product = new Product();
        List<Category> categoryList = categoryService.getAllCategoryList();
        model.addAttribute("product", product);
        model.addAttribute("categoryList", categoryList);
        return "products-add-form";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String showAddForm(Product product) {
        productService.addProduct(product);
        return "redirect:/products/list";
    }
}
