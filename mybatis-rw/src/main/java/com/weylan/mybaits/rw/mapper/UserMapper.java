package com.weylan.mybaits.rw.mapper;

import com.weylan.mybaits.rw.entity.User;

public interface UserMapper {


  User selectUerById(Long id);

  int insert(User user);


}
