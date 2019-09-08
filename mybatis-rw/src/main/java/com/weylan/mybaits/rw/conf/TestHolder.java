package com.weylan.mybaits.rw.conf;

public class TestHolder {

  private static final ThreadLocal<String> holder = new ThreadLocal<>();

  public static String get() {
    return holder.get();
  }

  public static void set(String str) {
    holder.set(str);
  }
}
