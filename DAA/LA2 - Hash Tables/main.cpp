#include <iostream>
#include "hashtable.h"
using namespace std;

// WARNING: Do not alter this main function.
// Doing so will nullify your grade for this activity.
int main(void) {
    cout << "Enter capacity of hash table: ";
    int N;
    cin >> N;

    HashTable *table = new HashTable(N);

    char op;
    char key;
    do {
        cout << "Operation: ";
        cin >> op;

        switch (op) {
            case 'i': // Insert
                cin >> key;
                cout << table->insert(key) << endl;
                break;

            case 's': // Search
                cin >> key;
                cout << table->search(key) << endl;
                break;

            case 'd': // Delete
                cin >> key;
                cout << table->remove(key) << endl;
                break;

            case 'p':
                table->print();
                break;

            case 'x':
                cout << "Exiting";
                break;
        }
    } while (op != 'x');

    return 0;
}