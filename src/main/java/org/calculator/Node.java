package org.calculator;

public interface Node {

    /**
     * Создание левого потомка у данного узла дерева.
     *
     * @param node узел, который необходимо сделать левым потомком
     */
    public void createLeftChild(TreeNode node);

    /**
     * Создание правого потомка у данного узла дерева.
     *
     * @param node узел, который необходимо сделать правого потомком
     */
    public void createRightChild(TreeNode node);

    /**
     * Получение данных, хранящихся в данном узле дерева
     *
     * @return данные, хранящиеся в данном узле дерева
     */
    public String getData();

    /**
     * Получение левого потомка данного узла
     * @return левый потомок данного узла
     */
    public TreeNode getLeftChild();

    /**
     * Получение правого потомка данного узла
     * @return правый потомок данного узла
     */
    public TreeNode getRightChild();
}
