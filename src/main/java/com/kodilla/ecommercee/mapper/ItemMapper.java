package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Item;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.ItemDto;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.ItemService;
import com.kodilla.ecommercee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemMapper {

    @Autowired
    CartService cartService;
    @Autowired
    ProductService productService;

    public Item mapToItem(final ItemDto itemDto) {
        return new Item(
                itemDto.getId(),
                getCartWithId(itemDto.getCart()),
                getProductWithId(itemDto.getProductId()),
                itemDto.getQuantity());
    }

    public ItemDto mapToItemDto(Item item) {
        return new ItemDto(
                item.getId(),
                item.getCart().getCartId(),
                item.getProduct().getId(),
                item.getProduct().getName(),
                item.getQuantity());
    }

    public List<ItemDto> mapToItemDtoList(final List<Item> itemList) {
        return itemList
                .stream()
                .map(this::mapToItemDto)
                .collect(Collectors.toList());
    }

    public List<Item> mapToItemList(final List<ItemDto> itemDtoList){
        return itemDtoList
                .stream()
                .map(this::mapToItem)
                .collect(Collectors.toList());
    }


    private Cart getCartWithId(Long id) {
        if (id == null || id == 0)
            return null;
        return cartService.getCart(id);
    }

    private Product getProductWithId(Long id) {
        if (id == null || id == 0)
            return null;
        return productService.getProduct(id);
    }
}
