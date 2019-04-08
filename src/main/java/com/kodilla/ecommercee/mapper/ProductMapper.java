package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    @Autowired
    CartService cartService;
    @Autowired
    GroupService groupService;

    public Product mapToProduct(final ProductDto productDto) {
        return new Product(productDto.getId(),
                productDto.getName(),
                productDto.getPrice(),
                cartService.getCart(productDto.getCartId()),
                groupService.getGroup(productDto.getGroupId()));

    }

    public ProductDto mapToProductDto(final Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCart().getCartId(),
                product.getGroup().getGroupId());
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> productList) {
        return productList.stream()
                .map(t -> new ProductDto(
                        t.getId(), t.getName(),
                        t.getPrice(), t.getCart().getCartId(),
                        t.getGroup().getGroupId()))
                .collect(Collectors.toList());
    }

    public List<Product> mapToProductList(final List<ProductDto> productDtoList){
        return productDtoList.stream().map(
                productDto -> new Product(productDto.getId(),
                productDto.getName(),productDto.getPrice(),
                cartService.getCart(productDto.getCartId()),
                        groupService.getGroup(productDto.getGroupId())))
                .collect(Collectors.toList());
    }
}