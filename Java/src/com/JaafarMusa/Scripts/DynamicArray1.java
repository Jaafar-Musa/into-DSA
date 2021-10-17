package com.JaafarMusa.Scripts;

import java.util.Iterator;

//suppress warning
public class DynamicArray1 <T> implements Iterable <T> {
    private T[] arr; //the internal static array
    private int len = 0; // length user thinks array is
    private int capacity = 0; // actual size of the array

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

    public T get(int index){
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

    public void add(T elem){
        if(len + 1 > capacity){
            if (capacity == 0) capacity = 1 ;
            else capacity *= 2;
            T[] new_arr = (T[]) new Object[capacity];
            for(int i = 0; i < len ; i++) new_arr[i] = arr[i];
        }
        arr[len++] = elem;
    }

    // remove elem at specific index
    public T removeAt(int index){
       if(index >= len || index < 0) throw new IndexOutOfBoundsException();
       T data = arr[index];
       T[] new_arr = (T[]) new Object[len--];
       for(int i = 0, j = 0; i < len; i++, j++ ){
           if(i == index) j--;
           else new_arr[j] = arr[i];
       }
       arr = new_arr;
       capacity = --len;
       return data;
    }

//    public indexOf(Object obj){
//
//    }

    public boolean remove(Object obj){
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
