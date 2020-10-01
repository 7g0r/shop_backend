package com.ichmielewski.shop_backend.resource;

import com.ichmielewski.shop_backend.dto.UserDTO;
import com.ichmielewski.shop_backend.entity.UserEntity;
import com.ichmielewski.shop_backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value = "/api/user")
public class UserResource extends AbstractResource<UserEntity, UserDTO> {


    private final UserService userService;
    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/find")
    @PreAuthorize("hasAuthority('view')")
    public List<UserDTO> getUsers(@RequestParam(required = false) String firstName,
                                  @RequestParam(required = false) String secondName,
                                  @RequestParam(required = false) String email,
                                  Pageable pageable) {
        return userService.getUsers(firstName, secondName, email, pageable);
    }


    @Override
    public UserService getService() {
        return userService;
    }

}
