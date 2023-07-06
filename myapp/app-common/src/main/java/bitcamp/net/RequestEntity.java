package bitcamp.net;

import com.google.gson.Gson;

public class RequestEntity {
  String command;
  String data;

  public <T> T getObject(Class<T> clazz) {
    return new Gson().fromJson(data,clazz);
  }

  public String toJson() {
    return new Gson().toJson(this);
  }

  public static RequestEntity fromJson(String json) {
    return new Gson().fromJson(json, RequestEntity.class);
  }

  public RequestEntity command(String command) {
    this.command = command;
    return this;
  }

  public RequestEntity data(Object obj) {
    if (obj.getClass() == String.class) {
      this.data = (String) obj;
    } else {
      this.data = new Gson().toJson(obj);
    }
    return this;
  }

  public String getCommand() {
    return command;
  }

  public String getData() {
    return data;
  }

}
