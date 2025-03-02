package com.chusdev.ems.backend.api.auth;

import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.chusdev.ems.backend.api.models.entities.User;
import com.chusdev.ems.backend.api.repositories.UserRepository;

import java.util.Optional;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<User> {

    private final UserRepository userRepository;

    public SpringSecurityAuditorAware(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @NonNull
    public Optional<User> getCurrentAuditor() {
        //Obtengo el nombre del usuario del contexto de Spring Security
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        
        //Recupero el usuario por el nombre usando el userRepository
        //Asumo que el usuario existe ya que está logado con ese nombre
        return userRepository.findByUsername(username);
    }
}
