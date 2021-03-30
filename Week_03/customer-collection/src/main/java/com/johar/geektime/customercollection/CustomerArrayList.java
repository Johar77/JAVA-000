package com.johar.geektime.customercollection;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;

/**
 * @ClassName: CustomerArrayList
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/3/29 08:37
 * @Since: 1.0.0
 */
public class CustomerArrayList<E> implements List<E>, RandomAccess, Cloneable, Serializable {

    private final static long serialVersionUID = -1L;

    /**
     * 存储实际的数据
     */
    private Object[] elementData;

    private final static Object[] EMPTY_DATA = {};

    /**
     * 默认容量，10
     */
    private final static int DEFAULT_CAPACITY = 10;

    /**
     * 数据的数量
     */
    private int size;


    public CustomerArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public CustomerArrayList(int initialCapacity){
        if (initialCapacity > 0){
            elementData = new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("initialCapacity must be greater 0");
        }
    }

    public CustomerArrayList(Object[] objects){
        if (objects == null){
            elementData = EMPTY_DATA;
        } else {
            elementData = new Object[objects.length];
            System.arraycopy(objects, 0, elementData, 0, objects.length);
            size = elementData.length;
        }
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
        for (int i = 0; i < size; i++){
            if (elementData[i].equals(o)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    private class CustomerIterator implements Iterator<E>{
        private int cursor;

        private int lastCursor = -1;

        @Override
        public boolean hasNext() {
            return cursor < CustomerArrayList.this.size;
        }

        @Override
        public E next() {
            if (cursor >= CustomerArrayList.this.size){
                throw new IllegalArgumentException("out of bound");
            }
            lastCursor = cursor;
            return (E)CustomerArrayList.this.elementData[++cursor];
        }

        @Override
        public void remove() {
            if (cursor < 0 || cursor >= CustomerArrayList.this.size){
                throw new IllegalArgumentException("out of bound");
            }

            CustomerArrayList.this.remove(cursor);
            cursor = lastCursor;
        }
    }


    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public boolean add(E e) {
        ensureCapacity(size + 1);
        elementData[size++] = e;

        return true;
    }

    private void ensureCapacity(int newSize){
        if (newSize <= elementData.length){
            return;
        }

        int newCapacity = elementData.length + elementData.length >> 1;
        if (newCapacity > newSize){
            newCapacity = newSize;
        }

        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++){
            if (elementData[i].equals(o)){
                System.arraycopy(elementData, i + 1, elementData, i, size - i - 1);
                elementData[size--] = null;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Iterator<?> iterator = c.iterator();
        while (iterator.hasNext()){
            Object current = iterator.next();
            boolean contained = false;
            for (int j = 0; j < size; j++){
                if (current.equals(elementData[j])){
                    contained = true;
                    break;
                }
            }

            if (!contained){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        ensureCapacity(c.size() + size);
        System.arraycopy(c, 0, elementData, size, c.size());
        size = size + c.size();
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < 0 || index > size){
            throw new IllegalArgumentException("out of bound");
        }
        ensureCapacity(c.size() + size);
        if (size > index){
            System.arraycopy(elementData, size, elementData, size + c.size(), c.size());
        }

        System.arraycopy(c, 0, elementData, size, c.size());
        size += c.size();

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (int i = 0; i < size; i++){

        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {

        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++){
            elementData[i] = null;
        }

        size = 0;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("out of bound");
        }
        return (E) elementData[index];
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("out of bound");
        }

        elementData[index] = element;

        return element;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size){
            throw new IllegalArgumentException("out of bound");
        }
        ensureCapacity(1 + size);
        if (size > index){
            System.arraycopy(elementData, size, elementData, size + 1, 1);
        }

        elementData[size] = element;
        size += 1;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("out of bound");
        }

        Object result = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index);

        return (E)result;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++){
            if (elementData[i].equals(o)){
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = indexOf(o);
        if (index < 0){
            return  index;
        }

        return size - index;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if (fromIndex > toIndex){
            throw new IllegalArgumentException("from must less toIndex");
        }

        if (fromIndex < 0 || fromIndex >= size || toIndex < 0 || toIndex >= size){
            throw new IllegalArgumentException("out of bound");
        }

        Object[] result = new Object[toIndex - fromIndex + 1];

        System.arraycopy(elementData, fromIndex, result, 0, result.length);

        return new CustomerArrayList<E>(result);
    }
}