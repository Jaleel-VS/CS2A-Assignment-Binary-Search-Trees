/**
 * Class that simulates a basic Binary Tree data structure and its operations.
 *
 * @param <DataType> the type parameter
 */
public class BinaryTree<DataType> {

    /**
     * The root node
     */
    BinaryTreeNode<DataType> root;
    /**
     * A variable that counts the number of operations per query.
     */
    private int operationCounter = 0;


    /**
     * Instantiates a new Binary tree.
     */
    public BinaryTree ()
    {
        root = null;
    }


    /**
     * Increment operation counter.
     */
    public void incrementOperationCounter() {
        this.operationCounter++;
    }

    /**
     * Store and return the current operationCounter, reset the counter.
     * @return the total number of operations of the current query.
     */
    public int getOperationCounter() {
        int temp_operator_value = this.operationCounter;
        this.operationCounter = 0;
        return temp_operator_value;
    }

  }