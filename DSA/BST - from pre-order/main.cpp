// DO NOT modify this file.
// Go to bst's convert_preorder method
#include <iostream>
#include <cstdlib>
#include "bst.h"
using namespace std;

int main(void) {
    BST* bst = new BST();
    char op;
    int size;
    cout << "Enter size of array: ";
    cin >> size;
    int* array = (int*) calloc (size, sizeof(int) );
    cout << "Enter pre-order: ";
    for (int i = 0; i < size; i++) {
        cin >> array[i];
    }
    bst->from_preorder(array, size);
    bst->print();
    return 0;
}