// DO NOT modify this file.

#include "node.h"
#include <string>
using namespace std;

class BinaryTree {
public:
    virtual node* left(node*) = 0;
    virtual node* addRoot(string e) = 0;
    virtual node* addLeft(node* p, string e) = 0;
    virtual node* addRight(node* p, string e) = 0;
    virtual string getRoot() = 0;
    virtual void print() = 0;
};