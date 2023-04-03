package datastructures.binarysearchtree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class BinarySearchTree {

    public Node root;

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        private Node(int value) {
            this.value = value;
        }
    }

    public boolean insert(int value) {
        Node newNode = new Node(value);
        if (root == null) {
            root = newNode;
            return true;
        }
        Node temp = root;
        while (true) {
            if (newNode.value == temp.value) return false;
            if (newNode.value < temp.value) {
                if (temp.left == null) {
                    temp.left = newNode;
                    return true;
                }
                temp = temp.left;
            } else {
                if (temp.right == null) {
                    temp.right = newNode;
                    return true;
                }
                temp = temp.right;
            }
        }
    }

    public boolean contains(int value) {
        if (root == null) return false;
        Node temp = root;
        while (temp != null) {
            if (value < temp.value) {
                temp = temp.left;
            } else if (value > temp.value) {
                temp = temp.right;
            } else {
                return true;
            }
        }
        return false;
    }


    public ArrayList<Integer> DFSInOrder() { // same concepts as pre-order and post-order DFS except now current node
        // value added between looking left and right.
        ArrayList<Integer> results = new ArrayList<>(); //empty ArrayList created to store values of traversed nodes

        class Traverse { //constructor called from 'new' keyword below
            Traverse(Node currentNode) {
                if (currentNode.left != null) { //recursive looking left, adding each node to call stack until leaf
                    new Traverse(currentNode.left);
                }
                results.add(currentNode.value); // leaf value added on first pass. will be preceding node on other passes
                // then pops the last node call off to preceding
                if (currentNode.right != null) { // looking right to get leaf from right side of preceding node, already
                    // having added the leaf's preceding node value, now the leaf value (or whatever node up the tree it is)
                    // and then pop the leaf call of the stack, then pop preceding node off stack and keep going up the tree
                    new Traverse(currentNode.right);
                }
            }
        }

        new Traverse(root);
        return results;
    }

}

