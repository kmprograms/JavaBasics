package pl.kmprograms.spliterator_example;

import java.util.*;
import java.util.stream.Collectors;

public class MainSpliteratorExample {
    public static void main(String[] args) {
        List<Rectangle> rectangles = List.of(
                    new Rectangle(10, 19),
                    new Rectangle(10, 21),
                    new Rectangle(10, 20),
                    new Rectangle(10, 20),
                    new Rectangle(1, 1),
                    new Rectangle(10, 21),
                    new Rectangle(13, 23),
                    new Rectangle(13, 23),
                    new Rectangle(5, 25),
                    new Rectangle(225, 225),
                    new Rectangle(5, 25),
                    new Rectangle(5, 26),
                    new Rectangle(5, 27),
                    new Rectangle(28, 29),
                    new Rectangle(2, 9),
                    new Rectangle(42, 49),
                    new Rectangle(28, 29),
                    new Rectangle(28, 29)
                )
                .stream()
                .sorted(Comparator.comparingDouble(Rectangle::area))
                .peek(r -> System.out.println(r.area()))
                .collect(Collectors.toList());

        // zwykle zastosowanie
        // RectangleSpliterator rs1 = new RectangleSpliterator(rectangles);
        // while (rs1.tryAdvance(System.out::println)) {}

        // rozbijanie
        List<Spliterator<Rectangle>> rectangleSpliterators = new ArrayList<>();
        Spliterator<Rectangle> rs = new RectangleSpliterator(rectangles);
        while (rs != null) {
            rectangleSpliterators.add(rs);
            rs = rs.trySplit();
        }

        rectangleSpliterators.forEach(rsp -> {
            System.out.println("---------------------------");
            System.out.println("---------------------------");
            rsp.forEachRemaining(System.out::println);
        });
    }
}
