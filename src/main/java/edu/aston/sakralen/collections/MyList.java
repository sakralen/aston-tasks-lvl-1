package edu.aston.sakralen.collections;

import java.util.Collection;

public interface MyList<T> {
    int size();

    T get(int index);

    void add(T element);

    boolean addAll(Collection<? extends T> collection);

    void set(T element, int index);

    boolean remove(T element);

    static <T extends Comparable<? super T>> void sort(MyList<T> list) {
        if (list.size() == 0) {
            return;
        }

        for (int i = 0; i < list.size() - 1; i++) {
            boolean isSwapped = false;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).compareTo(list.get(j)) > 0) {
                    T el = list.get(i);
                    list.set(list.get(j), i);
                    list.set(el, j);
                    isSwapped = true;
                }
            }
            if (!isSwapped) {
                break;
            }
        }
    }
}
