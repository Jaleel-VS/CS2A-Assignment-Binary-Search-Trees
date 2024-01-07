/**
 * Class that simulates a basic Binary Search Tree data structure and its operations, that inherits
 * from the Binary Tree class
 *
 * @param <DataType> the type parameter
 */
public class BinarySearchTree<DataType extends Comparable<? super DataType>> extends BinaryTree<DataType> {

    /**
     * Insert node at the root of the Binary Search Tree
     *
     * @param nodeValue the node value
     */
    void insertNode(DataType nodeValue) {
        incrementOperationCounter();
        if (root == null) {
            root = new BinaryTreeNode<>(nodeValue);
        }
        else {
            insertNode(root, nodeValue);
        }
    }

    /**
     * Insertion method that compares the node value of the current node to the node
     * value of the new node
     *
     * @param parentNode  the parent node
     * @param nodeValue the node value of the node to be inserted
     */
    public void insertNode(BinaryTreeNode<DataType> parentNode, DataType nodeValue) {
        if (nodeValue.compareTo(parentNode.nodeValue) <= 0) {
            incrementOperationCounter();
            if (parentNode.leftNode == null) {
                parentNode.leftNode = new BinaryTreeNode<>(nodeValue);
            }

            else {
                insertNode(parentNode.leftNode, nodeValue);
            }
        }
        else {
            incrementOperationCounter();
            if (parentNode.rightNode == null) {

                parentNode.rightNode = new BinaryTreeNode<>(nodeValue);
            }

            else {
                insertNode(parentNode.rightNode, nodeValue);
            }
        }
    }

    /**
     * Find the given object in the binary search tree and
     * return the binary tree it's located at
     *
     * @param data the object that's being queried
     * @return the binary tree node
     */
    public BinaryTreeNode<DataType> find ( DataType data )
    {
        incrementOperationCounter();
        if (root == null) {
            return null;
        }
        else
            return find (data, root);
    }

    /**
     * Find the given object in the binary search tree and
     * return the binary tree it's located at
     *
     * @param data    the object that's being queried
     * @param node the node
     * @return the binary tree node
     */
    public BinaryTreeNode<DataType> find ( DataType data, BinaryTreeNode<DataType> node )
    {
        if (data.compareTo(node.nodeValue) == 0) {
            incrementOperationCounter();
            return node;
        }
        else if (data.compareTo(node.nodeValue) < 0){
            incrementOperationCounter();
            return (node.leftNode == null) ? null : find(data, node.leftNode);
        } else {
            incrementOperationCounter();
            return (node.rightNode == null) ? null : find(data, node.rightNode);
        }
    }






}