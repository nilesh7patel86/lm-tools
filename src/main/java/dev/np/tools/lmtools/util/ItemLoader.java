package dev.np.tools.lmtools.util;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public interface ItemLoader<T> {
    String SPLIT_REGEX = "\\|";

    List<T> load() throws IOException;

    default List<String> readLinesFrom(String path) throws IOException {
        return Files.readAllLines(Paths.get(new ClassPathResource(path).getFile().getPath()));
    }

    default String parseValue(String value) {
        if (value.isEmpty())
            return "0";
        return value
                .replace("%", "")
                .replace(",", "");
    }
}
