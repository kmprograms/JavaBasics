package pl.kmprograms.iterator_pattern;

public interface MyCollection<T> {
    void add(T element);
    void remove(T element);
    MyIterator<T> iterator();
}
