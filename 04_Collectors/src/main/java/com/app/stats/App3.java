package com.app.stats;

import com.app.model.Product;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class App3 {
    public static void main(String[] args) {

        List<Product> products = Arrays.asList(
                new Product("PR1", 2, new BigDecimal("100")),
                new Product("PR2", 1, new BigDecimal("200")),
                new Product("PR3", 2, new BigDecimal("50")),
                new Product("PR4", 3, new BigDecimal("50")),
                new Product("PR5", 2, new BigDecimal("200")),
                new Product("PR6", 5, new BigDecimal("400")),
                new Product("PR7", 4, new BigDecimal("400")),
                new Product("PR8", 1, new BigDecimal("200"))
        );

        StatisticsCollector<BigDecimal> priceCollector = new StatisticsCollector<>(BigDecimal::compareTo, BigDecimal::add);
        StatisticsCollector.Statistics<BigDecimal> priceStatistics = products.stream().map(Product::getPrice).collect(priceCollector);
        System.out.println(priceStatistics);
    }
}
