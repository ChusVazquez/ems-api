package com.chusdev.ems.backend.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chusdev.ems.backend.api.models.dto.UserDto;
import com.chusdev.ems.backend.api.models.dto.mapper.DtoMapperUser;
import com.chusdev.ems.backend.api.models.entities.Role;
import com.chusdev.ems.backend.api.models.entities.User;
import com.chusdev.ems.backend.api.models.interfaces.IUser;
import com.chusdev.ems.backend.api.models.request.UserRequest;
import com.chusdev.ems.backend.api.repositories.RoleRepository;
import com.chusdev.ems.backend.api.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        List<User> users = (List<User>) repository.findAll();

        return users.stream().map(u -> DtoMapperUser.builder().setUser(u).build()).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> findById(Long id) {
        return repository.findById(id)
                .map(u -> DtoMapperUser
                            .builder()
                            .setUser(u)
                            .build());                                     
    }

    @Override
    @Transactional
    public UserDto save(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);        
        user.setRoles(this.manageRoles(user));        

        User savedUser = repository.save(user);
        
        UserDto userDto = DtoMapperUser
            .builder()
            .setUser(savedUser)
            .build();
        
        return userDto;
    }

    @Override
    @Transactional
    public Optional<UserDto> update(UserRequest user, Long id) {
        Optional<User> o = this.repository.findById(id);
        UserDto userDto = null;
        if (o.isPresent()) {
            
            User userDb = o.orElseThrow();
            userDb.setUsername(user.getUsername());
            userDb.setEmail(user.getEmail());
            userDb.setRoles(this.manageRoles(user));
            userDto = this.save(userDb);
        }
        return Optional.ofNullable(userDto);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        repository.deleteById(id);
    }
    
    private List<Role> manageRoles(IUser user) {
        Optional<Role> o = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        if (o.isPresent())
        {            
            roles.add(o.orElseThrow());
        }

        if (user.isAdmin()){
            Optional<Role> oa = roleRepository.findByName("ROLE_ADMIN");
            if (oa.isPresent()) {
                roles.add(oa.orElseThrow());
            }
        }

        return roles;
    }
}
