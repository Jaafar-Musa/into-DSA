package com.JaafarMusa.DSYT;


import java.util.Arrays;
import java.util.Iterator;

@SuppressWarnings("unchecked")
public class DynamicArray1 <T> implements Iterable <T> {
    private T[] arr;  //the internal static array
    private int len = 0; // length user thinks array is
    private int capacity; // actual size of the array

    public DynamicArray1(){
        this(16);
    }
    
    public DynamicArray1(int capacity){
        if(capacity < 0) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        this.capacity = capacity;
        arr = (T[]) new Object[capacity];
    }

    public int size(){
        return len;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    // Get and Set require a bounds check
    public T get(int index){
        if(index >= len || index < 0) throw new IndexOutOfBoundsException();
        return arr[index];
    }
    public void set(int index, T elem){
        arr[index] = elem;
    }

    public void clear(){
        for(int i = 0; i < capacity; i++){
            arr[i] = null;
        }
        len = 0;
    }

    // add a specific element to array
    public void add(T elem){
        if(len + 1 > capacity){
            if (capacity == 0) capacity = 1 ;
            //doubling size not necessary but convenient
            else capacity *= 2;
            T[] new_arr = (T[]) new Object[capacity];
            for(int i = 0; i < len ; i++) new_arr[i] = arr[i];
            this.arr = new_arr;
        }
        arr[len++] = elem;
    }

    // remove elem at specific index
    public T removeAt(int index){
       if(index >= len || index < 0) throw new IndexOutOfBoundsException();
       T data = arr[index];
       T[] new_arr = (T[]) new Object[len-1];
       for(int i = 0, j = 0; i < len; i++, j++ ){
           if(i == index) j--;
           else new_arr[j] = arr[i];
       }
       arr = new_arr;
       capacity = --len;
       return data;
    }
    public boolean remove(Object obj){
        for(int i = 0; i < len; i++){
            if(this.arr[i].equals(obj)){
                this.removeAt(i);
                return true;
            }
        }
        return false;
    }
    public int indexOf(Object obj){
        for(int i = 0; i < len; i++){
            if(this.arr[i].equals(obj)){
                return i;
            }
        }
        return -1;
    }
    public boolean contains(Object obj){
        return this.indexOf(obj) != -1;
    }

    //iterator is fast but not as fast as iterative
    @Override public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < len;
            }

            @Override
            public T next() {
                return arr[index++];
            }
        };
    }

    @Override
    public String toString() {
        if(len == 0) return "[]";
        return "";
//        else {
//            StringBuilder sb = new StringBuilder();
////            return "DynamicArray1{" +
////                    "arr=" + Arrays.toString(arr) +
////                    ", len=" + len +
////                    ", capacity=" + capacity +
////                    '}';
////            return sb;
//        }
    }
}

