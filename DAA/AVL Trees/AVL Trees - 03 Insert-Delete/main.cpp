// DO NOT modify this file.
// Copy and paste your zigleft, zigright, restructure, height.
// Improve to bst's insert and remove methods.

#include "bst.h"
#include <iostream>
using namespace std;
int main() {
    BST* tree = new BST();
    node* nodes[100];
    char op;
    int num;
    do {
        cout << "Op: ";
        cin >> op;
        node* n;
        switch (op) {
            case 'i':
                cin >> num;
                nodes[num] = tree->insert(num);
                break;
            case 's':
                cin >> num;
                tree->search(num);
                break;
            case 'r':
                cin >> num;
                tree->remove(num);
                break;
            case 'p':
                tree->print();
                break;
            case 'x':
                cout << "Exiting";
                break;
            default:
                cout << "Invalid operation";
        }
    } while (op != 'x');
};