package com.weylan.mybatis.rw;

import com.weylan.mybaits.rw.MybatisApplication;
import com.weylan.mybaits.rw.entity.User;
import com.weylan.mybaits.rw.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisApplication.class)
public class ApplicationTest {


  @Autowired
  private UserService userService;

  @Test
  public void testInsert(){
    User user = new User(1L,"小明");

    userService.insert(user);
  }

  @Test
  public void testQuery(){
    User user = userService.selectById(1L);
    System.out.println(user);
  }

  @Test
  public void testQueryFromMaster(){
    User user = userService.selectByIdFromMaster(1L);
    System.out.println(user);
  }
}
