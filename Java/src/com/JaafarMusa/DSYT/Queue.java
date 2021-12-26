package com.JaafarMusa.DSYT;

import java.util.Iterator;
import java.util.LinkedList;

//this queue allows for null elements
public class Queue<T> implements Iterable<T>{
    LinkedList<T> list = new LinkedList<>();

    public Queue(){}

    public Queue(T firstElem){
        offer(firstElem);
        
    }

    public int size(){
        return list.size();
    }
    //check if stack is empty
    public boolean isEmpty(){
        return this.size() == 0;
    }
    // peek the first elem in the front of the queue
    public T peek(){
        if(this.isEmpty()) throw new RuntimeException("Queue Empty");
        return this.list.peekFirst();
    }
    public T poll(){
        if(this.isEmpty()) throw new RuntimeException("Queue Empty");
        return this.list.removeFirst();
    }
    public void offer(T elem){
        list.addLast(elem);
    }

    @Override
    public Iterator<T> iterator() {
        return this.list.iterator();
    }
}
