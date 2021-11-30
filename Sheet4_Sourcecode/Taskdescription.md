# Sheet 4

Work with JUnit and JavaDoc from now on.


## Task 1 Implementation Binarytree

Implement generically in Java the elementary operations on binary trees mentioned in lecture 5.

Operations:<br>
(Tree = Treetyp, T = Nodetype)<br>
bin : Tree × T × Tree → Tree<br>
lef t : Tree → Tree<br>
right : Tree → Tree<br>
value : Tree → T<br>
is_empty : Tree → Boolean<br>

axioms<br>
(∀x, y : Tree; ∀b : T) left(bin(x, b, y)) = x<br>
right(bin(x, b, y)) = y<br>
value(bin(x, b, y)) = b<br>
is_empty(empty) = true<br>
is_empty(bin(x, b, y)) = false<br>

_________________________________________________________________________________________________________________________
  
  
## Task 2 Implementation Binarytree

Implement a search tree with integer values and one with string values in Java using the operations implemented above by using the generic definition.

Provide the user with the operations insert, delete, modif y and tree output for integer numbers. Modify here means to change the value of the key.

The tree output is to be realised by the processing component.

For the output of the tree, go through the tree in preorder order and enter the following for each node a its height h, its balance b and its direct sons c and d with height and balance. For example, if node a has sons c and d, output the following line:

a(ha, ba) : c(hc, bc), d(hd, bd)

Additionally implement an inorder traversal as described in the lecture. Replace the processing component again with an expression of the node content.

This gives you a good opportunity to test your tree structure. Give your programme a suitable interface with which to trigger the operations and traversal.