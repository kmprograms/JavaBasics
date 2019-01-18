package pl.kmprograms.spliterator_example;

import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class RectangleSpliterator implements Spliterator<Rectangle> {

    private List<Rectangle> rectangles;
    private int currentPosition;
    private int lastPosition;

    public RectangleSpliterator(List<Rectangle> rectangles) {
        this.rectangles = rectangles;
        lastPosition = rectangles.size() - 1;
    }

    public RectangleSpliterator(List<Rectangle> rectangles, int currentPosition, int lastPosition) {
        this.rectangles = rectangles;
        this.currentPosition = currentPosition;
        this.lastPosition = lastPosition;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Rectangle> action) {
        if (currentPosition > lastPosition) {
            return false;
        }

        action.accept(rectangles.get(currentPosition++));
        return true;
    }

    @Override
    public Spliterator<Rectangle> trySplit() {
        if (lastPosition - currentPosition == 0) {
            return null;
        }

        int splitPos = currentPosition;
        while (splitPos + 1 < rectangles.size() && Math.abs(rectangles.get(splitPos).area() - rectangles.get(splitPos + 1).area()) <= 10) {
            splitPos++;
        }

        RectangleSpliterator secondHalf = new RectangleSpliterator(rectangles, splitPos + 1, lastPosition);
        lastPosition = splitPos;
        return secondHalf;
    }

    @Override
    public long estimateSize() {
        return lastPosition - currentPosition;
    }

    @Override
    public int characteristics() {
        return SIZED;
    }
}
