package com.app.average;

import com.app.model.Product;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class App2 {

    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("PR1", 2, new BigDecimal("100")),
                new Product("PR2", 1, new BigDecimal("200")),
                new Product("PR3", 2, new BigDecimal("100")),
                new Product("PR4", 3, new BigDecimal("300")),
                new Product("PR5", 2, new BigDecimal("200")),
                new Product("PR6", 5, new BigDecimal("400")),
                new Product("PR7", 4, new BigDecimal("100")),
                new Product("PR8", 1, new BigDecimal("200"))
        );

        System.out.println(products.stream().map(Product::getPrice).collect(new AverageCollector()));

    }
}
