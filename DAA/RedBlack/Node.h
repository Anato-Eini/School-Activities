
#ifndef RED_BLACK_NODE_H
#define RED_BLACK_NODE_H

struct Node{
    int elem;
    bool isRed;
    Node* parent, *right, *left;
};

#endif //RED_BLACK_NODE_H
