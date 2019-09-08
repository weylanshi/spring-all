package com.weylan.mybaits.rw.service;

import com.weylan.mybaits.rw.entity.User;

public interface UserService {


   int insert(User user);

  User selectById(Long id);

  User selectByIdFromMaster(Long id);

}
