package com.app.bases;

import com.app.model.Product;
import org.eclipse.collections.impl.collector.BigDecimalSummaryStatistics;
import org.eclipse.collections.impl.collector.Collectors2;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args )
    {
        List<Product> products = Arrays.asList(
                new Product("PR1", 2, new BigDecimal("100")),   // 200
                new Product("PR2", 1, new BigDecimal("200")),   // 200
                new Product("PR3", 2, new BigDecimal("100")),   // 200
                new Product("PR4", 3, new BigDecimal("300")),   // 900
                new Product("PR5", 2, new BigDecimal("200")),   // 400
                new Product("PR6", 5, new BigDecimal("400")),   // 2000
                new Product("PR7", 4, new BigDecimal("100")),   // 400
                new Product("PR8", 1, new BigDecimal("200"))    // 200
        );

        products.stream().filter(p -> p.getQuantity() > 10).collect(Collectors.toList());


        // ------------------------------------------------------------------------------------------------
        // --------------------- OMOWIENIE PODSTAWOWEJ STRUKTURY Collector.of -----------------------------
        // ------------------------------------------------------------------------------------------------

        // okresla akumulator, w ktorym bedzie przechowywany wynik / wyniki koncowych obliczen
        Supplier<BigDecimal[]> supplier = () -> new BigDecimal[]{BigDecimal.ZERO};

        // okresla co i jak ma byc zapisywane do akumulatora
        BiConsumer<BigDecimal[], Product> accumulator = (acc, product) ->
                acc[0] = acc[0].add(product.getPrice().multiply(new BigDecimal(product.getQuantity())));

        // przydatne przy operacjach rownoleglych, czyli jak mamy polaczyc dwa wyniki z dwoch
        // rownoleglych strumieni
        BinaryOperator<BigDecimal[]> combiner = (res1, res2) -> {
            res1[0] = res1[0].add(res2[1]);
            return res1;
        };

        // okresla do jakiej postaci ma zostac przekonwertowany wynik z akumulatora
        Function<BigDecimal[], BigDecimal> finisher = total -> total[0];


        BigDecimal total = products.stream().collect(Collector.of(
                supplier, accumulator, combiner, finisher));
        System.out.println("TOTAL PRICE = " + total);


        // ECLIPSE COLLECTORS
        List<BigDecimal> list = Arrays.asList(
                BigDecimal.valueOf(0.1),
                BigDecimal.valueOf(1.1),
                BigDecimal.valueOf(2.1),
                BigDecimal.valueOf(0.1));

        BigDecimal sum =
                list.stream().collect(Collectors2.summingBigDecimal(e -> e));
        BigDecimalSummaryStatistics statistics =
                list.stream().collect(Collectors2.summarizingBigDecimal(e -> e));
        System.out.println(statistics.getMin());
        System.out.println(statistics.getMax());
        System.out.println(statistics.getAverage());
        System.out.println(statistics.getSum());

    }

}
