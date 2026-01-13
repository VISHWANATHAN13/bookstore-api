package com.bookstore_api.bookstore.service;

import com.bookstore_api.bookstore.common.APIResponse;
import com.bookstore_api.bookstore.dto.LoginRequestDTO;
import com.bookstore_api.bookstore.dto.SignUpRequestDTO;
import com.bookstore_api.bookstore.entity.User;
import com.bookstore_api.bookstore.repository.UserRepository;
import com.bookstore_api.bookstore.utility.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;

    public APIResponse signUp(SignUpRequestDTO signUpRequestDTO) {

        APIResponse apiResponse = new APIResponse();

//        apiResponse.setData("it's working");  // -- test
//            Validation

//      DTO to entity
        User userEntity = new User();
        userEntity.setName(signUpRequestDTO.getName());
        userEntity.setGender(signUpRequestDTO.getGender());
        userEntity.setEmailId(signUpRequestDTO.getEmailId());
        userEntity.setPhoneNumber(signUpRequestDTO.getPhoneNumber());
        userEntity.setPassword(passwordEncoder.encode(signUpRequestDTO.getPassword()));

//        Store entity
        userEntity = userRepository.save(userEntity);

//        generate JWT
        String token = jwtUtils.generateJwt(userEntity);
        Map<String, Object> data = new HashMap<>();
        data.put("AccessToken", token);

        apiResponse.setData(data);
//        return
        return apiResponse;

    }

    public APIResponse login(LoginRequestDTO loginRequestDTO) {

        APIResponse apiResponse = new APIResponse();

//        validate
        User user = userRepository.findOneByEmailIdIgnoreCase(loginRequestDTO.getEmailId());

//        response
        if (user == null || (!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword()))) {
            apiResponse.setData("User login failed");
            return apiResponse;
        }

//        generate JWT
        String token = jwtUtils.generateJwt(user);
        apiResponse.setData(user);
        apiResponse.setStatus(HttpStatus.OK.value());
//        System.out.println("Api response returned");
        return apiResponse;

    }


}
