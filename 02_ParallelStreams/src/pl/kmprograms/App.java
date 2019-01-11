package pl.kmprograms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {

        // WYSCIG
        /*
        ExecutorService executorService = Executors.newCachedThreadPool();
        int licznik[] = {0};
        for (int i = 0; i < 10; i++) {
            Runnable runnable = () -> {
                for (int j = 0; j < 1000; j++) {
                    licznik[0]++;
                }
                System.out.println(licznik[0]);
            };
            executorService.execute(runnable);
        }
        executorService.shutdown();
        */

        // SPOSOBY TWORZENIA STRUMIENIA ROWNOLEGLEGO

        List<Player> players = Arrays.asList(
                new Player("ADAM", 4),
                new Player("JAN", 3),
                new Player("EWA", 1),
                new Player("PAWEL", 4),
                new Player("IZA", 5),
                new Player("OLA", 2),
                new Player("KAMIL", 2),
                new Player("JACEK", 3)
        );


        Stream<Player> sPlayers = players.parallelStream();

        /*
        Stream<Player> sPlayers = Stream.of(
                new Player("OLA", 2),
                new Player("KAMIL", 2),
                new Player("JACEK", 3)
        ).parallel();
        */

        // ---------------------------------------------------------
        // Zadanie:
        // Zliczyc ile razy zawodnik odniosl dana ilosc zwyciestw
        // ---------------------------------------------------------

        // SPOSOB 1
        /*
        int maxWins = players.stream()
                .map(Player::getWins)
                .max(Integer::compareTo)
                .orElse(0);

        int[] wins = new int[maxWins + 1];
        players.stream()
                .parallel()
                .forEach(p -> wins[p.getWins()]++);

        // MOZLIWE INNE CIAGI ELEMENTOW W TABLICY
        // PRZY KOLEJNYCH URUCHOMIENIACH !!!
        System.out.println(Arrays.toString(wins));
        */

        // SPOSOB 2
        // Nalezy dopilnowac zeby operacja osadzane w strumieniu
        // mogly byc wykonywanie wielowatkowo
        /*
        Map<Integer, Long> groupedWins = players.stream()
                .parallel()
                .collect(Collectors.groupingBy(Player::getWins, Collectors.counting()));
        groupedWins.forEach((k, v) -> System.out.println(k + " " + v));
        */


        Stream<String> names = Arrays.asList("JAN", "DAREK", "ASIA")
                .parallelStream().unordered().limit(2);
        names.forEach(System.out::println);

        Map<Integer, List<String>> res = Arrays.asList("JAN", "DAREK", "ASIA")
                .parallelStream()
                .collect(Collectors.groupingByConcurrent(String::length));
        res.forEach((k, v) -> System.out.println(k + " " + v));

    }
}
