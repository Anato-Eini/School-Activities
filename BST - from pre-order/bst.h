#include "mybinarytree.h"

class BST {
    MyBinaryTree* tree = new MyBinaryTree();

public:
    // TODO copy and paste from previous activity
    bool search(int num) {
        return false;
    }

    void from_preorder(int* array, int size) {
        tree->size = size;
        tree->root = convert_preorder(array, 0, size-1, NULL);
    }

    // TODO convert_preorder recursively
    node* convert_preorder(int* array, int start, int end, node* parent) {
        if(start > end) return nullptr;
        node* n = new node;
        n->elem = array[start];
        n->parent = parent;
        int i = start + 1;
        while(i <= end && array[start] > array[i])
            i++;
        n->left = convert_preorder(array, start + 1, i - 1, n);
        n->right = convert_preorder(array, i , end, n);
        return n;
    }

    void print() {
        tree->print();
    }
};