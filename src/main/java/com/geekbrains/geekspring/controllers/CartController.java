package com.geekbrains.geekspring.controllers;

import com.geekbrains.geekspring.entities.CartItem;
import com.geekbrains.geekspring.entities.Category;
import com.geekbrains.geekspring.entities.Product;
import com.geekbrains.geekspring.services.CategoryService;
import com.geekbrains.geekspring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/cart")
public class CartController {

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

    @GetMapping()
    public String index(ModelMap modelMap, HttpSession session) {
        modelMap.put("total", sum(session));
        return "/cart";
    }

    @GetMapping("/buy/{id}")
    public String buy(@PathVariable("id") Long id,
                      HttpSession session) {
        if (session.getAttribute("cart") == null) {
            List<CartItem> cart = new ArrayList<CartItem>();
            cart.add(new CartItem(productService.getProductById(id), 1));
            session.setAttribute("cart", cart);
        } else {
            List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
            int index = isExists(id.intValue(), cart);
            if (index == -1) {
                cart.add(new CartItem(productService.getProductById(id), 1));
            } else {
                int quantity = cart.get(index).getQuantity() + 1;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart", cart);
        }
        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id,
                         HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        int index = isExists(id.intValue(), cart);
        cart.remove(index);
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    @PostMapping("update")
    public String update(HttpServletRequest request, HttpSession session) {
        String[] quantities = request.getParameterValues("quantity");
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        for (int i = 0; i < cart.size(); i++) {
            cart.get(i).setQuantity(Integer.parseInt(quantities[i]));
        }
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    private int isExists(int id, List<CartItem> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getId() == id)
                return i;
        }
        return -1;
    }

    private double sum(HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        double sum = 0;
        for (CartItem i : cart)  {
            sum += i.getQuantity() * i.getProduct().getPrice();
        }
        return sum;
    }
}
