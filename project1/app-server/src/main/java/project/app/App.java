package project.app;

import java.io.File;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class App {
  public static void main(String[] args) throws Exception {
    Tomcat tomcat = new Tomcat();

    tomcat.setPort(8888);

    tomcat.setBaseDir("temp");

    Connector connector = tomcat.getConnector();
    connector.setURIEncoding("UTF-8");

    StandardContext ctx = (StandardContext) tomcat.addWebapp(
            "/", // 컨텍스트 경로(웹 애플리케이션 경로)
            new File("src/main/webapp").getAbsolutePath() // 웹 애플리케이션 파일이 있는 실제 경로
    );
    ctx.setReloadable(true);

    WebResourceRoot resources = new StandardRoot(ctx);

    resources.addPreResources(new DirResourceSet(
            resources,
            "/WEB-INF/classes",
            new File("build/classes/java/main").getAbsolutePath(),
            "/"
    ));

    // 웹 애플리케이션을 환경 정보에 등록
    ctx.setResources(resources);


    // 톰캣 서버 구동
    tomcat.start();

    // 톰캣 서버를 구동한 후 종료될 때까지 JVM을 끝내지 말고 기다린다.
    tomcat.getServer().await();
  }
}
