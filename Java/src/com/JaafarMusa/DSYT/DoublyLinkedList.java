package com.JaafarMusa.DSYT;



import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T> {
    //size of the linked list
    private int size = 0;
    //head and tail of list
    private Node<T> head = null;
    private Node<T> tail = null;

    //internal node class
    private class Node<T>{
        //data in the node
        T data;
        Node<T> prev, next;
        Node(T data, Node<T> prev, Node<T> next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
        @Override
        public String toString() {
            return data.toString();
        }
    }
    // clears the list in linear time
    public void clear(){
    Node <T> trav = this.head;
    while (trav != null){
            Node<T> next = trav.next;
            trav.next = trav.prev = null;
            trav.data = null;
            trav = next;
        }
    this.size = 0;
    this.head = this.tail = null;
    }
    public int size(){
        return this.size;
    }
    public boolean isEmpty(){
        return this.size() == 0;
    }
    //O(1)
    public void addFirst(T elem){
        if(this.isEmpty()){
            this.head = this.tail = new Node<T>(elem,null,null);
        }else{
            this.head.prev = new Node<T>(elem, null, this.head);
            this.head = this.head.prev;
        }
        this.size++;
    }
    //O(1)
    public void addLast(T elem){
        if(this.isEmpty()){
            this.head = this.tail = new Node<T>(elem,null,null);
        }else{
            this.tail.next = new Node<T>(elem, this.tail, null);
            this.tail = this.tail.next;
        }
        this.size++;
    }
    public void add(T elem){
        this.addLast(elem);
    }
    //O(1)
    public T PeekFirst(){
        if(this.isEmpty()) throw new RuntimeException("Empty List");
        return this.head.data;
    }
    //O(1)
    public T PeekLast(){
        if(this.isEmpty()) throw new RuntimeException("Empty List");
        return this.tail.data;
    }
    //O(1)
    public T removeFirst(){
        if(this.isEmpty()) throw new RuntimeException("Empty List");
        //extract data
        T data = this.head.data;
        //move the head one node to the left
        this.head = this.head.next;
        --this.size;
        //check if the list is empty
        if(this.isEmpty()) this.tail = null;
        //clean the memory of the prev head
        else this.head.prev = null;

        return data;
    }
    //O(1)
    public T removeLast(){
        if(this.isEmpty()) throw new RuntimeException("Empty List");
        //extract data
        T data = this.tail.data;
        //move the head one node to the left
        this.tail = this.tail.prev;
        --this.size;
        //check if the list is empty
        if(this.isEmpty()) this.head = null;
        //clean the memory of the prev head
        else this.tail.next = null;

        return data;
    }
    //Remove an arbitrary node from linked list, O(1)
    private T remove(Node<T> node){
        //check if the node to be removed is at the head or the tail
        if(node.prev == null) return this.removeFirst();
        if(node.next == null) return this.removeLast();

        //make adjacent nodes skip over current node
        //node.next == next node, its prev pointer == current nodes prev
        node.next.prev = node.prev;
        // and vice versa
        node.prev.next = node.next;

        T data = node.data;

        //clear mem
        node.data = null;
        node.next = node.prev = null;
        --this.size;

        return data;
    }
    public T RemoveAt(int index){
        if(index < 0 || index >= this.size) throw new IllegalArgumentException();
        int i;
        Node<T> trav;

        //Start searching from beginning of the list
        if(index < size/2){
            for( i = 0, trav = head; i != index; i++ )
                trav = trav.next;
        }else{
            for( i = size - 1, trav = tail; i != index; i-- )
                trav = trav.prev;
        }
        return remove(trav);
    }
    //O(n)
    public boolean remove(Object obj){
        Node<T> trav = head;
        //support null value
        if(obj == null){
            for(trav = head; trav != null; trav = trav.next){
                if(trav.data == null){
                    remove(trav);
                    return true;
                }
            }
        }else{
            for(trav = head; trav != null; trav = trav.next){
                if(obj.equals(trav.data)){
                    remove(trav);
                    return true;
                }
            }
        }
        return false;
    }
    //find index of particular node O(N)
    public int indexOf(Object obj){
        int index = 0;
        Node<T> trav = head;
        if(obj == null){
            for(trav = head; trav != null; trav = trav.next, index++){
                if(trav.data == null)
                    return index;
            }
        }else{
            for(trav = head; trav != null; trav = trav.next, index++){
                if(obj.equals(trav.data))
                    return index;
            }
        }
        return -1;
    }
    public boolean contains(Object obj){
        return this.indexOf(obj) != -1;
    }

    //concurrent modification error not implemented
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> trav = head;
            @Override
            public boolean hasNext() {
                return trav != null;
            }

            @Override
            public T next() {
                T data = trav.data;
                trav = trav.next;
                return data;
            }
        };
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder().append("[");
        Node<T> trav = head;
        while (trav != null){
            sb.append(trav.data + ", ");
            trav = trav.next;
        }
        return sb.append(" ]").toString();
}
}
