// DO NOT modify this file.
// Go to mybinarytree's zigleft and zigright methods.

#include "mybinarytree.h"

class BST {
    BinaryTree* tree = new MyBinaryTree();

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
            // proceed to right
            return search_node(n->right, num);
        } else {
            return search_node(n->left, num);
        }
    }

    node* insert(int num) {
        node* n = tree->getRoot();
        if (n == NULL) {
            return tree->addRoot(num);
        }
        return insert_node(n, num);
    }

    node* insert_node(node* n, int num) {
        if (n == NULL) {
            return NULL;
        }
        if (n->elem == num) {
            return NULL;
        }
        if (num > n->elem) {
            if (!n->right) {
                return tree->addRight(n, num);
            } else {
                return insert_node(n->right, num);
            }
        } else {
            if (!n->left) {
                return tree->addLeft(n, num);
            } else {
                return insert_node(n->left, num);
            }
        }
    }

    bool remove(int num) {
        return remove_node(tree->getRoot(), num);
    }

    bool remove_node(node* n, int num) {
        if (n == NULL) {
            return false;
        }
        if (n->elem == num) {
            if (n->left && n->right) {
                node* r = n->right;
                while (r->left) {
                    r = r->left;
                }
                int rem = tree->remove(r);
                n->elem = rem;
            } else {
                tree->remove(n);
            }
            return true;
        }
        if (num > n->elem) {
            return remove_node(n->right, num);
        } else {
            return remove_node(n->left, num);
        }
    }

    void zigleft(node* curr) {
        tree->zigleft(curr);
    }

    void zigright(node* curr) {
        tree->zigright(curr);
    }

    void print() {
        tree->print();
    }
};