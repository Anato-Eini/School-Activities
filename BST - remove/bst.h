#include "mybinarytree.h"

class BST {
    BinaryTree* tree = new MyBinaryTree();

public:
    bool search(int num) {
        return search_node(tree->getRoot(), num);
    }

    bool search_node(node* n, int num) {
        if (n == NULL) {
            return false;
        }
        if (n->elem == num) {
            return true;
        }
        if (num > n->elem) {
            return search_node(n->right, num);
        } else {
            return search_node(n->left, num);
        }
    }

    bool insert(int num) {
        node* n = tree->getRoot();
        if (n == NULL) {
            tree->addRoot(num);
        }
        return insert_node(n, num);
    }

    bool insert_node(node* n, int num) {
        if (n == NULL) {
            return false;
        }
        if (n->elem == num) {
            return false;
        }
        if (num > n->elem) {
            if (!n->right) {
                tree->addRight(n, num);
                return true;
            } else {
                return insert_node(n->right, num);
            }
        } else {
            if (!n->left) {
                tree->addLeft(n, num);
                return true;
            } else {
                return insert_node(n->left, num);
            }
        }
    }
    // DO not modify the lines above.
    /* node* min(node* n){//para mo adto sa leftmost node
         while (n->left){
             n = n->left;
         }
         return n;
     } */
    // TODO implement remove method
    // You can add helper methods like what is done for insert and search
    bool remove(int num) {
        node* curr = tree->getRoot();
        if(!tree->getRoot()){
            return false;
        }
        while(curr){
            if(curr->elem > num){
                curr = curr->left;
            } else if(curr->elem < num){
                curr = curr->right;
            }else {
                break;
            }
        }
        if(!curr){
            return false;
        }
        if(curr->left && curr->right){
            node* righty = curr->right;
            while(righty->left){
                righty = righty->left;
            }
            curr->elem = righty->elem;
            curr = NULL;
            tree->remove(righty);
            return true;
        }else{
            tree->remove(curr);
            return true;
        }

    }

    void print() {
        tree->print();
    }
};