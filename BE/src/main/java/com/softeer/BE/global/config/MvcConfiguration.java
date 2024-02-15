package com.softeer.BE.global.config;

import com.softeer.BE.global.interceptor.UserAuthorizationInterceptor;
import com.softeer.BE.global.resolver.LoginUserResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class MvcConfiguration implements WebMvcConfigurer {
  private final UserAuthorizationInterceptor userAuthorizationInterceptor;
  private final LoginUserResolver loginUserResolver;
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(userAuthorizationInterceptor).addPathPatterns("/schedule/**","/reservation/**",
            "/user/logout/**","/user/programs/**","/user/participations/**","/user/mypage/**");
    WebMvcConfigurer.super.addInterceptors(registry);
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(loginUserResolver);
    WebMvcConfigurer.super.addArgumentResolvers(resolvers);
  }
}
