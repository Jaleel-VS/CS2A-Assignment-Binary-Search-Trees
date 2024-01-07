/**
 * The type Binary tree node.
 *
 * @param <DataType> the type parameter
 */
public class BinaryTreeNode<DataType> {
    /**
     * The Node value.
     */
    final DataType nodeValue;
    /**
     * The Left child node.
     */
    BinaryTreeNode<DataType> leftNode, /**
     * The Right child node.
     */
    rightNode;

    /**
     * Instantiates a new Binary tree node.
     *
     * @param nodeValue the node value
     */
    public BinaryTreeNode(DataType nodeValue) {
        this.nodeValue = nodeValue;
        this.leftNode = null;
        this.rightNode = null;
    }

}
