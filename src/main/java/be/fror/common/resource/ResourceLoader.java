/*
 * Copyright 2015 Olivier Grégoire.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package be.fror.common.resource;

import be.fror.common.io.ByteSource;

import java.io.IOException;
import java.io.UncheckedIOException;

import javax.annotation.Nonnull;

/**
 * Loads resource from {@link ByteSource}.
 *
 * @author Olivier Grégoire
 * @param <T> The type of the resource being loaded
 */
public interface ResourceLoader<T> {

  /**
   * Loads a resource from {@code source} but throws an {@code UncheckedIOException} instead of an
   * {@code IOException}.
   * 
   * <p>
   * This method may not return {@code null}.
   *
   * <p>
   * This method is the same as calling:
   *
   * <pre>{@code try {
   *   return load(source);
   * } catch (IOException ex) {
   *   throw new UncheckedIOException(ex);
   * }}</pre>
   *
   * @param <T> the type of the resource being loaded
   * @param source the source to load the resource from
   * @return the resource
   * @throws UncheckedIOException if any issue happens; wraps an {@code IOException}.
   */
  @Nonnull
  public default <T> T uncheckedLoad(ByteSource source) throws UncheckedIOException {
    try {
      return load(source);
    } catch (IOException ex) {
      throw new UncheckedIOException(ex);
    }
  }

  /**
   * Loads a resource from {@code source}.
   *
   * <p>
   * This method may not return {@code null}.
   *
   * @param <T> the type of the resource being loaded
   * @param source the source to load the resource from
   * @return the resource
   * @throws IOException if any issue happens
   */
  @Nonnull
  public <T> T load(ByteSource source) throws IOException;

}
