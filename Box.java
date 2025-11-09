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

    public T peek() {
        return this.item;
    }

    @Override
    public String toString() {
        return isEmpty() ? "Коробка пуста." : "В коробке находится: " + item.toString();
    }
}
