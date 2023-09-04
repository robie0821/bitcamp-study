package project.app.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import project.app.handler.InitServlet;
import project.util.SqlSessionFactoryProxy;

@WebListener
public class MyServletRequestListener implements ServletRequestListener {

  @Override
  public void requestDestroyed(ServletRequestEvent sre) {
    ((SqlSessionFactoryProxy) InitServlet.sqlSessionFactory).clean();
  }
}