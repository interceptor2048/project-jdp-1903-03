package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart, Long> {

    Cart findByCartId(Long cartId);

    @Override
    List<Cart> findAll();

    @Override
    Cart save(Cart cart);
}
