package bitcamp.util;

public interface ResourcePool<T> {
  public ManagedThread getResource();
  public void returnResource(ManagedThread resource);
}
