import uk.ac.soton.ecs.comp1206.labtestlibrary.interfaces.recursion.MinimumInArray;

public class MinInt implements MinimumInArray{
  public static void main(String[]args){
    int[] arr = {24,52,74,9,34,23,64,34};
    MinInt minInt = new MinInt();
    int minimum = minInt.findMin(arr);
    System.out.println("Minimum is: "+minimum);
  }

  public int findMin(int[] array){
    if(array.length==0){
      throw new IllegalArgumentException("Array is empty");
    }
    return findMin(array,0);
  }

  public int findMin(int[]array,int index){ //overloading findMin method
    if(index == array.length-1){ //if we've ran through the entire array
      return array[index];
    }
    int min = findMin(array,index+1);
    if(array[index]<min){
      min = array[index];
    }
    return min;
  }
}


