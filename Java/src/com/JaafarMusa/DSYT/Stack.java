package com.JaafarMusa.DSYT;

import java.util.EmptyStackException;
import java.util.Iterator;

public class Stack<T> implements Iterable<T>{
    //doubly
    private java.util.LinkedList<T> list = new java.util.LinkedList<>();
    //create an empty stack
    public Stack(){

    }
    //create a stack with an initial value
    public Stack(T elem){
        push(elem);
    }
    //return number of element in the stack
    public int size(){
        return list.size();
    }
    //check if stack is empty
    public boolean isEmpty(){
        return this.size() == 0;
    }
    public void push(T elem){
        list.addLast(elem);
    }
    //pop an element
    public T pop(){
        if(this.isEmpty()) throw new EmptyStackException();
        return this.list.removeLast();
    }
    public T peek(){
        if(this.isEmpty()) throw new EmptyStackException();
        return this.list.peekLast();
    }
    @Override
    public Iterator iterator() {
        return list.iterator();
    }
}
