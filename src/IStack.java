public interface IStack<T> {

    void push(T el);
    T peek();
    T pop();

    boolean isEmpty();
    int size();

    T[]  toArray();

    @Override
    String toString();

}