package com.chusdev.ems.backend.api.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chusdev.ems.backend.api.repositories.UserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository repository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.chusdev.ems.backend.api.models.entities.User> o = repository.findByUsername(username);
        if (!o.isPresent()){
            throw new UsernameNotFoundException(String.format("Username %s no existe en el sistema", username));
        }

        com.chusdev.ems.backend.api.models.entities.User user = o.orElseThrow();

        List<GrantedAuthority> authorities = user.getRoles()
            .stream()
            .map(r -> new SimpleGrantedAuthority(r.getName()))
            .collect(Collectors.toList());

        //12345 encriptado con bcrypt
        // String encryptedPass = "$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS";

        return new User(user.getUsername(), 
                user.getPassword(),                 
                true, 
                true, 
                true, 
                true, 
                authorities);
    }

}
