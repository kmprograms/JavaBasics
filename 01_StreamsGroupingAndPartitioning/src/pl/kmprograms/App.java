package pl.kmprograms;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {

        List<Product> products = Arrays.asList(
                new Product("APPLE", new BigDecimal("10"), Category.FRUITS),
                new Product("CARROT", new BigDecimal("30"), Category.VEGETABLES),
                new Product("BANANA", new BigDecimal("20"), Category.FRUITS),
                new Product("CUCUMBER", new BigDecimal("40"), Category.VEGETABLES),
                new Product("ORANGE", new BigDecimal("45"), Category.FRUITS),
                new Product("POTATO", new BigDecimal("25"), Category.VEGETABLES),
                new Product("PEACH", new BigDecimal("15"), Category.FRUITS)
        );

        // 1. Wypisać średnią cenę owoców
        System.out.println("----------------------- 1 ----------------------------");

        long howManyFruits = products.stream().filter(p -> p.getCategory().equals(Category.FRUITS)).count();
        BigDecimal totalPrice = products
                .stream()
                .filter(p -> p.getCategory() == Category.FRUITS)
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal averagePrice = totalPrice.divide(new BigDecimal(howManyFruits), 2, RoundingMode.DOWN);
        System.out.println("AVERAGE PRICE = " + averagePrice);

        // 2. Wypisać warzywa, w których nazwie występują więcej niż 2 samogłoski
        System.out.println("----------------------- 2 ----------------------------");
        products
                .stream()
                .filter(p -> p.getCategory() == Category.VEGETABLES && p.getName().replaceAll("[^AEYUIOaeyuio]", "").length() > 2)
                .forEach(System.out::println);

        // 3. Pogrupować warzywa i owoce według litery, od której zaczyna się ich nazwa.
        //    Wypisac dla każdej litery nazwy przyporządkowanych elementów oddzielone znakiem przecinka

        //    WERSJA 1
        //    A -> Product("APPLE", 10, FRUIT)
        //    C -> Product("CARROT", 30, VEGETABLE), Product("CUCUMBER", 40, VEGETABLE)
        //    B -> Product("BANANA", 20, FRUIT)
        //    P -> Product("POTATO", 25, VEGETABLE), Product("PEACH", 15, FRUIT)
        //    O -> Product("ORANGE", 55, FRUIT)

        //    WERSJA KONCOWA
        //    A -> APPLE
        //    C -> CARROT,CUCUMBER
        //    B -> BANANA
        //    P -> POTATO, PEACH
        //    O -> ORANGE
        System.out.println("----------------------- 3 ----------------------------");
        products
                .stream()
                .collect(Collectors.groupingBy(p -> p.getName().charAt(0))) // WERSJA 1
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().stream().map(Product::getName).collect(Collectors.joining(","))))
                .forEach((k, v) -> System.out.println(k + " -> " + v));

        // ZADANIE DODATKOWE
        // Wyznacz literę, dla której przyporządkowano produkty o największej sumie cen

        // 4. Zliczyć, ile warzy oraz owoców znajduje się w kolekcji.
        //    Zastosować grupowanie oraz partycjonowanie.

        Map<Category, Long> groupedProducts = products
                .stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
        System.out.println("GROUPING");
        groupedProducts.forEach((k, v) -> System.out.println(k + " : " + v));

        Map<Boolean, Long> groupedProduct2 = products
                .stream()
                .collect(Collectors.partitioningBy(p -> p.getCategory().equals(Category.VEGETABLES), Collectors.counting()));
        System.out.println("PARTITIONING");
        groupedProduct2.forEach((k, v) -> System.out.println(k + " : " + v));

    }

}
