package com.eomcs.io.ex01;

import java.io.File;
import java.io.FileFilter;

public class Exam0621x {
  public static void main(String[] args) throws Exception {
    class JavaFilter implements FileFilter {
      @Override
      public boolean accept(File file) {
        if (file.isFile() && file.getName().endsWith(".java"))
          return true;
        return false;
      }
    }

    File dir = new File(".");
    JavaFilter javaFilter = new JavaFilter();
    File[] files = dir.listFiles(javaFilter);
    for (File file : files) {
      System.out.printf("%s %12d %s\n",
          file.isDirectory() ? "d" : "-",
              file.length(),
              file.getName());
    }
  }
}


