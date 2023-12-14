package edu.aston.sakralen.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        System.out.println();

        // 1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей).
        System.out.println("1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей):");
        transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .forEach(System.out::println);

        System.out.println();

        // 2. Вывести список неповторяющихся городов, в которых работают трейдеры.
        System.out.println("2. Вывести список неповторяющихся городов, в которых работают трейдеры:");
        transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);

        System.out.println();

        // 3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.
        System.out.println("3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.");
        transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .filter(t -> t.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);

        System.out.println();

        // 4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке.
        System.out.println("4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке:");
        String names = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining(", "));
        System.out.println(names);

        System.out.println();

        // 5. Выяснить, существует ли хоть один трейдер из Милана.
        System.out.println("5. Выяснить, существует ли хоть один трейдер из Милана:");
        boolean isFromMilan = transactions.stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));

        System.out.println(isFromMilan);

        System.out.println();

        // 6. Вывести суммы всех транзакций трейдеров из Кембриджа.
        System.out.println("6. Вывести суммы всех транзакций трейдеров из Кембриджа:");
        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        System.out.println();

//        // Или имеется в виду сумма сумм для каждого трейдера?
//        var totalPerTrader = transactions.stream()
//                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
//                .collect(Collectors.groupingBy(t -> t.getTrader().getName(),
//                        Collectors.summingInt(Transaction::getValue)));
//
//        System.out.println(totalPerTrader);
//
//        System.out.println();

        // 7. Какова максимальная сумма среди всех транзакций?
        System.out.println("7. Какова максимальная сумма среди всех транзакций?");
        transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compareTo)
                .ifPresent(System.out::println);

        System.out.println();

        // 8. Найти транзакцию с минимальной суммой.
        System.out.println("8. Найти транзакцию с минимальной суммой:");
        transactions.stream()
                .min(Comparator.comparing(Transaction::getValue))
                .ifPresent(System.out::println);
    }
}
