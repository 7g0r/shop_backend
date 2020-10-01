package com.ichmielewski.shop_backend.repository;

import com.ichmielewski.shop_backend.dto.DictionaryDTO;
import com.ichmielewski.shop_backend.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface UserRepository extends AbstractRepository<com.ichmielewski.shop_backend.entity.UserEntity> {

    Optional<com.ichmielewski.shop_backend.entity.UserEntity> findByEmail(String email);

    UserEntity getUserByLogin(String login);


    @Query("SELECT new com.ichmielewski.shop_backend.dto.DictionaryDTO(id, firstName) FROM UserEntity ")
    List<DictionaryDTO> getUserDictionary();


}
