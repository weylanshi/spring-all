package com.weylan.mybaits.rw.conf;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAop {

  @Pointcut("!@annotation(com.weylan.mybaits.rw.conf.Master) " +
      "&& (execution(* com.weylan.mybaits.rw.service..*.select*(..)) " +
      "|| execution(* com.weylan.mybaits.rw.service..*.get*(..)))")
  public void readPointcut() {

  }

  @Pointcut("@annotation(com.weylan.mybaits.rw.conf.Master) " +
      "|| execution(* com.weylan.mybaits.rw.service..*.insert*(..)) " +
      "|| execution(* com.weylan.mybaits.rw.service..*.add*(..)) " +
      "|| execution(* com.weylan.mybaits.rw.service..*.update*(..)) " +
      "|| execution(* com.weylan.mybaits.rw.service..*.edit*(..)) " +
      "|| execution(* com.weylan.mybaits.rw.service..*.delete*(..)) " +
      "|| execution(* com.weylan.mybaits.rw.service..*.remove*(..))")
  public void writePointcut() {

  }

  @Before("readPointcut()")
  public void read() {
    DBContextHolder.slave();
  }

  @Before("writePointcut()")
  public void write() {
    DBContextHolder.master();
  }
}
