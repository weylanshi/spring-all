package com.weylan.mybaits.rw.controller;

import com.weylan.mybaits.rw.entity.User;
import com.weylan.mybaits.rw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @Autowired
  private UserService userService;

  @GetMapping("/insert")
  public void testInsert() {
    User user = new User("小明");

    userService.insert(user);
  }

  @GetMapping("/query")
  public User testQuery() {
    return userService.selectById(1L);
  }

  @GetMapping("/query/master")
  public void testQueryFromMaster() {
    User user = userService.selectByIdFromMaster(1L);
    System.out.println(user);
  }

}
