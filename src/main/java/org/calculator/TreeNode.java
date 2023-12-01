package org.calculator;

public class TreeNode implements Node {
    private String data;
    private TreeNode leftChild;
    private TreeNode rightChild;

    public TreeNode(String data) {
        this.data = data;
        leftChild = null;
        rightChild = null;
    }

    @Override
    public void createLeftChild(TreeNode node) {
        leftChild = node;
    }

    @Override
    public void createRightChild(TreeNode node) {
        rightChild = node;
    }

    @Override
    public String getData() {
        return data;
    }

    @Override
    public TreeNode getLeftChild() {
        return leftChild;
    }

    @Override
    public TreeNode getRightChild() {
        return rightChild;
    }
}
