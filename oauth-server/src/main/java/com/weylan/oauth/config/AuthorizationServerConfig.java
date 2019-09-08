package com.weylan.oauth.config;

import com.weylan.oauth.config.WebSecurityConfig.NoEncryptPasswordEncoder;
import com.weylan.oauth.error.CustomWebResponseExceptionTranslator;
import com.weylan.oauth.servcie.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private RedisConnectionFactory redisConnectionFactory;
  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  @Bean
  RedisTokenStore redisTokenStore(){
    return new RedisTokenStore(redisConnectionFactory);
  }
  /**
   * <p>注意，自定义TokenServices的时候，需要设置@Primary，否则报错，</p>
   * @return
   */
  @Primary
  @Bean
  public DefaultTokenServices defaultTokenServices(){

    DefaultTokenServices tokenServices = new DefaultTokenServices();
    tokenServices.setTokenStore(redisTokenStore());
    tokenServices.setSupportRefreshToken(true);
//    tokenServices.setClientDetailsService(clientDetails());
    tokenServices.setAccessTokenValiditySeconds(60*60*12); // token有效期自定义设置，默认12小时
    tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);//默认30天，这里修改

    return tokenServices;
  }
  @Bean
  public ClientDetailsService clientDetails() {
    return new InMemoryClientDetailsService();
//    return new JdbcClientDetailsService(dataSource);
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security
        .allowFormAuthenticationForClients()
        .tokenKeyAccess("permitAll()")
        .checkTokenAccess("isAuthenticated()");
  }
  @Bean
  public WebResponseExceptionTranslator<OAuth2Exception> webResponseExceptionTranslator(){
    return new CustomWebResponseExceptionTranslator();
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    //从数据库中读取client需配置,测试从内容写死
//    clients.withClientDetails()
    clients
//        .withClientDetails(clientDetails())
        .inMemory()
        .withClient("android")
        .scopes("read")
        .secret(new NoEncryptPasswordEncoder().encode("android"))
        .authorizedGrantTypes("password", "authorization_code", "refresh_token")
        .and()
        .withClient("webapp")
        .scopes("read")
        .authorizedGrantTypes("implicit")
        .and()
        .withClient("browser")
        .authorizedGrantTypes("refresh_token", "password");
  }



  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.tokenStore(redisTokenStore())
        .userDetailsService(userDetailsService)
        .authenticationManager(authenticationManager)
        .accessTokenConverter(accessTokenConverter());
//    endpoints.tokenServices(defaultTokenServices());
    // 使用jwt token
//    endpoints.accessTokenConverter(accessTokenConverter());
//    endpoints.exceptionTranslator(webResponseExceptionTranslator());//认证异常翻译
  }

  @Bean
  public JwtAccessTokenConverter accessTokenConverter(){
//    JwtAccessTokenConverter converter = new JwtAccessTokenConverter() {
//      @Override
//      public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//        final Map<String, Object> additionalInformation = new HashMap<>();
//
//        User userModel = (User) authentication.getUserAuthentication().getPrincipal();
//        //把用户的主键uin放进去
//        additionalInformation.put("uin", userModel.getUsername());
//        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
//        return super.enhance(accessToken, authentication);
//      }
//    };
    //非对称加密，但jwt长度过长
//        KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("kevin_key.jks"), "123456".toCharArray())
//                .getKeyPair("kevin_key");
//        converter.setKeyPair(keyPair);
//        return converter;
    //对称加密
    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    converter.setSigningKey("123");
    return converter;
  }
}
