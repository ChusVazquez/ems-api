package com.chusdev.ems.backend.api.models.dto.mapper;

import com.chusdev.ems.backend.api.models.dto.UserDto;
import com.chusdev.ems.backend.api.models.entities.User;

public class DtoMapperUser {    

    private DtoMapperUser() {}

    private User user;
    

    public static DtoMapperUser builder(){
        return new DtoMapperUser();        
    }


    public DtoMapperUser setUser(User user) {
        this.user = user;
        return this;
    }

    public UserDto build(){
        if (user == null){
            throw new RuntimeException("Debe pasar el entity user");
        }

        boolean isAdmin = user.getRoles().stream()
            .anyMatch(r -> "ROLE_ADMIN".equals(r.getName()));
        UserDto userDto = new UserDto(
            this.user.getId(), 
            this.user.getUsername(),
            this.user.getEmail(),
            isAdmin);

        return userDto;
    }

}
