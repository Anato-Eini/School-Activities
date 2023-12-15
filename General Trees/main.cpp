#include <iostream>
#include "mygentree.h"
using namespace std;

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char** argv) {
    GenTree* tree = new MyGenTree();
    node* nodes[100];
    char op;
    int par, chi;
    do {
        try {
            cout << "Op: ";
            cin >> op;
            switch (op) {
                case 'q':
                    cin >> par;
                    nodes[par] = tree->addRoot(par);
                    break;
                case 'i':
                    cin >> par >> chi;
                    nodes[chi] = tree->addChild(nodes[par], chi);
                    break;
                case 'p':
                    tree->print();
                    break;
                case 'd':
                    cin >> par;
                    cout << "Depth of " << par << ": " << nodes[par]->depth() << endl;
                    break;
                case 'h':
                    cin >> par;
                    cout << "Height of " << par << ": " << nodes[par]->height() << endl;
                    break;
                case 'r':
                    cin >> par;
                    tree->remove(nodes[par]);
                    break;
                case 'x':
                    cout << "Exiting...";
                    break;
            }
        } catch (exception& e) {
            cout << e.what() << endl;
        }
    } while (op != 'x');
}