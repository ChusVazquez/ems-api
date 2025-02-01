package com.chusdev.ems.backend.api.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.chusdev.ems.backend.api.models.entities.Role;

public interface RoleRepository
        extends CrudRepository<Role, Long> {

        Optional<Role> findByName(String name);        
}
