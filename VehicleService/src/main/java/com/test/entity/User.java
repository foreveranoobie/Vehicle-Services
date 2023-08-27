package com.test.entity;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")})
    private Set<Authority> authorities;

    @Column
    private boolean enabled;

    @Column(name = "non_expired")
    private boolean accountNonExpired;

    @Column
    private boolean credentialsNonExpired;

    @Column(name = "non_locked")
    private boolean accountNonLocked;

    public User() {
    }

    public User(String username, String password, Set<Authority> authorities, boolean enabled,
                boolean credentialsNonExpired, boolean accountNonExpired, boolean accountNonLocked) {
        this.username = username;
        this.password = password;
        this.authorities = new HashSet<>(authorities);
        this.enabled = enabled;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public Set<Authority> getAuthorities() {
        return new HashSet<>(authorities);
    }
}
