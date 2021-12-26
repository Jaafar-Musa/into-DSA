package com.JaafarMusa.DSYT;

import java.util.*;

//Allow only comparable elements; they implement the
//comparable interface
public class PQueue <T extends Comparable<T>>{

    // number of elements currently inside the heap
    private int heapSize = 0;
    //The internal capacity of the heap
    private int heapCapacity = 0;
    //A dynamic list to keep track of the elements inside the heap
    private List<T> heap = null;
    //This map keeps track of the possible indices a particular
    //Node value is found in the heap. Having this mapping lets us
    //Have O( log(n) ) removals and O(1) element containment check
    //at the cost of some additional space and minor overhead
    private Map<T, TreeSet<Integer>> map = new HashMap<>();

    // construct and initially set to empty
    public PQueue(){
        this(1);
    }
    public PQueue(int size){
        this.heap = new ArrayList<>(size);
    }
    public PQueue (T[] elems){
        this.heapSize = this.heapCapacity = elems.length;
        heap = new ArrayList<>( heapCapacity);
        for(int i = 0; i < heapSize; i++){
            mapAdd(elems[i],i);
            heap.add(elems[i]);
        }
    //heapify: the process of reshaping a binary tree into a heap
        //heapifying process O(n)
        for(int i = (this.heapSize/2)-1; i >= 0;i--){
            sink(i);
        }

    }
    //priority queue construction O (nlog(n))
    public PQueue(Collection<T> elems){
        this(elems.size());
        for (T elem : elems ) add(elem);
    }
    public boolean isEmpty(){
        return this.heapSize == 0;
    }
    public void Clear(){
        for (int i = 0; i < this.heapSize; i++){
            this.heap.set(i,null);
        }
        this.heapSize = 0;
        this.map.clear();
    }
    public int size(){
        return this.heapSize;
    }
    public T peek(){
        if(this.isEmpty()) return null;
        return heap.get(0);
    }
    //poll O (log(n))
    public T poll(){
        return this.heap.remove(0);
    }
    public boolean contains(T elem){
        if (elem == null ) return false;
        // map lookup O(1)
        return map.containsKey(elem);

        //Linear search would have been O(n)
    }
    // O(log(n))
    public void add(T elem){
        if (elem == null) throw new IllegalArgumentException();
        if(this.heapSize < this.heapCapacity){
            heap.set(heapSize, elem);
        }else{
            this.heap.add(elem);
            this.heapCapacity++;
        }
        mapAdd(elem, heapSize);
        swim(heapSize);
        heapSize++;
    }
    // tests if the value of node i <= node j
    // this method assumes i & j are valid indices, O(1)
    private boolean less(int i, int j){
        T node1 = heap.get(i);
        T node2 = heap.get(j);
        return node1.compareTo(node2) <= 0;
    }
//    O ( log (n))
    public void swim(int k){
        // grab the index of the parent node.
        int parent = ( k - 1 )/2;
        // keep swimming while we aren't at root and aren't less than parent
        while ( k > 0 && less(k,parent)){
            swap(parent, k);
            k = parent;
            parent = ( k - 1 )/2;
        }
    }
    public void sink(int k){
        while (true){
            int left = 2 * k + 1; //left node
            int right = 2 * k + 2;//right
            // assume that the left is the smallest of the two children
            int smallest = left;
            //find if right is smaller than left

            if(right < heapSize && less(right , left)){
                smallest = right;
            }
            //stop if we are out of bounds or can't sink anymore
            if(left >= heapSize || less(k, smallest)) break;
            swap(smallest, k);
            k = smallest;
        }
    }
//    swap two nodes O(1)
    private void swap(int i, int j){
        T i_Data = heap.get(i);
        T j_Data = heap.get(j);

        this.heap.set(i,j_Data);
        this.heap.set(j,j_Data);
        this.mapSwap(i_Data,j_Data, i,j);
    }
    // remove a particular element from the heap O(log(n))
    public boolean remove(T element){
        if(element == null) return false;
        //linear remove O(n)
//        for(int i = 0; i<heapSize; i++){
//            if(element.equals(heap.get(i))){
//                removeAt(i);
//                return true
//            }
//        }
        Integer index = mapGet(element);
        if(index == null)  removeAt(index);
        return index != null;
    }
    //O(log(N))
    private T removeAt(int i){
        if(this.isEmpty()) return null;
        heapSize--;
        T removedData = this.heap.get(i);
        swap(i,heapSize);
        heap.set(heapSize,null);
        mapRemove(removedData,heapSize);
        //if we removed last elem
        if(i == this.heapSize) return removedData;
        //get the swapped node
        T elem = heap.get(i);
        //try sinking
        this.sink(i);

        //If sinking did nth, try swimming
        if (heap.get(i).equals(elem)) swim(i);

        return removedData;
    }
    // Recursively checks if this heap is a min heap
    // This method is just for testing purposes to make
    // sure the heap invariant is still being maintained
    // Called this method with k=0 to start at the root
    public boolean isMinHeap(int k) {
        // If we are outside the bounds of the heap return true
        if (k >= heapSize) return true;

        int left = 2 * k + 1;
        int right = 2 * k + 2;

        // Make sure that the current node k is less than
        // both of its children left, and right if they exist
        // return false otherwise to indicate an invalid heap
        if (left < heapSize && !less(k, left)) return false;
        if (right < heapSize && !less(k, right)) return false;

        // Recurse on both children to make sure they're also valid heaps
        return isMinHeap(left) && isMinHeap(right);
    }
    @Override
    public String toString() {
        return heap.toString();
    }
    private void mapAdd(T elem, int ind){
        // tree set is a balanced binary search tree in java
        TreeSet<Integer> set = map.get(elem);
        //new value being inserted
        if( set == null){
            set = new TreeSet<>();
            set.add(ind);
            map.put(elem,set);
            //value already exists
        }else set.add(ind);
    }
    private void mapRemove(T value, int index){
        TreeSet<Integer> set = map.get(value);
        set.remove(index); //Takes O(log(n));
        if(set.size()==0) map.remove((value));
    }
    private Integer mapGet(T value){
        TreeSet<Integer> set = map.get(value);
        //return the highest index value
        if(set != null) return set.last();
        return null;
    }
    private void mapSwap(T val1, T val2, int val1Index, int val2Index){
        Set <Integer> set1 = map.get(val1);
        Set <Integer> set2 = map.get(val2);
        set1.remove(val1Index);
        set1.add(val2Index);
        set2.remove(val2Index);
        set2.add(val1Index);

    }
}

