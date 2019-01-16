package pl.kmprograms.iterator_pattern;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class MainIterator {
    public static void main(String[] args) {


        // ZAGADKA 1

        List<String> names = List.of("ALA", "BASIA", "CZAREK");
        List<String> names2 = Arrays.asList("ALA", "BASIA", "CZAREK");

        Map<String, Integer> m = Map.of("ADAM", 2000, "EWA", 4000);
        m.forEach((k, v) -> System.out.println(k + " " + v));

        // ZAGADKA 2
        System.out.println("---- 1 ----");
        Stream.of(names).forEach(x -> System.out.println("-> " + x));
        System.out.println("---- 2 ----");
        names.stream().forEach(x -> System.out.println("-> " + x));


        ProductCollection productCollection = new ProductCollection();
        productCollection.add(new Product("PRODUCT1", new BigDecimal("100")));
        productCollection.add(new Product("PRODUCT2", new BigDecimal("200")));
        productCollection.add(new Product("PRODUCT3", new BigDecimal("300")));
        productCollection.add(new Product("PRODUCT4", new BigDecimal("400")));
        productCollection.remove(new Product("PRODUCT4", new BigDecimal("400")));

        MyIterator<Product> iterator = productCollection.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
