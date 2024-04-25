import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class DAGSortTest {


  /**
   * Test for an empty graph.
   *
   * @throws InvalidNodeException
   * @throws CycleDetectedException
   */
  @Test
  public void testEmpty() {
    int[][] edges = new int[][]{};
    int[] expected = new int[]{};
    try {
      assertArrayEquals(expected, DAGSort.sortDAG(edges), "Empty graph not sorted correctly");
    } catch (CycleDetectedException e) {
      e.printStackTrace();
    } catch (InvalidNodeException e) {
      e.printStackTrace();
    }
  }

  /**
   * Test for a simple cycle.
   */
  @Test
  public void testSimpleCycle() {
    int[][] edges = {{1}, {0}}; // Simple cycle between 0 -> 1 -> 0
    assertThrows(CycleDetectedException.class, () -> DAGSort.sortDAG(edges),
        "Failed to detect simple cycle");
  }

  /**
   * Test for a complex cycle.
   */
  @Test
  public void testComplexCycle() {
    int[][] edges = {
        {1},    // Node 0 -> Node 1
        {2},    // Node 1 -> Node 2
        {3},    // Node 2 -> Node 3
        {0}     // Node 3 -> Node 0, completing the cycle
    };
    assertThrows(CycleDetectedException.class, () -> DAGSort.sortDAG(edges),
        "Failed to detect complex cycle involving multiple nodes");
  }

  /**
   * Tests for null input.
   */
  @Test
  public void testForNull() {
    assertThrows(NullPointerException.class, () -> DAGSort.sortDAG(null),
        "Algorithm does not handle null input correctly"); //Tests whether the exception is thrown
  }

  /**
   * Tests a single node graph.
   *
   * @throws InvalidNodeException
   * @throws CycleDetectedException
   */
  @Test
  public void testSingleNodeGraph() {
    int[][] edges = new int[1][]; //Single node graph
    edges[0] = new int[]{0};
    assertThrows(CycleDetectedException.class, () -> DAGSort.sortDAG(edges),
        "Single node graph not sorted correctly"); //Tests whether the exception is thrown
  }

  /**
   * Tests a linear graph(all nodes point to the next node, in a single line thus linear).
   *
   * @throws InvalidNodeException
   * @throws CycleDetectedException
   */
  @Test
  public void testLinearGraph() {
    int[][] edges = {{1}, {2}, {3}, {4}, {5},
        {}}; //Last pair of empty {} is for the last node, which does not point to any other node
    int[] expected = {0, 1, 2, 3, 4, 5};
    try {
      assertArrayEquals(expected, DAGSort.sortDAG(edges), "Linear graph not sorted correctly");
    } catch (CycleDetectedException e) {
      e.printStackTrace();
    } catch (InvalidNodeException e) {
      e.printStackTrace();
    }
  }

  /**
   * Tests for invalid node. Node with index 5 cannot point to a node with index 6, as 6 doesn't
   * exist.
   *
   * @throws CycleDetectedException
   */
  @Test
  public void testForInvalidNode() {
    int[][] edges = {{1}, {2}, {3}, {4}, {5}, {6}}; //edge 5 -> 6, 6 is an invalid node
    assertThrows(InvalidNodeException.class, () -> DAGSort.sortDAG(edges),
        "Invalid node not detected"); //Tests whether the exception is thrown
  }

  /**
   * Tests for a simple DAG.
   */
  @Test
  public void tesValidSimpleDAG() {
    int[][] edges = new int[][]{{1}, {2}, {3}, {}};
    assertDoesNotThrow(() -> {
      int[] result = DAGSort.sortDAG(edges);
      assertArrayEquals(new int[]{0, 1, 2, 3}, result);
    });
  }

  /**
   * Tests for a complex DAG. A complex DAG is a graph where each node can have multiple outgoing
   * edges and incoming edges.
   */
  @Test
  public void testValidComplexDAG() {
    int[][] edges = new int[][]{{1, 2}, {3}, {3}, {}};
    assertDoesNotThrow(() -> {
      int[] result = DAGSort.sortDAG(edges);
      assertTrue(isTopologicalOrder(result, edges));
    });
  }

  /**
   * Helper method to check if the given order is a valid topological order.
   */
  private boolean isTopologicalOrder(int[] order, int[][] edges) {
    /*position will map each node to its index in the order array,
     the index of each node in the order array indicated when the node appears in the topological sort*/
    int[] position = new int[order.length];
    for (int i = 0; i < order.length; i++) {
      position[order[i]] = i;
    }
    for (int i = 0; i < edges.length; i++) {
      for (int edge : edges[i]) {
        if (position[i] > position[edge]) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Tests for a graph with a negative node.
   */
  @Test
  public void testNegativeNode() {
    int[][] edges = new int[][]{{1, 2}, {2}, {-1}};
    assertThrows(InvalidNodeException.class, () -> DAGSort.sortDAG(edges),
        "Negative node not detected");
  }

  /**
   * Tests for a graph where a node points to a non existent node.
   */
  @Test
  public void testOutOfBoundsNode() {
    int[][] edges = new int[][]{{1, 2}, {2}, {3}};
    assertThrows(InvalidNodeException.class, () -> DAGSort.sortDAG(edges),
        "Out of bounds node not detected");
  }

  /**
   * Tests a graph with several disconnected subgraphs.
   */
  @Test
  public void testDisconnectedSubgraphs() {
    int[][] edges = {
        {1},    // Subgraph 1
        {},     // Subgraph 1
        {3},    // Subgraph 2
        {},     // Subgraph 2
        {5, 6}, // Subgraph 3
        {6},    // Subgraph 3
        {}      // Subgraph 3
    };
    assertDoesNotThrow(() -> {
      int[] result = DAGSort.sortDAG(edges);
      // Check if the result is a valid topological sorting
      assertTrue(isTopologicalOrder(result, edges),
          "Output is not a valid topological sort of the disconnected subgraphs");
    });
  }

  /**
   * Tests a graph where all nodes are disconnected (no edges).
   */
  @Test
  public void testAllDisconnectedNodes() {
    int[][] edges = new int[][]{{}, {}, {}, {}};
    assertDoesNotThrow(() -> {
      int[] result = DAGSort.sortDAG(edges);
      // Any permutation of 0, 1, 2, 3 is valid since there are no dependencies
      int[] expectedAnyOrder = {0, 1, 2, 3};
      assertTrue(isPermutationOf(result, expectedAnyOrder),
          "Output is not a valid permutation of all nodes");
    });
  }

  /**
   * Helper method to check if 'result' is a permutation of 'expected'.
   */
  private boolean isPermutationOf(int[] result, int[] expected) {
    if (result.length != expected.length) return false;
    int[] resCopy = Arrays.copyOf(result, result.length);
    int[] expCopy = Arrays.copyOf(expected, expected.length);
    Arrays.sort(resCopy);
    Arrays.sort(expCopy);
    return Arrays.equals(resCopy, expCopy);
  }

}
