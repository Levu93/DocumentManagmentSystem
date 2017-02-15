/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.fon.documentmanagementsystem.domain.Rola;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Vukasin
 */
public class UserDto implements UserDetails{

    private String username;
    private String password;
    private String ime;
    private String prezime;
    private Rola rola;

    public UserDto(String username, String password, String ime, String prezime, Rola rola) {
        this.username = username;
        this.password = password;
        this.ime = ime;
        this.prezime = prezime;
        this.rola = rola;
    }
    
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(rola.getNazivRole()));       
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return  username;
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
        return true;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public Rola getRola() {
        return rola;
    }
    
}
