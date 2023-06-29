package com.eomcs.io.ex06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyFileInputStream extends FileInputStream{
  byte[] buf = new byte[8192];
  int length;
  int pos;

  public MyFileInputStream(String name) throws FileNotFoundException {
    super(name);
  };

  @Override
  public int read() throws IOException {
    if (pos == length) {
      length = this.read(buf);
      pos = 0;
    }
    return buf[pos++];
  }
}
