package org.dnyanyog.services;

import java.util.Optional;
import org.dnyanyog.common.ResponseCode;
import org.dnyanyog.dto.UserData;
import org.dnyanyog.dto.UserRequest;
import org.dnyanyog.dto.UserResponse;
import org.dnyanyog.encryption.EncryptionService;
import org.dnyanyog.entity.Users;
import org.dnyanyog.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserManagementService {

  @Autowired UsersRepository userRepo;

  @Autowired EncryptionService encryptionService;

  public UserResponse adduser(@RequestBody UserRequest userRequest) throws Exception {
    UserResponse response = new UserResponse();

    Users usersTable = new Users();

    usersTable.setAge(userRequest.getAge());
    usersTable.setEmail(userRequest.getEmail());
    usersTable.setPassword(encryptionService.encrypt(userRequest.getPassword()));
    usersTable.setUserName(userRequest.getUsername());

    userRepo.save(usersTable);

    response.setStatus(ResponseCode.USER_ADDED.getStatus());
    response.setMessage(ResponseCode.USER_ADDED.getMessage());
    response.setUserId(usersTable.getUserId());

    return response;
  }

  public UserData getSingleUser(Long userId) {

    UserData userResponse = new UserData();

    Optional<Users> receivedData = userRepo.findById(userId);

    if (receivedData.isEmpty()) {
      userResponse.setStatus(ResponseCode.USER_NOT_FOUND.getStatus());
      userResponse.setMessage(ResponseCode.USER_NOT_FOUND.getMessage());
    } else {
      Users user = receivedData.get();
      userResponse.setStatus(ResponseCode.USER_SEARCH.getStatus());
      userResponse.setMessage(ResponseCode.USER_SEARCH.getMessage());
      userResponse.setAge(user.getAge());
      userResponse.setEmail(user.getEmail());
      userResponse.setUsername(user.getUserName());
      userResponse.setPassword(user.getPassword());
      userResponse.setUserId(user.getUserId());
    }
    return userResponse;
  }
}
