#include "mybinarytree.h"

class BST {
    MyBinaryTree* tree = new MyBinaryTree();

public:
    // TODO copy from the previous activity
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

    // TODO implement remove method
    // You can add helper methods like what is done for insert and search

    node* minVal(node* nodePtr, int num){
        node* curr = nodePtr;
        while(curr->left){
            curr = curr->left;
        }
        return curr;
    }

    node* removeHelper(node* nodePtr, int num){
        node* returner;
        if(nodePtr->elem > num){
            nodePtr->left = removeHelper(nodePtr->left, num);
        }else if(nodePtr->elem < num){
            nodePtr->right = removeHelper(nodePtr->right, num);
        }else if(nodePtr->left && nodePtr->right){
            node* temp = minVal(nodePtr->right, num);
            nodePtr->elem = temp->elem;
            nodePtr->right = removeHelper(nodePtr->right, nodePtr->elem);
        }else if(nodePtr->left){
            returner = nodePtr->left;
            tree->remove(nodePtr);
            return returner;
        }else if(nodePtr->right){
            returner = nodePtr->right;
            tree->remove(nodePtr);
            return returner;
        }else{
            tree->remove(nodePtr);
            return nullptr;
        }
        return nodePtr;
    }

    bool remove(int num) {
        if(search(num)){
            removeHelper(tree->getRoot(), num);
            return true;
        }else{
            return false;
        }
    }

    void print() {
        tree->print();
    }
};