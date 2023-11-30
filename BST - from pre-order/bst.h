#include "mybinarytree.h"

class BST {
    MyBinaryTree* tree = new MyBinaryTree();

public:
    bool search(int num) {
        return search_node(tree->getRoot(), num);
    }

    bool search_node(node* n, int num) {
        if (n == NULL) {
            return false;
        }
        if (n->elem == num) {
            return true;
        }
        if (num > n->elem) {
            return search_node(n->right, num);
        } else {
            return search_node(n->left, num);
        }
    }

    void from_preorder(int* array, int size) {
        tree->size = size;
        tree->root = convert_preorder(array, 0, size-1, NULL);
    }

    // TODO convert_preorder recursively
    node* convert_preorder(int* array, int start, int end, node* parent) {
        return convert_preorder(array, 0, size-1, NULL);
    }

    void print() {
        tree->print();
    }
};