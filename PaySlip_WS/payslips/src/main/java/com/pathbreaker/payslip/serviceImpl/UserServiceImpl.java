package com.pathbreaker.payslip.serviceImpl;

import com.pathbreaker.payslip.entity.User;
import com.pathbreaker.payslip.entity.UserLogin;
import com.pathbreaker.payslip.exception.Exceptions;
import com.pathbreaker.payslip.exception.NotFoundException;
import com.pathbreaker.payslip.mappers.UserLoginMapper;
import com.pathbreaker.payslip.mappers.UserMapper;
import com.pathbreaker.payslip.repository.UserLoginRepository;
import com.pathbreaker.payslip.repository.UserRepository;
import com.pathbreaker.payslip.request.UserRequest;
import com.pathbreaker.payslip.request.UserUpdateRequest;
import com.pathbreaker.payslip.response.ResultResponse;
import com.pathbreaker.payslip.response.UserResponse;
import com.pathbreaker.payslip.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper,
                           UserLoginRepository userLoginRepository,
                           UserLoginMapper userLoginMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userLoginRepository = userLoginRepository;
        this.userLoginMapper = userLoginMapper;
    }
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final UserLoginRepository userLoginRepository;
    private final UserLoginMapper userLoginMapper;
    @Override
    public ResponseEntity<?> createUser(UserRequest userRequest) {
        try {
            User userEntity = userMapper.entityToRequest(userRequest);
            UserLogin userLoginEntity = userLoginMapper.entityToRequest(userRequest);
            userEntity.setUserLoginEntity(userLoginEntity);
            userLoginEntity.setUserEntity(userEntity);

            userRepository.save(userEntity);

            ResultResponse result = new ResultResponse();
            log.info("User Registration is successfull " + userRequest.getUserId());
            result.setResult("User Registration is successfull " + userRequest.getUserId());

            return ResponseEntity.ok(result);
        } catch (Exceptions ex) {
            String message = "An error occured during User registration " + ex;
            log.info("An error occured during User registration " + ex);
            throw new Exceptions(HttpStatus.INTERNAL_SERVER_ERROR, message);
        }
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> userEntities = userRepository.findAll();

        List<UserResponse> userResponses = userEntities.stream()
                .map(userMapper::responseListToEntity)
                .collect(Collectors.toList());
        log.info("The retrieved User details are "+userResponses.size());

        return userResponses;
    }

    @Override
    public UserResponse getUserById(String userId) {
        try {
            Optional<User> userEntityOptional = userRepository.findByUserId(userId);
            if (userEntityOptional.isPresent()) {

                User userEntity = userEntityOptional.get();
                UserResponse response = userMapper.responseListToEntity(userEntity);

                log.info("Retrieving the User details of {}: " + userId);
                return response;
            } else {
                throw new NotFoundException(HttpStatus.NOT_FOUND, "The User with " + userId + " not found");
            }
        } catch (Exceptions ex) {
            // Handle other exceptions
            log.error("An error occurred while retrieving User by ID: " + userId, ex);
            throw new Exceptions(HttpStatus.INTERNAL_SERVER_ERROR,"An error occurred while retrieving User by ID: " + userId);
        }
    }

    @Override
    public ResponseEntity<?> updateUserById(String userId, UserUpdateRequest userUpdateRequest) {
        try {
            Optional<User> userEntityOptional = userRepository.findByUserId(userId);

            if (userEntityOptional.isPresent()) {
                User userEntity = userEntityOptional.get();

                // Update the existing resource with the new data from the request
                User users = userMapper.updateEntityFromRequest(userUpdateRequest, userEntity);
                // Update the resource skills entity as well, assuming it is a separate entity
                UserLogin userLoginEntity = userLoginMapper.updateEntityFromRequest(userUpdateRequest, userEntity.getUserLoginEntity());

                users.setUserLoginEntity(userLoginEntity);
                // Save the updated resource to the database
                userRepository.save(users);
                // loginRepository.save(loginEntity);

                ResultResponse result = new ResultResponse();
                log.info("USER update is successful for userId: " + userId);
                result.setResult("USER update is successful for userId : "+userId);

                return ResponseEntity.ok(result);
            } else {
                log.warn("The USER not found with " + userId);
                throw new NotFoundException(HttpStatus.NOT_FOUND, "The USER with " + userId + " not found");
            }
        } catch (Exceptions ex) {
            log.warn("An error occured while updating the USER " + userId);
            throw new NotFoundException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occured while updating the USER " + userId);
        }
    }
    @Override
    public ResponseEntity<?> deleteUserById(String userId) {
        try {
            Optional<User> userEntityOptional = userRepository.findByUserId(userId);

            if (userEntityOptional.isPresent()) {
                User userEntity = userEntityOptional.get();
                // Delete associated resource skills
                if (userEntity.getUserLoginEntity() != null) {
                    userLoginRepository.delete(userEntity.getUserLoginEntity());
                }
                // Delete the resource
                userRepository.delete(userEntity);

                ResultResponse result = new ResultResponse();
                log.info("USER deletion is successful for userId: " + userId);
                result.setResult("USER deletion is successful.....");

                return ResponseEntity.ok(result);
            } else {
                log.warn("The USER not found with "+userId);
                throw new NotFoundException(HttpStatus.NOT_FOUND, "The USER with " + userId + " not found");
            }
        } catch (Exceptions ex) {
            log.warn("An error occured while deleting the USER "+userId);
            throw new NotFoundException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occured while deleting the USER "+userId);
        }
    }
}
