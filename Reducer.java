@FunctionalInterface
public interface Reducer<T> {
    T apply(T acc, T value);
}
