package com.JaafarMusa;


import com.JaafarMusa.DSYT.DoublyLinkedList;
import com.JaafarMusa.DSYT.DynamicArray1;

public class Main {

    public static void main(String[] args) {
	// write your code here
        DynamicArray1<Integer> arr = new DynamicArray1<>(4);
        arr.add(1);
        arr.add(2);
        arr.add(2);
        arr.add(2);
//        System.out.println(arr.removeAt(1));
        System.out.println(arr.toString());
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

    }
}