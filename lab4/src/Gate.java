public class Gate implements Runnable{
  private Counter counter;
  private int numGuests;
  public Gate(Counter counter, int numGuests){
    this.counter = counter;
    this.numGuests = numGuests;
  }
  public void run(){
    for (int i=0;i<this.numGuests;i++){
      this.counter.addOne();
    }
  }

}
