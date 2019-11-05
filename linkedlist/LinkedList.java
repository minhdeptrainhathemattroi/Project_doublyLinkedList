package linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class LinkedList<T> implements Iterable<T> {
    private class ListNode<T> {
        private T payload;
        private ListNode<T> next;
        private ListNode<T> prev;

        public ListNode(T v) {
            this.payload = v;
        }
    }

    protected ListNode<T> frontNode;
    protected ListNode<T> backNode;
    protected int size;
    public LinkedList() {
        frontNode = null;
        backNode = null;
        size = 0;
    }

    public LinkedList(Iterable<? extends T> c) {
        for (T minh : c) {
            if (frontNode == null ) {
                frontNode = new ListNode<>(minh);
                backNode = frontNode;
            } else if (frontNode == backNode) {
                backNode = new ListNode<>(minh);
                frontNode.next = backNode;
                backNode.prev = frontNode;
            } else {
                ListNode<T> depTrai = backNode;
                backNode = new ListNode<>(minh);
                backNode.prev = depTrai;
                depTrai.next = backNode;
            }
            size++;
        }
        /* YOUR CODE HERE */
    }

    public T peekFront() {
        if (frontNode == null) {
            return null;
        } else {
            return frontNode.payload;
        }

        /* YOUR CODE HERE */
    }

    public T peekBack() {
        if (backNode == null) {
            return null;
        } else {
            return backNode.payload;
        }
        /* YOUR CODE HERE */
    }

    public T popFront() {
        T minh;
        if (size == 0) {
            throw new NoSuchElementException();
        } else if (size == 1) {
            minh = frontNode.payload;
            frontNode = null;
            backNode = null;
        } else {
            minh = frontNode.payload;
            frontNode = frontNode.next;
            frontNode.prev = null;
        }
        size--;
        return minh;
        /* YOUR CODE HERE */
    }

    public T popBack() {
        T minh;
        if (size == 0) {
            throw new NoSuchElementException();
        } else if (size == 1) {
            minh = backNode.payload;
            backNode = null;
            frontNode = null;
        } else {
            minh = backNode.payload;
            backNode = backNode.prev;
            backNode.next = null;
        }
        size--;
        return minh;
        /* YOUR CODE HERE */
    }

    public void pushBack(T value) {
        if (frontNode == null && backNode == null) {
            backNode = new ListNode<>(value);
            frontNode = backNode;
        } else if (frontNode == backNode) {
            backNode = new ListNode<>(value);
            frontNode.next = backNode;
            backNode.prev = frontNode;
        } else {
            ListNode<T> minh = backNode;
            backNode = new ListNode<>(value);
            backNode.prev = minh;
            minh.next = backNode;
        }
        size++;

        /* YOUR CODE HERE */
    }

    public void pushFront(T value) {
        if (size == 0) {
            frontNode = new ListNode<>(value);
            backNode = frontNode;
        } else if (size == 1) {
            frontNode = new ListNode<>(value);
            frontNode.next = backNode;
            backNode.prev = frontNode;
        } else {
            ListNode<T> minh = frontNode;
            frontNode = new ListNode<>(value);
            frontNode.next = minh;
            minh.prev = frontNode;
        }
        size++;
        /* YOUR CODE HERE */
    }

    public void add(T value) {
        pushFront(value);
        /* YOUR CODE HERE */
    }

    public void add(int index, T value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            pushFront(value);
        } else if (index == size) {
            pushBack(value);
        } else {
            ListNode<T> minh = frontNode;
            for (int i = 0; i < index; i++) {
                minh = minh.next;
            }
            ListNode<T> depTrai = minh;
            minh = new ListNode<>(value);
            minh.next = depTrai;
            minh.prev = depTrai.prev;
            depTrai.prev.next = minh;
            depTrai.prev = minh;
            size++;
        }


    }

    public T remove() {
        return popFront();
        /* YOUR CODE HERE */
    }
    /* YOUR CODE HERE */

    public T remove(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            return popFront();
        } else if (index == size - 1) {
            return popBack();
        } else {
            ListNode<T> minh = backNode;
            for (int i = 1; i < (size - index); i++) {
                minh = minh.prev;
            }
            T m = minh.payload;
            ListNode<T> depTrai = minh;
            minh = depTrai.next;
            minh.prev = depTrai.prev;
            depTrai.prev.next = minh;
            size--;
            return m;

        }
        /* YOUR CODE HERE */
    }

    private void remove(ListNode<T> node) {
        if (size == 1) {
            frontNode = null;
            backNode = null;
        } else {
            if (node == frontNode) {
                frontNode = frontNode.next;
                frontNode.prev = null;
            } else if (node == backNode) {
                backNode = backNode.prev;
                backNode.next = null;
            } else {
                ListNode<T> minh = frontNode;
                while (minh.next != null) {
                    minh = minh.next;
                    if (minh == node) {
                        break;
                    }
                }
                ListNode<T> depTrai = minh;
                minh = depTrai.next;
                minh.prev = depTrai.prev;
                depTrai.prev.next = minh;
            }
        }
        size--;
        /* YOUR CODE HERE */
    }

    public int size() {
        return this.size;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator(frontNode);
        /* YOUR CODE HERE */
    }

    public Iterator<T> reverseIterator() {
        return new LinkedListReverseIterator(backNode);
        /* YOUR CODE HERE */
    }


    public class LinkedListIterator implements Iterator<T> {
        protected ListNode<T> depTrai;
        /* YOUR CODE HERE */
        private ListNode<T> head;

        public LinkedListIterator(ListNode<T> head) {
            this.head = head;
        }

        @Override
        public boolean hasNext() {
            return head != null;

            /* YOUR CODE HERE */
        }


        @Override
        public T next() {
            if (hasNext()) {
                T m = head.payload;
                depTrai = head;
                head = head.next;
                return m;
            } else {
                throw new NoSuchElementException();
            }

            /* YOUR CODE HERE */
        }

        @Override
        public void remove() {
            if (depTrai == null) {
                throw new IllegalStateException();

            } else {
                LinkedList.this.remove(depTrai);
            }
            /* YOUR CODE HERE */
        }
    }

    public class LinkedListReverseIterator implements Iterator<T> {
        protected ListNode<T> depTrai;
        private ListNode<T> tail;

        /* YOUR CODE HERE */
        public LinkedListReverseIterator(ListNode<T> tail) {
            this.tail = tail;
        }

        @Override
        public boolean hasNext() {
            return this.tail != null;
            /* YOUR CODE HERE */
        }

        @Override
        public T next() {
            if (hasNext()) {
                T m = tail.payload;
                depTrai = tail;
                tail = tail.prev;
                return m;

            } else {
                throw new NoSuchElementException();
            }

            /* YOUR CODE HERE */
        }

        @Override
        public void remove() {
            if (depTrai == null) {
                throw new IllegalStateException();

            } else {
                LinkedList.this.remove(depTrai);
            }
            /* YOUR CODE HERE */
        }
    }

    // toString requires Iterator to be partially implemented to function
    // as it uses the for-each loop construct
    @Override
    public String toString() throws NullPointerException {

        String result = "[";

        for (T value : this) {
            result += String.format("%s, ", value.toString());
        }


        if (result.length() > 1) {
            result = result.substring(0, result.length() - 2);
        }

        result += "]";
        return result;
    }

}
