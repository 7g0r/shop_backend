package com.ichmielewski.shop_backend.service.impl;

import com.ichmielewski.shop_backend.dto.DictionaryDTO;
import com.ichmielewski.shop_backend.dto.UserDTO;
import com.ichmielewski.shop_backend.entity.AbstractEntity;
import com.ichmielewski.shop_backend.entity.UserEntity;
import com.ichmielewski.shop_backend.mappers.AbstractMapper;
import com.ichmielewski.shop_backend.mappers.UserMapper;
import com.ichmielewski.shop_backend.repository.AbstractRepository;
import com.ichmielewski.shop_backend.repository.UserRepository;
import com.ichmielewski.shop_backend.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class UserServiceImpl extends AbstractServiceImpl<UserEntity, UserDTO> implements UserService {

    private UserMapper userMapper;
    private UserRepository userRepository;


    public UserServiceImpl(EntityManager entityManager,
                           UserMapper userMapper,
                           UserRepository userRepository) {
        super(userRepository, userMapper, entityManager);
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<DictionaryDTO> getUserDictionary() {
        return userRepository.getUserDictionary();
    }

    @Override
    public List<UserDTO> getUsers(String firstName, String secondName, String email, Pageable pageable) {
        List<UserEntity> users = userRepository.findAll(new Specification<UserEntity>() {
            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.conjunction();
                if (!StringUtils.isEmpty(firstName)) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%"));
                }
                if (!StringUtils.isEmpty(secondName)) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("secondName"), "%" + secondName + "%"));
                }
                if (!StringUtils.isEmpty(email)) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("email"), "%" + email + "%"));
                }
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get("firstName")), criteriaBuilder.asc(root.get("id")));

                return predicate;
            }
        }, pageable).getContent();
        return userMapper.mapToDtos(users);
    }

    @Override
    protected Class<? extends AbstractEntity> getEntityClass() {
        return UserEntity.class;
    }
}
