public class Storage<T> {
    private final T value;

    public Storage(T value) {
        this.value = value;
    }

    public T getOrElse(T alternative) {
        if (value != null) {
            return value;
        } else {
            return alternative;
        }
    }

    @Override
    public String toString() {
        if (value == null) {
            return "Хранилище содержит: " + "null";
        } else {
            return "Хранилище содержит: " + value.toString();
        }

    }
}
