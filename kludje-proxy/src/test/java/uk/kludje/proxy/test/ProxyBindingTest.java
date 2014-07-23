package uk.kludje.proxy.test;

import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.concurrent.atomic.AtomicReference;

import static uk.kludje.proxy.ProxyBinding.binder;
import static uk.kludje.proxy.ProxyBinding.proxy;

@Ignore
public class ProxyBindingTest {

  private static final String TEST = "Hello, World!";

  @Test
  public void testProxyBinding() {
    AtomicReference<String> result = new AtomicReference<>();
    Runnable action = () -> {
      result.set(TEST);
    };

    Runnable r = binder(Runnable.class, Runnable.class).bind(proxy(Runnable.class)::run, action::run);
    r.run();
    Assert.assertEquals(TEST, result.get());
  }

  @Test(expected = UncheckedIOException.class)
  public void testExceptions() {
    Runnable action = () -> {
      throw new UncheckedIOException(new IOException());
    };

    Runnable r = binder(Runnable.class, Runnable.class).bind(proxy(Runnable.class)::run, action::run);
    r.run();
  }
}