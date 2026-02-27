package com.ESGIS.IRT2.Services;

import com.ESGIS.IRT2.Model.Abonne;
import com.ESGIS.IRT2.Model.Admin;
import com.ESGIS.IRT2.Repository.AbonneRepository;
import com.ESGIS.IRT2.Repository.AdminRepository;
import com.ESGIS.IRT2.Repository.CaissiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private AbonneRepository abonneRepository;

    @Autowired
    private CaissiereRepository caissiereRepository;

    @Autowired
    private AdminRepository adminRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUserName(username);
        if (admin == null) {
            throw new UsernameNotFoundException("User not found");
        } else {
            return new User(admin.getUserName(), admin.getPassword(), getGrantedAuthorities(admin.getRole()));
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }
}
