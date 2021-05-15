package darthrusya.modifableCollections;

import java.util.*;


/**
 * Code for studying purposes. Programming course. Kazan Federal University,
 * ITIS. http://study.istamendil.info/
 *
 * @author Alexander Ferenets (Istamendil) <ist.kazan@gmail.com>
 */
public class ModifableCollection<T> extends AbstractCollection<T>{

    private final static int DEFAULT_SIZE = 10;

    private T[] data;
    private int size;

    public ModifableCollection(){
        data = (T[]) new Object[DEFAULT_SIZE];
        size = DEFAULT_SIZE;
    }

    public ModifableCollection(Collection<? extends T> col){
        data = (T[]) new Object[col.size()];
        size = 0;
        for(T el : col){
            data[size] = el;
            size++;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return (Iterator<T>) new BasicCollectionIterator();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(T el) {
        BasicCollectionIterator it = new BasicCollectionIterator();
        while(it.hasNext()) {
            if (data[it.current].equals(el)) {
                return false;
            }
            it.next();
        }
        if(size >= data.length){
            T[] newData = (T[]) new Object[data.length * 2];
            System.arraycopy(data, 0, newData, 0, data.length);
            data = newData;
        }
        data[size] = el;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int cur = -1;
        BasicCollectionIterator it = new BasicCollectionIterator();
        while(it.hasNext()) {
            if (data[it.current].equals(o)) {
                cur = it.current;
                data[it.current] = null;
            }
            it.next();
        }
        if (cur != -1) {
            for (int i = cur; i < size-1; i++) {
                data[i] = data[i+1];
            }
            size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModifableCollection<?> that = (ModifableCollection<?>) o;
        return size == that.size &&
                Arrays.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    private class BasicCollectionIterator implements Iterator<T> {
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public T next() {
            T value = data[current];
            current++;
            return value;
        }
    }
}
