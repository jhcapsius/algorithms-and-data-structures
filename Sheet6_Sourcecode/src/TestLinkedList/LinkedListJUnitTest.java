/**
 * JUnit test-class for the link list operations
 * 
 * @author Jan-Henrik Capsius
 * @version 1.0
 */

package src.TestLinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import src.LinkedList.LinkedList;

public class LinkedListJUnitTest {
    LinkedList<String> linkedList = new LinkedList<>();

    @AfterAll
    public void initNewinkedlist(){
        this.linkedList = new LinkedList<>();
    }

    @Test
    public void addNextElemWithoutIndex(){
        //add an element to an empty list
        linkedList.print();
        assertTrue(linkedList.addNextElem("data"));
        assertTrue(linkedList.getElem(0).equals("data"));
        linkedList.print();


        //add an element to a list, that already have elemtens
        linkedList.print();
        assertTrue(linkedList.addNextElem("data2"));
        assertEquals(this.linkedList.getElem(1), "data2");
        linkedList.print();
    }

    @Test
    public void addNextElemWithIndex(){
        //list is empty
        linkedList.print();
        assertTrue(this.linkedList.addNextElem(0, "data"));
        linkedList.print();

        //add after last index
        assertTrue(this.linkedList.addNextElem(0, "data3"));
        linkedList.print();

        //add somewhere in the list
        assertTrue(this.linkedList.addNextElem(0, "data2"));
        this.linkedList.print();

        //index out of bounce
        assertFalse(this.linkedList.addNextElem(3000, "data"));
        assertFalse(this.linkedList.addNextElem(-1, "data"));
        this.linkedList.print();
    }

    @Test
    public void getNextElem(){
        //list is empty
        assertTrue(null == (this.linkedList.getNextElem(0)));

        this.linkedList.addNextElem("data1");
        //less than 2 entrys
        assertTrue(null == (this.linkedList.getNextElem(0)));

        this.linkedList.addNextElem("data2");
        this.linkedList.addNextElem("data3");
        this.linkedList.addNextElem("data4");

        //out of bounce
        assertTrue(null == this.linkedList.getNextElem(-1));
        assertTrue(null == this.linkedList.getNextElem(300));

        //get next item from the list
        assertTrue("data2".equals(this.linkedList.getNextElem(0)));
        assertTrue("data3".equals(this.linkedList.getNextElem(1)));
        assertTrue("data4".equals(this.linkedList.getNextElem(2)));
    }

    @Test
    public void removeNextElemWithoutIndex(){
        //list is empty
        assertFalse(this.linkedList.removeNextElem());

        //remove head
        this.linkedList.addNextElem("data1");
        assertTrue(this.linkedList.removeNextElem());
        this.linkedList.print();

        //remove last entry
        this.linkedList.addNextElem("data1");
        this.linkedList.addNextElem("data2");
        this.linkedList.addNextElem("data3");
        this.linkedList.addNextElem("data4");
        this.linkedList.print();
        assertTrue(this.linkedList.removeNextElem());
        this.linkedList.print();
    }

    @Test
    public void removeNextElemWithIndex(){
        //list is empty
        assertFalse(this.linkedList.removeNextElem(0));

        this.linkedList.addNextElem("data1");
        this.linkedList.addNextElem("data2");
        this.linkedList.addNextElem("data3");
        this.linkedList.addNextElem("data4");

        //out of bounce
        assertFalse(this.linkedList.removeNextElem(-1));
        assertFalse(this.linkedList.removeNextElem(300));

        //remove
        this.linkedList.print();
        assertTrue(this.linkedList.removeNextElem(0));
        this.linkedList.print();
        assertTrue(this.linkedList.removeNextElem(0));
        this.linkedList.print();
        assertTrue(this.linkedList.removeNextElem(0));
        this.linkedList.print();
    }

    @Test
    public void addPrevElemWithoutIndex(){
        //list is empty
        this.linkedList.print();
        assertFalse(this.linkedList.addPrevElem("data"));

        this.linkedList.addNextElem("data1");
    
        //add prev elem
        this.linkedList.print();
        assertTrue(this.linkedList.addPrevElem("data2"));
        this.linkedList.print();
        assertTrue(this.linkedList.addPrevElem("data3"));
        this.linkedList.print();
    }
    
    @Test
    public void addPrevElemWithIndex(){
        //list is empty
        this.linkedList.print();
        assertFalse(this.linkedList.addPrevElem(0,"data"));

        
        this.linkedList.addNextElem("data2");
        this.linkedList.addNextElem("data4");
        this.linkedList.addNextElem("data5");

        //add prev head
        this.linkedList.print();
        assertTrue(this.linkedList.addPrevElem(0, "data1"));
        this.linkedList.print();

        //add prev somewhere in the list
        this.linkedList.print();
        assertTrue(this.linkedList.addPrevElem(2, "data3"));
        this.linkedList.print();

        //out of bounce
        assertFalse(this.linkedList.addPrevElem(-1, "data"));
        assertFalse(this.linkedList.addPrevElem(300, "data"));

    }

    @Test
    public void getPrevElem(){
        //list is empty
        assertTrue(null == this.linkedList.getPrevElem(0));
        

        this.linkedList.addNextElem("data1");
        //less than 2 entrys
        assertTrue(null == (this.linkedList.getPrevElem(0)));
        
        this.linkedList.addNextElem("data2");
        this.linkedList.addNextElem("data3");
        this.linkedList.addNextElem("data4");
 
        //out of bounce
        assertTrue(null == this.linkedList.getPrevElem(-1));
        assertTrue(null == this.linkedList.getPrevElem(300));
        
        //get prev item from the list
        assertTrue("data1".equals(this.linkedList.getPrevElem(1)));
        assertTrue("data2".equals(this.linkedList.getPrevElem(2)));
        assertTrue("data3".equals(this.linkedList.getPrevElem(3)));
    }

    @Test
    public void removePrevElemWithoutIndex(){
        //list is empty
        assertFalse(this.linkedList.removePrevElem());

        this.linkedList.addNextElem("data1");
        
        //less than 2 entrys
        assertFalse(this.linkedList.removePrevElem());

        this.linkedList.addNextElem("data2");
        this.linkedList.addNextElem("data3");
        this.linkedList.addNextElem("data4");

        //remove prev items
        this.linkedList.print();
        assertTrue(this.linkedList.removePrevElem());
        this.linkedList.print();
        assertTrue(this.linkedList.removePrevElem());
        this.linkedList.print();
        assertTrue(this.linkedList.removePrevElem());
        this.linkedList.print();
    }

    @Test
    public void removePrevElemWithIndex(){
        //list is empty
        assertFalse(this.linkedList.removePrevElem(1));

        this.linkedList.addNextElem("data1");
        
        //less than 2 entrys
        assertFalse(this.linkedList.removePrevElem(1));

        this.linkedList.addNextElem("data2");
        this.linkedList.addNextElem("data3");
        this.linkedList.addNextElem("data4");

        //out of bounce
        assertFalse(this.linkedList.removePrevElem(0));
        assertFalse(this.linkedList.removePrevElem(300));

        //remove prev item somewhere in the list
        this.linkedList.print();
        assertTrue(this.linkedList.removePrevElem(2));
        this.linkedList.print();


    }
}
