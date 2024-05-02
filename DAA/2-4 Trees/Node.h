#ifndef INC_2_4_TREES_NODE_H
#define INC_2_4_TREES_NODE_H

#include <iostream>

using namespace std;

class Node {
    Node* parent;
    int* data, size;
    Node** children;
public:
    Node(int num, Node* parent);
    int indexOf(Node* child);
    void print(string prefix, Node* node);
    void setChildren(Node *wp, Node* w2p, int pos);
    void print();
    bool addKey(int num);

    friend class Tree24;
};

#endif //INC_2_4_TREES_NODE_H
