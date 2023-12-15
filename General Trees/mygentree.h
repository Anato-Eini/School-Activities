#include <stdexcept>
#include <iostream>
#include <cstdlib>
#include "gentree.h"
using namespace std;

class MyGenTree : public GenTree {
    node* root;
    int size;

public:
    node* addRoot(int e) {
        // TODO implementation [+10 pts]
        return NULL;
    }

    node* addChild(node* p, int e) {
        // TODO implementation [+30 pts]
        return NULL;
    }


    void remove(node* n) {
        // TODO implementation [+35 pts]

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