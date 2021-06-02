package darthrusya.SortedSet;

import java.util.*;

public class MySortedSet<E> implements SortedSet<E> {

    public static final int INCREASE_NUM = 2;
    public static final int DEFAULT_SIZE = 10;

    private E data[];
    private int size;
    private int capacity = DEFAULT_SIZE;
    private Comparator<E> com;

    public MySortedSet(){
        this.data = (E[]) new Object[DEFAULT_SIZE];
        this.size = 0;
    }

    public MySortedSet(Collection<? extends E> col, Comparator<E> com) {
        this.size = col.size();
        this.data = (E[]) new Object[size];
        addAll(col);
        sort();
    }

    public MySortedSet(Comparator<E> com) {
        data = (E[]) new Object[DEFAULT_SIZE];
        this.com = com;
    }


    @Override
    public Comparator<? super E> comparator() {
        return com;
    }

    @Override
    public SortedSet<E> subSet(E fromElement, E toElement) {
        SortedSet<E> set = new MySortedSet<>();
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
                set.add(data[i]);
            }
            return set;
        }
        throw new IllegalArgumentException("failure");
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        SortedSet<E> set = new MySortedSet<>();
        boolean flag = true;
        for (int i = 0; i < size && flag; i++) {
            if (!(data[i].equals(toElement))) {
                set.add(data[i]);
            } else {
                flag = false;
            }
        }
        if (!flag) {
            return set;
        }
        throw new IllegalArgumentException("failure");
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        SortedSet<E> set = new MySortedSet<>();
        int cur = -1;
        for (int i = 0; i < size; i++) {
            if (data[i].equals(fromElement)) {
                cur = i;
                break;
            }
        }
        if (cur != -1) {
            for (int i = cur+1; i < size; i++) {
                set.add(data[i]);
            }
            return set;
        }
        //exception
        throw new IllegalArgumentException("failure");
    }

    @Override
    public E first() {
        return data[0];
    }

    @Override
    public E last() {
        return data[size-1];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (Object obj : data) {
            if (obj != null) {
                if (obj.equals(o)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new BasicIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] tempdata = new Object[size];
        if (size >= 0) {
            System.arraycopy(data, 0, tempdata, 0, size);
        }
        return tempdata;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        T[] arr = (T[]) new Object[a.length];
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if (contains(a[i])) {
                arr[i] = a[i];
                count++;
            }
        }
        T[] buffArr = (T[]) new Object[count];
        for (int i = 0; i < count; i++) {
            buffArr[i] = arr[i];
        }
        return buffArr;
    }

    @Override
    public boolean add(E e) {
        if (size >= capacity) {
            resize();
        }
        if (!contains(e)) {
            data[size++] = e;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if ((o == null && data[i] == null) || (data[i].equals(o))) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object obj : c) {
            if (!contains(obj)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E el : c) {
            if (contains(el)) {
                return false;
            }
        }
        for (E el : c) {
            add(el);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for(Object obj: c) {
            if (contains(obj)) {
                remove(obj);
            }
        }
        return true;
    }

    @Override
    public void clear() {
        size = 0;
    }

    private void sort() {
        for (int i=0; i < size-1; i++) {
            for (int j=i; j<size; j++) {
                if (com.compare(data[i],data[j]) > 0) {
                    E temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                }
            }
        }
    }

    private void resize() {
        capacity *= INCREASE_NUM;
        E[] bufData = (E[]) new Object[capacity];
        if (size >= 0) {
            System.arraycopy(data, 0, bufData, 0, size);
        }
        data = bufData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MySortedSet<?> that = (MySortedSet<?>) o;
        return size == that.size &&
                capacity == that.capacity &&
                Arrays.equals(data, that.data) &&
                Objects.equals(com, that.com);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size, capacity, com);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("{");
        for (int i=0; i < size; i++) {
            str.append(data[i]);
            if (i < size -1) str.append(", ");
        }
        str.append("}");
        return str.toString();
    }

    private boolean remove(int index) {
        if (index < 0 || index >= size) {
            return false;
        }
        for (int i = index; i < size-1; i++) {
            data[i] = data[i+1];
        }
        size--;
        return false;
    }

    private class BasicIterator implements Iterator<E> {

        private int current;

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public E next() {
            if (current >= size) throw new NoSuchElementException();
            return data[current++];
        }

        @Override
        public void remove() {
            if (current == 0) throw new NoSuchElementException();
            MySortedSet.this.remove(current-1);
            current--;
        }
    }
}
