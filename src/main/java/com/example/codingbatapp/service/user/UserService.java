package com.example.codingbatapp.service.user;

import com.example.codingbatapp.entity.UsersEntity;
import com.example.codingbatapp.payload.ApiResponse;
import com.example.codingbatapp.payload.PagedResponse;

public interface UserService {

    PagedResponse<UsersEntity> getUsers(int page, int size);

    UsersEntity getUser(Long id);

    ApiResponse saveUser(UsersEntity userEntity);

    ApiResponse editUser(Long id , UsersEntity usersEntity);

    ApiResponse deleteUser(Long id);

}
