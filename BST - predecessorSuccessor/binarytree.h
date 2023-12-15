// DO NOT modify this file.
// Go to bst's predecessor & successor method
#include "node.h"

class BinaryTree {
public:
    virtual node* left(node*) = 0;
    virtual node* right(node*) = 0;
    virtual node* addRoot(int e) = 0;
    virtual node* addLeft(node* p, int e) = 0;
    virtual node* addRight(node* p, int e) = 0;
    virtual int remove(node* n) = 0;
    virtual node* getRoot() = 0;
    virtual void print() = 0;

    virtual node* sibling(node* n) = 0;
};