package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {


    public Product mapToProduct(ProductDto productDto) {
        return new Product(productDto.getId(),
                productDto.getName(),
                productDto.getPrice());
    }

    public ProductDto mapToProductDto(Product product){
        return new ProductDto(product.getId(),
                product.getName(),
                product.getPrice());
    }

    public List<ProductDto> mapToProductDtoList(List<Product> productList){
        return productList.stream()
                .map(t -> new ProductDto(t.getId(),t.getName(),t.getPrice()))
                .collect(Collectors.toList());
    }
}