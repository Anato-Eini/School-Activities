#ifndef INC_2_4_TREES_TREE24_H
#define INC_2_4_TREES_TREE24_H

#include "Node.h"

class Tree24 {
    Node* root;
    int size;
public:
    Tree24();
    Node* search(int num);
    Node* insert(int num);
    void print();
};


#endif //INC_2_4_TREES_TREE24_H
