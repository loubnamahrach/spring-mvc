package com.jee.bdccensetspringmvc.web;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import com.jee.bdccensetspringmvc.entities.Product;
import com.jee.bdccensetspringmvc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @GetMapping("/user/index")
    @PreAuthorize("hasRole('USER')")
    public String index(Model model, 
                        @RequestParam(name="page", defaultValue = "0") int page,
                        @RequestParam(name="size", defaultValue = "5") int size,
                        @RequestParam(name="keyword", defaultValue = "") String keyword) {
        org.springframework.data.domain.Page<Product> productPage = productRepository.findByNameContains(keyword, org.springframework.data.domain.PageRequest.of(page, size));
        model.addAttribute("productList", productPage.getContent());
        model.addAttribute("pages", new int[productPage.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "products";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/user/index";
    }
    @PostMapping("/admin/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@RequestParam(name = "id") Long id, String keyword, int page){
        productRepository.deleteById(id);
        return "redirect:/user/index?page=" + page + "&keyword=" + keyword;
    }
    @GetMapping("/admin/newProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "new-product";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/saveProduct")
    public String saveProduct(@Valid Product product, BindingResult bindingResult, Model model,
                              @RequestParam(name="page", defaultValue="0") int page,
                              @RequestParam(name="keyword", defaultValue="") String keyword) {
        if(bindingResult.hasErrors()) {
            if(product.getId() == null) return "new-product";
            else return "edit-product";
        }
        productRepository.save(product);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/admin/editProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public String editProduct(Model model, @RequestParam(name = "id") Long id,
                              @RequestParam(name="page", defaultValue="0") int page,
                              @RequestParam(name="keyword", defaultValue="") String keyword) {
        Product product = productRepository.findById(id).orElse(null);
        model.addAttribute("product", product);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "edit-product";
    }
    @GetMapping("/notAuthorized")
    public String notAuthorized(){
        return "notAuthorized";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }

}