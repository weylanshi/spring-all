package com.weylan.mybaits.rw.service.impl;

import com.weylan.mybaits.rw.conf.Master;
import com.weylan.mybaits.rw.entity.User;
import com.weylan.mybaits.rw.mapper.UserMapper;
import com.weylan.mybaits.rw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

  @Autowired(required = false)
  private UserMapper userMapper;

  @Transactional
  public int insert(User user){
    userMapper.insert(user);
    return 1;
  }

  public User selectById(Long id){
   return userMapper.selectUerById(id);
  }

  @Master
  public User selectByIdFromMaster(Long id){
   return userMapper.selectUerById(id);
  }



}
