package com.rfigueroa.figscrm.enums;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

//@Entity
//@Table(name = "role")
//@Data
public enum Role {

    GUEST,
    USER,
    ADMIN


    
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Integer id;
//
//    @Column(name = "name", length = 50)
//    private String name;
//
//    @ManyToMany(fetch = FetchType.LAZY,
//                cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
//    @JoinTable(
//        name = "user_roles",
//        joinColumns = @JoinColumn(name = "role_id"),
//        inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private List<User> users;
//
//
//    // constructors, getters, setters, toString, hashcode, and equals methods are defined by lombok
//
//
//    // add a convenience method for adding a single user
//    public void addUser(User theUser) {
//        if (users == null) {
//            users = new ArrayList<>();
//        }
//
//        users.add(theUser);
//    }
}
