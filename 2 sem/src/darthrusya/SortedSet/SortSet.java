package darthrusya.SortedSet;

import java.util.*;

public class SortSet<E> implements SortedSet<E> {

    public static final int DEFAULT_SIZE = 10;

    private Comparator<E> comparator;
    private E[] data;
    private int size;

    public SortSet() {
        data = (E[]) new Object[DEFAULT_SIZE];
        size = 0;
    }

    public SortSet(Collection<? extends E> col, Comparator<E> com) {
        size = col.size();
        data = (E[]) new Object[size];
        addAll(col);
        //resort();
    }

    public SortSet(Comparator<E> com) {
        data = (E[]) new Object[DEFAULT_SIZE];
        size = 0;
        this.comparator = com;
    }

    @Override
    public Comparator<? super E> comparator() {
        return comparator;
    }

    @Override
    public SortSet subSet(Object fromElement, Object toElement) {
        SortSet<E> ex = new SortSet<>();
        int head = -1;
        int tail = -1;
        for (int i = 0; i < size; i++) {
            if (data[i].equals(fromElement)) {
                head = i;
            }
            if (data[i].equals(toElement)) {
                tail = i;
            }
        }
        if (head != -1 && tail != -1) {
            for (int i = head+1; i < tail; i++) {
                ex.add(data[i]);
            }
            return ex;
        }
        throw new IllegalArgumentException("message");
    }

    @Override
    public SortSet<E> headSet(Object toElement) {
        SortSet<E> ex = new SortSet<E>();
        boolean flag = false;
        for (int i = 0; i < size; i++) {
            if (!(data[i].equals(toElement))) {
                ex.add(data[i]);
                flag = true;
            }
        }
        if (flag) {
            return ex;
        }
        throw new IllegalArgumentException("message");
    }

    @Override
    public SortSet tailSet(Object fromElement) {
        SortSet<E> ex = new SortSet<E>();
        int cur = -1;
        for (int i = 0; i < size; i++) {
            if (data[i].equals(fromElement)) {
                cur = i;
                break;
            }
        }
        if (cur != -1) {
            for (int i = cur+1; i < size; i++) {
                ex.add(data[i]);
            }
            return ex;
        }
        //exception
        throw new IllegalArgumentException("message");
    }

    @Override
    public E first() {
        if (size > 0) {
            return data[0];
        }
        throw new NoSuchElementException("nothing");
    }

    @Override
    public E last() {
        if (size > 0) {
            return data[size-1];
        }
        throw new NoSuchElementException("nothing");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return (Iterator<E>) new BasicIterator();
    }

    @Override
    public Object[] toArray() {
        return data;
    }

    @Override
    public boolean add(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return false;
            }
        }
        if(size >= data.length){
            E newData[] = (E[]) new Object[data.length * 2];
            System.arraycopy(data, 0, newData, 0, data.length);
            data = newData;
        }
        data[size] = e;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object e) {
        int cur = -1;
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                cur = i;
                break;
            }
        }
        if (cur != -1) {
            data[cur] = null;
            for (int i = cur; i < size-1; i++) {
                data[i] = data[i+1];
            }
            data[size-1] = null;
            size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SortSet<?> sortSet = (SortSet<?>) o;
        return size == sortSet.size &&
                Arrays.equals(data, sortSet.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "SortSet{" +
                "data=" + Arrays.toString(data) +
                '}';
    }

//    private E resort() {
//        for (int i = 0; i < size-1; i++) {
//            for (int j = i+1; j < size; j++) {
//                if (data[i] < data[j]) { //сравнение
//                    E ex = data[i];
//                    data[i] = data[j];
//                    data[j] = ex;
//                }
//            }
//        }
//    }

    private class BasicIterator implements Iterator<E> {

        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public E next() {
            E value = data[current];
            current++;
            return value;
        }

    }

    private class BasicComparator implements Comparator<E> {

        @Override
        public int compare(E o1, E o2) {
            return 0;
        }

    }

}
