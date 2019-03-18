package com.trexoftlondon.codetest.web;

import com.trexoftlondon.codetest.model.Product;
import com.trexoftlondon.codetest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ApiController {

    @Autowired
    ProductService productService;

    @GetMapping("/all-products")
    public ResponseEntity<List<Product>> getAllProducts(String categoryId, String labelType) {
        return ResponseEntity.ok().body(productService.getProducts(categoryId, labelType));
    }
}
