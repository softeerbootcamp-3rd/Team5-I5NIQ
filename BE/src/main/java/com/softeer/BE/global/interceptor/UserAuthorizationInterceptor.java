package com.softeer.BE.global.interceptor;

import com.softeer.BE.global.apiPayload.code.statusEnums.ErrorStatus;
import com.softeer.BE.global.exception.GeneralHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class UserAuthorizationInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    HttpSession currentSession = request.getSession(false);
    if(currentSession==null)
      throw new GeneralHandler(ErrorStatus._UNAUTHORIZED);
    return HandlerInterceptor.super.preHandle(request, response, handler);
  }
  @NoArgsConstructor
  @Getter
  public static class AuthorizationException extends RuntimeException{
    public AuthorizationException(String message){
      super(message);
    }
  }
}
