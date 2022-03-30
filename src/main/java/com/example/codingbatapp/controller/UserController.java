package com.example.codingbatapp.controller;

import com.example.codingbatapp.entity.UsersEntity;
import com.example.codingbatapp.payload.ApiResponse;
import com.example.codingbatapp.payload.PagedResponse;
import com.example.codingbatapp.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    /**
     * All User list return
     *
     * @return List<UserEntity>
     */

    @GetMapping("/list")
    public PagedResponse<UsersEntity> getUsers(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size
    ) {


        return userService.getUsers(page, size);
    }

    /**
     * This id a User return
     *
     * @param id
     * @return User
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsersEntity> getUser(
            @PathVariable Long id
    ) {
        UsersEntity user = userService.getUser(id);
        if (user == null)
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        return ResponseEntity.status(202).body(user);
    }


    /**
     * Save User
     *
     * @param userEntity
     * @return ApiResponse
     */
    @PostMapping("/save")
    public ResponseEntity<ApiResponse> saveUser(
            @Valid @RequestBody UsersEntity userEntity
    ) {


        ApiResponse apiResponse = userService.saveUser(userEntity);
        if (!apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);

        return ResponseEntity.status(201).body(apiResponse);

    }

    /**
     * Edit User password
     *
     * @param id
     * @param usersEntity
     * @return ApiResponse
     */
    @PutMapping("/edit/{id}")
    public ResponseEntity<ApiResponse> editUser(
            @PathVariable Long id,
            @Valid @RequestBody UsersEntity usersEntity
    ) {
        ApiResponse apiResponse = userService.editUser(id, usersEntity);

        if (!apiResponse.isSuccess())
            return ResponseEntity.status(409).body(apiResponse);

        return ResponseEntity.status(200).body(apiResponse);
    }

    /**
     * Delete user via id
     *
     * @param id
     * @return ApiResponse
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteUser(
            @PathVariable Long id
    ) {
        ApiResponse apiResponse = userService.deleteUser(id);
        if (!apiResponse.isSuccess())
            return ResponseEntity.status(409).body(apiResponse);

        return ResponseEntity.status(202).body(apiResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
