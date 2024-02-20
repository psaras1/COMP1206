public class Belt extends CyclicQueue{

  public Belt(int capacity) {
    super(capacity);
  }

  @Override
  public synchronized void enqueue(int x) {
    while(isFull()){
      try{
        wait(); //Puts current thread to 'sleep', holds current status
      }
      catch(InterruptedException e){
        Thread.currentThread().interrupt();
      }
    }
    super.enqueue(x);
    notifyAll(); //Tells current threads that space is available
  }

  @Override
  public synchronized int dequeue() {
    while(isEmpty()){
      try{
        wait(); //Puts current thread to 'sleep', holds current status
      }catch(InterruptedException e){
        Thread.currentThread().interrupt();
      }
    }
    notifyAll(); //Tells waiting threads that an element has been removed
    return super.dequeue();
  }
}
