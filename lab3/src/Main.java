public class Main   {

  public static void main(String[] args) {
    CyclicQueue cq = new CyclicQueue(5);
    cq.enqueue(5);
    cq.enqueue(5);
    cq.enqueue(5);
    cq.enqueue(5);
    cq.enqueue(5);
    cq.dequeue();
    cq.enqueue(10);
    cq.dequeue();
    cq.dequeue();
    cq.dequeue();

    cq.printQueue();

    Producer producer = new Producer(5,cq);
    Consumer consumer = new Consumer(13,cq);
    producer.run();
    cq.printQueue();


  }
}