package com.app.stats;

import java.text.MessageFormat;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class StatisticsCollector<T> implements Collector<T, StatisticsCollector.Statistics<T>, StatisticsCollector.Statistics<T>> {

    private Comparator<T> comparator;
    private BinaryOperator<T> binaryOperator;

    public StatisticsCollector(Comparator<T> comparator, BinaryOperator<T> binaryOperator) {
        this.comparator = comparator;
        this.binaryOperator = binaryOperator;
    }

    @Override
    public Supplier<Statistics<T>> supplier() {
        return Statistics::new;
    }

    @Override
    public BiConsumer<Statistics<T>, T> accumulator() {
        return (acc, element) -> {
            acc.setMin(acc.getMin() == null ? element : ( comparator.compare(acc.getMin(), element) <= 0 ? acc.getMin() : element));
            acc.setMax(acc.getMax() == null ? element : ( comparator.compare(acc.getMax(), element) >= 0 ? acc.getMax() : element));
            acc.setTotal(acc.getTotal() == null ? element : binaryOperator.apply(acc.getTotal(), element));
        };
    }

    @Override
    public BinaryOperator<Statistics<T>> combiner() {

        return (r1, r2) -> {
            Statistics<T> statistics = new Statistics<>();

            // MIN
            if (Objects.isNull(r1.getMin()) && Objects.isNull(r2.getMin())) {
                statistics.setMin(null);
            }
            else if (Objects.isNull(r1.getMin()) && Objects.nonNull(r2.getMin())) {
                statistics.setMin(r2.getMin());
            }
            else if (Objects.nonNull(r1.getMin()) && Objects.isNull(r2.getMin())) {
                statistics.setMin(r1.getMin());
            }
            else {
                statistics.setMin(comparator.compare(r1.getMin(), r2.getMin()) <= 0 ? r1.getMin() : r2.getMin());
            }

            // MAX
            if (Objects.isNull(r1.getMax()) && Objects.isNull(r2.getMax())) {
                statistics.setMax(null);
            }
            else if (Objects.isNull(r1.getMax()) && Objects.nonNull(r2.getMax())) {
                statistics.setMax(r2.getMax());
            }
            else if (Objects.nonNull(r1.getMax()) && Objects.isNull(r2.getMax())) {
                statistics.setMax(r1.getMax());
            }
            else {
                statistics.setMax(comparator.compare(r1.getMax(), r2.getMax()) <= 0 ? r1.getMax() : r2.getMax());
            }

            // DATA
            if (Objects.isNull(r1.getTotal()) && Objects.isNull(r2.getTotal())) {
                statistics.setTotal(null);
            }
            else if (Objects.isNull(r1.getTotal()) && Objects.nonNull(r2.getTotal())) {
                statistics.setTotal(r2.getTotal());
            }
            else if (Objects.nonNull(r1.getTotal()) && Objects.isNull(r2.getTotal())) {
                statistics.setTotal(r1.getTotal());
            }
            else {
                statistics.setTotal(binaryOperator.apply(r1.getTotal(), r2.getTotal()));
            }

            return statistics;
        };
    }

    @Override
    public Function<Statistics<T>, Statistics<T>> finisher() {
        throw new UnsupportedOperationException();
    }

    // CONCURRENT       zostana zastosowane akumulatory rownolegle
    // IDENTITY_FINISH  Function.identity() jako 4 argument i nie musisz go juz okreslac
    // UNORDERED        Collector nie musi dbac o kolejnosc elementow
    @Override
    public Set<Characteristics> characteristics() {
        return new HashSet<>(Arrays.asList(Characteristics.IDENTITY_FINISH, Characteristics.UNORDERED));
        // return new HashSet<>(Arrays.asList(Characteristics.UNORDERED)); // wyjatek - bo nie mamy Function
    }

    // ----------------------------------------------------------------------------------------------
    // ----------------------------------------------------------------------------------------------
    // ----------------------------------------------------------------------------------------------
    // WEWNETRZNA KLASA STATYCZNA
    public static class Statistics<T> {

        private T min;
        private T max;
        private T total;

        public Statistics() {
        }

        public Statistics(T min, T max, T total) {
            this.min = min;
            this.max = max;
            this.total = total;
        }

        public T getMin() {
            return min;
        }

        public void setMin(T min) {
            this.min = min;
        }

        public T getMax() {
            return max;
        }

        public void setMax(T max) {
            this.max = max;
        }

        public T getTotal() {
            return total;
        }

        public void setTotal(T total) {
            this.total = total;
        }

        @Override
        public String toString() {
            return MessageFormat.format("MIN: {0}, MAX: {1}, TOTAL: {2}", min, max, total);
        }
    }
}
