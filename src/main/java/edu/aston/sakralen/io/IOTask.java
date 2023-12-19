package edu.aston.sakralen.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class IOTask {
    private static final String TASK_DIR = Path.of("src", "main", "resources", "io-task").toString();

    public static void main(String[] args) throws IOException {
        List<String> textLines = Files.readAllLines(Path.of(TASK_DIR, "text-sample.txt"));
        List<String> textTokens = textLines.stream()
                .flatMap(line -> Stream.of(line.split("\\s+")))
                .map(String::toLowerCase)
                .map(token -> token.replaceAll("\\p{Punct}", ""))
                .filter(token -> !token.isEmpty())
                .toList();
        List<String> javaLines = Files.readAllLines(Path.of(TASK_DIR, "Public.java"));

        // 1. Задан файл с текстом, найти и вывести в консоль все слова,
        // начинающиеся с гласной буквы.
        List<String> vowels = List.of("а", "е", "и", "о", "у", "э", "ю", "я");

        List<String> startingWithVowel = textTokens.stream()
                .filter(token -> vowels.stream().anyMatch(token::startsWith))
                .toList();
        System.out.println(startingWithVowel);

        System.out.println();

        // 2. Задан файл с текстом, найти и вывести в консоль все слова,
        // для которых последняя буква одного слова совпадает с первой буквой следующего слова.
        List<List<String>> matchingPairs = new ArrayList<>();

        for (int i = 0; i < textTokens.size() - 1; i++) {
            String current = textTokens.get(i);
            String next = textTokens.get(i + 1);

            if (current.endsWith(String.valueOf(next.charAt(0)))) {
                matchingPairs.add(List.of(current, next));
            }
        }

        System.out.println(matchingPairs);

        System.out.println();

        // 3. Задан файл с текстом. В каждой строке найти и вывести наибольшее число цифр, идущих подряд.
        List<String> digitLines = Files.readAllLines(Path.of(TASK_DIR, "text-with-digits.txt"));

        List<String> digits = digitLines.stream()
                .map(line -> Pattern.compile("\\d+").matcher(line).results()
                        .map(MatchResult::group)
                        .max(Comparator.comparing(String::length))
                        .orElse("none"))
                .toList();

        System.out.println(digits);

        System.out.println();

        // 4. Задан файл с java-кодом. Прочитать текст программы из файла
        // и все слова public в объявлении атрибутов и методов класса заменить на слово private.
        // Результат сохранить в другой заранее созданный файл.

        // regex соответствует слову public не в кавычках и не перед class
        List<String> withPrivate = javaLines.stream()
                .map(line -> line.replaceAll("(?<!\")\\bpublic\\b(?!\")(?!\\s*class)", "private"))
                .toList();

        // WRITE + TRUNCATE_EXISTING заменяют опции по умолчанию, разрешая писать в только в файл, созданный заранее.
        Files.write(Path.of(TASK_DIR, "Private.java"), withPrivate,
                StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);

        // 5. Задан файл с java-кодом. Прочитать текст программы из файла
        // и записать в другой файл в обратном порядке символы каждой строки.
        List<String> reversedLines = javaLines.stream()
                .map(line -> new StringBuilder(line).reverse().toString())
                .toList();

        Files.write(Path.of(TASK_DIR, "Reversed.java"), reversedLines);
    }
}
