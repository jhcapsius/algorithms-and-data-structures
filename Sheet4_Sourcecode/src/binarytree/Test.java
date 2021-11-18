package src.binarytree;

public class Test {
    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.add(4);
        binaryTree.add(2);
        binaryTree.add(1);
        binaryTree.add(3);
        binaryTree.add(6);
        binaryTree.add(7);
        binaryTree.add(5);
        binaryTree.traversePreOrder();
        binaryTree.traversePreOrderDetails();
        if (binaryTree.containsNode(2)){
            binaryTree.deleteNode(2);
        }else{
            System.out.println("Eintrag nicht enthalten");
        }

        binaryTree.traversePreOrder();
        binaryTree.traversePreOrderDetails();
        
    }
    
}
