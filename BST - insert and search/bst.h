#include "mybinarytree.h"

class BST {
    BinaryTree* tree = new MyBinaryTree();

public:
    bool search(int num) {
        return searchHelper(tree->getRoot(), num);
    }

    node* searchHelper(node* nodePtr, int num){
        if(!nodePtr || nodePtr->elem == num){
            return nodePtr;
        }else if(nodePtr->elem > num){
            return searchHelper(nodePtr->left, num);
        }else return searchHelper(nodePtr->right, num);
    }

    bool insert(int num) {
        if(!tree->getRoot()){
            tree->addRoot(num);
            return true;
        } else return insertHelper(tree->getRoot(), num);
    }

    bool insertHelper(node* nodePtr, int num){
        if(nodePtr->elem == num){
            return false;
        }else if(nodePtr->elem < num){
            if(nodePtr->right){
                return insertHelper(nodePtr->right, num);
            }else{
                nodePtr->right = tree->addRight(nodePtr, num);
                return true;
            }
        }else
        if(nodePtr->left){
            return insertHelper(nodePtr->left, num);
        }else{
            nodePtr->left = tree->addLeft(nodePtr, num);
            return true;
        }
    }

    void print() {
        tree->print();
    }
};