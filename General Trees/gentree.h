#include "node.h"

class GenTree {
public:
    virtual node* addRoot(int) = 0;
    virtual node* addChild(node*, int) = 0;
    virtual void remove(node*) = 0;
    virtual void print() = 0;
};