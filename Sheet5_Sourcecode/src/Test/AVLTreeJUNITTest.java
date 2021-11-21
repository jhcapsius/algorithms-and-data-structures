/**
 * Testclass to check the given axioms from the sheet
 */

package src.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.Test;
import src.AVLTree.AVLTree;

public class AVLTreeJUNITTest {
    AVLTree<Integer> avlTree = new AVLTree<>();
    
    /**
     * is_empty(empty) = true
     */
    @Test
    public void isEmpty(){
        assertTrue(avlTree.isEmpty());
    }

     /**
     * is_empty(bin(x, b, y)) = false
     */
    @Test
    public void isNotEmpty(){
        avlTree.add(0);
        assertFalse(avlTree.isEmpty());
    } 

    /**
    * value(bin(x, b, y)) = b
    */
    @Test
    public void getRoot(){
        avlTree.add(0);
        assertEquals(0, avlTree.getRoot().getData());
    }

    /**
     * (∀x, y : T ree; ∀b : T) left(bin(x, b, y)) = x
     */
    @Test
    public void leftSubtree(){
        avlTree = avlTree.testTreeInteger();
        avlTree.printLeftSubtree();
    }

    /**
     * (∀x, y : T ree; ∀b : T) right(bin(x, b, y)) = y
     */
    @Test
    public void rightSubtree(){
        avlTree = avlTree.testTreeInteger();
        avlTree.printRightSubtree();
    }
}
