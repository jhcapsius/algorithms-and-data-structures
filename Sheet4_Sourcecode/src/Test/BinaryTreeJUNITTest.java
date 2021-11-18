/**
 * Testclass to check the given axioms from the sheet
 */

package src.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import src.binarytree.BinaryTree;

public class BinaryTreeJUNITTest {
    BinaryTree<Integer> binaryTree = new BinaryTree<>();

    /**
     * is_empty(empty) = true
     */
    @Test
    public void isEmpty(){
        assertTrue(binaryTree.is_empty());
    }

    /**
     * is_empty(bin(x, b, y)) = false
     */
    @Test
    public void isNotEmpty(){
        binaryTree.add(0);
        assertFalse(binaryTree.is_empty());
    } 


     /**
     * value(bin(x, b, y)) = b
     */
    @Test
    public void getRoot(){
        binaryTree.add(0);
        assertEquals(0, binaryTree.getRoot());
    }

    /**
     * (∀x, y : T ree; ∀b : T) left(bin(x, b, y)) = x
     */
    @Test
    public void leftSubtree(){
        binaryTree = binaryTree.testBinaryTree();
        binaryTree.printLeftSubtree();
    }

    /**
     * (∀x, y : T ree; ∀b : T) right(bin(x, b, y)) = y
     */
    @Test
    public void rightSubtree(){
        binaryTree = binaryTree.testBinaryTree();
        binaryTree.printRightSubtree();
    }

    
}
