#include <iostream>
#include "Tree24.h"

using namespace std;
int main() {
    Tree24 *tree = new Tree24();
    tree->insert(13);
    tree->insert(21);
    tree->insert(2);
    tree->insert(100);
    tree->insert(10);
    tree->insert(30);
    tree->insert(9);
    tree->insert(1);
    tree->print();
    return 0;
}
