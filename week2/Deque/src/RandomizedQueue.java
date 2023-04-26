import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int capacity;

    private int generateRandomIterator() {return StdRandom.uniformInt(1,capacity );}
    private void resize(int newCapacity) {
        Item[] copy = (Item[]) new Object[newCapacity];
        for (int i =0; i < capacity  ; i++ ) {
            copy[i] = items[i];
        }
        this.items = copy;
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.capacity = 1;
        this.items = (Item[]) new Object[capacity];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return items.length == 1;
    }

    // return the number of items on the randomized queue
    public int size() {return this.items.length;}

    // add the item
    public void enqueue(Item item) {
        //Check if array is at max length
        if (item == null) {throw new IllegalArgumentException();}
        if (items.length == this.capacity) resize(2 * items.length);
        items[capacity++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        //First check if is empty
        if (this.isEmpty()) {throw new NoSuchElementException();}

        //Grab random index
        int indexOfItemToDequeue = generateRandomIterator();

        //Get the item of said index
        Item itemToReturn = this.items[indexOfItemToDequeue];
        Item[] newItems = (Item[]) new Object[capacity -1];

        int indexOfNewItems = 0;

        for (int i = 0; i< capacity ; i++) {
            if (items[i] != itemToReturn  ) { newItems[indexOfNewItems++] = this.items[i]; }
        }
        this.capacity --;
        this.items = newItems;
        return itemToReturn;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (this.isEmpty()) {throw  new NoSuchElementException();}
        return items[generateRandomIterator()];
    }
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {return new ReverseArrayIterator();}

    private class ReverseArrayIterator implements  Iterator<Item> {
        RandomizedQueue<Item> copy;
        public ReverseArrayIterator(){
            copy = new RandomizedQueue<Item>();
            copy.items = items;
            copy.capacity = capacity;

        }
        public boolean hasNext() {return  !copy.isEmpty();}
        public void remove() {throw new UnsupportedOperationException();}
        public Item next() {
          if (!hasNext()) {throw  new NoSuchElementException();}
          Item item = copy.dequeue();
          return item;
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> test = new RandomizedQueue<String>();
        test.enqueue("One");
        test.enqueue("Two");
        test.enqueue("Three");
        test.dequeue();
        test.sample();
        test.isEmpty();
        test.size();
//        test.dequeue();
        for (String s: test
             ) {
            System.out.println("item: " + s);
        }

    }

}