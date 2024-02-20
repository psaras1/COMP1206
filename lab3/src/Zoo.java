public class Zoo {
  public static void main(String[]args){
    Counter counter = new Counter();
    int numberOfGates = 10;
    int guestsPerGate =50000;
    int expectedGuests = numberOfGates*guestsPerGate;
    Thread []myThread1 = new Thread[numberOfGates];

    for(int i = 0; i<numberOfGates;i++){
      myThread1[i] = new Thread(new Gate(counter,guestsPerGate));
      myThread1[i].start();
    }
    for(Thread gate: myThread1){
      try {
        gate.join();
      }
      catch (InterruptedException e){
        e.getMessage();
      }
    }

    System.out.println("Expected guests: "+expectedGuests);
    System.out.println("Actual guests: "+counter.getCounter());




  }
}
