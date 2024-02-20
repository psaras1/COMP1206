import uk.ac.soton.ecs.comp1206.labtestlibrary.interfaces.threading.UnitCounter;

public class Counter implements UnitCounter {
  private  int count;
  public void addOne(){
    this.count++;
  }
  public int getCounter(){
    return this.count;
  }


}
