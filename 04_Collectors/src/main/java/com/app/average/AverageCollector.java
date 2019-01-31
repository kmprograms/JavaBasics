package com.app.average;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class AverageCollector implements Collector<BigDecimal, AverageCollector.BigDecimalAccumulator, BigDecimal> {

    @Override
    public Supplier<BigDecimalAccumulator> supplier() {
        return BigDecimalAccumulator::new;
    }

    @Override
    public BiConsumer<BigDecimalAccumulator, BigDecimal> accumulator() {
        return BigDecimalAccumulator::add;
    }

    @Override
    public BinaryOperator<BigDecimalAccumulator> combiner() {
        return BigDecimalAccumulator::combine;
    }

    @Override
    public Function<BigDecimalAccumulator, BigDecimal> finisher() {
        return BigDecimalAccumulator::average;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }

    static class BigDecimalAccumulator {
        private BigDecimal total = BigDecimal.ZERO;
        private BigDecimal count = BigDecimal.ZERO;

        public BigDecimalAccumulator() {
        }

        public BigDecimalAccumulator(BigDecimal total, BigDecimal count) {
            this.total = total;
            this.count = count;
        }

        public BigDecimal getTotal() {
            return total;
        }

        public void setTotal(BigDecimal total) {
            this.total = total;
        }

        public BigDecimal getCount() {
            return count;
        }

        public void setCount(BigDecimal count) {
            this.count = count;
        }

        BigDecimal average() {
            return BigDecimal.ZERO.compareTo(count) == 0 ? BigDecimal.ZERO : total.divide(count, 2, BigDecimal.ROUND_CEILING);
        }

        BigDecimalAccumulator combine(BigDecimalAccumulator acc) {
            return new BigDecimalAccumulator(total.add(acc.getTotal()), count.add(acc.getCount()));
        }

        void add(BigDecimal decimal) {
            count = count.add(BigDecimal.ONE);
            total = total.add(decimal);
        }
    }

}
