package com.example.openapi.mustache;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public final class LoggingTemplateRenderer {

    private LoggingTemplateRenderer() {
    }

    public static void main(String[] args) throws IOException {
        if (Boolean.parseBoolean(System.getProperty("mustache.renderer.skip", "false"))) {
            return;
        }

        if (args.length != 3) {
            throw new IllegalArgumentException("Esperado: <template> <dados.json> <arquivo-alvo>");
        }

        Path templatePath = Path.of(args[0]);
        Path dataPath = Path.of(args[1]);
        Path outputPath = Path.of(args[2]);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data = mapper.readValue(dataPath.toFile(), new TypeReference<Map<String, Object>>() {
        });

        MustacheFactory factory = new DefaultMustacheFactory(templatePath.getParent().toFile());
        try (Reader templateReader = Files.newBufferedReader(templatePath, StandardCharsets.UTF_8);
             Writer writer = prepareWriter(outputPath)) {
            Mustache mustache = factory.compile(templateReader, templatePath.getFileName().toString());
            mustache.execute(writer, data).flush();
        }
    }

    private static Writer prepareWriter(Path outputPath) throws IOException {
        Files.createDirectories(outputPath.getParent());
        return Files.newBufferedWriter(outputPath, StandardCharsets.UTF_8);
    }
}
