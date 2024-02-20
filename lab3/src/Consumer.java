import uk.ac.soton.ecs.comp1206.labtestlibrary.interfaces.threading.FactoryWorker;
import uk.ac.soton.ecs.comp1206.labtestlibrary.interfaces.threading.NumberQueue;

public class Consumer extends FactoryWorker {

  public Consumer (int id, NumberQueue belt){
    super("consumer",id,belt);
  }
  public void message (int message){
    System.out.println(this.jobType+" "+this.id+" picked "+ message);
  }
  public int action(){
    return belt.dequeue();
  }
  public void run(){
    while(!Thread.currentThread().isInterrupted()){
      try {
        int number = action();
      }
      catch (IndexOutOfBoundsException e){
        messageError();
      }
    }
  }
}
