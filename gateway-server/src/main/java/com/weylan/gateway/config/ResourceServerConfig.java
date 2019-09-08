//package com.weylan.gateway.config;
//
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableOAuth2Sso
//public class ResourceServerConfig extends WebSecurityConfigurerAdapter {
//
////  private static final String[] AUTH_WHITELIST = {
////      "/**/v2/api-docs",
////      "/swagger-resources",
////      "/swagger-resources/**",
////      "/configuration/ui",
////      "/configuration/security",
////      "/swagger-ui.html",
////      "swagger-resources/configuration/ui",
////      "/doc.html",
////      "/webjars/**"
////  };
////  @Autowired
////  private OAuth2WebSecurityExpressionHandler expressionHandler;
//
//
////  @Override
////  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//////    resources.expressionHandler(expressionHandler);
////    super.configure(resources);
////  }
//
//
//
//
//  @Override
//  public void configure(HttpSecurity http) throws Exception {
//    // 请求 鉴权中心,swagger 放行
////    http.authorizeRequests().antMatchers("/v2/api-docs","/auth/**").permitAll();
//
////    ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
////    for (String au:AUTH_WHITELIST) {
////      http.authorizeRequests().antMatchers(au).permitAll();
////    }
////    http.authorizeRequests().anyRequest().authenticated();
////    registry.anyRequest().access("@permissionService.hasPermission(request,authentication)");
//    http.csrf().disable();
//  }
//
////  @Bean
////  public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
////    OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
////    expressionHandler.setApplicationContext(applicationContext);
////    return expressionHandler;
////  }
//
//}
