/*
 * Copyright 2015 McDowell
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.kludje.array;

import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Created by user on 13/12/15.
 */
public interface FixedSet<E> extends FixedCollection<E>, Set<E> {

  @Override
  @Deprecated
  default boolean add(E e) {
    throw new UnsupportedOperationException();
  }

  @Override
  @Deprecated
  default boolean remove(Object o) {
    throw new UnsupportedOperationException();
  }

  @Override
  @Deprecated
  default boolean addAll(Collection<? extends E> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  @Deprecated
  default boolean removeAll(Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  @Deprecated
  default boolean removeIf(Predicate<? super E> filter) {
    throw new UnsupportedOperationException();
  }

  @Override
  @Deprecated
  default boolean retainAll(Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  @Deprecated
  default void clear() {
    throw new UnsupportedOperationException();
  }
}
