public class Box<T> {
    private T item;

    public Box() {
        this.item = null;
    }

    public boolean isEmpty() {
        return item == null;
    }

    public void put(T newItem) throws Exception {
        if (!isEmpty()) {
            throw new Exception("Ошибка: коробка уже заполнена!");
        }
        this.item = newItem;
    }

    public T get() {
        T temp = this.item;
        this.item = null;
        return temp;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Коробка пуста.";
        } else {
            return "В коробке находится: " + item.toString();
        }
    }
}
