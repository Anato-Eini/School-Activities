// DO NOT modify this file.
// Go to bst's predecessor & successor method
#include <iostream>
#include "bst.h"
using namespace std;

int main(void) {
    BST* bst = new BST();
    char op;
    int input, ind, res;
    do {
        cout << "Op: ";
        cin >> op;
        switch (op) {
            case 's':
                cin >> input;
                cout << "Is there " << input << "? " << bst->search(input) << endl;
                break;
            case 'i':
                cin >> input;
                bst->insert(input);
                break;
                // case 'r':
                // 	cin >> input;
                // 	bst->remove(input);
                // 	break;
            case '<':
                cin >> input;
                res = bst->predecessor(input);
                if (res == -1) {
                    cout << "No predecessor" << endl;
                } else {
                    cout << "Predecessor: " << res << endl;
                }
                break;
            case '>':
                cin >> input;
                res = bst->successor(input);
                if (res == -1) {
                    cout << "No successor" << endl;
                } else {
                    cout << "Successor: " << res << endl;
                }
                break;
            case 'p':
                bst->print();
                break;
            case 'x':
                cout << "Exiting" << endl;
                break;
            default:
                cout << "Invalid operation" << endl;
        }
    } while (op != 'x');

    return 0;
}