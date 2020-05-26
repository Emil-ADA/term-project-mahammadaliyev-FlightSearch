public class MyStackWithLL<T> implements IStack<T> {

    Node head;
    int size;

    class Node {
        T data;
        Node next;

        Node(T data) {

            this.data = data;

        }
    }

    MyStackWithLL() {
        head = null;
        size = 0;
    }

    @Override
    public void push(T el) {
        Node newNode = new Node(el);

        size++;

        if(head == null)

            head = newNode;

        else {

            newNode.next = head;
            head = newNode;

        }

    }

    @Override
    public T peek() {

        if(size == 0) {

            System.out.println("Stack is empty!");

        }

        return head.data;
    }

    @Override
    public T pop() {

        T lastChance = head.data;
        size--;
        head = head.next;
        return lastChance;

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T[] toArray() {

        T[] arr = (T[]) new Object[size];
        Node temp = head;

        for(int i = size - 1; i >= 0; i--) {

            arr[i] = temp.data;
            temp = temp.next;

        }

        return arr;
    }

}
