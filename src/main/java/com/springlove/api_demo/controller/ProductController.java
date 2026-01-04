package com.springlove.api_demo.controller;

import com.springlove.api_demo.dto.ProductCreateDto;
import com.springlove.api_demo.dto.ProductPatchDto;
import com.springlove.api_demo.entity.Product;
import com.springlove.api_demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {
    // Field
    private final ProductService productService;

    // Constructor
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET /api/products
    // User, Manager
    /*
    @GetMapping("/products")
    public List<Product> findAllProducts() {
        return productService.findAllProducts();
    }*/

    // GET /api/products/{id}
    // User, Manager
    @GetMapping("/products/{id}")
    public Product findProductById(@PathVariable int id) {
        return productService.findProductById(id);
    }

    // POST /api/products
    // Manager
    @PostMapping("/products")
    public Product saveProduct(@Valid @RequestBody ProductCreateDto productCreateDto) {
        return productService.addProduct(productCreateDto);
    }

    // PUT /api/products/{id}
    // Manager
    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable int id,
                                 @Valid @RequestBody ProductCreateDto productCreateDto) {
        return productService.updateProduct(id, productCreateDto);
    }

    // DELETE /api/products/{id}
    // Manager
    @DeleteMapping("/products/{id}")
    public void deleteProductById(@PathVariable int id) {
        productService.deleteProductById(id);
    }

    // PATCH /api/products/{id}
    // Manager
    @PatchMapping("/products/{id}")
    public Product patchProduct(@PathVariable int id,
                                @Valid @RequestBody ProductPatchDto productPatchDto) {
        return productService.patchProduct(id, productPatchDto);
    }

    // Pagination
    // http://localhost:8080/api/products?page=0&size=2                => Test for pagination
    // http://localhost:8080/api/products?page=0&size=2&sort=price,asc => Test for sort
    @GetMapping("/products")
    public Page<Product> getProducts(Pageable pageable) {
        return productService.findAll(pageable);
    }


}
