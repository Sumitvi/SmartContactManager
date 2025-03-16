package com.scm.SmartContactManager.entities;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    @Column(name="user_name" , nullable = false)
    private String name;

    @Column(unique = true , nullable = false)
    private String email;

    @Getter(AccessLevel.NONE)
    private String password;

    @Lob
    private String about;

    @Lob
    private String profilePic;

    private String phoneNumber;

    @Getter(AccessLevel.NONE)
    private boolean enabled = true;

//    public boolean isEmailVarified() {
//        return emailVarified;
//    }
//
//    public void setEmailVarified(boolean emailVarified) {
//        this.emailVarified = emailVarified;
//    }

    public boolean isEmailVarified() {
        return emailVarified;
    }

    public void setEmailVarified(boolean emailVarified) {
        this.emailVarified = emailVarified;
    }

    private boolean emailVarified = false;
    private boolean phoneVarified = false;

//    SELF , GOOGLE , LINKEDIN , GITHUB
    @Enumerated(value = EnumType.STRING)
    private Providers provider = Providers.SELF;
    private String providerUserId;




    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL , fetch = FetchType.LAZY , orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();


//    overridden methods



    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleList = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> roles = roleList.stream().map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
        return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }


}
