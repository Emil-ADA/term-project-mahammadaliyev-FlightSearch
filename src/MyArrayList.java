import java.util.Iterator;

public class MyArrayList<T> implements Iterable<T> {

    T data[];
    int size;

    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public MyArrayList() {

        this(DEFAULT_CAPACITY);

    }

    @SuppressWarnings("unchecked")
    public MyArrayList(int initialCapacity) {

        data = (T[]) new Object[initialCapacity];
        size = 0;

    }

    public void add(T newEntry) {

        add(size, newEntry);

    }

    public void add(int newPosition, T newEntry) {

        if(newPosition < 0 || newPosition > size) throw new IndexOutOfBoundsException();

        if(newPosition > data.length - 1) increaseSize();

        if(newPosition < size) {

            for(int i = size; i > newPosition; i--) {

                data[i] = data[i - 1];

            }

            data[newPosition] = newEntry;
            size++;

        } else data[size++] = newEntry;

    }

    public T set(int index, T target) {

        if(target == null) throw new NullPointerException();

        if(index < 0 || index >= data.length) throw new IndexOutOfBoundsException();

        T oldVal = data[index];

        data[index] = target;

        return oldVal;

    }

    public T remove(int givenPosition) {

        if(givenPosition < 0 || givenPosition >= data.length) throw new IndexOutOfBoundsException();

        for(int i = givenPosition; i < size; i++) {

            data[i] = data[i + 1];

        }

        data[size++ - 1] = null;

        return null;

    }

    public boolean remove(T target) {

        if(target == null) throw new NullPointerException();

        for(int i = 0; i < size - 1; i++) {

            if(data[i] == target) {

                remove(i);

            }

        }

        return true;

    }

    public void clear() {

        for(int i = 0; i < size; i++) {

            data[i] = null;

        }

        size = 0;

    }

    public T replace(int givenPosition, T newEntry) {

        T oldVal = data[givenPosition];

        if(givenPosition < 0 || givenPosition >= data.length) throw new IndexOutOfBoundsException();

        data[givenPosition] = newEntry;

        return oldVal;
    }

    public T get(int index) {
        if(index <  0 || index >= data.length) throw  new IndexOutOfBoundsException();
        return data[index];
    }

    public T[] toArray() {

        T[] newData = (T[]) new Object[size];

        for(int i = 0; i < size - 1; i++) {

            newData[i] = data[i];

        }

        return newData;

    }

    public boolean contains(T anEntry) {

        for(int i = 0; i < size; i++)
            if(data[i] == anEntry) return true;

        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();

        for (T anEntry : data) {
            if (anEntry != null) {

                str.append(anEntry);
                str.append(", ");

            }

        }

        return "[" + str.substring(0, str.length() - 2) + "]";

    }

    public void increaseSize() {

        T newData[] = (T[]) new Object[size * 2];

        for (int i = 0; i < size; i++) {

            newData[i] = data[i];

        }

        data = newData;

    }

    public MyArrayList<T> reverseArrayList() {

        MyArrayList<T> revArrayList = new MyArrayList<>();

        for (int i = size - 1; i >= 0; i--)
            revArrayList.add(data[i]);

        return revArrayList;
    }


    @Override
    public Iterator<T> iterator() {

        Iterator<T> it = new Iterator<T>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                return data[currentIndex++];
            }

        };

        return it;
    }
}
