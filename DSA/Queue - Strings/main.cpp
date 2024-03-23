#include <iostream>
#include "singlyqueue.h"
using namespace std;

int main() {
    Queue* queue = new SinglyQueue();
    string input;
    char op;
    do {
        cout << "Enter op: ";
        cin >> op;
        switch (op) {
            case 'a' :
                cin >> input;
                queue->enqueue(input);
                break;
            case 'r':
                cout << "Removed " << queue->dequeue() << endl;
                break;
            case 'f':
                cout << "Last element: " << queue->first() << endl;
                break;
            case 's':
                cout << "Size: " << queue->size() << endl;
                break;
            case '?':
                cout << "Empty? " << queue->isEmpty() << endl;
                break;
            case 'p':
                queue->print();
                break;
            case 'x':
                cout << "Exiting";
                break;
        }
    } while (op != 'x');
    return 0;
}