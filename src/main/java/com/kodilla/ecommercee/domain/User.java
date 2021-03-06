package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Entity
@ToString
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private Long userId;

    @Column(name = "username")
    private String userName;

    @Column(name = "isblocked")
    private Boolean isBlocked;

    @Column(name = "useridkey")
    private Long userIdKey;

    @OneToMany(mappedBy = "user")
    private List<Order> ordersList = new ArrayList<>();

    public User() {
    }

    public User(String userName, Boolean isBlocked, Long userIdKey) {
        this.userName = userName;
        this.isBlocked = isBlocked;
        this.userIdKey = userIdKey;
    }

}

