#ifndef RED_BLACK_REDBLACK_H
#define RED_BLACK_REDBLACK_H

#include <iostream>
#include "Node.h"

using namespace std;
class RedBlack {
    /// null pointer will improve deleteNode algorithm significantly
    Node* root, *null;
    int size;
    Node* createNode(int num, Node* parent);
    void insertHelper(int num, Node* node);
    void rotateLeft(Node* node);
    void rotateRight(Node* node);
    void insertFix(Node* node);
    void nodeTransplant(Node* v, Node* u);
    Node* findNodeHelper(Node* node, int num);
    Node* findNode(int num);
public:
    RedBlack();
    void insert(int num);
    void deleteNode(int num);
    bool search(int num);
    void print();
    void print(const std::string& pre, char loc, Node* node);
};


#endif //RED_BLACK_REDBLACK_H
