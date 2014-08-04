package uk.kludje.fluent;

import uk.kludje.fn.nary.TriConsumer;
import uk.kludje.fn.nary.TriFunction;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Makes any type fluent.
 *
 * @param <T> the type of the underlying value
 */
public final class Fluent<T> {
  private T t;

  private Fluent(T t) {
    this.t = t;
  }

  /**
   * Creates a new fluent instance.
   *
   * @param t   the non-null type to adapt
   * @param <T> the adapted type
   * @return a new instance
   */
  public static <T> Fluent<T> fluent(T t) {
    Objects.requireNonNull(t);
    return new Fluent<>(t);
  }

  /**
   * Passes the underlying value to a consumer.
   * Usage:
   * <pre>
   *   AtomicInteger two = fluent(new AtomicInteger())
   *   .f(AtomicInteger::incrementAndGet)
   *   .f(AtomicInteger::incrementAndGet)
   *   .get();
   * </pre>
   *
   * @param consumer typically a method reference for T
   * @return this
   */
  public Fluent<T> f(Consumer<? super T> consumer) {
    consumer.accept(t);
    return this;
  }

  /**
   * Passes the underlying value to a consumer, with an argument.
   * Usage:
   * <pre>
   * List&lt;String&gt; list = fluent(new ArrayList&lt;String&gt;())
   *                           .f(List::add, "a")
   *                           .f(List::add, "b")
   *                           .f(List::add, "c")
   *                           .f(List::remove, "b")
   *                           .map(Collections::unmodifiableList)
   *                           .get();
   * </pre>
   *
   * @param consumer typically a method reference for T
   * @param a an argument
   * @param <A> argument type
   * @return this
   */
  public <A> Fluent<T> f(BiConsumer<? super T, A> consumer, A a) {
    consumer.accept(t, a);
    return this;
  }

  /**
   * Passes the underlying value to a consumer, with two arguments.
   * Usage:
   * <pre>
   *   Map&lt;String, String&gt; map = fluent(new HashMap&lt;String, String&gt;())
   *                                  .f(Map::put, "a", "A")
   *                                  .f(Map::put, "b", "B")
   *                                  .f(Map::put, "c", "C")
   *                                  .map(Collections::unmodifiableMap)
   *                                  .get();
   * </pre>
   *
   * @param consumer typically a method reference for T
   * @param a first argument
   * @param b second argument
   * @param <A> type of a
   * @param <B> type of b
   * @return this
   */
  public <A, B> Fluent<T> f(TriConsumer<? super T, A, B> consumer, A a, B b) {
    consumer.accept(t, a, b);
    return this;
  }

  public Nullary<T> nullary(Consumer<? super T> consumer) {
    Objects.requireNonNull(consumer);
    return new Nullary(this, consumer);
  }

  public <A> Unary<T, A> unary(BiConsumer<? super T, A> consumer) {
    Objects.requireNonNull(consumer);
    return new Unary(this, consumer);
  }

  public <A,B> Binary<T, A, B> binary(TriConsumer<? super T, A, B> consumer) {
    Objects.requireNonNull(consumer);
    return new Binary(this, consumer);
  }

  /**
   * Unwraps the value.
   *
   * @return the underlying value
   */
  public T get() {
    return t;
  }

  public <M> Fluent<M> map(Function<T, M> mapper) {
    return new Fluent<M>(mapper.apply(t));
  }

  public <M, A> Fluent<M> map(BiFunction<T, A, M> mapper, A a) {
    return new Fluent<M>(mapper.apply(t, a));
  }

  public <M, A, B> Fluent<M> map(TriFunction<T, A, B, M> mapper, A a, B b) {
    return new Fluent<M>(mapper.apply(t, a, b));
  }

  /**
   * A string of undefined form for debugging purposes.
   *
   * @return a string form.
   */
  @Override
  public String toString() {
    return "Fluent{" + t + "}";
  }

  public static abstract class FluentMethod<T> {
    private final Fluent<T> parent;

    private FluentMethod(Fluent<T> parent) {
      this.parent = parent;
    }

    public Fluent<T> unbind() {
      return parent;
    }

    public T get() {
      return parent.t;
    }
  }

  public static class Nullary<T> extends FluentMethod<T> {
    private final Consumer<? super T> consumer;

    private Nullary(Fluent<T> parent, Consumer<? super T> consumer) {
      super(parent);
      this.consumer = consumer;
    }

    public Nullary<T> invoke() {
      consumer.accept(get());
      return this;
    }
  }

  public static class Unary<T, A> extends FluentMethod<T> {
    private final BiConsumer<? super T, A> consumer;

    private Unary(Fluent<T> parent, BiConsumer<? super T, A> consumer) {
      super(parent);
      this.consumer = consumer;
    }

    public Unary<T, A> invoke(A a) {
      consumer.accept(get(), a);
      return this;
    }
  }

  public static class Binary<T, A, B> extends FluentMethod<T> {
    private final TriConsumer<? super T, A, B> consumer;

    private Binary(Fluent<T> parent, TriConsumer<? super T, A, B> consumer) {
      super(parent);
      this.consumer = consumer;
    }

    public Binary<T, A, B> invoke(A a, B b) {
      consumer.accept(get(), a, b);
      return this;
    }
  }
}
