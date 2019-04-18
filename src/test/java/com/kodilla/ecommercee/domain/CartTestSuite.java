package com.kodilla.ecommercee.domain;


import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.ItemDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.CartService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class CartTestSuite {

    @Autowired
    private CartService cartService;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    CartMapper cartMapper;

    @Test
    public void testCartSave() {
        //Given
        Cart cart = new Cart();

        cartService.saveCart(cart);

        //When
        Cart readCart = cartService.getCart(cart.getCartId());

        //Then
        Assert.assertEquals(cart, readCart);
    }

    @Test
    public void testCartFindByAll() {
        //Given
        Cart cart = new Cart();
        Cart cart2 = new Cart();
        Cart cart3 = new Cart();

        cartService.saveCart(cart);
        cartService.saveCart(cart2);
        cartService.saveCart(cart3);

        //When
        List<Cart> cartList = cartService.getAllCarts();

        //Then
        Assert.assertEquals(3, cartList.size());
    }


    @Test
    public void testProductsFromCart() {
        //Given
        Cart cart = new Cart();
        Item item1 = new Item(cart, new Product("Ham", 10.5), 10);
        Item item2 = new Item(cart, new Product("Hamster", 3.4), 5);
        Item item3 = new Item(cart, new Product("Hammer", 10.1), 2);
        cartService.saveCart(cart);

        //When
        cartService.getCart(cart.getCartId()).getItemsList().add(item1);
        cartService.getCart(cart.getCartId()).getItemsList().add(item2);
        cartService.getCart(cart.getCartId()).getItemsList().add(item3);
        cartService.saveCart(cart);

        //Then
        Assert.assertEquals(3, cartService.getCart(cart.getCartId()).getItemsList().size());


    }


    @Test
    public void testGetAllCarts() {
        //Given
        Cart cart = new Cart();
        Cart cart2 = new Cart();
        Cart cart3 = new Cart();
        Cart cart4 = new Cart();

        cartService.saveCart(cart);
        cartService.saveCart(cart2);
        cartService.saveCart(cart3);
        cartService.saveCart(cart4);

        //When
        List<Cart> cartList = cartService.getAllCarts();

        //Then
        Assert.assertEquals(4, cartList.size());
    }

    @Test
    public void testMapToCartDto() {
        //Given
        Cart cart = new Cart();
        Item item1 = new Item(cart, new Product("Ham", 10.5), 10);
        Item item2 = new Item(cart, new Product("Hamster", 3.4), 5);
        Item item3 = new Item(cart, new Product("Hammer", 10.1), 2);

        cart.getItemsList().add(item1);
        cart.getItemsList().add(item2);
        cart.getItemsList().add(item3);
        cartService.saveCart(cart);

        //When
        CartDto cartDto = cartMapper.mapToCartDto(cart);

        //Then
        Assert.assertEquals("Hammer",cartDto.getItemsList().get(2).getProductName());
        Assert.assertEquals(3,cartDto.getItemsList().size());

    }

    @Test
    public void testMapToCart(){
        //Given
        ItemDto itemDto1 = new ItemDto(1L,1L,1L,"Ham",10);
        ItemDto itemDto2 = new ItemDto(1L,1L,1L,"Hamster",5);
        ItemDto itemDto3 = new ItemDto(1L,1L,1L,"Hammer",7);
        List<ItemDto>itemDtoList = new ArrayList<>();
        itemDtoList.add(itemDto1);
        itemDtoList.add(itemDto2);
        itemDtoList.add(itemDto3);
        CartDto cartDto = new CartDto(1L,itemDtoList);

        Cart cart = cartMapper.mapToCart(cartDto);
        cartService.saveCart(cart);
        cart = cartMapper.mapToCart(cartDto);

        System.out.println(cartDto.getCartId());
        System.out.println(cartDto.getItemsList().get(0).getProductName());
        System.out.println(cart.getCartId());
        //System.out.println(cart.getItemsList().get(0).getProduct().getName());



    }

    @Test
    public void testMapToCartDtoList() {
        //Given
        Cart cart1 = new Cart();
        Item item1 = new Item(cart1, new Product("Ham", 10.5), 10);
        Item item2 = new Item(cart1, new Product("Hamster", 3.4), 5);
        Item item3 = new Item(cart1, new Product("Hammer", 10.1), 2);
        cart1.getItemsList().add(item1);
        cart1.getItemsList().add(item2);
        cart1.getItemsList().add(item3);

        Cart cart2 = new Cart();
        Item item4 = new Item(cart2, new Product("Hamburger", 7.5), 2);
        Item item5 = new Item(cart2, new Product("Computer", 1500.0), 1);
        Item item6 = new Item(cart2, new Product("Camera", 500.0), 1);
        cart2.getItemsList().add(item4);
        cart2.getItemsList().add(item5);
        cart2.getItemsList().add(item6);

        List<Cart> cartList = new ArrayList<>();
        cartList.add(cart1);
        cartList.add(cart2);

        //When
        List<CartDto> cartDtoList = cartMapper.mapToCartDtoList(cartList);

        //Then
        Assert.assertEquals("Camera",cartDtoList.get(1).getItemsList().get(2).getProductName());
        Assert.assertEquals(2,cartDtoList.size());



    }
}
