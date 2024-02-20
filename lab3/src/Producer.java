import java.util.Random;
import uk.ac.soton.ecs.comp1206.labtestlibrary.interfaces.threading.FactoryWorker;
import uk.ac.soton.ecs.comp1206.labtestlibrary.interfaces.threading.NumberQueue;

public class Producer extends FactoryWorker {

  public Producer(int id, NumberQueue belt) {
    super("producer", id, belt);
  }

  public void message(int message) {
    System.out.println(this.jobType + " " + this.id + " picked " + message);
  }

  public int action() {
    Random x = new Random();
    int ran = x.nextInt(500);
    belt.enqueue(ran);
    return ran;

  }

  public void run() {
    while (!Thread.currentThread().isInterrupted()) {
      try {
        int number = action();
      } catch (IndexOutOfBoundsException e) {
        messageError();
        break;
      }

    }
  }


}
