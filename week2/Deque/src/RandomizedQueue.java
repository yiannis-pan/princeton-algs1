import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int capacity;
//    private int generateRandomIterator(int length) {return StdRandom.uniformInt(0, length );}
    private void showStats() {
        System.out.println("Stats");
        for (int i =0; i < this.items.length; i++) {
            System.out.println("At index: " + i + ", Item: " + this.items[i]);
        }
    }
//    TODO: Better randomizer
    private int findProperIndex() {
        int i;
        for ( i = 0; i < this.items.length; i++){
            if (this.items[i] != null) {
                return i;
            }
        }
        throw new Error("Capacity:  " + capacity + ", length: " + this.items.length  );
    }
    private void resize(int newCapacity) {
        Item[] copy = (Item[]) new Object[newCapacity];
        int j = 0;
        for (int i =0; i < this.items.length  ; i++ ) {
            if (this.items[i] != null) {
            copy[j] = items[i];
            j++;
            }
        }
        this.items = copy;
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.capacity = 0;
        this.items = (Item[]) new Object[capacity];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return this.capacity == 0;
    }

    // return the number of items on the randomized queue
    public int size() {return this.capacity;}

    // add the item
    public void enqueue(Item item) {
        //Check if array is at max length
        if (item == null) {throw new IllegalArgumentException();}
        if (capacity == 0) resize(1); else if (items.length == this.capacity) resize(2 * items.length);
        for (int i = 0; i<this.items.length; i++){
        if (this.items[i] == null) {
            this.items[i] = item;
            capacity++;
            StdRandom.shuffle(this.items);
            break;
            }
        }
    }

    // remove and return a random item
    public Item dequeue() {
        //First check if is empty
        if (this.isEmpty()) {throw new NoSuchElementException();}
        if (this.capacity > 0 && this.capacity == this.items.length / 4) resize(this.items.length/2);
        int indexOfItemToDequeue = this.findProperIndex();
        //Grab random index
        //Get the item of said index
        Item itemToReturn = this.items[indexOfItemToDequeue];
        this.items[indexOfItemToDequeue] = null;

        this.capacity --;
        return itemToReturn;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (this.isEmpty()) {throw  new NoSuchElementException();}
        StdRandom.shuffle(this.items);
        int possibleIndex = findProperIndex();
        return items[possibleIndex];
    }


    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {return new ReverseArrayIterator();}

    private class ReverseArrayIterator implements  Iterator<Item> {
        RandomizedQueue<Item> copy;
        public ReverseArrayIterator(){
            this.copy = new RandomizedQueue<Item>();
            copy.items = (Item[]) new Object[items.length];
            for (int i = 0; i<items.length; i++){
                copy.items[i] = items[i];
            }
            this.copy.capacity = capacity;
            StdRandom.shuffle(copy.items);
        }
        public boolean hasNext() {
            return !copy.isEmpty();
        }
        public void remove() {throw new UnsupportedOperationException();}
        public Item next() {
          if (!hasNext()) {
              throw  new NoSuchElementException();
          }
          return copy.dequeue();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> test = new RandomizedQueue<>();
        test.enqueue(20);
//        test.showStats();
        test.enqueue(44);
//        test.showStats();
//        test.dequeue();
//        test.showStats();
        test.enqueue(22);
        test.enqueue(24);

//        test.showStats();
        for (int i: test
             ) {
            System.out.println(i);
        }

//        test.showStats();
//        test.showStats();
//        test.showStats();
        System.out.println(test.sample());
        System.out.println(test.size());
//        test.showStats();
        test.dequeue();
//        test.showStats();
//        test.dequeue();
//        test.showStats();
//        test.dequeue();
//        test.showStats();
//        test.dequeue();



//        test.showStats();
//        test.showStats();
//        test.dequeue();
//        test.showStats();
//        test.dequeue();



//        test.dequeue();
//        test.showStats();
//        System.out.println(test.sample());
//        System.out.println(test.dequeue());
//        for (int i: test
//        ) {
//            System.out.println(i);
//        }
//        System.out.println("Adding");
//        test.enqueue("One");
//        System.out.println("Finished Adding");
//        test.printStats();
//        test.enqueue("Two");
//        test.printStats();
//        test.enqueue("Three");
//        test.printStats();
//        test.enqueue("Four");
//        System.out.println(test.size() + ": Size");
//
//        System.out.println(test.sample() + ": Sample");
//        test.dequeue();
//        test.printStats();
//        System.out.println(test.sample());
//        System.out.println(test.size());
//        test.enqueue("Five");
//        test.printStats();
//        test.enqueue("Six");

//        System.out.println(test.dequeue());
//        test.printStats();
//        System.out.println(test.items.length);
//        System.out.println("About to dequeue");
//        test.dequeue();
//        test.printStats();
//        System.out.println("About to dequeue");
//        test.dequeue();
//        test.printStats();
//        System.out.println("About to dequeue");
//        test.dequeue();
//        test.printStats();
//        System.out.println("About to dequeue");
//        test.dequeue();
//        test.printStats();
//        System.out.println("About to dequeue");
//        test.dequeue();
//        test.printStats();
//        System.out.println("About to dequeue");
//        test.dequeue();
//        test.printStats();
//        test.sample();
//        test.isEmpty();
//        test.size();
////        test.dequeue();
//        for (String s: test
//             ) {
//            System.out.println("item: " + s);
//        }
    }
}