import uk.ac.soton.ecs.comp1206.labtestlibrary.datastructure.Tree;
import uk.ac.soton.ecs.comp1206.labtestlibrary.interfaces.recursion.MinimumInTree;

public class MinTree implements MinimumInTree {

  public static void main(String[] args) {
    Tree tree = new Tree(24,
        new Tree(45, null,
            new Tree(6, null, null))
        , new Tree(17,
        new Tree(74, null, null), null));
    MinTree minTree = new MinTree();
    System.out.println("Minimum is: " + minTree.findMin(tree));
  }

  public int findMin(Tree tree) {
    int min = tree.getVal();
    int leftMin =0 ;
    int rightMin = 0;
    if (tree.left() != null) {
      leftMin = findMin(tree.left());
      min = Math.min(leftMin, min);
    }

    if (tree.right() != null) {
      rightMin = findMin(tree.right());
      min = Math.min(rightMin, min);
    }
    return min;
  }


}
