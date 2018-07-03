package week_II.homework_IV;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    @org.junit.jupiter.api.Test
    void contains() {
        BinarySearchTree<Integer> ibst = new BinarySearchTree<>();

        ibst.add(9);
        ibst.add(28);
        ibst.add(14);
        ibst.add(7);
        ibst.add(22);
        ibst.add(11);
        ibst.add(34);
        ibst.add(17);
        ibst.add(52);
        ibst.add(26);
        ibst.add(13);
        ibst.add(40);
        ibst.add(20);
        ibst.add(10);
        ibst.add(5);
        ibst.add(16);
        ibst.add(8);
        ibst.add(4);
        ibst.add(2);
        ibst.add(1);

        assertFalse(ibst.contains(15));
        assertTrue(ibst.contains(7));
    }

    @org.junit.jupiter.api.Test
    void remove() {
        BinarySearchTree<Integer> ibst = new BinarySearchTree<>();

        ibst.add(5);
        ibst.add(16);
        ibst.add(8);
        ibst.add(4);
        ibst.add(2);
        ibst.add(1);

        ibst.remove(16);

        assertTrue(!ibst.contains(16));
    }

    @org.junit.jupiter.api.Test
    void depth() {
        BinarySearchTree<Integer> ibst = new BinarySearchTree<>();

        ibst.add(8);
        ibst.add(6);
        ibst.add(16);
        ibst.add(4);
        ibst.add(10);
        ibst.add(20);
        ibst.add(2);
        ibst.add(9);
        ibst.add(12);

        assertEquals(0, ibst.depth(8));
        assertEquals(2, ibst.depth(20));
    }

    @org.junit.jupiter.api.Test
    void height() {
        BinarySearchTree<Integer> ibst = new BinarySearchTree<>();

        ibst.add(8);
        ibst.add(6);
        ibst.add(16);
        ibst.add(4);
        ibst.add(10);
        ibst.add(20);
        ibst.add(2);
        ibst.add(9);
        ibst.add(12);

        assertEquals(3, ibst.height(8));
        assertEquals(0, ibst.height(20));
    }

    @org.junit.jupiter.api.Test
    void isBalanced() {
        BinarySearchTree<Integer> ibst = new BinarySearchTree<>();

        ibst.add(9);
        ibst.add(28);
        ibst.add(14);
        ibst.add(7);
        ibst.add(22);
        ibst.add(11);
        ibst.add(34);
        ibst.add(17);
        ibst.add(52);
        ibst.add(26);
        ibst.add(13);
        ibst.add(40);
        ibst.add(20);
        ibst.add(10);
        ibst.add(5);
        ibst.add(16);
        ibst.add(8);
        ibst.add(4);
        ibst.add(2);
        ibst.add(1);

        // Collatz conjecture of 9 produces an unbalanced BTS
        assertFalse(ibst.isBalanced());
    }
}