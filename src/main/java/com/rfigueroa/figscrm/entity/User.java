//package com.rfigueroa.figscrm.entity;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import javax.persistence.*;
//
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//@Entity
//@Table(name = "user")
//@Data
//@NoArgsConstructor
//public class User implements UserDetails {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Integer id;
//
//    @Column(name = "username", length = 50)
//    private String userName;
//
//    @Column(name = "first_name", length = 50)
//    private String firstName;
//
//    @Column(name = "last_name", length = 50)
//    private String lastName;
//
//    @Column(name = "email", length = 50)
//    private String email;
//
//    @Column(name = "password", length = 80)
//    private String password;
//
//   @Enumerated(EnumType.STRING)
//    private Role role;
//
////    @ManyToMany(fetch = FetchType.LAZY,
////                cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
////    @JoinTable(
////        name = "user_roles",
////        joinColumns = @JoinColumn(name = "user_id"),
////        inverseJoinColumns = @JoinColumn(name = "role_id"))
////    private List<Role> roles;
//
//
//    // constructors, getters, setters, toString, hashcode, and equals methods are defined by lombok
//
//
//    // add convenience method for adding a role to a user...
////    public void addRole(Role roleToAdd) {
////
////        if (roles == null) {
////            roles = new ArrayList<>();
////        }
////
////        roles.add(roleToAdd);
////    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(role.name()));
//    }
//
//    @Override
//    public String getUsername() {
//        return userName;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
