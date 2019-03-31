package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id", unique = true)
    private Long cartId;

    @OneToMany(mappedBy = "cart")
    private List<Product> productsList = new ArrayList<>();
}