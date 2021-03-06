package com.kodilla.ecommercee.domain;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "productgroups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private Long groupId;

    @Column(name = "groupname")
    private String groupName;

    @OneToMany(mappedBy = "group")
    private List<Product> productsList = new ArrayList<>();

    public Group(String groupName) {
        this.groupName = groupName;
    }
}