import uk.ac.soton.ecs.comp1206.labtestlibrary.interfaces.threading.NumberQueue;

public class CyclicQueue implements NumberQueue {

  private int capacity;
  private int[] arr;
  private int head;
  private int tail;

  public CyclicQueue(int capacity) {
    this.capacity = capacity;
    this.arr = new int[capacity];
    this.tail = -1;
    this.head = -1;
  }

  public boolean isEmpty() {
    return this.head ==-1;
  }

  public void enqueue(int x){
    if ((this.tail+1)%arr.length == this.head) { //if by enqueueing we reach the front again,full
      throw new IndexOutOfBoundsException("Queue full");
    } else if (isEmpty()) {
      this.head++;
    }
    this.tail = (this.tail + 1) % this.arr.length;
    this.arr[this.tail] = x;
  }

  public int dequeue() {
    if (isEmpty()) {
      throw new IndexOutOfBoundsException("Queue empty");
    }
    int toDequeue = arr[head];
    if (this.head == this.tail) { //means that we only have 1 element in the queue
      this.head = this.tail = -1;
    }
    else{
      this.head = (this.head + 1) % this.arr.length;
    }

    return toDequeue;
  }
  public void printQueue(){
    for (int i : this.arr){
      System.out.print(i+",");
    }
    System.out.println();
  }


}
