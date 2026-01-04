package com.springlove.api_demo.service;

import com.springlove.api_demo.dto.ProductCreateDto;
import com.springlove.api_demo.dto.ProductPatchDto;
import com.springlove.api_demo.entity.Product;
import com.springlove.api_demo.exception.common.ResourceNotFoundException;
import com.springlove.api_demo.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    // Field
    private final ProductRepository productRepository;

    // Constructor
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // GET /api/products
    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    // GET /api/products/{id}
    @Override
    public Product findProductById(int id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found (ID = " + id + ")"));
    }

    // POST /api/products
    @Override
    @Transactional
    public Product addProduct(ProductCreateDto productCreateDto) {
        Product product = new Product();

        product.setName(productCreateDto.getName());
        product.setPrice(productCreateDto.getPrice());
        product.setStock(productCreateDto.getStock());

        return productRepository.save(product);
    }

    // PUT /api/products/{id}
    @Override
    @Transactional
    public Product updateProduct(int id, ProductCreateDto productCreateDto) {
        Product productFound = findProductById(id);

        productFound.setName(productCreateDto.getName());
        productFound.setPrice(productCreateDto.getPrice());
        productFound.setStock(productCreateDto.getStock());

        return productRepository.save(productFound);
    }

    // DELETE /api/products/{id}
    @Override
    @Transactional
    public void deleteProductById(int id) {
        productRepository.deleteById(id);
    }

    // PATCH /api/products/{id}
    @Override
    public Product patchProduct(int id, ProductPatchDto productPatchDto) {
        Product productFound = findProductById(id);

        if (productPatchDto.getName() != null) {
            productFound.setName(productPatchDto.getName());
        }

        if (productPatchDto.getPrice() != null) {
            productFound.setPrice(productPatchDto.getPrice());
        }

        if (productPatchDto.getStock() != null) {
            productFound.setStock(productPatchDto.getStock());
        }

        return productRepository.save(productFound);
    }

    // Pagination
    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }


}
