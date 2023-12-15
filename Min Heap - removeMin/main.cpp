// DO NOT MODIFY this code file.
// Go to arrayheap.h

#include <iostream>
#include <string>
#include "arrayheap.h"
using namespace std;

int main() {
	MinHeap* heap = new ArrayMinHeap();
    char op;
    int num;
    do {
    	cout << "Op: ";
    	cin >> op;
    	switch (op) {
    		case 'i':
    			cin >> num;
    			heap->insert(num);
    			break;
    		case 'r':
    			cout << "Removed " << heap->removeMin() << endl;
    			break;
    		case 'p':
    			heap->print();
    			break;
    		case 'x':
    			cout << "Exiting" << endl;
    			break;
    		default:
    			cout << "Invalid operation" << endl;
    			break;
		}
	} while (op != 'x');
    return 0;
}