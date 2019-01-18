package pl.kmprograms.spliterator;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainSpliterator {
    public static void main(String[] args) {

        List<Person> people = List.of(
                new Person("ADAM", 23),
                new Person("PAWEL", 13),
                new Person("AGA", 33),
                new Person("MIREK", 26),
                new Person("KAMIL", 37),
                new Person("NATALIA", 25),
                new Person("EWA", 28),
                new Person("IGOR", 38)
        );

        // --------------------------------------------------------------------
        // forEachRemaining
        System.out.println("\n\nforEachRemaining()");
        Spliterator<Person> ps1 = people.spliterator();
        ps1.forEachRemaining(System.out::println);

        // --------------------------------------------------------------------
        // tryAdvance()
        System.out.println("\n\ntryAdvance()");
        Spliterator<Person> ps2 = people.spliterator();
        ps2.tryAdvance(System.out::println);

        // --------------------------------------------------------------------
        // trySplit()
        System.out.println("\n\ntrySplit()");
        Spliterator<Person> ps3 = people.spliterator();
        Spliterator<Person> ps4 = ps3.trySplit();
        Spliterator<Person> ps5 = ps4.trySplit();
        System.out.println("----- 1 -----");
        // ps3.forEachRemaining(System.out::println);
        System.out.println("----- 2 -----");
        // ps4.forEachRemaining(System.out::println);
        System.out.println("----- 3 -----");
        // ps5.forEachRemaining(System.out::println);

        // --------------------------------------------------------------------
        // trySplit() w przykladzie z wielowatkowoscia
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new MyThread<>(ps3));
        executorService.execute(new MyThread<>(ps4));
        executorService.execute(new MyThread<>(ps5));
        executorService.shutdown();


        // --------------------------------------------------------------------
        // estimatedSize()
        System.out.println("\n\nestimatedSize()");
        Spliterator<Person> ps6 = people.spliterator();
        System.out.println(ps6.estimateSize());
        ps6.tryAdvance(System.out::println);
        System.out.println(ps6.estimateSize());
        // metoda getExactSizeIfKnown() zwraca to samo co
        // estimatedSize() o ile sekwencja zrodlowa danych ma charakterystyke
        // SIZED a w przeciwnym razie zwraca -1
        System.out.println(ps6.getExactSizeIfKnown());


        // --------------------------------------------------------------------
        // spliterator typow prymitywnych
        Spliterator.OfInt ints = Arrays.spliterator(new int[] {10, 20, 30});
        // Spliterator.OfDouble
        // Spliterator.OfLong




        // --------------------------------------------------------------------
        // characteristics()
        System.out.println("\n\ncharacteristics()");
        System.out.println(ps1.characteristics());

        // -> wygodniej dowiadywac sie o charakteystyce w ponizszy sposob:
        if (ps1.hasCharacteristics(Spliterator.ORDERED)) {
            System.out.println("ORDERED");
        }
        if (ps1.hasCharacteristics(Spliterator.DISTINCT)) {
            System.out.println("DISTINCT");
        }
        if (ps1.hasCharacteristics(Spliterator.SORTED)) {
            System.out.println("SORTED");
        }
        if (ps1.hasCharacteristics(Spliterator.SIZED)) {
            System.out.println("SIZED");
        }

        if (ps1.hasCharacteristics(Spliterator.CONCURRENT)) {
            System.out.println("CONCURRENT");
        }
        if (ps1.hasCharacteristics(Spliterator.IMMUTABLE)) {
            System.out.println("IMMUTABLE");
        }
        if (ps1.hasCharacteristics(Spliterator.NONNULL)) {
            System.out.println("NONNULL");
        }
        if (ps1.hasCharacteristics(Spliterator.SUBSIZED)) {
            System.out.println("SUBSIZED");
        }
    }
}
