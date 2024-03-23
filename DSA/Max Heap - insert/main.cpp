// DO NOT MODIFY this code file.
// Go to arrayheap.h
#include <iostream>
#include <string>
#include "arrayheap.h"
using namespace std;

int main() {
	MaxHeap* heap = new ArrayMaxHeap();
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