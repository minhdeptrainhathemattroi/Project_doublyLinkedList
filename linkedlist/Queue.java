package linkedlist;

import java.util.Iterator;

public class Queue<T> extends LinkedList<T> {
    public Queue() {

    }
    @Override
    public void add(int index, T value){
        throw new RuntimeException();
    }
    @Override
    public T remove(int index){
        throw new RuntimeException();
    }
    @Override
    public T peekFront(){
        throw new RuntimeException();
    }
    @Override
    public T peekBack(){
        throw new RuntimeException();
    }
    @Override
    public T popFront(){
        throw new RuntimeException();
    }
    @Override
    public T popBack(){
        throw new RuntimeException();
    }
    @Override
    public Iterator<T> iterator(){
        throw new RuntimeException();
    }
    @Override
    public Iterator<T> reverseIterator(){
        throw new RuntimeException();
    }

    public void add(T value) {
        super.pushBack(value);

        /* YOUR CODE HERE */
    }

    public T remove() {
        return super.popFront();
    }

    public T peek() {
        return super.peekFront();
    }
}
