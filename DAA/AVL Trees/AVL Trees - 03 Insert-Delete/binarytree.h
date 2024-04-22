// DO NOT modify this file.
// Copy and paste your zigleft, zigright, restructure, height.
// Improve to bst's insert and remove methods.

#include "node.h"

class BinaryTree {
public:
    virtual node* left(node*) = 0;
    virtual node* right(node*) = 0;
    virtual void zigleft(node*) = 0;
    virtual void zigright(node*) = 0;
    virtual node* addRoot(int e) = 0;
    virtual node* addLeft(node* p, int e) = 0;
    virtual node* addRight(node* p, int e) = 0;
    virtual int remove(node*) = 0;
    virtual node* getRoot() = 0;
    virtual void print() = 0;
};