package com.ichmielewski.shop_backend.service;

import com.ichmielewski.shop_backend.dto.DictionaryDTO;
import com.ichmielewski.shop_backend.dto.UserDTO;
import com.ichmielewski.shop_backend.entity.UserEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService extends AbstractService<UserEntity, UserDTO> {
    List<DictionaryDTO> getUserDictionary();

    List<UserDTO> getUsers(String firstName, String secondName, String email, Pageable pageable);
}
