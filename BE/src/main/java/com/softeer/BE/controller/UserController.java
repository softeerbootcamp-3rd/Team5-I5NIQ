package com.softeer.BE.controller;

import com.softeer.BE.domain.dto.UsersRequest;
import com.softeer.BE.domain.dto.UsersRequest.JoinForm;
import com.softeer.BE.domain.dto.UsersRequest.LoginForm;
import com.softeer.BE.domain.dto.UsersResponse;
import com.softeer.BE.domain.dto.UsersResponse.UserIdDuplicated;
import com.softeer.BE.domain.entity.Users;
import com.softeer.BE.global.apiPayload.ApiResponse;
import com.softeer.BE.global.session.UserSessionValue;
import com.softeer.BE.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
  private final UserService userService;

  @GetMapping("/id/validation")
  public ApiResponse<UserIdDuplicated> checkDuplicate(@RequestParam(value = "user-id")String userId){
    Boolean checkUserDuplicated = userService.isDuplicated(userId);
    return ApiResponse.isSuccess(UserIdDuplicated.of(checkUserDuplicated));
  }

  @PostMapping("/join")
  public ApiResponse<Boolean> join(@RequestBody JoinForm joinForm){
    userService.join(joinForm);
    return ApiResponse.isSuccess(true);
  }

  @PostMapping("/login")
  public ApiResponse<Boolean> login(@RequestBody LoginForm loginForm,HttpServletRequest request){
    if(userService.validUser(loginForm))
      throw new RuntimeException("login failure exception");
    Users user = userService.findUserAfterValidation(loginForm.getId());
    HttpSession session = request.getSession(true);
    session.setAttribute("user",UserSessionValue.of(user));
    return ApiResponse.isSuccess(true);
  }
}
