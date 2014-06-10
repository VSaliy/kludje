package co.uk.kludje.test.sample;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;

import static co.uk.kludje.fn.function.UFunction.asUFunction;
import static java.util.stream.Collectors.toMap;

/**
 * Line counter - stream approach with checked exception handling.
 */
public class LineCounter4 implements LineCounter {

  @Override
  public Map<Path, Long> countLines(Collection<? extends Path> paths) throws IOException {
    return paths.stream()
        .collect(toMap(p -> p, asUFunction(this::linesIn)));
  }

  private long linesIn(Path path) throws IOException {
    try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
      return lines.count();
    }
  }
}
