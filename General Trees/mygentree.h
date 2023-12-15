#include <stdexcept>
#include <iostream>
#include <cstdlib>
#include "gentree.h"
using namespace std;

class MyGenTree : public GenTree {
    node* root;
    int size;
    node* create_node(node* p, int e){
        size++;
        return new node{new node*[10], 0, p, e};
    }
public:
    node* addRoot(int e) {
        if(root) throw logic_error("Already has root");
        root = create_node(nullptr, e);
        return root;
    }

    node* addChild(node* p, int e) {
        p->children[p->num_child] = create_node(p, e);
        return p->children[p->num_child++];
    }


    void remove(node* n) {
        if(n->num_child != 0) throw logic_error(to_string(n->elem) + " has children");
        if(root == n){
            root = nullptr;
            size = 0;
            delete n;
            return;
        }
        for(int a = 0; a < n->parent->num_child; a++){
            if(n->parent->children[a] == n){
                for(int b = a + 1; b < n->parent->num_child; b++){
                    n->parent->children[b - 1] = n->parent->children[b];
                }
                n->parent->num_child--;
                size--;
                return;
            }
        }
    }

    // DO NOT MODIFY this line onwards.
    void print() {
        cout << "Size: " << size << endl;
        if (!root) {
            cout << "EMPTY" << endl;
            return;
        }
        node* curr = root;
        print_node("", root);
        cout << "Status: " << check_parent(root, NULL) << endl;
    }

    void print_node(string prefix, node* n) {
        cout << prefix;
        cout << "+-->: ";
        cout << n->elem << endl;
        for (int i = 0; i < n->num_child; i++) {
            print_node(prefix + "|   ", n->children[i]);
        }
    }

    bool check_parent(node* curr, node* par) {
        if (!curr) {
            return true;
        }
        if (curr->parent != par) {
            return false;
        }
        bool res = true;
        for (int i = 0; i < curr->num_child; i++) {
            res &= check_parent(curr->children[i], curr);
        }
        return res;
    }
};