package com.kevin.jmh.sample;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author wangyong
 */
public class GcProfilerSample {


}


class AlexClassLoader extends URLClassLoader {


  private final byte[] byteArray;


  public AlexClassLoader(byte[] byteArray) {
    super(new URL[0], ClassLoader.getSystemClassLoader());
    this.byteArray = byteArray;
  }

  @Override
  protected Class<?> findClass(String name) throws ClassNotFoundException {
    return defineClass(name, byteArray, 0, byteArray.length);
  }
}
