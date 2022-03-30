package com.example.codingbatapp.service.user;

import com.example.codingbatapp.entity.UsersEntity;
import com.example.codingbatapp.payload.ApiResponse;
import com.example.codingbatapp.payload.PagedResponse;
import com.example.codingbatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     * All User list return
     *
     * @return List<UserEntity>
     */
    @Override
    public PagedResponse<UsersEntity> getUsers(
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size);
        Page<UsersEntity> usersPage = userRepository.findAll(pageable);
        List<UsersEntity> content
                = usersPage.getNumberOfElements() == 0? Collections.emptyList() : usersPage.getContent();
        return new PagedResponse<>(content, usersPage.getNumber(), usersPage.getSize(),
        usersPage.getTotalElements(), usersPage.getTotalPages(), usersPage.isLast());
    }


    /**
     * This id a User return
     *
     * @param id
     * @return User
     */
    @Override
    public UsersEntity getUser(
            Long id
    ) {
        Optional<UsersEntity> userEntityOptional
                = userRepository.findById(id);
        if (userEntityOptional.isEmpty())
            return null;
        UsersEntity userEntity = userEntityOptional.get();
        return userEntity;
    }


    /**
     * Save User
     *
     * @param userEntity
     * @return ApiResponse
     */
    @Override
    public ApiResponse saveUser(
            UsersEntity userEntity
    ) {

        boolean byEmail
                = userRepository
                .existsByEmail(userEntity.getEmail());

        if (byEmail){
            return new ApiResponse("Problem with account creation:Sorry, that email address is already in use\n" +
                            "Please go back and try again",false);
        }

        UsersEntity save = userRepository.save(userEntity);
        return new ApiResponse("Created user", true);
    }

    /**
     * Edit User password
     *
     * @param id
     * @param usersEntity
     * @return ApiResponse
     */
    @Override
    public ApiResponse editUser(
            Long id,
            UsersEntity usersEntity
    ) {
        Optional<UsersEntity> optionalUserEntity = userRepository.findById(id);
        if (optionalUserEntity.isEmpty()){
           return new ApiResponse("USER NOT FOUND",false);
        }
        UsersEntity userEntity = optionalUserEntity.get();
        userEntity.setPassword(userEntity.getPassword());
        userRepository.save(userEntity);
        return new ApiResponse("EDIT USER",true);
    }


    /**
     * Delete user via id
     *
     * @param id
     * @return ApiResponse
     */
    @Override
    public ApiResponse deleteUser(
            Long id
    ) {
        try {
            userRepository.deleteById(id);
            return new ApiResponse( " delete", true);
        }catch (Exception e){
            return new ApiResponse("Xatolik!!", false);
        }
    }
}
