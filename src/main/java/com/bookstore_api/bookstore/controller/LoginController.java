package com.bookstore_api.bookstore.controller;

import com.bookstore_api.bookstore.common.APIResponse;
import com.bookstore_api.bookstore.dto.LoginRequestDTO;
import com.bookstore_api.bookstore.dto.SignUpRequestDTO;
import com.bookstore_api.bookstore.entity.User;
import com.bookstore_api.bookstore.service.LoginService;
import com.bookstore_api.bookstore.utility.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<APIResponse> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {

        APIResponse apiResponse = loginService.signUp(signUpRequestDTO);

        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse> login(@RequestBody LoginRequestDTO loginRequestDTO) {

        APIResponse apiResponse = loginService.login(loginRequestDTO);

        if (apiResponse.getStatus() == HttpStatus.OK.value()) {
            User user = (User) apiResponse.getData(); // Make sure loginService returns the User object
            String token = jwtUtils.generateJwt(user);
            apiResponse.setData(token); // Return token to client
        }

        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

    }

    @GetMapping("/privateApi")
    public ResponseEntity<APIResponse> privateApi(@RequestHeader(value = "Authorization",defaultValue = "") String authorization) {
        APIResponse apiResponse = new APIResponse();
        try {
            Claims claims = jwtUtils.verify(authorization);
            apiResponse.setData("This is a private API call. User ID: " + claims.get("userId"));
            return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
        } catch (RuntimeException e) {
            apiResponse.setStatus(401);
            apiResponse.setData(e.getMessage());
            return ResponseEntity.status(401).body(apiResponse);
        }
    }


}
