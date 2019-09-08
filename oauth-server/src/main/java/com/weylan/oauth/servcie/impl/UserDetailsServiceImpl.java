package com.weylan.oauth.servcie.impl;

import java.util.HashSet;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    if(!userName.equals("admin")){
      throw new UsernameNotFoundException("用户:" + userName + ",不存在!");
    }
    return new User("admin","123",true,true,true,true,new HashSet<>());
  }
}
