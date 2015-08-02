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

package uk.kludje.experimental.collect;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Convenience methods for implementing the {@link SparseArray} contract.
 */
public final class SparseArrayContracts {
  private SparseArrayContracts() {
  }

  /**
   * Use to implement {@link SparseArray#equals(Object)}.
   *
   * @param sparseArray the array
   * @param other the object to compare it with - may be null
   * @return true if equal
   */
  public static boolean areEqual(SparseArray<?> sparseArray, Object other) {
    assert sparseArray != null;

    if (sparseArray == other) {
      return true;
    }

    if (other == null) {
      return false;
    }

    if (other instanceof SparseArray<?>) {
      SparseArray<?> sa = (SparseArray<?>) other;

      int size = sparseArray.size();

      if (size != sa.size()) {
        return false;
      }

      for (int i = 0; i < size; i++) {
        int key = sparseArray.keyAt(i);
        Object t0 = sparseArray.get(key);
        Object t1 = sa.get(key);
        if (!Objects.equals(t0, t1)) {
          return false;
        }
      }

      return true;
    }

    return false;
  }

  /**
   * Use to implement {@link SparseArray#hashCode()}.
   *
   * @param sparseArray the array
   * @return the hash
   */
  public static int hashCodeOf(SparseArray<?> sparseArray) {
    int hash = 0;

    for (int i = 0, size = sparseArray.size(); i < size; i++) {
      int key = sparseArray.keyAt(i);
      Object o = sparseArray.get(key);
      hash = hash * 31 + Objects.hashCode(o);
    }

    return hash;
  }

  /**
   * Use to implement {@link SparseArray#toString()}.
   *
   * @param sparseArray the array
   * @return a string form for debugging
   */
  public static String toString(SparseArray<?> sparseArray) {
    StringJoiner joiner = new StringJoiner(", ", "[", "]");

    for (int i = 0, size = sparseArray.size(); i < size; i++) {
      int key = sparseArray.keyAt(i);
      Object o = sparseArray.get(key);
      joiner.add(Objects.toString(o));
    }

    return joiner.toString();
  }
}