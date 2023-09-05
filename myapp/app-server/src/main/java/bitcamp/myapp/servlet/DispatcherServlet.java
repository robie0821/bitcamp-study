package bitcamp.myapp.servlet;

import bitcamp.myapp.config.AppConfig;
import bitcamp.myapp.config.NcpConfig;
import bitcamp.myapp.controller.RequestMapping;
import bitcamp.myapp.controller.RequestParam;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@WebServlet(
        value = "/app/*",
        loadOnStartup = 1)
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class DispatcherServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  AnnotationConfigApplicationContext iocContainer;

  Map<String, RequestHandlerMapping> handlerMap = new HashMap<>();

  @Override
  public void init() throws ServletException {
    iocContainer = new AnnotationConfigApplicationContext(AppConfig.class, NcpConfig.class);

    String[] names = iocContainer.getBeanDefinitionNames();
    for (String name : names) {
      registerRequestHandler(iocContainer.getBean(name));
    }
  }

  private void registerRequestHandler(Object bean) {
    Method[] methods = bean.getClass().getDeclaredMethods();
    for (Method method : methods) {
      RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
      if (requestMapping == null) {
        continue;
      }
      handlerMap.put(requestMapping.value(), new RequestHandlerMapping(bean, method));
    }
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String pageControllerPath = request.getPathInfo();
    if (request.getContentType() != null && request.getContentType().equalsIgnoreCase("multipart/form-data")) {
      request.getParts();
    }

    response.setContentType("text/html;charset=UTF-8");

    RequestHandlerMapping requestHandlerMapping = handlerMap.get(pageControllerPath);
    if (requestHandlerMapping == null) {
      throw new ServletException("요청을 처리할 Handler가 없습니다.");
    }

    Map<String, Object> model = new HashMap<>();
    try {
      Object[] arguments = prepareArguments(requestHandlerMapping.handler, request, response, model);

      String viewUrl = (String) requestHandlerMapping.handler.invoke(requestHandlerMapping.controller, arguments);

      Set<Map.Entry<String, Object>> entrySet = model.entrySet();
      for (Map.Entry<String, Object> entry : entrySet) {
        request.setAttribute(entry.getKey(), entry.getValue());
      }

      if (viewUrl.startsWith("redirect:")) {
        response.sendRedirect(viewUrl.substring(9));
      } else {
        request.getRequestDispatcher(viewUrl).include(request, response);
      }

    } catch (Exception e) {
      Set<Map.Entry<String, Object>> entrySet = model.entrySet();
      for (Map.Entry<String, Object> entry : entrySet) {
        request.setAttribute(entry.getKey(), entry.getValue());
      }
      throw new ServletException("요청 처리 중 오류 발생", e);
    }
  }

  private Object[] prepareArguments(
          Method handler,
          HttpServletRequest request,
          HttpServletResponse response,
          Map<String, Object> model) throws Exception {
    Parameter[] params = handler.getParameters();
    ArrayList<Object> arguments = new ArrayList<>();

    for (Parameter param : params) {
      if (param.getType() == HttpServletRequest.class || param.getType() == ServletRequest.class) {
        arguments.add(request);
      } else if (param.getType() == HttpServletResponse.class || param.getType() == ServletResponse.class) {
        arguments.add(response);
      } else if (param.getType() == HttpSession.class) {
        arguments.add(request.getSession());
      } else if (param.getType() == String.class) {
        arguments.add(request.getParameter(param.getAnnotation(RequestParam.class).value()));
      } else if (param.getType() == int.class) {
        arguments.add(Integer.parseInt(request.getParameter(param.getAnnotation(RequestParam.class).value())));
      } else if (param.getType() == char.class) {
        arguments.add((request.getParameter(param.getAnnotation(RequestParam.class).value())).charAt(0));
      } else if (param.getType() == Map.class) {
        arguments.add(model);
      } else if (param.getType() == Part.class) {
        arguments.add(request.getPart(param.getAnnotation(RequestParam.class).value()));
      } else if (param.getType() == Part[].class) {
        String paramName = param.getAnnotation(RequestParam.class).value();
        ArrayList<Part> parts = new ArrayList<>();
        for (Part part : request.getParts()) {
          if (part.getName().equals(paramName)) {
            parts.add(part);
          }
        }
        arguments.add(parts.toArray(new Part[]{}));
      } else {
        arguments.add(getValueObject(param.getType(), request));
      }
    }

    return arguments.toArray();
  }

  private Object getValueObject(Class<?> clazz, HttpServletRequest request) throws Exception {
    Constructor<?> constructor = clazz.getConstructor();
    Object obj = constructor.newInstance();

    Method[] methods = clazz.getMethods();
    for (Method method : methods) {
      if (!method.getName().startsWith("set")) {
        continue;
      }

      String propName = method.getName().substring(3, 4).toLowerCase() +
              method.getName().substring(4);

      String paramValue = request.getParameter(propName);
      if (paramValue == null) {
        continue;
      }

      method.invoke(obj, strToPrimitiveType(paramValue, method.getParameters()[0].getType()));
    }

    return obj;
  }

  private Object strToPrimitiveType(String value, Class<?> type) {
    if (type == String.class) {
      return value;
    } else if (type == int.class) {
      return Integer.parseInt(value);
    } else if (type == char.class) {
      return value.charAt(0);
    } else if (type == boolean.class) {
      return Boolean.valueOf(value);
    } else {
      return null;
    }
  }

  static class RequestHandlerMapping {
    Object controller;
    Method handler;

    public RequestHandlerMapping() {
    }

    public RequestHandlerMapping(Object controller, Method handler) {
      this.controller = controller;
      this.handler = handler;
    }
  }
}