package pl.kmprograms.spliterator;

import java.text.MessageFormat;
import java.util.Spliterator;

public class MyThread<T> implements Runnable {

    private Spliterator<T> spliterator;

    public MyThread(Spliterator<T> spliterator) {
        this.spliterator = spliterator;
    }

    @Override
    public void run() {
        spliterator.forEachRemaining(t -> System.out.println(t + " from " + Thread.currentThread().getName()));
    }
}
