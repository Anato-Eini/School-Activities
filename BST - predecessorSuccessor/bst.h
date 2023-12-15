#include <vector>
#include "mybinarytree.h"

class BST {
    BinaryTree* tree = new MyBinaryTree();
    vector<int> array;
public:
    bool search(int num) {
        return search_node(tree->getRoot(), num);
    }

    node* search_node(node* n, int num) {
        if (n == nullptr) {
            return nullptr;
        }
        if (n->elem == num) {
            return n;
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
    void fillVector(node* n){
        if(n){
            fillVector(n->left);
            array.push_back(n->elem);
            fillVector(n->right);
        }
    }
    // TODO predecessor
    int predecessor(int num) {
        if(array.empty()){
            fillVector(tree->getRoot());
        }
        int pred = -1;
        for(int i: array){
            if(i == num) break;
            pred = i;
        }
        return pred;
    }

    // TODO successor
    int successor(int num) {
        if(array.empty()){
            fillVector(tree->getRoot());
        }
        for(int a = 0; a < array.size(); a++){
            if(array[a] == num && a + 1 < array.size()) return array[a + 1];
            else if(array[a] == num) return - 1;
        }
    }

    void print() {
        tree->print();
    }
};