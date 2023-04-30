import edu.princeton.cs.algs4.In;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class Node{
        Item item;
        Node next;
        Node prev;
    }
    private int size;
     private Node first;
     private Node last;

    // construct an empty deque
    public Deque(){
        this.size = 0;
        this.first = null;
        this.last = null;
    }
    // is the deque empty?
    public boolean isEmpty() {
        return this.size == 0;
    }
    // return the number of items on the deque
    public int size() {
        return this.size;
    }
    // add the item to the front
    public void addFirst(Item item){
        //Check if item is null
        if (item == null) {throw new IllegalArgumentException();}

        //Make the new node and assign the item
        Node newFirst = new Node();
        newFirst.item = item;

        //If queue is empty then the new node won't point to a next or a prev node
        if (this.isEmpty()) {
            this.first = newFirst;
            this.last = newFirst;
        } else {
            //The current first will become the prev of the new first
            newFirst.prev = first;
            //The current first will have the new first as it's next
            first.next = newFirst;

            this.first = newFirst;
        }
        //Increase the size of the que
        this.size ++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {throw new IllegalArgumentException();}
        //Make a new node and assign the item
        Node newLast = new Node();
        newLast.item = item;

        //If the que is empty the new node will become the first and last element
        if (this.isEmpty()) {
            this.first = newLast;
            this.last = newLast;
        } else {
            //If the que is not empty the new current last will point to the new last as prev
            this.last.prev = newLast;
            //The new last wont have any prev node to point
//            newLast.prev = null;
            //The new last next will be the old last
            newLast.next = this.last;
            //The current last will become the new last
            this.last = newLast;
        }
        //Increase the size of the que
        this.size ++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (this.isEmpty()) {throw new NoSuchElementException();}
        this.size--;
        Node firstOne = first;
        if (this.size() >= 1) {
            this.first = first.prev;
        } else {
            this.last = null;
            this.first = null;
        }
        return firstOne.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (this.isEmpty()) {throw new NoSuchElementException();}
        if (this.size() > 1) {
            Node oldLast = this.last;
            this.last = last.next;
            this.last.prev = null;
            this.size--;
            return oldLast.item;
        }
        Item onlyNodeItem = this.last.item;
        this.first = null;
        this.last = null;
        this.size--;
        return onlyNodeItem;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() {
            return current != null;
        }
        public void remove() {throw new UnsupportedOperationException();}

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.prev;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args){
//        Deque<Integer> test = new Deque<Integer>();
        Deque<String> test = new Deque<String>();
        test.addFirst("I'm the first first one in");
        test.addFirst("I'm the first first one in 2");
        test.addFirst("I'm the first first one in 3");
        test.addLast("I'm the first to the to the end");
        test.addLast("I'm the first to the to the end 2");
        test.addLast("I'm the first to the to the end 3");
        test.addLast("I'm the last last one in");
//        test.removeFirst();
//        test.removeLast();
//        test.addLast(1);
//        test.addFirst(2);
//        test.removeLast();
        test.removeFirst();
//        test.addLast(5);
//        test.addFirst(6);
//        test.removeFirst();
//        test.addFirst(8);
        System.out.println(test.size());
        System.out.println(test.iterator());

//        System.out.println(test.size() + " Elements");
        System.out.println(test.isEmpty());

//        test.removeFirst();

//        test.removeLast();
        test.removeLast();
//        test.removeLast();

/*        System.out.println(test.size() + ": size");
        System.out.println(test.size());

        System.out.println(test.removeFirst() + ": removing");
        System.out.println(test.removeLast() + ": removing") ;*/
        for (Object s: test) { System.out.println(s);
        }
    }
}