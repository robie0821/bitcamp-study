package bitcamp.myapp.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyServletRequestListener implements ServletRequestListener {

  public MyServletRequestListener() {
    
  }

  @Override
  public void requestDestroyed(ServletRequestEvent sre) {
//    SqlSessionFactoryProxy sqlSessionFactoryProxy = (SqlSessionFactoryProxy) sre.getServletContext().getAttribute("sqlSessionFactory");
//    sqlSessionFactoryProxy.clean();
  }
}