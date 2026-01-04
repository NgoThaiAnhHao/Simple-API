package com.springlove.api_demo.service;

import com.springlove.api_demo.dto.ProductCreateDto;
import com.springlove.api_demo.dto.ProductPatchDto;
import com.springlove.api_demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<Product> findAllProducts();

    Product findProductById(int id);

    Product addProduct(ProductCreateDto productCreateDto);

    Product updateProduct(int id, ProductCreateDto productCreateDto);

    void deleteProductById(int id);

    Product patchProduct(int id, ProductPatchDto productPatchDto);

    Page<Product> findAll(Pageable pageable);
}
