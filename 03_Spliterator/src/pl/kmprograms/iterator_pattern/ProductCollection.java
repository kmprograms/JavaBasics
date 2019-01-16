package pl.kmprograms.iterator_pattern;

import java.util.ArrayList;
import java.util.List;

public class ProductCollection implements MyCollection<Product> {

    private List<Product> products = new ArrayList<>();

    @Override
    public void add(Product element) {
        if (element == null) {
            throw new NullPointerException("ADD - ELEMENT IS NULL");
        }
        products.add(element);
    }

    @Override
    public void remove(Product element) {
        if (element == null) {
            throw new NullPointerException("REMOVE - ELEMENT IS NULL");
        }
        products.remove(element);
    }

    @Override
    public MyIterator<Product> iterator() {
        return new ProductIterator(products);
    }


    // --------------------------------------------------------------
    // klasa prywatna implementujaca zachowanie iteratora
    // --------------------------------------------------------------

    private class ProductIterator implements MyIterator<Product> {

        private List<Product> products;
        private int index;

        public ProductIterator(List<Product> products) {
            this.products = products;
        }

        @Override
        public boolean hasNext() {
            return index < products.size();
        }

        @Override
        public Product next() {
            return products.get(index++);
        }
    }
}
