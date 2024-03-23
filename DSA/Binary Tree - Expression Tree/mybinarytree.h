// DO NOT modify this file.

#include <cstdlib>
#include <iostream>
#include <string>
#include "binarytree.h"
using namespace std;

class MyBinaryTree : public BinaryTree {
    int size;

public:
    node* root;
    node* create_node(string e, node* parent) {
        node* n = (node*) calloc( 1, sizeof(node) );
        n->elem = e;
        n->parent = parent;
        return n;
    }

    node* addRoot(string e) {
        if (root) {
            cout << "Root exists" << endl;
            return NULL;
        }
        node* n = create_node(e, NULL);
        root = n;
        size++;
        return n;
    }

    node* left(node* p) {
        return p->left;
    }

    node* right(node* p) {
        return p->right;
    }

    node* addLeft(node* p, string e) {
        if (p->left) {
            cout << "Left of " << p->elem << " exists" << endl;
            return NULL;
        }
        node* n = create_node(e, p);
        p->left = n;
        size++;
        return n;
    }

    node* addRight(node* p, string e) {
        if (p->right) {
            cout << "Right of " << p->elem << " exists" << endl;
            return NULL;
        }
        node* n = create_node(e, p);
        p->right = n;
        size++;
        return n;
    }

    string getRoot() {
        return root->elem;
    }

    int evaluate() {
        return root->evaluate();
    }

    void print() {
        cout << "Size: " << size << endl;
        if (!root) {
            cout << "EMPTY" << endl;
            return;
        }
        node* curr = root;
        print_node("", root, false);
        cout << "Status: " << check_parent(root, NULL) << endl;
    }

    void print_node(string prefix, node* n, bool isLeft) {
        cout << prefix;
        cout << (isLeft ? "+--L: " : "+--R: " );
        cout << n->elem << endl;
        if (n->left) {
            print_node(prefix + "|   ", n->left, true);
        }
        if (n->right) {
            print_node(prefix + "|   ", n->right, false);
        }
    }

    bool check_parent(node* curr, node* par) {
        if (!curr) {
            return true;
        }
        if (curr->parent != par) {
            cout << "Illegal parent of " << curr->elem << ": " << curr->parent << " -- must be " << par << endl;
            return false;
        }
        return check_parent(curr->left, curr) && check_parent(curr->right, curr);
    }
};