package edu.aston.sakralen.collections;

import java.util.Arrays;
import java.util.Collection;

public class MyArrayList<T> implements MyList<T> {
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 4;
    private int capacity = DEFAULT_CAPACITY;
    private Object[] elements = new Object[DEFAULT_CAPACITY];

    public MyArrayList() {

    }

    public MyArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
    }

    public MyArrayList(Collection<? extends T> collection) {
        addAll(collection);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }

        return (T) elements[index];
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        if (collection.isEmpty()) {
            return false;
        }

        for (T el : collection) {
            add(el);
        }
        return true;
    }

    @Override
    public void set(T element, int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }

        elements[index] = element;
    }

    @Override
    public void add(T element) {
        ensureCapacity();

        elements[size] = element;
        size++;
    }

    private void ensureCapacity() {
        if (size < capacity) {
            return;
        }

        capacity *= 2;
        elements = Arrays.copyOf(elements, capacity);
    }

    @Override
    public boolean remove(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            return false;
        }

        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;

        size--;
        return true;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++) {
            sb.append(elements[i].toString()).append(", ");
        }
        sb.append(elements[size - 1]).append("]");
        return sb.toString();
    }
}
