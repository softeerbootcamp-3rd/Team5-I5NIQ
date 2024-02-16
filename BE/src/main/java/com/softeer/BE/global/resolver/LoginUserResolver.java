package com.softeer.BE.global.resolver;

import com.softeer.BE.global.session.UserSessionValue;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class LoginUserResolver implements HandlerMethodArgumentResolver {
  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.hasParameterAnnotation(LoginUser.class)
            && parameter.getParameterType().equals(UserSessionValue.class);
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
    HttpSession session = (request==null) ? null : request.getSession(false);
    return findUserSessionValue(session);
  }

  private UserSessionValue findUserSessionValue(HttpSession session){
    if(session==null)
      return null;
    UserSessionValue result;
    try {
      result = (UserSessionValue) session.getAttribute("user");
    }catch (Exception e){
      result = null;
    }
    return result;
  }
}
