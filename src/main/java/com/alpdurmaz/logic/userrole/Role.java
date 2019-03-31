package com.alpdurmaz.logic.userrole;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "userrole")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int id;
    @Column(name = "userrole")
    private String role;
}
